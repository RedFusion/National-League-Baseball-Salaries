package ru.menkin.servlets;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import ru.menkin.models.Player;
import ru.menkin.store.UserCache;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class UploadServlet extends HttpServlet
{
    private static final int MEMORY_THRESHOLD = 1024 * 1024 * 3;  // 3MB
    private static final int MAX_FILE_SIZE = 1024 * 1024 * 40; // 40MB
    private static final int MAX_REQUEST_SIZE = 1024 * 1024 * 50; // 50MB

    private final UserCache USER_CACHE = UserCache.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        boolean isMultipart = ServletFileUpload.isMultipartContent(req);

        DiskFileItemFactory factory = new DiskFileItemFactory();

        // sets memory threshold - beyond which files are stored in disk
        factory.setSizeThreshold(MEMORY_THRESHOLD);
        // sets temporary location to store files
        factory.setRepository(new File(System.getProperty("java.io.tmpdir")));

        ServletFileUpload upload = new ServletFileUpload(factory);

        // sets maximum size of upload file
        upload.setFileSizeMax(MAX_FILE_SIZE);

        // sets maximum size of request (include file + form data)
        upload.setSizeMax(MAX_REQUEST_SIZE);
        // Configure a repository (to ensure a secure temp location is used)
        ServletContext servletContext = this.getServletConfig().getServletContext();
        File repository = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
        factory.setRepository(repository);

        // Parse the request
        try
        {
            List<FileItem> items = upload.parseRequest(req);
            for (FileItem fileItem : items)
            {
                if (!fileItem.isFormField())
                {
                    InputStream inputStream = fileItem.getInputStream();

                    List<Player> list = convert(inputStream);
                    for (Player player : list)
                    {
                        //add players into cash
                        this.USER_CACHE.add(player);
                        //System.out.println(player.getId() + " " + player.getTeam() + " " + player.getName() +
                        //" " + String.format("%.0f", Double.parseDouble(player.getSalary())) + " " + player.getPosition());
                    }
                    inputStream.close();
                }
            }
        } catch (FileUploadException e)
        {
            e.printStackTrace();
        }
        RequestDispatcher dispatcher = req.getRequestDispatcher("/View.jsp");
        dispatcher.forward(req, resp);
    }

    //convert stream to list
    public static List<Player> convert(InputStream inputStream)
    {
        AtomicInteger ids = new AtomicInteger();
        List<Player> list = new ArrayList<Player>();
        try
        {
            Workbook workbook= new HSSFWorkbook(inputStream);
            //Get the number of sheets in the xlsx file
            int numberOfSheets = workbook.getNumberOfSheets();
            //loop through each of the sheets
            for (int i = 0; i < numberOfSheets; i++)
            {
                //Get the nth sheet from the workbook
                Sheet sheet = workbook.getSheetAt(i);
                //every sheet has rows, iterate over them
                Iterator<Row> rowIterator = sheet.iterator();
                while(rowIterator.hasNext())
                {
                    String team = "";
                    String name = "";
                    String salary = "";
                    String position = "";

                    //Get the row object
                    Row row = rowIterator.next();
                    //Every row has columns, get the column iterator and iterate over them
                    Iterator<Cell> cellIterator = row.cellIterator();
                    int count = 1;
                    while(cellIterator.hasNext())
                    {
                        //Get the Cell object
                        Cell cell = cellIterator.next();
                        if (cell.getCellType() == Cell.CELL_TYPE_STRING)
                        {
                            switch (count)
                            {
                                case 1:
                                {
                                    team = cell.getStringCellValue();
                                    count++;
                                    break;
                                }
                                 case 2:
                                {
                                    name = cell.getStringCellValue();
                                    count++;
                                    break;
                                }
                                 case 4:
                                {
                                    position = cell.getStringCellValue();
                                    count++;
                                    break;
                                }
                            }
                        }
                        if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC)
                        {
                            salary = String.valueOf(cell.getNumericCellValue());
                            count++;
                        }
                    }
                    if(!team.equals("") && !name.equals("") && !salary.equals("") && !position.equals(""))
                    {
                        Player player = new Player(ids.incrementAndGet(), team, name, salary, position);
                        list.add(player);
                    }
                }
            }
        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return list;
    }
}

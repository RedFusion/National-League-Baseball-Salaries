package ru.menkin.servlets;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import ru.menkin.models.Player;
import ru.menkin.store.UserCache;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class UploadServlet extends HttpServlet {
    private static final int MEMORY_THRESHOLD = 1024 * 1024 * 3;  // 3MB
    private static final int MAX_FILE_SIZE = 1024 * 1024 * 40; // 40MB
    private static final int MAX_REQUEST_SIZE = 1024 * 1024 * 50; // 50MB

    private static final int COUNT_CELLS_IN_ROW = 4;
    private static final int NUMBER_OF_NUMERIC_CELL = 3;

    private final UserCache USER_CACHE = UserCache.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        boolean isMultipart = ServletFileUpload.isMultipartContent(req);
        if (isMultipart) {
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
            try {
                List<FileItem> items = upload.parseRequest(req);
                for (FileItem fileItem : items) {
                    if (!fileItem.isFormField()) {
                        InputStream inputStream = fileItem.getInputStream();

                        List<Player> list = convert(inputStream);
                        for (Player player : list) {
                            //add players into cash
                            this.USER_CACHE.add(player);
                        }
                        inputStream.close();
                    }
                }
            }
            catch (FileUploadException e) {
                e.printStackTrace();
            }
            resp.sendRedirect(String.format("%s%s", req.getContextPath(), "/view"));
        }
    }

    /**
     *
     * @param inputStream - stream read from uploadfile
     * @return converted list from inputStream
     */

    public static List<Player> convert(InputStream inputStream) {
        int index = 1;
        List<Player> list = new ArrayList<Player>();
        try {
            Workbook workbook = new HSSFWorkbook(inputStream);
            //Get the number of sheets in the xls file
            int numberOfSheets = workbook.getNumberOfSheets();
            //loop through each of the sheets
            for (int i = 0; i < numberOfSheets; i++) {
                //Get the nth sheet from the workbook
                Sheet sheet = workbook.getSheetAt(i);
                //every sheet has rows, iterate over them
                for (Row row : sheet) {
                    //Get the row object
                    //Every row has columns, get the column iterator and iterate over them
                    if (isValidRow(row)) {
                        Iterator<Cell> cells = row.cellIterator();
                        Player player =
                                new Player(index++, cells.next().getStringCellValue(),
                                        cells.next().getStringCellValue(),
                                        String.valueOf(cells.next().getNumericCellValue()),
                                        cells.next().getStringCellValue());
                        list.add(player);
                    }
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     *check row on validity
     * @param row
     * @return validity of row
     */

    public static boolean isValidRow(Row row) {
        int count = 0;
        boolean flag = true;
        Iterator<Cell> cells = row.cellIterator();
        while (cells.hasNext()) {
            Cell cell = cells.next();
            if(cell.getCellType() == Cell.CELL_TYPE_NUMERIC){
                count++;
            }
            if(cell.getCellType() == Cell.CELL_TYPE_STRING){
                count++;
                if(count == NUMBER_OF_NUMERIC_CELL){
                    flag = false;
                    break;
                }
            }
        }
        if (count != COUNT_CELLS_IN_ROW) {
            flag = false;
        }
        return flag;
    }
}
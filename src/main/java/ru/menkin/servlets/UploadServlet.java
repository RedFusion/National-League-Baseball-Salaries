package ru.menkin.servlets;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import ru.menkin.models.Player;
import ru.menkin.store.UserCache;
import ru.menkin.utils.ReadXls;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Class upload file *.xls with baseball players table
 * @author Menkin
 */
public class UploadServlet extends HttpServlet {
    private static final int MEMORY_THRESHOLD = 1024 * 1024 * 3;  // 3MB
    private static final int MAX_FILE_SIZE = 1024 * 1024 * 40; // 40MB
    private static final int MAX_REQUEST_SIZE = 1024 * 1024 * 50; // 50MB

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
                        //instantiation ReadXls
                        ReadXls readXls = new ReadXls(inputStream);
                        List<Player> list = readXls.convert();
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
}
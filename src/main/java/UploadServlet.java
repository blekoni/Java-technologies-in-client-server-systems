import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import java.io.*;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@WebServlet("/UploadServlet")
public class UploadServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final int LINE_LENGTH = 80;
    private static final String path = "c:/Windows/temp";

    public UploadServlet() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (ServletFileUpload.isMultipartContent(request)) {
            try {
                List<FileItem> multipart = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
                for (FileItem item : multipart) {
                    if (!item.isFormField()) {
                        File directory = new File(path);
                        if (!directory.exists()) {
                            directory.mkdir();
                        }

                        try (FileWriter writer = new FileWriter(path + File.separator + "result.txt", false);
                             BufferedReader reader = new BufferedReader(new InputStreamReader(item.getInputStream()));) {
                            String start = "   ";
                            boolean firstLine = true;
                            int position = 0;
                            while (reader.ready()) {
                                String line = reader.readLine();

                                if (firstLine) {
                                    firstLine = false;
                                    writer.write(start);
                                    position = writeString(line.trim(), writer, 3);
                                } else {
                                    // новий абзац
                                    if (line.matches("^\\s\\s(.)*")) {
                                        writer.append('\n');
                                        writer.write(start);
                                        position = writeString(line.trim(), writer, 3);
                                    } else {
                                        position = writeString(line.trim(), writer, position);
                                    }
                                }
                            }
                            writer.flush();
                        } catch (IOException ex) {
                            System.out.println(ex.getMessage());
                        }
                    }
                }
                //File uploaded successfully
                request.setAttribute("message", "File Uploaded Successfully");
            } catch (Exception ex) {
                request.setAttribute("message", "File Upload Failed due to " + ex);
            }
        } else {

            request.setAttribute("message", "No File found");
        }
        request.getRequestDispatcher("/result.jsp").forward(request, response);

    }

    private int writeString(String text, FileWriter writer, int position) throws IOException {
        if (position != 0) {
            if (text.length() < LINE_LENGTH - position) {
                writer.write(text);
                return text.length() + 3;
            }
            if (text.length() == LINE_LENGTH - position) {
                writer.write(text);
                writer.append('\n');
                return 0;
            }
        }

        while (text.length() > LINE_LENGTH - position ) {
            String temp = text.substring(0, LINE_LENGTH - position).replaceAll("\\s+$", "");
            text = text.substring(LINE_LENGTH - position);
            position = 0;
            writer.write(temp);
            writer.append('\n');
        }
        if (text.length() == LINE_LENGTH) {
            writer.write(text.trim());
            writer.append('\n');
            return 0;
        }
        writer.write(text);
        return text.length();
    }
}

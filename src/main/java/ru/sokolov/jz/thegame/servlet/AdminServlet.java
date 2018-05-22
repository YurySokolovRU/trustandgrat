package ru.sokolov.jz.thegame.servlet;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.util.IOUtils;
import ru.sokolov.jz.thegame.utils.ExcelUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by sokolov
 * Created on 26.04.2018.
 */
@WebServlet(name = "AdminServlet", urlPatterns = "/admin")
public class AdminServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("xls") != null) {
            response.setContentType("application/x-download");
            response.setHeader("Content-Disposition", "attachment; filename=result.xls");
            try (OutputStream out = response.getOutputStream()) {
                writeIntoExcel(out);
            }
        } else if (request.getParameter("doc") != null) {
            response.setContentType("application/x-download");
            response.setHeader("Content-Disposition", "attachment; filename=description.docx");
            try (OutputStream out = response.getOutputStream()) {
                try (InputStream is = AdminServlet.class.getClassLoader().getResourceAsStream("description.docx")) {
                    IOUtils.copy(is, out);
                }
            }
        } else if (request.getParameter("player_id") != null) {
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("views/include/admin/player_rating.jsp");
            requestDispatcher.forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if ("jz".equals(request.getParameter("pass"))) {
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("views/admin.jsp");
            requestDispatcher.forward(request, response);
        } else {
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("login");
            requestDispatcher.forward(request, response);
        }
    }

    private void writeIntoExcel(OutputStream os) throws IOException {
        Workbook book = ExcelUtils.createResultBook();
        book.write(os);
        os.flush();
        book.close();
    }
}
package ru.sokolov.jz.thegame.servlet;

import org.apache.poi.ss.usermodel.Workbook;
import ru.sokolov.jz.thegame.utils.ExcelUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

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
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println("<img src='images/work_for_it.jpg'>");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("page") != null) {
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("views/include/admin/" + request.getParameter("page") + ".jsp");
            requestDispatcher.forward(request, response);
        } else if ("jz".equals(request.getParameter("pass"))) {
            String admin = request.getParameter("admin");
            if ("true".equals(admin)) {
                request.getSession().setAttribute("admin", admin);
            } else {
                request.getSession().setAttribute("admin", null);
            }
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
package ru.sokolov.jz.thegame.servlet;

import ru.sokolov.jz.thegame.entities.Player;
import ru.sokolov.jz.thegame.entities.User;
import ru.sokolov.jz.thegame.model.RozenbergScaleTest;
import ru.sokolov.jz.thegame.model.TheGameModel;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by sokolov
 * Created on 23.04.2018.
 */
@WebServlet(name = "LoginServlet", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page;
        HttpSession session = request.getSession();
        String userLogin = (String) session.getAttribute("user_login");
        Player player = (Player) session.getAttribute("player");
        if (userLogin == null || userLogin.trim().length() == 0 || player == null) {
            String login = request.getParameter("login");
            String password = request.getParameter("password");
            TheGameModel model = TheGameModel.getInstance();
            User user = model.getUser(login);
            if (user == null) {
                user = new User(login);
                user.setPassword(password);
            }
            if (user.getPassword().equals(password)) {
                player = new Player(user);
                player.setTimestamp(String.valueOf(System.currentTimeMillis()));
                player.setRozenbergNumber(RozenbergScaleTest.calcRozenbergVariantForNewPlayer());
            }
        }
        if (player == null) {
            request.setAttribute("error", "");
            request.setAttribute("login", request.getParameter("login") != null ? request.getParameter("login") : "");
            page = "views/login.jsp";
        } else {
            String contact = request.getParameter("contact");
            if (contact != null && contact.trim().length() > 0) {
                player.getUser().setContact(contact);
            }
            session.setAttribute("user_login", player.getUser().getLogin());
            session.setAttribute("player", player);
            session.setAttribute("step", PlayServlet.STAGE1_INSTRUCTION);
            page = "/play";
        }
        if (player != null && player.getUser() != null) {
            TheGameModel.getInstance().storeUser(player.getUser());
        }
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(page);
        requestDispatcher.forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().removeAttribute("user_login");
        request.getSession().removeAttribute("player");
        request.getSession().removeAttribute("step");
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("views/login.jsp");
        requestDispatcher.forward(request, response);
    }
}

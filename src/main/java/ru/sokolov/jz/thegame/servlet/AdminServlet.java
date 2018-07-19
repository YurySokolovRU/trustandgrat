package ru.sokolov.jz.thegame.servlet;

import com.google.gson.Gson;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.util.IOUtils;
import ru.sokolov.jz.thegame.entities.Player;
import ru.sokolov.jz.thegame.entities.User;
import ru.sokolov.jz.thegame.model.RozenbergScaleTest;
import ru.sokolov.jz.thegame.model.Stage3Model;
import ru.sokolov.jz.thegame.model.TheGameModel;
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
import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by sokolov
 * Created on 15.06.2018.
 */
@WebServlet(name = "AdminServlet", urlPatterns = "/admin")
public class AdminServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if ("jz".equals(request.getParameter("pass"))) {
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("views/admin.jsp");
            requestDispatcher.forward(request, response);
        } else {
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("login");
            requestDispatcher.forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        switch (action) {
            case "xls":
                response.setContentType("application/x-download");
                response.setHeader("Content-Disposition", "attachment; filename=result.xls");
                try (OutputStream out = response.getOutputStream()) {
                    writeIntoExcel(out);
                }
                break;
            case "doc":
                response.setContentType("application/x-download");
                response.setHeader("Content-Disposition", "attachment; filename=description.docx");
                try (OutputStream out = response.getOutputStream()) {
                    try (InputStream is = OldAdminServlet.class.getClassLoader().getResourceAsStream("description.docx")) {
                        IOUtils.copy(is, out);
                    }
                }
                break;
            case "init":
                List<Player> allPlayers = TheGameModel.getInstance().getCompletedPlayers();
                int gamesCount = allPlayers.size();
                int[] rozenbergVariants = new int[4];
                int[] stage3Variants = new int[3];

                for (Player player : allPlayers) {
                    rozenbergVariants[player.getRozenbergNumber().getVariantNumber() - 1]++;
                    stage3Variants[player.getStage3variant().getNumber() - 1]++;
                }

                List<User> users = new ArrayList<>();
                Map<User, List<Player>> uPlayers =  TheGameModel.getInstance().getUsersPlayers();
                for (User user : uPlayers.keySet()) {
                    if (uPlayers.get(user).size() == 0) {
                        continue;
                    }
                    if (!users.contains(user)) {
                        users.add(user);
                    }
                }

                Map<String, String> options = new LinkedHashMap<>();
                options.put("gamesCount", String.valueOf(gamesCount));
                options.put("usersCount", String.valueOf(users.size()));
                for (RozenbergScaleTest.VARIANT variant : RozenbergScaleTest.VARIANT.values()) {
                    options.put(variant.name(), String.valueOf(rozenbergVariants[variant.getVariantNumber() - 1]));
                }
                for (Stage3Model.VARIANT variant : Stage3Model.VARIANT.values()) {
                    options.put(variant.name(), String.valueOf(stage3Variants[variant.getNumber() - 1]));
                }

                String json = new Gson().toJson(options);
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write(json);
                break;
            case "getUsers":
                getUsers(response);
                break;
            case "getUPlayers":
                getUPlayers(response, request);
                break;
            case "deleteUser":
                String userLogin = request.getParameter("login");
                TheGameModel.getInstance().deleteUser(userLogin);
                getUsers(response);
                break;
            case "deleteUPlayer":
                String timestamp = request.getParameter("timestamp");
                TheGameModel.getInstance().deletePlayer(timestamp);
                getUPlayers(response, request);
                break;
            case "getUserInfo":
                userLogin = request.getParameter("login");
                User user = TheGameModel.getInstance().getUser(userLogin);
                options = new LinkedHashMap<>();
                options.put("Login", user.getLogin());
                options.put("Age", String.valueOf(user.getAge()));
                options.put("Seх", user.getSex() == 1 ? "M" : "Ж");
                options.put("Income", String.valueOf(user.getIncome()));
                options.put("Contact", user.getContact());
                options.put("count", String.valueOf(user.getCount()));
                json = new Gson().toJson(options);
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write(json);
                break;
            case "getPlayerInfo":
                userLogin = request.getParameter("login");
                timestamp = request.getParameter("timestamp");
                Player player = TheGameModel.getInstance().getUserPlayer(userLogin, timestamp);
                options = new LinkedHashMap<>();
                options.put("Login", userLogin);
                options.put("getTimestamp", player.getTimestamp() + " (" + player.getAsDateString() + ")");
                options.put("isAlreadyPlayed", String.valueOf(player.isAlreadyPlayed()));
                options.put("getRozenbergNumber", String.valueOf(player.getRozenbergNumber().getVariantNumber()));
                options.put("isRozTrust", String.valueOf(player.isRozTrust()));
                options.put("getRozTrustNumber", String.valueOf(player.getRozTrustNumber()));
                options.put("getStage3variant", String.valueOf(player.getStage3variant().getNumber()));

                for (int i = 1; i <=5; i++) {
                    options.put("getStage3bet", String.valueOf(player.getStage3bet(i - 1)));
                    options.put("getStage3betAsPercent", String.valueOf(player.getStage3betAsPercent(i - 1)));
                    options.put("getStage3afterBetQ", String.valueOf(player.getStage3afterBetQ(i - 1)));
                }

                options.put("getStage3Wallet", String.valueOf(player.getStage3Wallet()));
                options.put("isWantStage4", String.valueOf(player.isWantStage4()));
                options.put("getStage4q", String.valueOf(player.getStage4q()));
                options.put("getStage4Wallet", String.valueOf(player.getStage4Wallet()));

                for (int i = 1; i <=6; i++) {
                    options.put("getStage5q", String.valueOf(player.getStage5q(i - 1)));
                }

                options.put("getStage6GratitudeNumber", String.valueOf(player.getStage6GratitudeNumber()));

                for (int i = 1; i <=5; i++) {
                    options.put("getStage7return", String.valueOf(player.getStage7return(i - 1)));
                }

                options.put("getStage7return", String.valueOf(player.getStage7Wallet()));
                options.put("getStage7SuperReturn", String.valueOf(player.getStage7SuperReturn()));
                options.put("getStagesResultWallet", String.valueOf(player.getStagesResultWallet()));

                for (int i = 1; i <=6; i++) {
                    options.put("getStage8q", String.valueOf(player.getStage8q(i - 1)));
                }

                options.put("isMarkedAccordance", String.valueOf(player.isMarkedAccordance()));

                for (int i = 1; i <=4; i++) {
                    options.put("getStage10q", String.valueOf(player.getStage10q(i - 1)));
                }

                for (int i = 1; i <=4 ; i++) {
                    for (int j = 1; j <= 4; j++) {
                        options.put("getSlonomuh", String.valueOf(player.getSlonomuh(i-1, j-1)));
                    }
                }
                options.put("getRatingBetsSum", String.valueOf(player.getRatingBetsSum()));
                options.put("getRatingBetsPercent", String.valueOf(player.getRatingBetsPercent()));
                options.put("getRatingReturnsSum", String.valueOf(player.getRatingReturnsSum()));
                options.put("getRatingReturnsPercent", String.valueOf(player.getRatingReturnsPercent()));
                options.put("getRatingSuperSum", String.valueOf(player.getRatingSuperSum()));
                json = new Gson().toJson(options);
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write(json);
                break;
        }
    }

    private void getUsers(HttpServletResponse response) throws IOException {
        Map<User, List<Player>> uPlayers;
        String json;List<String> usersLogins = new ArrayList<>();
        uPlayers =  TheGameModel.getInstance().getUsersPlayers();
        for (User user : uPlayers.keySet()) {
            if (uPlayers.get(user).size() == 0) {
                continue;
            }
            if (!usersLogins.contains(user.getLogin())) {
                usersLogins.add(user.getLogin());
            }
        }

        json = new Gson().toJson(usersLogins);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    private void getUPlayers(HttpServletResponse response, HttpServletRequest request) throws IOException {
        String userLogin = request.getParameter("login");
        List<Player> uPlayers =  TheGameModel.getInstance().getUserPlayers(userLogin);
        List<String> players = uPlayers.stream().map(Player::getTimestamp).collect(Collectors.toList());
        String json = new Gson().toJson(players);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    private void writeIntoExcel(OutputStream os) throws IOException {
        Workbook book = ExcelUtils.createResultBook(true);
        book.write(os);
        os.flush();
        book.close();
    }
}

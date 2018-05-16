package ru.sokolov.jz.thegame.servlet;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import ru.sokolov.jz.thegame.entities.Player;
import ru.sokolov.jz.thegame.entities.User;
import ru.sokolov.jz.thegame.model.TheGameModel;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

/**
 * Created by sokolov
 * Created on 26.04.2018.
 */
@WebServlet(name = "AdminServlet", urlPatterns = "/admin")
public class AdminServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/x-download");
        response.setHeader("Content-Disposition", "attachment; filename=result.xls");
        try (OutputStream out = response.getOutputStream()) {
            writeIntoExcel(out);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if ("jz".equals(request.getParameter("pass"))) {
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
        Map<User, List<Player>> players = TheGameModel.getInstance().getUsersPlayers();
        Workbook book = new HSSFWorkbook();
        Sheet usersSheet = book.createSheet("Users");
        Sheet playersSheet = book.createSheet("Players");

        int counter = 0;
        Row userHeaderRow = usersSheet.createRow(counter++);
        createStringCell("Login", userHeaderRow, 0);
        createStringCell("Password", userHeaderRow, 1);
        createStringCell("Contact", userHeaderRow, 2);
        createStringCell("Sex", userHeaderRow, 3);
        createStringCell("Age", userHeaderRow, 4);
        createStringCell("Income", userHeaderRow, 5);
        createStringCell("Count", userHeaderRow, 6);

        int plCounter = 0;
        // Заголовок
        Row playerCommentRow = playersSheet.createRow(plCounter++);
        int colCounter = 0;
        createStringCell("Login", playerCommentRow, colCounter++);//1
        createStringCell("Timestamp", playerCommentRow, colCounter++);
        createStringCell("AlreadyPlayed", playerCommentRow, colCounter++);
        createStringCell("RozenbergNumber", playerCommentRow, colCounter++);
        createStringCell("RozTrust", playerCommentRow, colCounter++);
        createStringCell("RozTrustNumber", playerCommentRow, colCounter++);
        createStringCell("Stage3variant", playerCommentRow, colCounter++);
        createStringCell("Stage3bet(1)", playerCommentRow, colCounter++);
        createStringCell("Stage3betAsPercent(1)", playerCommentRow, colCounter++);
        createStringCell("Stage3afterBetQ(1)", playerCommentRow, colCounter++);//10
        createStringCell("Stage3bet(2)", playerCommentRow, colCounter++);
        createStringCell("Stage3betAsPercent(2)", playerCommentRow, colCounter++);
        createStringCell("Stage3afterBetQ(2)", playerCommentRow, colCounter++);
        createStringCell("Stage3bet(3)", playerCommentRow, colCounter++);
        createStringCell("Stage3betAsPercent(3)", playerCommentRow, colCounter++);
        createStringCell("Stage3afterBetQ(3)", playerCommentRow, colCounter++);
        createStringCell("Stage3bet(4)", playerCommentRow, colCounter++);
        createStringCell("Stage3betAsPercent(4)", playerCommentRow, colCounter++);
        createStringCell("Stage3afterBetQ(4)", playerCommentRow, colCounter++);
        createStringCell("Stage3bet(5)", playerCommentRow, colCounter++);//20
        createStringCell("Stage3betAsPercent(5)", playerCommentRow, colCounter++);
        createStringCell("Stage3afterBetQ(5)", playerCommentRow, colCounter++);
        createStringCell("Stage3Wallet", playerCommentRow, colCounter++);
        createStringCell("WantStage4", playerCommentRow, colCounter++);
        createStringCell("Stage4q", playerCommentRow, colCounter++);
        createStringCell("Stage4Wallet", playerCommentRow, colCounter++);
        createStringCell("Stage5q(1)", playerCommentRow, colCounter++);
        createStringCell("Stage5q(2)", playerCommentRow, colCounter++);
        createStringCell("Stage5q(3)", playerCommentRow, colCounter++);
        createStringCell("Stage5q(4)", playerCommentRow, colCounter++);//30
        createStringCell("Stage5q(5)", playerCommentRow, colCounter++);
        createStringCell("Stage5q(6)", playerCommentRow, colCounter++);
        createStringCell("GratitudeNumber", playerCommentRow, colCounter++);
        createStringCell("Stage7return(1)", playerCommentRow, colCounter++);
        createStringCell("Stage7return(2)", playerCommentRow, colCounter++);
        createStringCell("Stage7return(3)", playerCommentRow, colCounter++);
        createStringCell("Stage7return(4)", playerCommentRow, colCounter++);
        createStringCell("Stage7return(5)", playerCommentRow, colCounter++);
        createStringCell("Stage7Wallet", playerCommentRow, colCounter++);
        createStringCell("Stage7SuperReturn", playerCommentRow, colCounter++);//40
        createStringCell("StagesResultWallet", playerCommentRow, colCounter++);
        createStringCell("Stage8q(1)", playerCommentRow, colCounter++);
        createStringCell("Stage8q(2)", playerCommentRow, colCounter++);
        createStringCell("Stage8q(3)", playerCommentRow, colCounter++);
        createStringCell("Stage8q(4)", playerCommentRow, colCounter++);
        createStringCell("Stage8q(5)", playerCommentRow, colCounter++);
        createStringCell("Stage8q(6)", playerCommentRow, colCounter++);
        createStringCell("MarkedAccordance", playerCommentRow, colCounter++);
        createStringCell("Slonomuh_q(1)", playerCommentRow, colCounter++);
        createStringCell("Slonomuh_q(2)", playerCommentRow, colCounter++);
        createStringCell("Slonomuh_q(3)", playerCommentRow, colCounter++);//50
        createStringCell("Slonomuh_q(4)", playerCommentRow, colCounter++);
        for (int i = 1; i <=4 ; i++) {
            for (int j = 1; j <= 4; j++) {
                createStringCell("Slonomuh_stat(" + i + "-" + j + ")", playerCommentRow, colCounter++);
            }
        }
        createStringCell("betsSum", playerCommentRow, colCounter++);
        createStringCell("betsPercent", playerCommentRow, colCounter++);
        createStringCell("returnsSum", playerCommentRow, colCounter++);
        createStringCell("returnsPercent", playerCommentRow, colCounter++);
        createStringCell("superSum", playerCommentRow, colCounter);


        for (User user : players.keySet()) {
            Row row = usersSheet.createRow(counter++);
            createStringCell(user.getLogin(), row, 0);
            createStringCell(user.getPassword(), row, 1);
            createStringCell(user.getContact(), row, 2);
            createNumericCell(user.getSex(), row, 3);
            createNumericCell(user.getAge(), row, 4);
            createNumericCell(user.getIncome(), row, 5);
            createNumericCell(user.getCount(), row, 6);

            for (Player player : players.get(user)) {
                Row pRow = playersSheet.createRow(plCounter++);
                colCounter = 0;
                createStringCell(user.getLogin(), pRow, colCounter++);//1
                createStringCell(player.getTimestamp(), pRow, colCounter++);
                createBooleanCell(player.isAlreadyPlayed(), pRow, colCounter++);
                createNumericCell(player.getRozenbergNumber().getVariantNumber(), pRow, colCounter++);
                createBooleanCell(player.isRozTrust(), pRow, colCounter++);
                createNumericCell(player.getRozTrustNumber(), pRow, colCounter++);
                createNumericCell(player.getStage3variant().getNumber(), pRow, colCounter++);
                createNumericCell(player.getStage3bet(0), pRow, colCounter++);
                createNumericCell(player.getStage3betAsPercent(0), pRow, colCounter++);
                createNumericCell(player.getStage3afterBetQ(0), pRow, colCounter++);//10
                createNumericCell(player.getStage3bet(1), pRow, colCounter++);
                createNumericCell(player.getStage3betAsPercent(1), pRow, colCounter++);
                createNumericCell(player.getStage3afterBetQ(1), pRow, colCounter++);
                createNumericCell(player.getStage3bet(2), pRow, colCounter++);
                createNumericCell(player.getStage3betAsPercent(2), pRow, colCounter++);
                createNumericCell(player.getStage3afterBetQ(2), pRow, colCounter++);
                createNumericCell(player.getStage3bet(3), pRow, colCounter++);
                createNumericCell(player.getStage3betAsPercent(3), pRow, colCounter++);
                createNumericCell(player.getStage3afterBetQ(3), pRow, colCounter++);
                createNumericCell(player.getStage3bet(4), pRow, colCounter++);//20
                createNumericCell(player.getStage3betAsPercent(4), pRow, colCounter++);
                createNumericCell(player.getStage3afterBetQ(4), pRow, colCounter++);
                createNumericCell(player.getStage3Wallet(), pRow, colCounter++);
                createBooleanCell(player.isWantStage4(), pRow, colCounter++);
                createNumericCell(player.getStage4q(), pRow, colCounter++);
                createNumericCell(player.getStage4Wallet(), pRow, colCounter++);
                createNumericCell(player.getStage5q(0), pRow, colCounter++);
                createNumericCell(player.getStage5q(1), pRow, colCounter++);
                createNumericCell(player.getStage5q(2), pRow, colCounter++);
                createNumericCell(player.getStage5q(3), pRow, colCounter++);//30
                createNumericCell(player.getStage5q(4), pRow, colCounter++);
                createNumericCell(player.getStage5q(5), pRow, colCounter++);
                createNumericCell(player.getStage6GratitudeNumber(), pRow, colCounter++);
                createNumericCell(player.getStage7return(0), pRow, colCounter++);
                createNumericCell(player.getStage7return(1), pRow, colCounter++);
                createNumericCell(player.getStage7return(2), pRow, colCounter++);
                createNumericCell(player.getStage7return(3), pRow, colCounter++);
                createNumericCell(player.getStage7return(4), pRow, colCounter++);
                createNumericCell(player.getStage7Wallet(), pRow, colCounter++);
                createNumericCell(player.getStage7SuperReturn(), pRow, colCounter++);//40
                createNumericCell(player.getStagesResultWallet(), pRow, colCounter++);
                createNumericCell(player.getStage8q(0), pRow, colCounter++);
                createNumericCell(player.getStage8q(1), pRow, colCounter++);
                createNumericCell(player.getStage8q(2), pRow, colCounter++);
                createNumericCell(player.getStage8q(3), pRow, colCounter++);
                createNumericCell(player.getStage8q(4), pRow, colCounter++);
                createNumericCell(player.getStage8q(5), pRow, colCounter++);
                createBooleanCell(player.isMarkedAccordance(), pRow, colCounter++);
                createNumericCell(player.getStage10q(0), pRow, colCounter++);
                createNumericCell(player.getStage10q(1), pRow, colCounter++);
                createNumericCell(player.getStage10q(2), pRow, colCounter++);//50
                createNumericCell(player.getStage10q(3), pRow, colCounter++);
                for (int i = 1; i <=4 ; i++) {
                    for (int j = 1; j <= 4; j++) {
                        createNumericCell(player.getSlonomuh(i-1, j-1), pRow, colCounter++);
                    }
                }
                createNumericCell(player.getRatingBetsSum(), pRow, colCounter++);
                createNumericCell(player.getRatingBetsPercent(), pRow, colCounter++);
                createNumericCell(player.getRatingReturnsSum(), pRow, colCounter++);
                createNumericCell(player.getRatingReturnsPercent(), pRow, colCounter++);
                createNumericCell(player.getRatingSuperSum(), pRow, colCounter);
            }
        }

        for (int i = 0; i < usersSheet.getRow(0).getPhysicalNumberOfCells(); i++) {
            usersSheet.autoSizeColumn(i);
        }

        for (int i = 0; i < playersSheet.getRow(0).getPhysicalNumberOfCells(); i++) {
            playersSheet.autoSizeColumn(i);
        }

        book.write(os);
        os.flush();
        book.close();
    }

    private void createStringCell(String value, Row row, int counter) {
        Cell cell = row.createCell(counter);
        cell.setCellValue(value);
    }

    private void createNumericCell(int value, Row row, int counter) {
        Cell cell = row.createCell(counter);
        cell.setCellType(Cell.CELL_TYPE_NUMERIC);
        cell.setCellValue(value);
    }

    private void createBooleanCell(boolean value, Row row, int counter) {
        Cell cell = row.createCell(counter);
        cell.setCellType(Cell.CELL_TYPE_BOOLEAN);
        cell.setCellValue(value);
    }
}

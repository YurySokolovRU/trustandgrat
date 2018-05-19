package ru.sokolov.jz.thegame.utils;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import ru.sokolov.jz.thegame.entities.Player;
import ru.sokolov.jz.thegame.entities.User;
import ru.sokolov.jz.thegame.model.TheGameModel;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by sokolov
 * Created on 18.05.2018.
 */
public class ExcelUtils {

    public static Workbook createResultBook() throws IOException {
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

        for (int i = 1; i <=5; i++) {
            createStringCell("Stage3bet(" + i + ")", playerCommentRow, colCounter++);
            createStringCell("Stage3betAsPercent(" + i + ")", playerCommentRow, colCounter++);
            createStringCell("Stage3afterBetQ(" + i + ")", playerCommentRow, colCounter++);//10
        }

        createStringCell("Stage3Wallet", playerCommentRow, colCounter++);
        createStringCell("WantStage4", playerCommentRow, colCounter++);
        createStringCell("Stage4q", playerCommentRow, colCounter++);
        createStringCell("Stage4Wallet", playerCommentRow, colCounter++);

        for (int i = 1; i <=6; i++) {
            createStringCell("Stage5q(" + i + ")", playerCommentRow, colCounter++);
        }

        createStringCell("GratitudeNumber", playerCommentRow, colCounter++);

        for (int i = 1; i <=5; i++) {
            createStringCell("Stage7return(" + i + ")", playerCommentRow, colCounter++);
        }

        createStringCell("Stage7Wallet", playerCommentRow, colCounter++);
        createStringCell("Stage7SuperReturn", playerCommentRow, colCounter++);//40
        createStringCell("StagesResultWallet", playerCommentRow, colCounter++);

        for (int i = 1; i <=6; i++) {
            createStringCell("Stage8q(" + i + ")", playerCommentRow, colCounter++);
        }

        createStringCell("MarkedAccordance", playerCommentRow, colCounter++);

        for (int i = 1; i <=4; i++) {
            createStringCell("Slonomuh_q(" + i + ")", playerCommentRow, colCounter++);
        }

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

                for (int i = 1; i <=5; i++) {
                    createNumericCell(player.getStage3bet(i-1), pRow, colCounter++);
                    createNumericCell(player.getStage3betAsPercent(i-1), pRow, colCounter++);
                    createNumericCell(player.getStage3afterBetQ(i-1), pRow, colCounter++);//10
                }

                createNumericCell(player.getStage3Wallet(), pRow, colCounter++);
                createBooleanCell(player.isWantStage4(), pRow, colCounter++);
                createNumericCell(player.getStage4q(), pRow, colCounter++);
                createNumericCell(player.getStage4Wallet(), pRow, colCounter++);

                for (int i = 1; i <=6; i++) {
                    createNumericCell(player.getStage5q(i - 1), pRow, colCounter++);
                }

                createNumericCell(player.getStage6GratitudeNumber(), pRow, colCounter++);

                for (int i = 1; i <=5; i++) {
                    createNumericCell(player.getStage7return(i - 1), pRow, colCounter++);
                }

                createNumericCell(player.getStage7Wallet(), pRow, colCounter++);
                createNumericCell(player.getStage7SuperReturn(), pRow, colCounter++);//40
                createNumericCell(player.getStagesResultWallet(), pRow, colCounter++);

                for (int i = 1; i <=6; i++) {
                    createNumericCell(player.getStage8q(i - 1), pRow, colCounter++);
                }

                createBooleanCell(player.isMarkedAccordance(), pRow, colCounter++);

                for (int i = 1; i <=4; i++) {
                    createNumericCell(player.getStage10q(i - 1), pRow, colCounter++);
                }

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

        return book;
    }

    private static void createStringCell(String value, Row row, int counter) {
        Cell cell = row.createCell(counter);
        cell.setCellValue(value);
    }

    private static void createNumericCell(int value, Row row, int counter) {
        Cell cell = row.createCell(counter);
        cell.setCellType(Cell.CELL_TYPE_NUMERIC);
        cell.setCellValue(value);
    }

    private static void createBooleanCell(boolean value, Row row, int counter) {
        Cell cell = row.createCell(counter);
        cell.setCellType(Cell.CELL_TYPE_BOOLEAN);
        cell.setCellValue(value);
    }
}

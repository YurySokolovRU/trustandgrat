package ru.sokolov.jz.thegame.utils;

import org.jdom2.Content;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import ru.sokolov.jz.thegame.entities.Player;
import ru.sokolov.jz.thegame.entities.User;
import ru.sokolov.jz.thegame.model.RozenbergScaleTest;
import ru.sokolov.jz.thegame.model.Stage3Model;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sokolov
 * Created on 12.04.2018.
 */
public class XMLUtils {
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            User user = new User("sokolov");
            user.setPassword("pass");
            Player player = new Player(user);
            player.setTimestamp(String.valueOf(System.currentTimeMillis()));
            player.setRozenbergNumber(RozenbergScaleTest.VARIANT.VAR1);
            player.setStage3variant(Stage3Model.VARIANT.UP_PERCENT);
            String path = "jz";
            saveUserDocument(user, path);
            savePlayerDocument(player, path);
        }
    }

    public static void saveUserDocument(User user, String path) {
        File userFile = getUserFile(user, path);
        if (userFile.exists()) {
            userFile.delete();
        }
        // Создаем документ
        Document xmlDoc = new Document();
        // Создаем корневой элемент
        Element root = new Element("user");
        // Добавляем корневой элемент в документ
        xmlDoc.setRootElement(root);
        createElement(root, "login", user.getLogin());
        createElement(root, "password", user.getPassword());
        createElement(root, "contact", user.getContact());
        createElement(root, "sex", String.valueOf(user.getSex()));
        createElement(root, "age", String.valueOf(user.getAge()));
        createElement(root, "income", String.valueOf(user.getIncome()));
        createElement(root, "count", String.valueOf(user.getCount()));

        try {
            Format fmt = Format.getPrettyFormat();
            XMLOutputter serializer = new XMLOutputter(fmt);
//            serializer.output(xmlDoc, System.out);
            try (OutputStream os = new FileOutputStream(getUserFile(user, path))) {
                serializer.output(xmlDoc, os);
                os.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static File getUserFile(User user, String path) {
        return getUserFile(user.getLogin(), path);
    }

    private static File getUserFile(String userLogin, String path) {
        return new File(path + "/" + userLogin + ".xml");
    }

    private static Element createElement(Element rootElement, String name, String value) {
        Element el = new Element(name);
        if (value != null) {
            el.addContent(value);
        }
        rootElement.addContent(el);
        return el;
    }

    // Сохранение Игры. Есть три момента сохранения, два предварительных (после определения варианта шкалы Розенберга и после варианта игры Доверие) и один финальный
    public static void savePlayerDocument(Player player, String path) {
        File playerFile = getPlayerFile(player, path);
        if (playerFile.exists()) {
            playerFile.delete();
        }
        // Создаем документ
        Document xmlDoc = new Document();
        // Создаем корневой элемент
        Element root = new Element("player");
        // Добавляем корневой элемент в документ
        xmlDoc.setRootElement(root);
        createElement(root, "timestamp", player.getTimestamp());
        createElement(root, "rozVariant", String.valueOf(player.getRozenbergNumber().getVariantNumber()));

        if (player.getStage3variant() != null) {// если null - это предварительное сохранение после подсчета варианта шкалы Розенберга - дальше не записываем
            createElement(root, "alreadyPlayed", String.valueOf(player.isAlreadyPlayed()));
            createElement(root, "rozTrust", String.valueOf(player.isRozTrust()));
            createElement(root, "rozTrustNumber", String.valueOf(player.getRozTrustNumber()));
            createElement(root, "stage3Variant", String.valueOf(player.getStage3variant().getNumber()));

            if (player.isCompleted()) {// если не стоит признака Завершена игра - это предварительное сохранение после подсчета варианта игры Доверие - дальше не записываем
                Element stage3Bets = createElement(root, "stage3bets", null);
                for (int i = 1; i < 6; i++) {//bet1,..,5
                    createElement(stage3Bets, "bet" + i, String.valueOf(player.getStage3bet(i - 1)));
                }

                Element stage3BetsAsPercent = createElement(root, "stage3betsAsPercent", null);
                for (int i = 1; i < 6; i++) {//bet1percent,..,5
                    createElement(stage3BetsAsPercent, "bet" + i + "percent", String.valueOf(player.getStage3betAsPercent(i - 1)));
                }

                Element stage3AfterBetsQ = createElement(root, "stage3afterBetsQ", null);
                for (int i = 1; i < 6; i++) {//afterBet1q,..,5
                    createElement(stage3AfterBetsQ, "afterBet" + i + "q", String.valueOf(player.getStage3afterBetQ(i - 1)));
                }

                createElement(root, "stage3Wallet", String.valueOf(player.getStage3Wallet()));
                createElement(root, "wantStage4", String.valueOf(player.isWantStage4()));
                createElement(root, "stage4q", String.valueOf(player.getStage4q()));
                createElement(root, "stage4Wallet", String.valueOf(player.getStage4Wallet()));

                Element stage5q = createElement(root, "stage5q", null);
                for (int i = 1; i < 7; i++) {//answer1,..,6
                    createElement(stage5q, "answer" + i, String.valueOf(player.getStage5q(i - 1)));
                }

                Element stage7Returns = createElement(root, "stage7returns", null);
                for (int i = 1; i < 6; i++) {//return1,..,5
                    createElement(stage7Returns, "return" + i, String.valueOf(player.getStage7return(i - 1)));
                }

                createElement(root, "gratitudeNumber", String.valueOf(player.getStage6GratitudeNumber()));

                createElement(root, "stage7Wallet", String.valueOf(player.getStage7Wallet()));
                createElement(root, "stage7SuperReturn", String.valueOf(player.getStage7SuperReturn()));
                createElement(root, "stagesResultWallet", String.valueOf(player.getStagesResultWallet()));

                Element stage8q = createElement(root, "stage8q", null);
                for (int i = 1; i < 7; i++) {//answer1,..,6
                    createElement(stage8q, "answer" + i, String.valueOf(player.getStage8q(i - 1)));
                }

                createElement(root, "markedAccordance", String.valueOf(player.isMarkedAccordance()));

                Element stage10q = createElement(root, "slonomuh", null);
                for (int i = 1; i < 5; i++) {//answer1,..,4
                    createElement(stage10q, "answer" + i, String.valueOf(player.getStage10q(i - 1)));
                }

                Element slonomuh = createElement(root, "slonomuh-stat", null);
                for (int i = 1; i <= 4; i++) {//stat_1-1
                    for (int j = 1; j <= 4; j++) {
                        createElement(slonomuh, "stat_" + i + "-" + j, String.valueOf(player.getSlonomuh(i - 1, j - 1)));
                    }
                }

                Element summary = createElement(root, "summary", null);
                createElement(summary, "betsSum", String.valueOf(player.getRatingBetsSum()));
                createElement(summary, "betsPercent", String.valueOf(player.getRatingBetsPercent()));
                createElement(summary, "returnsSum", String.valueOf(player.getRatingReturnsSum()));
                createElement(summary, "returnsPercent", String.valueOf(player.getRatingReturnsPercent()));
                createElement(summary, "superSum", String.valueOf(player.getRatingSuperSum()));
            }
        }

        createElement(root, "completed", String.valueOf(player.isCompleted()));

        try {
            // Получаем "красивый" формат для вывода XML
            // с переводами на новую строку и отступами
            Format fmt = Format.getPrettyFormat();
            // Выводим созданный XML в файл, используя подготовленный формат
            XMLOutputter serializer = new XMLOutputter(fmt);
//            serializer.output(xmlDoc, System.out);
            File folder = new File(path + "/" + player.getUser().getLogin());
            if (!folder.exists()) {
                folder.mkdir();
            }
            try (OutputStream os = new FileOutputStream(new File(path + "/" + player.getUser().getLogin() + "/" + player.getTimestamp() + ".xml"))) {
                serializer.output(xmlDoc, os);
                os.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static File getPlayerFile(Player player, String path) {
        return new File(path + "/" + player.getUser().getLogin() + "/" + player.getTimestamp() + ".xml");
    }

    public static User readUserFromDocument(String login, String path) {
        File userFile = getUserFile(login, path);
        if (userFile.exists()) {
            return readUserFromFile(userFile);
        } else {
            return null;
        }
    }

    public static User readUserFromFile(File file) {
        Document xmlDoc;
        SAXBuilder parser = new SAXBuilder();
        try {
            xmlDoc = parser.build(file);
        } catch (JDOMException | IOException e) {
            throw new RuntimeException(e);
        }

        Element root = xmlDoc.getRootElement();
        User user = new User();
        user.setLogin(readValue(root, "login"));
        user.setPassword(readValue(root, "password"));
        user.setContact(readValue(root, "contact"));
        user.setSex(Integer.parseInt(readValue(root, "sex")));
        user.setAge(Integer.parseInt(readValue(root, "age")));
        user.setIncome(Integer.parseInt(readValue(root, "income")));
        user.setCount(Integer.parseInt(readValue(root, "count")));
        return user;
    }

    public static Document getUserDocument(String login, String path) {
        SAXBuilder parser = new SAXBuilder();
        try {
            return parser.build(getUserFile(login, path));
        } catch (JDOMException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static String readValue(Element rootElement, String name) {
        List<Element> children = rootElement.getChildren(name);
        if (children.size() > 0) {
            List<Content> contents = children.get(0).getContent();
            if (contents.size() > 0) {
                return contents.get(0).getValue();
            }
        }
        return "";
    }

    private static String readElementValue(Element element) {
        List<Content> contents = element.getContent();
        if (contents.size() > 0) {
            return contents.get(0).getValue();
        }
        return "";
    }

    public static List<User> readAllUsers(String path) {
        List<User> users = new ArrayList<>();
        File rootFolder = new File(path);
        File[] files = rootFolder.listFiles(File::isFile);

        if (files != null) {
            for (File file : files) {
                users.add(readUserFromFile(file));
            }
        }
        return users;
    }

    public static List<Player> readPlayers(User user, String path) {
        return readPlayers(user.getLogin(), path);
    }

    public static List<Player> readPlayers(String login, String path) {
        List<Player> players = new ArrayList<>();
        File userFolder = new File(path + "/" + login);
        if (userFolder.exists()) {
            File[] files = userFolder.listFiles();
            if (files != null) {
                for (File file : files) {
                    Player player = readPlayerFromFile(file);
                    player.setUser(readUserFromDocument(login, path));
                    players.add(player);
                }
            }
        }
        return players;
    }

    private static Player readPlayerFromFile(File file) {
        SAXBuilder parser = new SAXBuilder();
        try {
            Document document = parser.build(file);
            Element root = document.getRootElement();
            Player player = new Player();
            player.setTimestamp(readValue(root, "timestamp"));
            player.setRozenbergNumber(RozenbergScaleTest.VARIANT.getByNumber(Integer.parseInt(readValue(root, "rozVariant"))));

            if (!"".equals(readValue(root, "stage3Variant"))) {
                player.setAlreadyPlayed(Boolean.parseBoolean(readValue(root, "alreadyPlayed")));
                player.setRozTrust(Boolean.parseBoolean(readValue(root, "rozTrust")));
                player.setRozTrustNumber(Integer.parseInt(readValue(root, "rozTrustNumber")));
                player.setStage3variant(Stage3Model.VARIANT.getByNumber(Integer.parseInt(readValue(root, "stage3Variant"))));

                if (!"false".equals(readValue(root, "completed"))) {
                    Element stage3Bets = getElement(root, "stage3bets");
                    if (stage3Bets != null) {//bet1,..,5
                        for (int i = 1; i < 6; i++) {
                            player.setStage3bet(Integer.parseInt(readValue(stage3Bets, "bet" + i)), i - 1);
                        }
                    }

                    Element stage3BetsAsPercent = getElement(root, "stage3betsAsPercent");
                    if (stage3BetsAsPercent != null) {//bet1percent..,5
                        for (int i = 1; i < 6; i++) {
                            player.setStage3betAsPercent(Integer.parseInt(readValue(stage3BetsAsPercent, "bet" + i + "percent")), i - 1);
                        }
                    }

                    Element stage3AfterBetsQ = getElement(root, "stage3afterBetsQ");
                    if (stage3Bets != null) {//afterBet1q,..,5
                        for (int i = 1; i < 6; i++) {
                            player.setStage3afterBetQ(Integer.parseInt(readValue(stage3AfterBetsQ, "afterBet" + i + "q")), i - 1);
                        }
                    }

                    player.setStage3Wallet(Integer.parseInt(readValue(root, "stage3Wallet")));
                    player.setWantStage4(Boolean.parseBoolean(readValue(root, "wantStage4")));
                    player.setStage4q(Integer.parseInt(readValue(root, "stage4q")));
                    player.setStage4Wallet(Integer.parseInt(readValue(root, "stage4Wallet")));

                    Element stage5q = getElement(root, "stage5q");
                    if (stage5q != null) {//answer1,..,6
                        for (int i = 1; i < 7; i++) {
                            player.setStage5q(Integer.parseInt(readValue(stage5q, "answer" + i)), i - 1);
                        }
                    }

                    player.setStage6GratitudeNumber(Integer.parseInt(readValue(root, "gratitudeNumber")));

                    Element stage7Returns = getElement(root, "stage7returns");
                    if (stage7Returns != null) {//return1,..,5
                        for (int i = 1; i < 6; i++) {
                            player.setStage7return(Integer.parseInt(readValue(stage7Returns, "return" + i)), i - 1);
                        }
                    }

                    player.setStage7Wallet(Integer.parseInt(readValue(root, "stage7Wallet")));
                    player.setStage7SuperReturn(Integer.parseInt(readValue(root, "stage7SuperReturn")));
                    player.setStagesResultWallet(Integer.parseInt(readValue(root, "stagesResultWallet")));

                    Element stage8q = getElement(root, "stage8q");
                    if (stage8q != null) {//answer1,..,6
                        for (int i = 1; i < 7; i++) {
                            player.setStage8q(Integer.parseInt(readValue(stage8q, "answer" + i)), i - 1);
                        }
                    }

                    player.setMarkedAccordance(Boolean.parseBoolean(readValue(root, "markedAccordance")));

                    Element stage10q = getElement(root, "slonomuh");
                    if (stage10q != null) {//answer1,..,4
                        for (int i = 1; i < 5; i++) {
                            player.setStage10q(Integer.parseInt(readValue(stage10q, "answer" + i)), i - 1);
                        }
                    }

                    Element slonomuh = getElement(root, "slonomuh-stat");
                    for (int i = 1; i <= 4; i++) {
                        for (int j = 1; j <= 4; j++) {
                            player.setSlonomuh(i - 1, j - 1, Integer.parseInt(readValue(slonomuh, "stat_" + i + "-" + j)));
                        }
                    }

                    Element summary = getElement(root, "summary");
                    if (summary != null) {
                        player.setRatingBetsSum(Integer.parseInt(readValue(summary, "betsSum")));
                        player.setRatingBetsPercent(Integer.parseInt(readValue(summary, "betsPercent")));
                        player.setRatingReturnsSum(Integer.parseInt(readValue(summary, "returnsSum")));
                        player.setRatingReturnsPercent(Integer.parseInt(readValue(summary, "returnsPercent")));
                        player.setRatingSuperSum(Integer.parseInt(readValue(summary, "superSum")));
                    }
                }
            }

            Element completed = getElement(root, "completed");
            if (completed == null || "true".equals(readElementValue(completed))) {
                player.setCompleted(true);
            } else {
                player.setCompleted(false);
            }

            return player;
        } catch (JDOMException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<Player> readAllPlayers(String path) {
        List<Player> players = new ArrayList<>();
        File rootFolder = new File(path);
        File[] folders = rootFolder.listFiles(File::isDirectory);

        if (folders != null) {
            for (File folder : folders) {
                File[] playerFiles = folder.listFiles();
                if (playerFiles != null) {
                    for (File playerFile : playerFiles) {
                        Player player = readPlayerFromFile(playerFile);
                        player.setUser(readUserFromDocument(folder.getName(), path));
                        players.add(player);
                    }
                }
            }
        }
        return players;
    }

    private static Element getElement(Element rootElement, String name) {
        List<Element> children = rootElement.getChildren(name);
        return children.size() > 0 ? children.get(0) : null;
    }
}

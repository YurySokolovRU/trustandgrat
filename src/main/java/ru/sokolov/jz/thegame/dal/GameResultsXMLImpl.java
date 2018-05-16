package ru.sokolov.jz.thegame.dal;

import ru.sokolov.jz.thegame.entities.Player;
import ru.sokolov.jz.thegame.entities.User;
import ru.sokolov.jz.thegame.utils.XMLUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

/**
 * Created by sokolov
 * Created on 25.04.2018.
 */
public class GameResultsXMLImpl implements IGameResults {
    private final String FOLDER_PATH;

    private static GameResultsXMLImpl instance = new GameResultsXMLImpl();

    public static GameResultsXMLImpl getInstance() {
        return instance;
    }

    private GameResultsXMLImpl() {
        try {
            Properties props = new Properties();
            try (InputStream propertiesFileIS = this.getClass().getClassLoader().getResourceAsStream("config.properties")) {
                props.load(propertiesFileIS);
                FOLDER_PATH = props.getProperty("XML_ROOT_FOLDER");
                if (!ensureFolderPath()) {
                    throw new RuntimeException("Failed to handle [" + FOLDER_PATH +"]");
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to read config.properties file");
        }
    }

    @Override
    public User getUserByLogin(String login) {
        return XMLUtils.readUserFromDocument(login, FOLDER_PATH);
    }

    @Override
    public void saveUser(User user) {
        XMLUtils.saveUserDocument(user, FOLDER_PATH);
    }

    @Override
    public void savePlayer(Player player) {
        saveUser(player.getUser());
        XMLUtils.savePlayerDocument(player, FOLDER_PATH);
    }

    @Override
    public List<User> getUsers() {
        return XMLUtils.readAllUsers(FOLDER_PATH);
    }

    @Override
    public List<Player> getUserPlayers(String userLogin) {
        return XMLUtils.readPlayers(userLogin, FOLDER_PATH);
    }

    @Override
    public List<Player> getPlayers() {
        return XMLUtils.readAllPlayers(FOLDER_PATH);
    }

    private boolean ensureFolderPath() {
        File folder = new File(FOLDER_PATH);
        return folder.exists() || folder.mkdirs();
    }
}

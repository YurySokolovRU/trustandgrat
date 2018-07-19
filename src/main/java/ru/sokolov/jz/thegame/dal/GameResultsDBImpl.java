package ru.sokolov.jz.thegame.dal;

import ru.sokolov.jz.thegame.entities.Player;
import ru.sokolov.jz.thegame.entities.User;

import java.util.List;

/**
 * Created by sokolov
 * Created on 24.04.2018.
 */
public class GameResultsDBImpl implements IGameResults {
    private static GameResultsDBImpl instance = new GameResultsDBImpl();

    public static GameResultsDBImpl getInstance() {
        return instance;
    }

    private GameResultsDBImpl() {
    }

    @Override
    public User getUserByLogin(String login) {
        return null;
    }

    @Override
    public void saveUser(User user) {
    }

    @Override
    public void savePlayer(Player player) {

    }

    @Override
    public List<User> getUsers() {
        return null;
    }

    @Override
    public List<Player> getUserPlayers(String userLogin) {
        return null;
    }

    @Override
    public Player getUserPlayer(String userLogin, String timestamp) {
        return null;
    }

    @Override
    public List<Player> getPlayers() {
        return null;
    }

    @Override
    public void deletePlayer(String timestamp) {

    }

    @Override
    public void deleteUser(String userLogin) {

    }
}

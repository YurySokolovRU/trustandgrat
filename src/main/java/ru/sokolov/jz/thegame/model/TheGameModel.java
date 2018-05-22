package ru.sokolov.jz.thegame.model;

import ru.sokolov.jz.thegame.dal.GameResultsFactory;
import ru.sokolov.jz.thegame.dal.IGameResults;
import ru.sokolov.jz.thegame.entities.Player;
import ru.sokolov.jz.thegame.entities.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by sokolov
 * Created on 16.04.2018.
 */
public class TheGameModel {
    IGameResults gr;

    private static TheGameModel anInstance = new TheGameModel();

    public static TheGameModel getInstance() {
        return anInstance;
    }

    private TheGameModel() {
        gr = GameResultsFactory.getGameResults("xml");
    }

    public Player createPlayer(String userLogin) {
        User user = gr.getUserByLogin(userLogin);
        if (user == null) {
            throw new IllegalArgumentException("Wrong user_login [" + userLogin + "]");
        }
        Player player = new Player(user);
        player.setRozenbergNumber(RozenbergScaleTest.calcRozenbergVariantForNewPlayer());
        player.setTimestamp(String.valueOf(System.currentTimeMillis()));
        initStorePlayer(player);
        return player;
    }

    public List<Player> getAllPlayers() {
        return gr.getPlayers();
    }

    public List<Player> getActualPlayers() {
        List<Player> actualPlayers = new ArrayList<>();
        List<Player> players = gr.getPlayers();
        for (Player player : players) {
            if (player.isCompleted() || (System.currentTimeMillis() - Long.parseLong(player.getTimestamp())) < 10800000) {
                actualPlayers.add(player);
            }
        }
        return actualPlayers;
    }

    public List<Player> getCompletedPlayers() {
        return getCompletedPlayers(null);
    }

    public List<Player> getCompletedPlayers(String userLogin) {
        List<Player> completedPlayers = new ArrayList<>();
        List<Player> players = userLogin == null ? gr.getPlayers() : gr.getUserPlayers(userLogin);
        for (Player player : players) {
            if (player.isCompleted()) {
                completedPlayers.add(player);
            }
        }
        return completedPlayers;
    }

    public Map<User, List<Player>> getUsersPlayers() {
        Map<User, List<Player>> userPlayers = new HashMap<>();
        List<User> users = gr.getUsers();
        for (User user : users) {
            userPlayers.put(user, getCompletedPlayers(user.getLogin()));
        }
        return userPlayers;
    }

    public User getUser(String userLogin) {
        return gr.getUserByLogin(userLogin);
    }

    public void storeUser(User user) {
        gr.saveUser(user);
    }

    public void initStorePlayer(Player player) {
        gr.savePlayer(player);
    }

    public void finalStorePlayer(Player player) {
        player.getUser().setCount(player.getUser().getCount() + 1);
        player.setCompleted(true);
        gr.savePlayer(player);
    }

    public Player getPlayerById(String timestamp) {
        List<Player> allPlayers = TheGameModel.getInstance().getAllPlayers();
        for (Player player : allPlayers) {
            if (player.getTimestamp().equals(timestamp)) {
                return player;
            }
        }
        return null;
    }
}

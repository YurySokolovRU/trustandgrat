package ru.sokolov.jz.thegame.dal;

import ru.sokolov.jz.thegame.entities.Player;
import ru.sokolov.jz.thegame.entities.User;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by sokolov
 * Created on 24.04.2018.
 */
public class GameResultsInMemoryImpl implements IGameResults {
    private static GameResultsInMemoryImpl instance = new GameResultsInMemoryImpl();
    private List<User> users = new ArrayList<>();
    private List<Player> players = new ArrayList<>();

    public static GameResultsInMemoryImpl getInstance() {
        return instance;
    }

    private GameResultsInMemoryImpl() {
    }

    @Override
    public User getUserByLogin(String login) {
        for (User user : users) {
            if (user.getLogin().equals(login)) {
                return user;
            }
        }
        return null;
    }

    @Override
    public void saveUser(User user) {
        users.add(user);
    }

    @Override
    public void savePlayer(Player player) {
        saveUser(player.getUser());
        players.add(player);
    }

    @Override
    public List<User> getUsers() {
        return users;
    }

    @Override
    public List<Player> getUserPlayers(String userLogin) {
        return this.players.stream().filter(player -> player.getUser().getLogin().equals(userLogin)).collect(Collectors.toList());
    }

    @Override
    public Player getUserPlayer(String userLogin, String timestamp) {
        for (Player player : players) {
            if (player.getTimestamp().equals(timestamp)) {
                return player;
            }
        }
        return null;
    }

    @Override
    public List<Player> getPlayers() {
        return players;
    }

    @Override
    public void deletePlayer(String timestamp) {
        for (Player player : players) {
            if (player.getTimestamp().equals(timestamp)) {
                players.remove(player);
                break;
            }
        }
    }

    @Override
    public void deleteUser(String userLogin) {
        for (User user : users) {
            if (user.getLogin().equals(userLogin)) {
                users.remove(user);
                break;
            }
        }
    }
}

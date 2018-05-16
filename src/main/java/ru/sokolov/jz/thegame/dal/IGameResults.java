package ru.sokolov.jz.thegame.dal;

import ru.sokolov.jz.thegame.entities.Player;
import ru.sokolov.jz.thegame.entities.User;

import java.util.List;

/**
 * Created by sokolov
 * Created on 13.04.2018.
 */
public interface IGameResults {
    User getUserByLogin(String login);
    void saveUser(User user);
    void savePlayer(Player player);
    List<User> getUsers();
    List<Player> getUserPlayers(String userLogin);
    List<Player> getPlayers();
}

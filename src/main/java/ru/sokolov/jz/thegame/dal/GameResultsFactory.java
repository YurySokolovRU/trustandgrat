package ru.sokolov.jz.thegame.dal;

/**
 * Created by sokolov
 * Created on 24.04.2018.
 */
public class GameResultsFactory {
    public static IGameResults getGameResults(String type) {
        switch (type) {
            case "sql":
                return GameResultsDBImpl.getInstance();
            case "java":
                return GameResultsInMemoryImpl.getInstance();
            case "xml":
                return GameResultsXMLImpl.getInstance();
        }
        throw new IllegalArgumentException("Unknown provider type [" + type + "]");
    }
}

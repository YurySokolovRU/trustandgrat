package ru.sokolov.jz.thegame.model;

import ru.sokolov.jz.thegame.entities.Player;

import java.util.List;
import java.util.Random;

/**
 * Created by sokolov
 * Created on 26.04.2018.
 */
public class Stage3Model {
    public enum VARIANT {
        UP_PERCENT(1),
        DOWN_PERCENT(2),
        RANDOM_PERCENT(3);

        double[] UP_PERCENTS = new double[] {.1,.2,.29,.5,.7};
        double[] DOWN_PERCENTS = new double[] {.7,.5,.35,.2,.1};

        private int number;

        VARIANT(int number) {
            this.number = number;
        }

        public int getNumber() {
            return number;
        }

        public double getPercent(int round) {
            switch (number) {
                case 1: return UP_PERCENTS[round - 1];
                case 2: return DOWN_PERCENTS[round - 1];
                default: {
                    double rand = new Random().nextInt(101);
                    return rand / 100;
                }
            }
        }

        public static VARIANT getByNumber(int number) {
            for (VARIANT variant : values()) {
                if (variant.getNumber() == number) {
                    return variant;
                }
            }
            throw new IllegalArgumentException("wrong variant number [" + number + "]");
        }
    }

    public static Stage3Model.VARIANT calcStage3VariantForNewPlayer(Player thePlayer) {
        if (thePlayer.getUser().getCount() > 0) {
            return Stage3Model.VARIANT.RANDOM_PERCENT;
        } else {
            int[] gameVariantCounts = new int[3];
            List<Player> players = TheGameModel.getInstance().getActualPlayers();
            for (Player player : players) {
                if (player.getStage3variant() == Stage3Model.VARIANT.UP_PERCENT) {
                    gameVariantCounts[0]++;//up
                } else if (player.getStage3variant() == Stage3Model.VARIANT.DOWN_PERCENT) {
                    gameVariantCounts[1]++;//down
                } else {
                    gameVariantCounts[2]++;//random
                }
            }
            int minimum = gameVariantCounts[0];
            Stage3Model.VARIANT variant = Stage3Model.VARIANT.UP_PERCENT;
            for (int i = 1; i < 3; i++) {
                if (gameVariantCounts[i] < minimum) {
                    minimum = gameVariantCounts[i];
                    variant = Stage3Model.VARIANT.getByNumber(i + 1);
                }
            }
            return variant;
        }
    }
}

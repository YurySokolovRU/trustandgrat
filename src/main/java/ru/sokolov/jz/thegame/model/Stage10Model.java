package ru.sokolov.jz.thegame.model;

import java.util.*;

/**
 * Created by sokolov
 * Created on 14.05.2018.
 */
public class Stage10Model {
    public enum PHRASES {
        PHRASE_1_1("Воробей быстрый", 11),
        PHRASE_1_2("Воробей медленный", 12),
        PHRASE_1_3("Слон быстрый", 13),
        PHRASE_1_4("Слон медленный", 14),

        PHRASE_2_1("Воробей сильный", 21),
        PHRASE_2_2("Воробей слабый", 22),
        PHRASE_2_3("Слон сильный", 23),
        PHRASE_2_4("Слон слабый", 24),

        PHRASE_3_1("Комар быстрый", 31),
        PHRASE_3_2("Комар медленный", 32),
        PHRASE_3_3("Волк быстрый", 33),
        PHRASE_3_4("Волк медленный", 34),

        PHRASE_4_1("Комар сильный", 41),
        PHRASE_4_2("Комар слабый", 42),
        PHRASE_4_3("Волк сильный", 43),
        PHRASE_4_4("Волк слабый", 44);

        private String text;
        private int number;

        PHRASES(String text, int number) {
            this.text = text;
            this.number = number;
        }

        public int getNumber() {
            return number;
        }

        public String getText() {
            return text;
        }

        public static int getNumberByText(String text) {
            for (PHRASES phrase : values()) {
                if (phrase.getText().equals(text)) {
                    return phrase.getNumber();
                }
            }
            return 0;
        }
    }

    private Integer[] groupsOrder;
    private Integer[] phrasesOrder;

    public Stage10Model() {
        groupsOrder = getRandomOrder(Arrays.asList(1, 2, 3, 4));
        phrasesOrder = getRandomOrder(Arrays.asList(1, 2, 3, 4));
    }

    public String getPhrase(int group, int phrase) {
        int randomGroupNumber = groupsOrder[group-1];
        int randomPhraseNumber = phrasesOrder[phrase-1];
        switch (randomGroupNumber) {
            case 1: switch (randomPhraseNumber) {
                case 1: return PHRASES.PHRASE_1_1.getText();
                case 2: return PHRASES.PHRASE_1_2.getText();
                case 3: return PHRASES.PHRASE_1_3.getText();
                case 4: return PHRASES.PHRASE_1_4.getText();
            } break;
            case 2:  switch (randomPhraseNumber) {
                case 1: return PHRASES.PHRASE_2_1.getText();
                case 2: return PHRASES.PHRASE_2_2.getText();
                case 3: return PHRASES.PHRASE_2_3.getText();
                case 4: return PHRASES.PHRASE_2_4.getText();
            } break;
            case 3:  switch (randomPhraseNumber) {
                case 1: return PHRASES.PHRASE_3_1.getText();
                case 2: return PHRASES.PHRASE_3_2.getText();
                case 3: return PHRASES.PHRASE_3_3.getText();
                case 4: return PHRASES.PHRASE_3_4.getText();
            } break;
            case 4:  switch (randomPhraseNumber) {
                case 1: return PHRASES.PHRASE_4_1.getText();
                case 2: return PHRASES.PHRASE_4_2.getText();
                case 3: return PHRASES.PHRASE_4_3.getText();
                case 4: return PHRASES.PHRASE_4_4.getText();
            } break;
        }
        return "undefined";
    }

    private Integer[] getRandomOrder(List<Integer> numbers) {
        Collections.shuffle(numbers);
        return numbers.toArray(new Integer[4]);
    }
}

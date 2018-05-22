package ru.sokolov.jz.thegame.model;

import ru.sokolov.jz.thegame.entities.Player;

import java.util.List;
import java.util.Random;

/**
 * Created by sokolov
 * Created on 23.04.2018.
 */
public class RozenbergScaleTest {
    private static final String Q1_PREFIX = "Как Вы думаете,";
    private static final String Q1_PHRASE_A = "большинству людей можно доверять";
    private static final String Q1_OR = "или";
    private static final String Q1_PHRASE_B = "во взаимодействии с другими нужно соблюдать осторожность";
    private static final String Q1_ANSWER_A = "Большинству людей можно доверять";
    private static final String Q1_ANSWER_B = "Во взаимодействии с другими нужно соблюдать осторожность";

    private static final String Q2_PREFIX = "Могли бы Вы сказать, что люди";
    private static final String Q2_PHRASE_A = "чаще всего стремятся быть полезными другим";
    private static final String Q2_OR = "или они";
    private static final String Q2_PHRASE_B = "думают только о себе";
    private static final String Q2_ANSWER_A = "Стремятся быть полезными другим";
    private static final String Q2_ANSWER_B = "Думают только о себе";

    private static final String Q3_PREFIX = "Как Вы думаете, большинство людей";
    private static final String Q3_PHRASE_A = "вели бы себя честно";
    private static final String Q3_OR = "или";
    private static final String Q3_PHRASE_B = "попытались бы обмануть вас, если бы им представилась такая возможность";
    private static final String Q3_ANSWER_A = "Вели бы себя честно";
    private static final String Q3_ANSWER_B = "Попытались бы обмануть, если бы им представилась такая возможность";

    public enum VARIANT {
        VAR1(1),
        VAR2(2),
        VAR3(3),
        VAR4(4);

        int variantNumber;

        VARIANT(int variantNumber) {
            this.variantNumber = variantNumber;
        }

        public int getVariantNumber() {
            return variantNumber;
        }

        public static VARIANT getByNumber(int number) {
            for (VARIANT variant : values()) {
                if (variant.getVariantNumber() == number) {
                    return variant;
                }
            }
            throw new IllegalArgumentException("wrong variant number [" + number + "]");
        }
    }

    enum QUESTIONS {
        Q1(Q1_PREFIX, Q1_PHRASE_A, Q1_OR, Q1_PHRASE_B, Q1_ANSWER_A, Q1_ANSWER_B),
        Q2(Q2_PREFIX, Q2_PHRASE_A, Q2_OR, Q2_PHRASE_B, Q2_ANSWER_A, Q2_ANSWER_B),
        Q3(Q3_PREFIX, Q3_PHRASE_A, Q3_OR, Q3_PHRASE_B, Q3_ANSWER_A, Q3_ANSWER_B);

        private String q_prefix;
        private String q_phrase_a;
        private String q_or;
        private String q_phrase_b;
        private String answer_a;
        private String answer_b;

        QUESTIONS(String q_prefix, String q_phrase_a, String q_or, String q_phrase_b, String answer_a, String answer_b) {
            this.q_prefix = q_prefix;
            this.q_phrase_a = q_phrase_a;
            this.q_or = q_or;
            this.q_phrase_b = q_phrase_b;
            this.answer_a = answer_a;
            this.answer_b = answer_b;
        }

        public String getQ_prefix() {
            return q_prefix;
        }

        public String getQ_phrase_a() {
            return q_phrase_a;
        }

        public String getQ_or() {
            return q_or;
        }

        public String getQ_phrase_b() {
            return q_phrase_b;
        }

        public String getAnswer_a() {
            return answer_a;
        }

        public String getAnswer_b() {
            return answer_b;
        }

        public static QUESTIONS getByNumber(int qNumber) {
            switch (qNumber) {
                case 1:
                    return Q1;
                case 2:
                    return Q2;
                case 3:
                    return Q3;
                default:
                    throw new RuntimeException();
            }
        }
    }


    private VARIANT variant;

    public RozenbergScaleTest(VARIANT variant) {
        this.variant = variant;
    }

    public TestVariant getQPair(int questionNumber) {
        QUESTIONS q = QUESTIONS.getByNumber(questionNumber);
        TestVariant test = new TestVariant();
        switch (variant) {
            case VAR1:
                test.setQuestion(String.join(" ", q.getQ_prefix(), q.getQ_phrase_a(), q.getQ_or(), q.getQ_phrase_b()) + "?");
                test.setAnswer1(q.getAnswer_a());
                test.setAnswer2(q.getAnswer_b());
                break;
            case VAR2:
                test.setQuestion(String.join(" ", q.getQ_prefix(), q.getQ_phrase_b(), q.getQ_or(), q.getQ_phrase_a()) + "?");
                test.setAnswer1(q.getAnswer_b());
                test.setAnswer2(q.getAnswer_a());
                break;
            case VAR3:
                int random = new Random().nextInt(2);
                switch (random) {
                    case 0:
                        test.setThirdVariantRandom(0);
                        test.setQuestion(String.join(" ", q.getQ_prefix(), q.getQ_phrase_a(), q.getQ_or(), q.getQ_phrase_b()) + "?");
                        test.setAnswer1(q.getAnswer_b());
                        test.setAnswer2(q.getAnswer_a());
                        break;
                    case 1:
                        test.setThirdVariantRandom(1);
                        test.setQuestion(String.join(" ", q.getQ_prefix(), q.getQ_phrase_b(), q.getQ_or(), q.getQ_phrase_a()) + "?");
                        test.setAnswer1(q.getAnswer_a());
                        test.setAnswer2(q.getAnswer_b());
                        break;
                }
                break;
            case VAR4: {
                switch (questionNumber) {
                    case 1:
                        test.setQuestion(String.join(" ", q.getQ_prefix(), q.getQ_phrase_a(), q.getQ_or(), q.getQ_phrase_b()) + "?");
                        test.setAnswer1(q.getAnswer_a());
                        test.setAnswer2(q.getAnswer_b());
                        break;
                    case 2:
                        test.setQuestion(String.join(" ", q.getQ_prefix(), q.getQ_phrase_a(), q.getQ_or(), q.getQ_phrase_b()) + "?");
                        test.setAnswer1(q.getAnswer_a());
                        test.setAnswer2(q.getAnswer_b());
                        break;
                    case 3:
                        test.setQuestion(String.join(" ", q.getQ_prefix(), q.getQ_phrase_b(), q.getQ_or(), q.getQ_phrase_a()) + "?");
                        test.setAnswer1(q.getAnswer_b());
                        test.setAnswer2(q.getAnswer_a());
                        break;
                }
                break;
            }
        }
        return test;
    }

    public int calcTrust(int[] qAnswers, TestVariant[] thirdVariant) {
        int count = 0;
        switch (variant) {
            case VAR1: count = (qAnswers[0] == 1 ? 1 : 0) + (qAnswers[1] == 1 ? 1 : 0) + (qAnswers[2] == 1 ? 1 : 0); break;
            case VAR2: count = (qAnswers[0] == 1 ? 0 : 1) + (qAnswers[1] == 1 ? 0 : 1) + (qAnswers[2] == 1 ? 0 : 1); break;
            case VAR3:
                for (int i = 0; i < 3; i++) {
                    if (thirdVariant[i].getThirdVariantRandom() == 0) {
                        count += qAnswers[i] == 1 ? 0 : 1;
                    } else {
                        count += qAnswers[i] == 1 ? 1 : 0;
                    }
                }
                break;
            case VAR4: count = (qAnswers[0] == 1 ? 1 : 0) + (qAnswers[0] == 1 ? 1 : 0) + (qAnswers[0] == 1 ? 0 : 1); break;
        }
        return count;
    }

    public static boolean isTrust(int trustNumber) {
        return trustNumber >=2;
    }

    public static RozenbergScaleTest.VARIANT calcRozenbergVariantForNewPlayer() {
        int[] rozenbergVariantCounts = new int[4];
        List<Player> players = TheGameModel.getInstance().getActualPlayers();
        for (Player player : players) {
            rozenbergVariantCounts[player.getRozenbergNumber().getVariantNumber()-1]++;
        }
        RozenbergScaleTest.VARIANT variant = RozenbergScaleTest.VARIANT.VAR1;
        int minimum = rozenbergVariantCounts[0];
        for (int i = 1; i < 4; i++) {
            if (rozenbergVariantCounts[i] < minimum) {
                minimum = rozenbergVariantCounts[i];
                variant = RozenbergScaleTest.VARIANT.getByNumber(i + 1);
            }
        }
        return variant;
    }

    public class TestVariant {
        private String question;
        private String answer1;
        private String answer2;

        private int thirdVariantRandom;

        public String getQuestion() {
            return question;
        }

        public void setQuestion(String question) {
            this.question = question;
        }

        public String getAnswer1() {
            return answer1;
        }

        public void setAnswer1(String answer1) {
            this.answer1 = answer1;
        }

        public String getAnswer2() {
            return answer2;
        }

        public void setAnswer2(String answer2) {
            this.answer2 = answer2;
        }

        public int getThirdVariantRandom() {
            return thirdVariantRandom;
        }

        public void setThirdVariantRandom(int thirdVariantRandom) {
            this.thirdVariantRandom = thirdVariantRandom;
        }
    }
}

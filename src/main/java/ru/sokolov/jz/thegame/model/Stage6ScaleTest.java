package ru.sokolov.jz.thegame.model;

/**
 * Created by sokolov
 * Created on 12.05.2018.
 */
public class Stage6ScaleTest {
    private static final String Q = "Какое из двух утверждений больше соответствует действительности:";

    private static final String Q1_ANSWER_A = "В моей жизни было и есть многое, достойное благодарности";
    private static final String Q1_ANSWER_B = "Если бы мне пришлось перечислить всё, за что я благодарен жизни, список получился бы не очень длинным";

    private static final String Q2_ANSWER_A = "Я благодарен очень многим людям";
    private static final String Q2_ANSWER_B = "Оглядываясь вокруг, я не вижу, за что в этом мире быть благодарным";

    private static final String Q3_ANSWER_A = "С возрастом я испытываю все большую благодарность и любовь к людям и событиям своей жизни";
    private static final String Q3_ANSWER_B = "Долго придется ждать, пока я стану испытывать благодарность к кому-либо или за что-то";

    private RozenbergScaleTest.VARIANT variant;

    enum QUESTIONS {
        Q1(Q1_ANSWER_A, Q1_ANSWER_B),
        Q2(Q2_ANSWER_A, Q2_ANSWER_B),
        Q3(Q3_ANSWER_A, Q3_ANSWER_B);

        private String answer_a;
        private String answer_b;

        QUESTIONS(String answer_a, String answer_b) {
            this.answer_a = answer_a;
            this.answer_b = answer_b;
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

    public Stage6ScaleTest(RozenbergScaleTest.VARIANT variant) {
        this.variant = variant;
    }

    public TestVariant getQPair(int questionNumber) {
        QUESTIONS q = QUESTIONS.getByNumber(questionNumber);
        TestVariant test = new TestVariant();
        switch (variant) {
            case VAR1:
                test.setQuestion(Q);
                test.setAnswer1(q.getAnswer_a());
                test.setAnswer2(q.getAnswer_b());
                break;
            case VAR2:
                test.setQuestion(Q);
                test.setAnswer1(q.getAnswer_b());
                test.setAnswer2(q.getAnswer_a());
                break;
            case VAR3:
                switch (questionNumber) {
                    case 1:
                        test.setQuestion(Q);
                        test.setAnswer1(q.getAnswer_a());
                        test.setAnswer2(q.getAnswer_b());
                        break;
                    case 2:
                        test.setQuestion(Q);
                        test.setAnswer1(q.getAnswer_b());
                        test.setAnswer2(q.getAnswer_a());
                        break;
                    case 3:
                        test.setQuestion(Q);
                        test.setAnswer1(q.getAnswer_a());
                        test.setAnswer2(q.getAnswer_b());
                        break;
                }
                break;
            case VAR4: {
                switch (questionNumber) {
                    case 1:
                        test.setQuestion(Q);
                        test.setAnswer1(q.getAnswer_b());
                        test.setAnswer2(q.getAnswer_a());
                        break;
                    case 2:
                        test.setQuestion(Q);
                        test.setAnswer1(q.getAnswer_a());
                        test.setAnswer2(q.getAnswer_b());
                        break;
                    case 3:
                        test.setQuestion(Q);
                        test.setAnswer1(q.getAnswer_b());
                        test.setAnswer2(q.getAnswer_a());
                        break;
                }
                break;
            }
        }
        return test;
    }

    public int calcGratitude(int[] qAnswers) {
        int count = 0;
        switch (variant) {
            case VAR1: count = (qAnswers[0] == 1 ? 1 : 0) + (qAnswers[1] == 1 ? 1 : 0) + (qAnswers[2] == 1 ? 1 : 0); break;
            case VAR2: count = (qAnswers[0] == 1 ? 0 : 1) + (qAnswers[1] == 1 ? 0 : 1) + (qAnswers[2] == 1 ? 0 : 1); break;
            case VAR3: count = (qAnswers[0] == 1 ? 1 : 0) + (qAnswers[1] == 1 ? 0 : 1) + (qAnswers[2] == 1 ? 1 : 0); break;
            case VAR4: count = (qAnswers[0] == 1 ? 0 : 1) + (qAnswers[1] == 1 ? 1 : 0) + (qAnswers[2] == 1 ? 0 : 1); break;
        }
        return count;
    }

    public class TestVariant {
        private String question;
        private String answer1;
        private String answer2;

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
    }
}

package ru.sokolov.jz.thegame.servlet;

import ru.sokolov.jz.thegame.entities.Player;
import ru.sokolov.jz.thegame.model.RozenbergScaleTest;
import ru.sokolov.jz.thegame.model.Stage3Model;
import ru.sokolov.jz.thegame.model.Stage6ScaleTest;
import ru.sokolov.jz.thegame.model.TheGameModel;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

/**
 * Created by sokolov
 * Created on 12.04.2018.
 */
@WebServlet(name = "PlayServlet", urlPatterns = "/play")
public class PlayServlet extends HttpServlet {
    public static final String STAGE1_INSTRUCTION = "stage1";
    public static final String STAGE2_ROZENBERG = "stage2";//Шкала Розенберга
    public static final String STAGE3 = "stage3";//ИГРА (доверяющий)
    public static final String STAGE4 = "stage4";// суперигра (доверяющий)
    public static final String STAGE5 = "stage5";// опросник (доверяющий)
    public static final String STAGE6 = "stage6";// Не Розенберг
    public static final String STAGE7 = "stage7";// ИГРА (благодарящий)
    public static final String STAGE7_SUPER = STAGE7 + "_super";// суперигра (благодарящий)
    public static final String STAGE8 = "stage8";// опросник (благодарящий)
    public static final String STAGE9 = "stage9";// паспортичка
    public static final String STAGE10 = "stage10";// слономух
    public static final String STAGE11 = "stage11";// рейтинг

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path;
        HttpSession session = request.getSession();
        if (session.getAttribute("user_login") == null || ((String) session.getAttribute("user_login")).trim().length() == 0) {
            path = "/login";
        } else {
            path = "views/play.jsp";
            if (session.getAttribute("player") == null) {
                Player player = TheGameModel.getInstance().createPlayer((String) session.getAttribute("user_login"));
                session.setAttribute("player", player);
            }
            String step = (String) session.getAttribute("step");
            if (step == null || step.trim().equals("")) {
                step = STAGE1_INSTRUCTION;
                session.setAttribute("step", step);
            }
            String stepPassed = request.getParameter(step + "_passed");
            if ("true".equals(stepPassed)) {
                postStep(step, request);
                if ("final".equals(request.getSession().getAttribute("step"))) {
                    path = "/final";
                }
            }
        }
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(path);
        requestDispatcher.forward(request, response);
    }

    private void postStep(String step, HttpServletRequest request) {
        Player player = (Player) request.getSession().getAttribute("player");
        switch (step) {
            case STAGE1_INSTRUCTION:
                boolean alreadyPlayed = Boolean.parseBoolean(request.getParameter("alreadyPlayed"));
                player.setAlreadyPlayed(alreadyPlayed);
                request.getSession().setAttribute("step", STAGE2_ROZENBERG);
                break;
            case STAGE2_ROZENBERG:
                int q1Answer = Integer.parseInt(request.getParameter("roz_q1"));
                int q2Answer = Integer.parseInt(request.getParameter("roz_q2"));
                int q3Answer = Integer.parseInt(request.getParameter("roz_q3"));
                int trustNumber = new RozenbergScaleTest(player.getRozenbergNumber()).calcTrust(new int[] {q1Answer, q2Answer, q3Answer}, player.getRozenbergVariant3());
                player.setRozTrustNumber(trustNumber);
                player.setRozTrust(RozenbergScaleTest.isTrust(trustNumber));
                Stage3Model.VARIANT stage3variant = Stage3Model.calcStage3VariantForNewPlayer(player);
                player.setStage3variant(stage3variant);
                request.getSession().setAttribute("step", STAGE3);
                break;
            case STAGE3:
                request.getSession().setAttribute("step", STAGE3 + "_1");
                break;
            case STAGE3 + "_1":
                stage3BetHandler(1, request, player);
                request.getSession().setAttribute("step", STAGE3 + "_1_after");
                break;
            case STAGE3 + "_1_after":
                stage3AfterBetHandler(1, step, request, player);
                request.getSession().setAttribute("step", STAGE3 + "_2");
                break;
            case STAGE3 + "_2":
                stage3BetHandler(2, request, player);
                request.getSession().setAttribute("step", STAGE3 + "_2_after");
                break;
            case STAGE3 + "_2_after":
                stage3AfterBetHandler(2, step, request, player);
                request.getSession().setAttribute("step", STAGE3 + "_3");
                break;
            case STAGE3 + "_3":
                stage3BetHandler(3, request, player);
                request.getSession().setAttribute("step", STAGE3 + "_3_after");
                break;
            case STAGE3 + "_3_after":
                stage3AfterBetHandler(3, step, request, player);
                request.getSession().setAttribute("step", STAGE3 + "_4");
                break;
            case STAGE3 + "_4":
                stage3BetHandler(4, request, player);
                request.getSession().setAttribute("step", STAGE3 + "_4_after");
                break;
            case STAGE3 + "_4_after":
                stage3AfterBetHandler(4, step, request, player);
                request.getSession().setAttribute("step", STAGE3 + "_5");
                break;
            case STAGE3 + "_5":
                stage3BetHandler(5, request, player);
                request.getSession().setAttribute("step", STAGE3 + "_5_after");
                break;
            case STAGE3 + "_5_after":
                stage3AfterBetHandler(5, step, request, player);
                request.getSession().setAttribute("step", STAGE4 + "_before");
                break;
            case STAGE4 + "_before":
                if (PlayServlet.getPlayerWallet(step, player) > 0) {
                    boolean wantStage4 = Boolean.parseBoolean(request.getParameter(step + "_q"));
                    player.setWantStage4(wantStage4);
                    if (wantStage4) {
                        request.getSession().setAttribute("step", STAGE4);
                    } else {
                        request.getSession().setAttribute("step", STAGE5);
                    }
                } else {
                    request.getSession().setAttribute("step", STAGE5);
                }
                break;
            case STAGE4:
                delay(1000);
                request.getSession().setAttribute("step", STAGE4 + "_after1");
                break;
            case STAGE4 + "_after1":
                randomDelay();
                int random = new Random().nextInt(101);
                int result = player.getStage3Wallet()*3*random / 100;
                player.setStage4Wallet(result);
                request.getSession().setAttribute("step", STAGE4 + "_after2");
                break;
            case STAGE4 + "_after2":
                int afterStage4q = Integer.parseInt(request.getParameter(step + "_q"));
                player.setStage4q(afterStage4q);
                request.getSession().setAttribute("step", STAGE5);
                break;
            case STAGE5:
                String stage5q1 = request.getParameter("stage5_1");
                String stage5q2 = request.getParameter("stage5_2");
                String stage5q3 = request.getParameter("stage5_3");
                String stage5q4 = request.getParameter("stage5_4");
                String stage5q5 = request.getParameter("stage5_5");
                String stage5q6 = request.getParameter("stage5_6");
                player.setStage5q(new int[]{
                        Integer.parseInt(stage5q1), Integer.parseInt(stage5q2), Integer.parseInt(stage5q3),
                        Integer.parseInt(stage5q4), Integer.parseInt(stage5q5), Integer.parseInt(stage5q6)}
                );
                if (player.isWantStage4()) {
                    player.setStage7Wallet(player.getStage4Wallet());
                } else {
                    player.setStage7Wallet(player.getStage3Wallet());
                }
                request.getSession().setAttribute("step", STAGE6 + "_before");
                break;
            case STAGE6 + "_before":
                request.getSession().setAttribute("step", STAGE6);
                break;
            case STAGE6:
                q1Answer = Integer.parseInt(request.getParameter(STAGE6 + "_q1"));
                q2Answer = Integer.parseInt(request.getParameter(STAGE6 + "_q2"));
                q3Answer = Integer.parseInt(request.getParameter(STAGE6 + "_q3"));
                int gratitudeNumber = new Stage6ScaleTest(player.getRozenbergNumber()).calcGratitude(new int[]{q1Answer, q2Answer, q3Answer});
                player.setStage6GratitudeNumber(gratitudeNumber);
                request.getSession().setAttribute("step", STAGE7);
                break;
            case STAGE7:
                request.getSession().setAttribute("step", STAGE7 + "_wait_1");
                break;
            case STAGE7 + "_wait_1":
                // перед раундом: определяем ставку компьютера, *3 и передаем игроку
                stage7WaitHandler(1, request, player);
                request.getSession().setAttribute("step", STAGE7 + "_return_1");
                break;
            case STAGE7 + "_return_1":
                // после раунда: получаем от игрока возврат и записываем
                stage7ReturnHandler(1, request, player);
                request.getSession().setAttribute("step", STAGE7 + "_wait_2");
                break;
            case STAGE7 + "_wait_2":
                // перед раундом: определяем ставку компьютера, *3 и передаем игроку
                stage7WaitHandler(2, request, player);
                request.getSession().setAttribute("step", STAGE7 + "_return_2");
                break;
            case STAGE7 + "_return_2":
                // после раунда: получаем от игрока возврат и записываем
                stage7ReturnHandler(2, request, player);
                request.getSession().setAttribute("step", STAGE7 + "_wait_3");
                break;
            case STAGE7 + "_wait_3":
                // перед раундом: определяем ставку компьютера, *3 и передаем игроку
                stage7WaitHandler(3, request, player);
                request.getSession().setAttribute("step", STAGE7 + "_return_3");
                break;
            case STAGE7 + "_return_3":
                // после раунда: получаем от игрока возврат и записываем
                stage7ReturnHandler(3, request, player);
                request.getSession().setAttribute("step", STAGE7 + "_wait_4");
                break;
            case STAGE7 + "_wait_4":
                // перед раундом: определяем ставку компьютера, *3 и передаем игроку
                stage7WaitHandler(4, request, player);
                request.getSession().setAttribute("step", STAGE7 + "_return_4");
                break;
            case STAGE7 + "_return_4":
                // после раунда: получаем от игрока возврат и записываем
                stage7ReturnHandler(4, request, player);
                request.getSession().setAttribute("step", STAGE7 + "_wait_5");
                break;
            case STAGE7 + "_wait_5":
                // перед раундом: определяем ставку компьютера, *3 и передаем игроку
                stage7WaitHandler(5, request, player);
                request.getSession().setAttribute("step", STAGE7 + "_return_5");
                break;
            case STAGE7 + "_return_5":
                // после раунда: получаем от игрока возврат и записываем
                stage7ReturnHandler(5, request, player);
                request.getSession().setAttribute("step", STAGE7_SUPER);
                break;
            case STAGE7_SUPER:
                int returnValue = Integer.parseInt(request.getParameter(step + "_return"));
                player.setStage7SuperReturn(returnValue);
                request.getSession().setAttribute("step", STAGE7_SUPER + "_wait");
                break;
            case STAGE7_SUPER + "_wait":
                int bet = player.isWantStage4() ? player.getStage3Wallet() : 0;
                player.setStagesResultWallet(player.getStage7Wallet() + bet - player.getStage7SuperReturn());
                randomDelay();
                request.getSession().setAttribute("step", STAGE7_SUPER + "_result");
                break;
            case STAGE7_SUPER + "_result":
                request.getSession().setAttribute("step", STAGE8);
                break;
            case STAGE8:
                String stage8q1 = request.getParameter("stage8_1");
                String stage8q2 = request.getParameter("stage8_2");
                String stage8q3 = request.getParameter("stage8_3");
                String stage8q4 = request.getParameter("stage8_4");
                String stage8q5 = request.getParameter("stage8_5");
                String stage8q6 = request.getParameter("stage8_6");
                player.setStage8q(new int[]{
                        Integer.parseInt(stage8q1), Integer.parseInt(stage8q2), Integer.parseInt(stage8q3),
                        Integer.parseInt(stage8q4), Integer.parseInt(stage8q5), Integer.parseInt(stage8q6)}
                );
                request.getSession().setAttribute("step", STAGE8 + "_after");
                break;
            case STAGE8 + "_after":
                boolean markedAccordance = Boolean.parseBoolean(request.getParameter(step + "_q"));
                player.setMarkedAccordance(markedAccordance);
                if (player.isAlreadyPlayed() && player.getUser().getCount() > 0) {
                    request.getSession().setAttribute("step", STAGE10);
                } else {
                    request.getSession().setAttribute("step", STAGE9);
                }
                break;
            case STAGE9:
                String sexValue = request.getParameter("stage9_sex");
                String ageValue = request.getParameter("stage9_age");
                String incomeValue = request.getParameter("stage9_income");
                player.getUser().setSex(Integer.parseInt(sexValue));
                player.getUser().setAge(Integer.parseInt(ageValue));
                player.getUser().setIncome(Integer.parseInt(incomeValue));
                request.getSession().setAttribute("step", STAGE10);
                break;
            case STAGE10:
                q1Answer = Integer.parseInt(request.getParameter(STAGE10 + "_q1"));
                q2Answer = Integer.parseInt(request.getParameter(STAGE10 + "_q2"));
                q3Answer = Integer.parseInt(request.getParameter(STAGE10 + "_q3"));
                int q4Answer = Integer.parseInt(request.getParameter(STAGE10 + "_q4"));
                player.setStage10q(new int[] {q1Answer, q2Answer, q3Answer, q4Answer});
                player.setRatingBetsSum(getBetsSum(player));
                player.setRatingBetsPercent(getBetsPercent(player));
                player.setRatingReturnsSum(getReturnsSum(player));
                player.setRatingReturnsPercent(getReturnsPercent(player));
                player.setRatingSuperSum(getSuperSums(player));
                TheGameModel.getInstance().storePlayer(player);
                request.getSession().setAttribute("step", STAGE11);
                break;
            case STAGE11:
                request.getSession().setAttribute("step", "final");
                break;
        }
    }

    private void randomDelay() {
        int random = new Random().nextInt(14);
        int delay = (random + 5)*1000;
        delay(delay);
    }

    private void delay(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void stage3BetHandler(int betNumber, HttpServletRequest request, Player player) {
        int bet = Integer.parseInt(request.getParameter(STAGE3 + "_" + betNumber + "_bet"));
        if (bet == 0) {
            delay(1000);
        } else {
            randomDelay();
        }
        player.setStage3bet(bet, betNumber - 1);
        player.setStage3betAsPercent(player.getStage3Wallet() != 0 ? bet * 100 / player.getStage3Wallet() : 0, betNumber - 1);
        double percent = player.getStage3variant().getPercent(betNumber);
        int returnValue = (int) (bet*3*percent);
        player.setStage3return(returnValue, betNumber - 1);
        player.setStage3Wallet(player.getStage3Wallet() - bet + returnValue);
    }

    private void stage3AfterBetHandler(int betNumber, String step, HttpServletRequest request, Player player) {
        int afterBetQ = Integer.parseInt(request.getParameter(step + "_q"));
        player.setStage3afterBetQ(afterBetQ, betNumber - 1);
    }

    // перед раундом: определяем ставку компьютера, *3, добавляем в кошелек и передаем игроку
    private void stage7WaitHandler(int roundNumber, HttpServletRequest request, Player player) {
        int compBet = player.getStage3bet(roundNumber - 1);
        player.setStage7Wallet(player.getStage7Wallet() + 3 * compBet);
        randomDelay();
    }

    // после раунда: получаем от игрока возврат и записываем
    private void stage7ReturnHandler(int roundNumber, HttpServletRequest request, Player player) {
        int returnValue = Integer.parseInt(request.getParameter(STAGE7 + "_" + roundNumber + "_return"));
        player.setStage7return(returnValue, roundNumber - 1);
        player.setStage7Wallet(player.getStage7Wallet() - returnValue);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    public static Integer getPlayerWallet(String step, Player player) {
        switch (step) {
            case STAGE1_INSTRUCTION:
                break;
            case STAGE2_ROZENBERG:
                break;
            case STAGE3:
                break;
            case STAGE4 + "_before":
                return player.getStage3Wallet();
            case STAGE4:
                return player.isWantStage4() ? player.getStage4Wallet() : player.getStage3Wallet();
            case STAGE4 + "_after1":
                return player.getStage4Wallet();
            case STAGE4 + "_after2":
                return player.getStage4Wallet();
            case STAGE5:
                return player.isWantStage4() ? player.getStage4Wallet() : player.getStage3Wallet();
            case STAGE6 + "_before":
                return player.getStage7Wallet();
            case STAGE6:
                return player.getStage7Wallet();
            case STAGE7:
                return player.getStage7Wallet();
            case STAGE7 + "_super":
                return player.getStage7Wallet();
            case STAGE7 + "_super_wait":
                return player.getStage7Wallet();
            case STAGE7 + "_super_result":
                return player.getStagesResultWallet();
            case STAGE8:
                return player.getStagesResultWallet();
            case STAGE8 + "_after":
                return player.getStagesResultWallet();
            case STAGE9:
                return player.getStagesResultWallet();
            default: if (step.startsWith(STAGE3)) {
                        return player.getStage3Wallet();
                    } else if (step.startsWith(STAGE7 + "_wait") || step.startsWith(STAGE7 + "_return")) {
                return player.getStage7Wallet();
            }
        }
        return null;
    }

    public static String getCreditSpelling(int count) {
        int lastDigit = count % 10;
        if (lastDigit == 0 || lastDigit >= 5) {
            return count + " баллов (рублей)";
        } else if (lastDigit == 1) {
            return count + " балл (рубль)";
        } else {
            return count + " балла (рубля)";
        }
    }

    // Сумма ставок
    public static int getBetsSum(Player player) {
        int betsSum = 0;
        for (int bet : player.getStage3bets()) {
            betsSum += bet;
        }
        return betsSum;
    }

    // Средний процент ставок от суммы кошелька
    public static int getBetsPercent(Player player) {
        int betsPercent = 0;
        for (int percent : player.getStage3betsAsPercent()) {
            betsPercent += percent;
        }
        return betsPercent / 5;
    }

    // Сумма благодарностей
    public static int getReturnsSum(Player player) {
        int returnsSum = 0;
        for (int ret : player.getStage7returns()) {
            returnsSum += ret;
        }
        return returnsSum;
    }

    // Средний процент благодарности от ставки партнера
    public static int getReturnsPercent(Player player) {
        int percent = 0;
        for (int i = 0; i < player.getStage7returns().length; i++) {
            percent += (player.getStage3bet(i) != 0 ? player.getStage7return(i) * 100 / (player.getStage3bet(i) * 3) : 0);
        }
        return percent/5;
    }

    public static int getSuperSums(Player player) {
        return (player.isWantStage4() ? player.getStage3Wallet() : 0) + player.getStage7SuperReturn();
    }

    public static class TotalData {
        List<Player> allPlayers;
        Player currentPlayer;

        public TotalData(Player player) {
            this.allPlayers = TheGameModel.getInstance().getAllPlayers();
            currentPlayer = player;
        }

        public int getBetsSumRatingPosition(int betsSum) {
            List<Player> tempList = new ArrayList<>(allPlayers);
            tempList.sort(new Comparator<Player>() {
                @Override
                public int compare(Player p1, Player p2) {
                    return compareToSort(p1.getRatingBetsSum(), p2.getRatingBetsSum());
                }
            });
            int rating = 1;
            for (Player player : tempList) {
                if (!player.getTimestamp().equals(currentPlayer.getTimestamp())) {
                    if (player.getRatingBetsSum() > betsSum) {
                        rating++;
                    } else {
                        break;
                    }
                }
            }
            return rating;
        }

        public int getBetsPercentRatingPosition(int betsPercent) {
            List<Player> tempList = new ArrayList<>(allPlayers);
            tempList.sort(new Comparator<Player>() {
                @Override
                public int compare(Player p1, Player p2) {
                    return compareToSort(p1.getRatingBetsPercent(), p2.getRatingBetsPercent());
                }
            });
            int rating = 1;
            for (Player player : tempList) {
                if (!player.getTimestamp().equals(currentPlayer.getTimestamp())) {
                    if (player.getRatingBetsPercent() > betsPercent) {
                        rating++;
                    } else {
                        break;
                    }
                }
            }
            return rating;
        }

        public int getReturnsSumRatingPosition(int returnsSum) {
            List<Player> tempList = new ArrayList<>(allPlayers);
            tempList.sort(new Comparator<Player>() {
                @Override
                public int compare(Player p1, Player p2) {
                    return compareToSort(p1.getRatingReturnsSum(), p2.getRatingReturnsSum());
                }
            });
            int rating = 1;
            for (Player player : tempList) {
                if (!player.getTimestamp().equals(currentPlayer.getTimestamp())) {
                    if (player.getRatingReturnsSum() > returnsSum) {
                        rating++;
                    } else {
                        break;
                    }
                }
            }
            return rating;
        }

        public int getReturnsPercentRatingPosition(int returnsPercent) {
            List<Player> tempList = new ArrayList<>(allPlayers);
            tempList.sort(new Comparator<Player>() {
                @Override
                public int compare(Player p1, Player p2) {
                    return compareToSort(p1.getRatingReturnsPercent(), p2.getRatingReturnsPercent());
                }
            });
            int rating = 1;
            for (Player player : tempList) {
                if (!player.getTimestamp().equals(currentPlayer.getTimestamp())) {
                    if (player.getRatingReturnsPercent() > returnsPercent) {
                        rating++;
                    } else {
                        break;
                    }
                }
            }
            return rating;
        }

        public int getResultWalletRatingPosition(int stagesResultWallet) {
            List<Player> tempList = new ArrayList<>(allPlayers);
            tempList.sort(new Comparator<Player>() {
                @Override
                public int compare(Player p1, Player p2) {
                    return compareToSort(p1.getStagesResultWallet(), p2.getStagesResultWallet());
                }
            });
            int rating = 1;
            for (Player player : tempList) {
                if (!player.getTimestamp().equals(currentPlayer.getTimestamp())) {
                    if (player.getStagesResultWallet() > stagesResultWallet) {
                        rating++;
                    } else {
                        break;
                    }
                }
            }
            return rating;
        }

        public int getSuperSumsRatingPosition(int sum) {
            List<Player> tempList = new ArrayList<>(allPlayers);
            tempList.sort(new Comparator<Player>() {
                @Override
                public int compare(Player p1, Player p2) {
                    return compareToSort(p1.getRatingSuperSum(), p2.getRatingSuperSum());
                }
            });
            int rating = 1;
            for (Player player : tempList) {
                if (!player.getTimestamp().equals(currentPlayer.getTimestamp())) {
                    if (player.getRatingSuperSum() > sum) {
                        rating++;
                    } else {
                        break;
                    }
                }
            }
            return rating;
        }

        private int compareToSort(int var1, int var2) {
            if (var1 > var2) {
                return -1;
            } else if (var1 == var2) {
                return 0;
            } else {
                return 1;
            }
        }

        public String[][] getWalletTopUsers() {
            String[][] top = new String[5][2];
            List<Player> tempList = new ArrayList<>(allPlayers);
            tempList.sort(new Comparator<Player>() {
                @Override
                public int compare(Player p1, Player p2) {
                    return compareToSort(p1.getStagesResultWallet(), p2.getStagesResultWallet());
                }
            });
            int counter = 0;
            for (Player player : tempList) {
                if (counter < 5) {
                    String login = player.getUser().getLogin();
                    top[counter][0] = login;
                    top[counter++][1] = String.valueOf(player.getStagesResultWallet());
                }
            }
            return top;
        }

        public String[][] getTrustTopUsers() {
            String[][] top = new String[5][2];
            List<Player> tempList = new ArrayList<>(allPlayers);
            tempList.sort(new Comparator<Player>() {
                @Override
                public int compare(Player p1, Player p2) {
                    return compareToSort(p1.getRatingBetsPercent(), p2.getRatingBetsPercent());
                }
            });
            int counter = 0;
            for (Player player : tempList) {
                if (counter < 5) {
                    String login = player.getUser().getLogin();
                    top[counter][0] = login;
                    top[counter++][1] = String.valueOf(player.getRatingBetsPercent());
                }
            }
            return top;
        }

        public String[][] getGratitudeTopUsers() {
            String[][] top = new String[5][2];
            List<Player> tempList = new ArrayList<>(allPlayers);
            tempList.sort(new Comparator<Player>() {
                @Override
                public int compare(Player p1, Player p2) {
                    return compareToSort(p1.getRatingReturnsPercent(), p2.getRatingReturnsPercent());
                }
            });
            int counter = 0;
            for (Player player : tempList) {
                if (counter < 5) {
                    String login = player.getUser().getLogin();
                    top[counter][0] = login;
                    top[counter++][1] = String.valueOf(player.getRatingReturnsPercent());
                }
            }
            return top;
        }

        public String[][] getRiskTopUsers() {
            String[][] top = new String[5][2];
            List<Player> tempList = new ArrayList<>(allPlayers);
            tempList.sort(new Comparator<Player>() {
                @Override
                public int compare(Player p1, Player p2) {
                    return compareToSort(p1.getRatingSuperSum(), p2.getRatingSuperSum());
                }
            });
            int counter = 0;
            for (Player player : tempList) {
                if (counter < 5) {
                    String login = player.getUser().getLogin();
                    top[counter][0] = login;
                    top[counter++][1] = String.valueOf(player.getRatingSuperSum());
                }
            }
            return top;
        }
    }
}

package ru.sokolov.jz.thegame.entities;

import ru.sokolov.jz.thegame.model.RozenbergScaleTest;
import ru.sokolov.jz.thegame.model.Stage3Model;

/**
 * Created by sokolov
 * Created on 24.04.2018.
 */
public class Player {
    private String timestamp;
    private User user;

    private boolean alreadyPlayed;// уже играл?
    private RozenbergScaleTest.VARIANT rozenbergNumber;
    private RozenbergScaleTest.TestVariant[] rozenbergVariant3;
    private boolean rozTrust;//Trust
    private int rozTrustNumber;//Trust number
    private int stage6GratitudeNumber;//Gratitude number

    private Stage3Model.VARIANT stage3variant;//восходящий/нисходящий/рандомный процент

    private int[] stage3bets = new int[5];// ставки игры этапа 3
    private int[] stage3betsAsPercent = new int[5];// ставки игры этапа 3 как процент от суммы кошелька на текущий момент
    private int[] stage3returns = new int[5];// ставки игры этапа 3
    private int[] stage3afterBetQ = new int[5];// ответ на вопрос об ожидании после раундов этапа 3 (0 - Больше, 1 - Затрудняюсь, 2 - Меньше)
    private int stage3Wallet = 5000;// кошелек с начала игры и до 1-й суперигры
    private boolean wantStage4;// хочет суперигру?
    private int stage4q;// вопрос после суперигры этапа 3
    private int stage4Wallet;// кошелек после 1-й суперигры
    private int[] stage5q = new int[6];// ответы на оценку рещения после этапа 3
    private int[] stage7returns = new int[5];// возраты игры этапа 7
    private int stage7Wallet;//кошелек перед 2-й суперигрой
    private int stage7SuperReturn;// ставка во 2-й суперигре
    private int stagesResultWallet;//кошелек в конце игры
    private int[] stage8q = new int[6];// ответы на оценку решения после этапа 7
    private int[] stage10q = new int[4];// ответы на Слономуха
    private boolean markedAccordance;// заметили ли соответствие

    private int[][] slonomuh = new int[4][4];// порядок предъявления Слономуха

    private int ratingBetsSum;
    private int ratingBetsPercent;
    private int ratingReturnsSum;
    private int ratingReturnsPercent;
    private int ratingSuperSum;

    public Player() {
    }

    public Player(User user) {
        this.user = user;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public RozenbergScaleTest.VARIANT getRozenbergNumber() {
        return rozenbergNumber;
    }

    public RozenbergScaleTest.TestVariant[] getRozenbergVariant3() {
        return rozenbergVariant3;
    }

    public void setRozenbergVariant3(RozenbergScaleTest.TestVariant[] rozenbergVariant3) {
        this.rozenbergVariant3 = rozenbergVariant3;
    }

    public boolean isRozTrust() {
        return rozTrust;
    }

    public void setRozTrust(boolean rozTrust) {
        this.rozTrust = rozTrust;
    }

    public int getRozTrustNumber() {
        return rozTrustNumber;
    }

    public void setRozTrustNumber(int rozTrustNumber) {
        this.rozTrustNumber = rozTrustNumber;
    }

    public int getStage6GratitudeNumber() {
        return stage6GratitudeNumber;
    }

    public void setStage6GratitudeNumber(int stage6GratitudeNumber) {
        this.stage6GratitudeNumber = stage6GratitudeNumber;
    }

    public Stage3Model.VARIANT getStage3variant() {
        return stage3variant;
    }

    public void setStage3variant(Stage3Model.VARIANT stage3variant) {
        this.stage3variant = stage3variant;
    }

    public boolean isAlreadyPlayed() {
        return alreadyPlayed;
    }

    public void setAlreadyPlayed(boolean alreadyPlayed) {
        this.alreadyPlayed = alreadyPlayed;
    }

    public int getStage3bet(int index) {
        return stage3bets[index];
    }

    public void setStage3bet(int stage3bet, int index) {
        this.stage3bets[index] = stage3bet;
    }

    public int[] getStage3bets() {
        return stage3bets;
    }

    public void setStage3bets(int[] stage3bets) {
        this.stage3bets = stage3bets;
    }

    public int getStage3betAsPercent(int index) {
        return stage3betsAsPercent[index];
    }

    public void setStage3betAsPercent(int stage3betAsPercent, int index) {
        this.stage3betsAsPercent[index] = stage3betAsPercent;
    }

    public int[] getStage3betsAsPercent() {
        return stage3betsAsPercent;
    }

    public void setStage3betsAsPercent(int[] stage3betsAsPercent) {
        this.stage3betsAsPercent = stage3betsAsPercent;
    }

    public int getStage3return(int index) {
        return stage3returns[index];
    }

    public void setStage3return(int stage3return, int index) {
        this.stage3returns[index] = stage3return;
    }

    public int[] getStage3returns() {
        return stage3returns;
    }

    public void setStage3returns(int[] stage3returns) {
        this.stage3returns = stage3returns;
    }

    public int getStage3afterBetQ(int index) {
        return stage3afterBetQ[index];
    }

    public void setStage3afterBetQ(int stage3afterBetQ, int index) {
        this.stage3afterBetQ[index] = stage3afterBetQ;
    }

    public int[] getStage3afterBetQ() {
        return stage3afterBetQ;
    }

    public void setStage3afterBetQ(int[] stage3afterBetQ) {
        this.stage3afterBetQ = stage3afterBetQ;
    }

    public int getStage3Wallet() {
        return stage3Wallet;
    }

    public void setStage3Wallet(int stage3Wallet) {
        this.stage3Wallet = stage3Wallet;
    }

    public boolean isWantStage4() {
        return wantStage4;
    }

    public void setWantStage4(boolean wantStage4) {
        this.wantStage4 = wantStage4;
    }

    public int getStage4q() {
        return stage4q;
    }

    public void setStage4q(int stage4q) {
        this.stage4q = stage4q;
    }

    public int getStage4Wallet() {
        return stage4Wallet;
    }

    public void setStage4Wallet(int stage4Wallet) {
        this.stage4Wallet = stage4Wallet;
    }

    public int getStage5q(int index) {
        return stage5q[index];
    }

    public void setStage5q(int stage5q, int index) {
        this.stage5q[index] = stage5q;
    }

    public int[] getStage5q() {
        return stage5q;
    }

    public void setStage5q(int[] stage5q) {
        this.stage5q = stage5q;
    }

    public int[] getStage7returns() {
        return stage7returns;
    }

    public int getStage7return(int index) {
        return stage7returns[index];
    }

    public void setStage7returns(int[] stage7returns) {
        this.stage7returns = stage7returns;
    }

    public void setStage7return(int stage7return, int index) {
        this.stage7returns[index] = stage7return;
    }

    public int getStage7Wallet() {
        return stage7Wallet;
    }

    public void setStage7Wallet(int stage7Wallet) {
        this.stage7Wallet = stage7Wallet;
    }

    public int getStage7SuperReturn() {
        return stage7SuperReturn;
    }

    public void setStage7SuperReturn(int stage7SuperReturn) {
        this.stage7SuperReturn = stage7SuperReturn;
    }

    public int getStage8q(int index) {
        return stage8q[index];
    }

    public void setStage8q(int stage8q, int index) {
        this.stage8q[index] = stage8q;
    }

    public int[] getStage8q() {
        return stage8q;
    }

    public void setStage8q(int[] stage8q) {
        this.stage8q = stage8q;
    }

    public int getStage10q(int index) {
        return stage10q[index];
    }

    public void setStage10q(int stage10q, int index) {
        this.stage10q[index] = stage10q;
    }

    public int[] getStage10q() {
        return stage10q;
    }

    public void setStage10q(int[] stage10q) {
        this.stage10q = stage10q;
    }

    public int getStagesResultWallet() {
        return stagesResultWallet;
    }

    public void setStagesResultWallet(int stagesResultWallet) {
        this.stagesResultWallet = stagesResultWallet;
    }

    public boolean isMarkedAccordance() {
        return markedAccordance;
    }

    public void setMarkedAccordance(boolean markedAccordance) {
        this.markedAccordance = markedAccordance;
    }

    public User getUser() {
        return user;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setRozenbergNumber(RozenbergScaleTest.VARIANT rozenbergNumber) {
        this.rozenbergNumber = rozenbergNumber;
    }

    public int[][] getSlonomuh() {
        return slonomuh;
    }

    public void setSlonomuh(int[][] slonomuh) {
        this.slonomuh = slonomuh;
    }

    public void setSlonomuh(int groupIndex, int phraseIndex, int value) {
        slonomuh[groupIndex][phraseIndex] = value;
    }

    public int getSlonomuh(int groupIndex, int phraseIndex) {
        return slonomuh[groupIndex][phraseIndex];
    }

    public int getRatingBetsPercent() {
        return ratingBetsPercent;
    }

    public void setRatingBetsPercent(int ratingBetsPercent) {
        this.ratingBetsPercent = ratingBetsPercent;
    }

    public int getRatingBetsSum() {
        return ratingBetsSum;
    }

    public void setRatingBetsSum(int ratingBetsSum) {
        this.ratingBetsSum = ratingBetsSum;
    }

    public int getRatingReturnsPercent() {
        return ratingReturnsPercent;
    }

    public void setRatingReturnsPercent(int ratingReturnsPercent) {
        this.ratingReturnsPercent = ratingReturnsPercent;
    }

    public int getRatingReturnsSum() {
        return ratingReturnsSum;
    }

    public void setRatingReturnsSum(int ratingReturnsSum) {
        this.ratingReturnsSum = ratingReturnsSum;
    }

    public int getRatingSuperSum() {
        return ratingSuperSum;
    }

    public void setRatingSuperSum(int ratingSuperSum) {
        this.ratingSuperSum = ratingSuperSum;
    }
}

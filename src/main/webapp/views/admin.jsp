<%@ page import="ru.sokolov.jz.thegame.servlet.PlayServlet" %>
<%@ page import="ru.sokolov.jz.thegame.model.TheGameModel" %>
<%@ page import="ru.sokolov.jz.thegame.entities.Player" %>
<%@ page import="java.util.List" %>
<%@ page import="ru.sokolov.jz.thegame.model.Stage3Model" %>
<%@ page import="ru.sokolov.jz.thegame.model.RozenbergScaleTest" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
    <meta name='viewport' content='width=device-width,initial-scale=1'/>
    <meta content='true' name='HandheldFriendly'/>
    <meta content='width' name='MobileOptimized'/>
    <meta content='yes' name='apple-mobile-web-app-capable'/>

</head>
<body>
<form action="" method="post">
    <input type=hidden name=xls>
    <input type=submit value="Данные">
</form>
<hr>
<form action="" method="post">
    <input type=hidden name=doc>
    <input type="submit" value="Описание столбцов">
</form>
<hr>
<form action="" method="post">
    Player ID: <input name=player_id required="true">
    <input type="submit" value="Рейтинг игрока">
</form>
<hr>
<%
    List<Player> allPlayers = TheGameModel.getInstance().getCompletedPlayers();
    int gamesCount = allPlayers.size();
    int[] rozenbergVariants = new int[4];
    int[] stage3Variants = new int[3];

    for (Player player : allPlayers) {
        rozenbergVariants[player.getRozenbergNumber().getVariantNumber() - 1]++;
        stage3Variants[player.getStage3variant().getNumber() - 1]++;
    }
%>
Всего игр: <%=gamesCount%>
<br>
Розенберг:
<br>
<% for (RozenbergScaleTest.VARIANT variant : RozenbergScaleTest.VARIANT.values()) {
    out.println("&nbsp;&nbsp;&nbsp;&nbsp;" + variant.name() + ": " + rozenbergVariants[variant.getVariantNumber() - 1] + "<br>");
} %>
Доверие:
<br>
<% for (Stage3Model.VARIANT variant : Stage3Model.VARIANT.values()) {
    out.println("&nbsp;&nbsp;&nbsp;&nbsp;" + variant.name() + ": " + stage3Variants[variant.getNumber() - 1] + "<br>");
} %>
</body>
</html>

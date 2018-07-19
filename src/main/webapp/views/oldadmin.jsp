<%@ page import="ru.sokolov.jz.thegame.entities.Player" %>
<%@ page import="ru.sokolov.jz.thegame.entities.User" %>
<%@ page import="ru.sokolov.jz.thegame.model.RozenbergScaleTest" %>
<%@ page import="ru.sokolov.jz.thegame.model.Stage3Model" %>
<%@ page import="ru.sokolov.jz.thegame.model.TheGameModel" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
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
<form action="" method="post" id="get_rating">
    Player ID: <input id="player_id" name=player_id required="true">
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

    List<User> users = new ArrayList<>();
    Map<User, List<Player>> uPlayers =  TheGameModel.getInstance().getUsersPlayers();
    for (User user : uPlayers.keySet()) {
        if (uPlayers.get(user).size() == 0) {
            continue;
        }
        if (!users.contains(user)) {
            users.add(user);
        }
    }
%>
<script>
    function showPlayers() {
        var playersHTML = '';
        <% for (Player player : allPlayers) { %>
            playersHTML += '<span onmouseover=\'this.style.color = "red"\' onmouseout=\'this.style.color = "black"\'><%=player.getAsDateString()%> <span onclick=\'toRating(this)\'><%=player.getTimestamp()%></span> (<%=player.getUser().getLogin()%>)</span><br>';
        <% } %>
        document.getElementById('players').innerHTML = playersHTML;
        document.getElementById('players').style.display = document.getElementById('players').style.display === 'none' ? 'block' : 'none';
    }

    function showUsers() {
        var usersHTML = '';
        <% for (User user : users) {
            if (uPlayers.get(user).size() == 0) {
                continue;
            }
         %>
            usersHTML += '<span style=\'padding-left:20px;\' onclick=\'showUPlayers("<%=user.getLogin()%>")\' onmouseover=\'this.style.color = "red"\' onmouseout=\'this.style.color = "black"\'><%=user.getLogin()%></span><br>';
            var playersHTML = '<span id=\'<%=user.getLogin()%>_players\' style=\'display: none\' class=\'hideable\'>';
            <% for (Player player : uPlayers.get(user)) { %>
                playersHTML += '<span style=\'padding-left:40px;\' onmouseover=\'this.style.color = "red"\' onmouseout=\'this.style.color = "black"\' onclick=\'toRating(this)\'><%=player.getTimestamp()%></span><br>';
            <% } %>
            playersHTML += '</span>';
            usersHTML +=playersHTML;
        <% } %>
        document.getElementById('users').innerHTML = usersHTML;
        document.getElementById('users').style.display = document.getElementById('users').style.display === 'none' ? 'block' : 'none';
    }

    function showUPlayers(login) {
        var els = document.getElementsByClassName('hideable');
        for (var i = 0; i < els.length; i++) {
            els[i].style.display = 'none';
        }
        document.getElementById(login + '_players').style.display = document.getElementById(login + '_players').style.display === 'none' ? 'block' : 'none';
    }

    function toRating(span) {
        document.getElementById('player_id').value=span.innerText;
        document.getElementById('get_rating').submit();
    }
</script>
<span onclick="showPlayers(this)" onmouseover="this.style.color = 'blue'" onmouseout="this.style.color = 'black'">Всего игр: <%=gamesCount%></span>
<div id='players' style="display:none"></div>
<br>
<span onclick="showUsers(this)" onmouseover="this.style.color = 'blue'" onmouseout="this.style.color = 'black'">Всего игроков:  <%=users.size()%></span>
<div id='users' style="display:none"></div>
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

<%@ page import="ru.sokolov.jz.thegame.servlet.PlayServlet" %>
<%@ page import="ru.sokolov.jz.thegame.entities.Player" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    Player player = (Player) session.getAttribute("player");
    String step = (String) session.getAttribute("step");
    Integer wallet = PlayServlet.getPlayerWallet(step, player);

    int betsSum = 0;
    int returnsSum = 0;
    for (int bet : player.getStage3bets()) {
        betsSum += bet;
    }

    for (int ret : player.getStage7returns()) {
        returnsSum += ret;
    }
%>
<div style='color:red;'>
    Таким образом, за 5 раундов Вам доверили суммарно <span style="font-weight:bold;"><%=PlayServlet.getCreditSpelling(betsSum)%></span> (эта сумма была утроена при передаче Вам).
    <br>Суммарно в качестве благодарности Вы вернули <span style="font-weight:bold;"><%=PlayServlet.getCreditSpelling(returnsSum)%></span>.
    <br>В Вашем кошельке сейчас <span style="font-weight:bold;"><%=PlayServlet.getCreditSpelling(PlayServlet.getPlayerWallet(step, player))%></span>.
</div>

<p class="instructions">Вам предоставляется возможность сыграть в суперигру, ставкой вашего партнера будут все его
    накопленные за игру баллы (деньги).<br>А вам предлагается заранее решить, какой суммой вы отблагодарите
    его за доверие</p>
<input type="number" name="<%=step%>_return" min="0" max="<%=wallet.intValue()%>" step="1" autofocus="true" required>
<br/>

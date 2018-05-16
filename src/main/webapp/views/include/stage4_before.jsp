<%@ page import="ru.sokolov.jz.thegame.entities.Player" %>
<%@ page import="ru.sokolov.jz.thegame.servlet.PlayServlet" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<% Player player = (Player) session.getAttribute("player");
    String step = (String) session.getAttribute("step"); 
    int betsSum = 0;
    int returnsSum = 0;
    int result = 0;
    for (int ret : player.getStage3returns()) {
        returnsSum += ret;
        result += ret;
    }
    for (int bet : player.getStage3bets()) {
        betsSum += bet;
        result -= bet;
    }
%>
<div style='color:red;'>
    Таким образом, за 5 раундов Вы доверили суммарно <span style="font-weight:bold;"><%=PlayServlet.getCreditSpelling(betsSum)%></span>
    <br>Суммарно в качестве благодарности Вам вернули <span style="font-weight:bold;"><%=PlayServlet.getCreditSpelling(returnsSum)%></span>
    <br>Итого за 5 раундов Вы <%=(result >= 0 ? "выиграли" : "проиграли") %> <span style="font-weight:bold;"><%=PlayServlet.getCreditSpelling(Math.abs(result))%></span>,
    и в Вашем кошельке сейчас <span style="font-weight:bold;"><%=PlayServlet.getCreditSpelling(PlayServlet.getPlayerWallet(step, player))%></span>
</div>

<% if (PlayServlet.getPlayerWallet(step, player) > 0) { %>
    <p class="instructions">Вам предоставляется возможность сыграть в суперигру, ставкой в которой будут <span style="font-weight:bold;font-size: larger">все</span> Ваши накопленные за игру баллы (деньги)</p>
    <br/>
    <ul>
        <li>Хотите ли сыграть?
            <ul>
                <li><input type="radio" name="<%=step%>_q" value="true">&nbsp;&nbsp;Да</li>
                <li><input type="radio" name="<%=step%>_q" value="false">&nbsp;&nbsp;Нет</li>
            </ul>
            <div id="<%=step%>_warning" style='display:none; color:red;'>Сделайте выбор</div>
        </li>
    </ul>
<% } %>
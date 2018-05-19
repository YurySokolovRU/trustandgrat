<%@ page import="ru.sokolov.jz.thegame.servlet.PlayServlet" %>
<%@ page import="ru.sokolov.jz.thegame.entities.Player" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>

<%
    Player player = (Player) session.getAttribute("player");
    String step = (String) session.getAttribute("step");

    int roundNumber = Integer.parseInt(step.substring((PlayServlet.STAGE7 + "_return_").length()));
    int compBet = player.getStage3bet(roundNumber - 1);
%>
<%--stage7_return_{n}--%>
<h1>Раунд <%=roundNumber%></h1>

<p class="instructions">Благодря тому, что ваш партнер по игре доверил Вам некоторую сумму, Вы получаете
    <span class="red"><%=PlayServlet.getCreditSpelling(3 * compBet)%></span>.
    Вы можете вознаградить Вашего партнера и передать ему от 0 до <%=PlayServlet.getCreditSpelling(player.getStage7Wallet())%>. Размер
    возвращаемой суммы определяется исключительно Вашим решением
</p>
<input type="number" style="width: 100px" name="<%=PlayServlet.STAGE7%>_<%=roundNumber%>_return" min="0" max="<%=player.getStage7Wallet()%>" step="1" autofocus="true" required>
<br/>
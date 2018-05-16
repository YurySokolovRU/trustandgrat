<%@ page import="ru.sokolov.jz.thegame.servlet.PlayServlet" %>
<%@ page import="ru.sokolov.jz.thegame.entities.Player" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>

<%
    Player player = (Player) session.getAttribute("player");
    String step = (String) session.getAttribute("step");
%>
<%--stage3_*--%>
<% int betNumber = Integer.parseInt(step.substring(PlayServlet.STAGE3.length() + 1)); %>
<h1>Раунд <%=betNumber%>
</h1>
<p class="instructions">Сделайте Вашу ставку от 0 до <%=PlayServlet.getCreditSpelling(player.getStage3Wallet())%>, исходя из того, насколько Вы
    доверяете Вашему партнеру. Сумма ставки будет увеличена нами в 3 раза и передана Вашему партнеру по
    игре. После этого партнер при его желании может вернуть Вам определенную сумму баллов (рублей). Размер
    возвращаемой суммы определяется исключительно решением Вашего партнера по игре</p>
<input type="number" name="<%=step%>_bet" min="0" max="<%=player.getStage3Wallet()%>" step="1" required autofocus="true">
<span id="<%=step%>_pause" style="color:red;display:none;">Ваш партнер принимает решение...</span>
<br>
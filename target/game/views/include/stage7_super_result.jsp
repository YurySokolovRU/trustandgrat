<%@ page import="ru.sokolov.jz.thegame.servlet.PlayServlet" %>
<%@ page import="ru.sokolov.jz.thegame.entities.Player" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    Player player = (Player) session.getAttribute("player");
%>

<% int bet = player.isWantStage4() ? player.getStage3Wallet() : 0;
    int returnValue = player.getStage7SuperReturn();
%>
<div class="red">Ваш партнер доверил Вам <%=PlayServlet.getCreditSpelling(bet)%>. Вы вернули ему <%=PlayServlet.getCreditSpelling(returnValue)%></div>
<br/>

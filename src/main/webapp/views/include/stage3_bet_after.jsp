<%@ page import="ru.sokolov.jz.thegame.servlet.PlayServlet" %>
<%@ page import="ru.sokolov.jz.thegame.entities.Player" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>

<%
    Player player = (Player) session.getAttribute("player");
    String step = (String) session.getAttribute("step");
    int roundNumber = Integer.parseInt(step.substring((PlayServlet.STAGE3 + "_").length(), (PlayServlet.STAGE3 + "_").length() + 1));
    int compReturn = player.getStage3return(roundNumber - 1);
%>
<%--stage3_{n}_after--%>
<div id="<%=step%>_return" style='color:red;'>Ваш партнер вернул вам в качестве благодарности за доверие <span style="font-weight:bold;"><%=PlayServlet.getCreditSpelling(compReturn)%></span></div>
<ul>
    <li>Можно ли сказать, что это сильно больше, чем Вы рассчитывали получить, или сильно меньше?
        <ul>
            <li><input type="radio" name="<%=step%>_q" value="2">&nbsp;&nbsp;Сильно больше, чем я рассчитывал</li>
            <li><input type="radio" name="<%=step%>_q" value="1">&nbsp;&nbsp;Сильно меньше, чем я рассчитывал</li>
            <li><input type="radio" name="<%=step%>_q" value="0">&nbsp;&nbsp;Ни один из вариантов не подходит</li>
        </ul>
        <div id="<%=step%>_warning" style='display:none; color:red;'>Сделайте выбор</div>
    </li>
</ul>
<% if (!step.contains("5")) { %>
    <p>Если вы готовы к следующему раунду, нажмите кнопку</p>
<% } %>
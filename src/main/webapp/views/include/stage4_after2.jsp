<%@ page import="ru.sokolov.jz.thegame.entities.Player" %>
<%@ page import="ru.sokolov.jz.thegame.servlet.PlayServlet" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>

<%
    Player player = (Player) session.getAttribute("player");
    String step = (String) session.getAttribute("step");
%>
<div style="color:red">Вы доверили Вашему партнеру <span style='font-weight: bold;'><%=PlayServlet.getCreditSpelling(player.getStage3Wallet())%></span></div>
<br/>
<div style="color:red">Ваш партнер вернул вам в качестве благодарности за доверие <span style='font-weight: bold;'><%=PlayServlet.getCreditSpelling(player.getStage4Wallet())%></span></div>
<br/>
<ul>
    <li>Можно ли сказать, что это сильно больше, чем Вы рассчитывали получить, или сильно меньше?
        <ul>
            <li><input type="radio" name="<%=step%>_q" value="0">&nbsp;&nbsp;Сильно больше, чем я рассчитывал</li>
            <li><input type="radio" name="<%=step%>_q" value="1">&nbsp;&nbsp;Затрудняюсь ответить</li>
            <li><input type="radio" name="<%=step%>_q" value="2">&nbsp;&nbsp;Сильно меньше, чем я рассчитывал</li>
        </ul>
        <div id="<%=step%>_warning" style='display:none; color:red;'>Сделайте выбор</div>
    </li>
</ul>
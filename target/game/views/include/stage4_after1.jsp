<%@ page import="ru.sokolov.jz.thegame.entities.Player" %>
<%@ page import="ru.sokolov.jz.thegame.servlet.PlayServlet" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>

<%
    Player player = (Player) session.getAttribute("player");
%>
<div class="red">Вы доверили Вашему партнеру <span style='font-weight: bold;'><%=PlayServlet.getCreditSpelling(player.getStage3Wallet())%></span></div>
<br/>
<div class="red">Ваш партнер принимает решение...</div>
<script>
    submitForm();
</script>
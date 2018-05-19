<%@ page import="ru.sokolov.jz.thegame.servlet.PlayServlet" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>

<%
    String step = (String) session.getAttribute("step");
%>
<%--stage7_wait_{n}--%>
<% int roundNumber = Integer.parseInt(step.substring((PlayServlet.STAGE7 + "_wait_").length())); %>
<h1>Раунд <%=roundNumber%></h1>

<span class="red">Ожидание ставки Вашего партнера. Ваш партнер принимает решение...</span>
<br/>
<script>
    submitForm();
</script>
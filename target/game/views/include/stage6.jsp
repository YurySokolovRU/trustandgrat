<%@ page import="ru.sokolov.jz.thegame.entities.Player" %>
<%@ page import="ru.sokolov.jz.thegame.model.Stage6ScaleTest" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<% Player player = (Player) session.getAttribute("player"); %>
<p class="instructions">Внимательно прочитайте три вопроса и варианты ответов к ним.<br>Выберите тот из двух вариантов
    ответов, который больше соответствует Вашему представлению о себе.</p>
<ol>
    <%
        Stage6ScaleTest bean = new Stage6ScaleTest(player.getRozenbergNumber());
        for (int i = 1; i <= 3; i++) {
            Stage6ScaleTest.TestVariant variant = bean.getQPair(i);
    %>
    <li><%=variant.getQuestion() %>
        <ul>
            <li onclick="selectInput(this)"><input type="radio" name="stage6_q<%=i%>" value="1">&nbsp;<%=variant.getAnswer1()%></li>
            <li onclick="selectInput(this)"><input type="radio" name="stage6_q<%=i%>" value="2">&nbsp;<%=variant.getAnswer2()%></li>
        </ul>
        <div id="stage6_warning<%=i%>" class="red" style='display:none;'>Сделайте выбор</div>
    </li>
    <br>
    <% } %>
</ol>
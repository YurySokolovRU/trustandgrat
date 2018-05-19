<%@ page import="ru.sokolov.jz.thegame.model.RozenbergScaleTest" %>
<%@ page import="ru.sokolov.jz.thegame.entities.Player" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<% Player player = (Player) session.getAttribute("player"); %>
<p class="instructions">Внимательно прочитайте три вопроса и варианты ответов к ним. Если Вы считаете, что какой-либо
    из двух вариантов ответов соответствует Вашему представлению о себе и других людях, выберите его.</p>
<ol>
    <%
        RozenbergScaleTest rozenberg = new RozenbergScaleTest(player.getRozenbergNumber());
        RozenbergScaleTest.TestVariant[] variants = new RozenbergScaleTest.TestVariant[3];

        for (int i = 1; i <= 3; i++) {
            RozenbergScaleTest.TestVariant variant = rozenberg.getQPair(i);
            variants[i - 1] = variant;
    %>
    <li><%=variant.getQuestion() %>
        <ul>
            <li onclick="selectInput(this)"><input type="radio" name="roz_q<%=i%>" value="1">&nbsp;<%=variant.getAnswer1()%></li>
            <li onclick="selectInput(this)"><input type="radio" name="roz_q<%=i%>" value="2">&nbsp;<%=variant.getAnswer2()%></li>
        </ul>
        <div id="rozenberg_warning<%=i%>" class="red" style='display:none;'>Сделайте выбор</div>
        <br>
    </li>
    <% }
        if (player.getRozenbergNumber() == RozenbergScaleTest.VARIANT.VAR3) {
            player.setRozenbergVariant3(variants);
        }
    %>
</ol>
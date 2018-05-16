<%@ page import="ru.sokolov.jz.thegame.model.Stage10Model" %>
<%@ page import="ru.sokolov.jz.thegame.entities.Player" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<p class="instruction">Мы подводим итоги Вашей игры, а пока просим Вас ответить еще на 4 вопроса, оценив правильность данных утверждений</p>

<ol>
    <%
        Player player = (Player) session.getAttribute("player");
        Stage10Model model = new Stage10Model();
        for (int i = 1; i <= 4; i++) {
    %>
    <li>Выберите наиболее правильное из утверждений:
        <ul>
            <% String phrase = model.getPhrase(i, 1);
                int slonomuh = Stage10Model.PHRASES.getNumberByText(phrase);
                player.setSlonomuh(i-1, 0, slonomuh); %>
            <li><input type="radio" name="stage10_q<%=i%>" value="1">&nbsp;<%=phrase%></li>
            <% phrase = model.getPhrase(i, 2);
                slonomuh = Stage10Model.PHRASES.getNumberByText(phrase);
                player.setSlonomuh(i-1, 1, slonomuh);%>
            <li><input type="radio" name="stage10_q<%=i%>" value="2">&nbsp;<%=phrase%></li>
            <% phrase = model.getPhrase(i, 3);
                slonomuh = Stage10Model.PHRASES.getNumberByText(phrase);
                player.setSlonomuh(i-1, 2, slonomuh);%>
            <li><input type="radio" name="stage10_q<%=i%>" value="3">&nbsp;<%=phrase%></li>
            <% phrase = model.getPhrase(i, 4);
                slonomuh = Stage10Model.PHRASES.getNumberByText(phrase);
                player.setSlonomuh(i-1, 3, slonomuh);%>
            <li><input type="radio" name="stage10_q<%=i%>" value="4">&nbsp;<%=phrase%></li>
        </ul>
        <div id="stage10_warning<%=i%>" style='display:none; color:red;'>Сделайте выбор</div>
    </li>
    <br>
    <% } %>
</ol>
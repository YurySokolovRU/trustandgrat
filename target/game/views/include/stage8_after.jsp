<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<% String step = (String) session.getAttribute("step"); %>

<ul>
    <li>Заметили ли Вы, что ставки Вашего партнера по игре соответствуют ставкам, которые Вы сами доверяли Вашему партнеру?
        <ul>
            <li onclick="selectInput(this)"><input type="radio" name="<%=step%>_q" value="true">&nbsp;&nbsp;Да</li>
            <li onclick="selectInput(this)"><input type="radio" name="<%=step%>_q" value="false">&nbsp;&nbsp;Нет</li>
        </ul>
        <div id="<%=step%>_q_warning" class="red" style='display:none;'>Сделайте выбор</div>
    </li>
</ul>
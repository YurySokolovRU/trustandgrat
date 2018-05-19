<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<h1>Инструкция</h1>

<p class="instructions">Вам будет предложено ответить на несколько вопросов о доверии.<br>Затем Вы начнете 10
    раундов игры: по 5 - в
    роли доверяющего и благодарящего.<br>
    По окончанию Вам зададут несколько вопросов о ваших стратегиях принятия решения.<br>Затем будет показано
    ваше место в общем рейтинге игроков с учетом количества сыгранных партий.</p>
<br/>

<ul>
    <li>Играли ли Вы уже в эту игру?
        <ul>
            <li onclick="selectInput(this)"><input type="radio" name="alreadyPlayed" value="true">&nbsp;&nbsp;Да</li>
            <li onclick="selectInput(this)"><input type="radio" name="alreadyPlayed" value="false">&nbsp;&nbsp;Нет</li>
        </ul>
        <div id="alreadyPlayed_warning" class="red" style='display:none;'>Сделайте выбор</div>
    </li>
</ul>

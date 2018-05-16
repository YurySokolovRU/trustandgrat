<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<h1>Инструкция</h1>

<p class="instructions">Вам будет предложено ответить на несколько вопросов о доверии.<br>Затем вы начнете 10
    раундов игры: по 5 - в
    роли доверяющего и благодарящего.<br>
    По окончанию вам зададут несколько вопросов о ваших стратегиях принятия решения.<br>Затем будет показано
    ваше место в общем рейтинге игроков с учетом количества сыгранных партий.</p>
<br/>

<ul>
    <li>Играли ли Вы уже в эту игру?
        <ul>
            <li><input type="radio" name="alreadyPlayed" value="true">&nbsp;&nbsp;Да</li>
            <li><input type="radio" name="alreadyPlayed" value="false">&nbsp;&nbsp;Нет</li>
        </ul>
        <div id="alreadyPlayed_warning" style='display:none; color:red;'>Сделайте выбор</div>
    </li>
</ul>

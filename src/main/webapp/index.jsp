<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
    <style>
        body {
            font-size: 20px;
        }
    </style>
</head>
<body>
<div style="width: 70%; margin: 0 auto; padding-top: 100px;">
    <h1>Рады приветствовать вас</h1>
    <p>Мы предлагаем вам принять участие в игре.<br>В каждом ее раунде вам предстоит решить
        доверять или не доверять незнакомцу выделенные ресурсы.<br>Если вы примете решение доверить, то ресурс умножится и будет
        передан партнеру.<br>Ваш партнер может принять решение вознаграждать или не вознаграждать вас за проявленное доверие.<br>В
        следующем раунде вы будете встречаться с тем же или новым партнером.<br>Вам предстоит игра как в роли доверяющего, так и
        благодарящего партнера.</p>
    <% String userLogin = (String) session.getAttribute("user_login");
        session.removeAttribute("player");//на всякий случай
        session.removeAttribute("step");//на всякий случай
        if (userLogin == null || userLogin.trim().length() == 0) { %>
    <button onclick="location.href='/game/login'">Начать игру</button>
    <% } else { %>
    <button onclick="location.href='/game/play'">Начать игру</button>
    <% } %>
</div>
</body>
</html>

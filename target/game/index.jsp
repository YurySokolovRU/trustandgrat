<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <title></title>
    <link id="stylesheet" rel="stylesheet" href="style/desktop.css" type="text/css"/>
    <script src="js/device.js"></script>
    <script>
        if (device.mobile()) {
            document.getElementById("stylesheet").href="style/mobile.css";
        }
    </script>
    <meta name='viewport' content='width=device-width,initial-scale=1'/>
    <meta content='true' name='HandheldFriendly'/>
    <meta content='width' name='MobileOptimized'/>
    <meta content='yes' name='apple-mobile-web-app-capable'/>
</head>
<body>
<div class="container">
    <h1>Рады приветствовать Вас</h1>
    <p>Мы предлагаем Вам принять участие в игре.<br>В каждом ее раунде Вам предстоит решить
        доверять или не доверять незнакомцу выделенные ресурсы.<br>Если Вы примете решение доверить, то ресурс умножится и будет
        передан партнеру.<br>Ваш партнер может принять решение вознаграждать или не вознаграждать Вас за проявленное доверие.<br>В
        следующем раунде Вы будете встречаться с тем же или новым партнером.<br>Вам предстоит игра как в роли доверяющего, так и
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

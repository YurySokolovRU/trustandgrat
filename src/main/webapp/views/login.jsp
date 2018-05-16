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
<%
    session.removeAttribute("user_login");
    session.removeAttribute("player");
    session.removeAttribute("step");
%>
<div style="width: 70%; margin: 0 auto; padding-top: 100px;">
    <p style="font-size: 16px; font-weight: bold;">Введите Ваш логин и пароль.<br>Если вы играете впервые, введите любой логин (анлийскими буквами) и пароль, а также контакты (почту или номер телефона) для связи с Вами</p>
    <form action="" method="post">
        <% if (request.getAttribute("error") != null) { %>
            <div style="color:red">Не совпадает пара логин/пароль</div>
        <% } %>
        <table>
            <tr><td>Логин</td><td><input id="login" name="login" autofocus required pattern="[a-z_A-Z0-9]*"></td></tr>
            <tr><td>Пароль</td><td><input id="password" type="password" name="password" required></td></tr>
            <tr><td>Контакт: E-mail, номер телефона и т.д.</td><td><input name="contact"> (если не указывали ранее)</td></tr>
        </table>
        <% if (request.getAttribute("login") != null) { %>
            <script>
                document.getElementById("login").value = '<%=request.getAttribute("login")%>';
                document.getElementById("password").focus();
            </script>
        <% } %>
        <button type="submit">Далее</button>
    </form>
</div>
</body>
</html>

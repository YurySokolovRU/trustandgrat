<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
    <meta name='viewport' content='width=device-width,initial-scale=1'/>
    <meta content='true' name='HandheldFriendly'/>
    <meta content='width' name='MobileOptimized'/>
    <meta content='yes' name='apple-mobile-web-app-capable'/>

    <link id="stylesheet" rel="stylesheet" href="style/desktop.css" type="text/css"/>
    <script src="js/device.js"></script>
    <script>
        if (device.mobile()) {
            document.getElementById("stylesheet").href="style/mobile.css";
        }
    </script>
</head>
<body>
<%
    session.removeAttribute("user_login");
    session.removeAttribute("player");
    session.removeAttribute("step");
%>
<div class="container">
    <h1>Введите Ваш логин и пароль</h1>
    <span style="font-style: italic">Если Вы играете впервые, введите любой логин и пароль, а также контакты (почту или номер телефона) для связи с Вами</span>
    <br>
    <br>
    <form action="" method="post">
        <% if (request.getAttribute("error") != null) { %>
            <div class="red">Не совпадает пара логин/пароль</div>
        <% } %>
        <fieldset>
            <table>
                <tr valign="top"><td>Логин&nbsp;<span class="red">*</span></td><td><input id="login" name="login" autofocus required pattern="[a-z_A-Z0-9]*">&nbsp;<span style="font-style: italic">(допустимые символы: латинские буквы, цифры, знак подчеркивания)</span></td></tr>
                <tr><td colspan="2"><div style="width: 80%"><hr></div></td></tr>
                <tr valign="top"><td>Пароль&nbsp;<span class="red">*</span></td><td><input id="password" type="password" name="password" required>&nbsp;<span style="font-style: italic">(допустимые символы: латинские буквы, цифры, знак подчеркивания)</span></td></tr>
                <tr><td colspan="2"><div style="width: 80%"><hr></div></td></tr>
                <tr valign="top"><td>Контакт: e-mail, номер телефона и т.д.</td><td><input name="contact">&nbsp;<span style="font-style: italic">(если не указывали ранее)</span></td></tr>
            </table>
        </fieldset>
        <% if (request.getAttribute("login") != null) { %>
            <script>
                document.getElementById("login").value = '<%=request.getAttribute("login")%>';
                document.getElementById("password").focus();
            </script>
        <% } %>
        <br>
        <button type="submit">Далее</button>
    </form>
</div>
</body>
</html>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<div class="container">
    <p>Игра окончена. Спасибо за участие.</p>
    <% request.getSession().removeAttribute("player");
        request.getSession().removeAttribute("step"); %>

    <button onclick="location.href='/game/play'">Играть заново</button>
</div>
</body>
</html>
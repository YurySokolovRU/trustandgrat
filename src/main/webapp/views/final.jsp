<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <p>Игра окончена. Спасибо за участие.</p>
    <% request.getSession().removeAttribute("player");
        request.getSession().removeAttribute("step"); %>

    <button onclick="location.href='/game/play'">Играть заново</button>
</div>
</body>
</html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
    <meta name='viewport' content='width=device-width,initial-scale=1'/>
    <meta content='true' name='HandheldFriendly'/>
    <meta content='width' name='MobileOptimized'/>
    <meta content='yes' name='apple-mobile-web-app-capable'/>

    <script>
        function checkProfessor() {
            return prompt("А Вы точно Профессор?\nСколько будет 2x2?") == '4';
        }
    </script>
</head>
<body>
<form action="" method="post">
    <input type=hidden name=xls>
    <input type=submit value="Профессор хочет выгрузить данные">
</form>

<form action="" method="post" onsubmit="return checkProfessor();">
    <input type=hidden name=doc>
    <input type="submit" value="Профессор хочет знать, что означают столбцы">
</form>
</body>
</html>

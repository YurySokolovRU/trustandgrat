<%@ page import="ru.sokolov.jz.thegame.model.RozenbergScaleTest" %>
<%@ page import="ru.sokolov.jz.thegame.model.Stage3Model" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
    <meta name='viewport' content='width=device-width,initial-scale=1'/>
    <meta content='true' name='HandheldFriendly'/>
    <meta content='width' name='MobileOptimized'/>
    <meta content='yes' name='apple-mobile-web-app-capable'/>
    <script src = 'js/jquery.js'></script>
    <script src = 'js/admin.js'></script>
    <script>
        $(document).ready(
                function() {
                    $.post('admin', {action:'init'}, function(responseJson) {
                        $('#total_users').html('<span style="cursor: pointer;text-decoration: underline">' + responseJson.usersCount + ' (' + responseJson.gamesCount + ' игр)</span>');
                        var rozenbergHtml = '';
                        <% for (RozenbergScaleTest.VARIANT variant : RozenbergScaleTest.VARIANT.values()) { %>
                            rozenbergHtml += '&nbsp;&nbsp;&nbsp;&nbsp;<%=variant.name()%>: ' + responseJson.<%=variant.name()%> + '<br>';
                        <% } %>
                        $('#rozenberg').html(rozenbergHtml);
                        var stage3Html = '';
                        <% for (Stage3Model.VARIANT variant : Stage3Model.VARIANT.values()) { %>
                            stage3Html += '&nbsp;&nbsp;&nbsp;&nbsp;<%=variant.name()%>: ' + responseJson.<%=variant.name()%> + '<br>';
                        <% } %>
                        $('#stage3').html(stage3Html);
                    });
                }
        );
        $(document).on('click', '#total_users', showUsers);
        $(function() {
            var offset = $("#info").offset();
            var topPadding = 15;
            $(window).scroll(function() {
                if ($(window).scrollTop() > offset.top) {
                    $("#info").stop().animate({marginTop: $(window).scrollTop() - offset.top + topPadding});
                }
                else {$("#info").stop().animate({marginTop: 0});};});
        });
    </script>
</head>
<body>
<form action="" method="post">
    <input type=hidden name=action value=xls>
    <input type=submit value="Данные">
</form>
<hr>
<form action="" method="post">
    <input type=hidden name=action value=doc>
    <input type="submit" value="Описание столбцов">
</form>
<hr>
<div style="width: 20%; float: left; height: 1500px;">
    Всего игроков: <span id="total_users" onmouseover="this.style.color='blue'" onmouseout="this.style.color='black'"><b>идёт подсчет..</b></span>
    <div id='users'></div>
    <br>
    Розенберг: <div id="rozenberg"><b>идёт подсчёт..</b></div>
    <br>
    Доверие:
    <div id="stage3"><b>идёт подсчёт..</b></div>
</div>
<div style="margin-top: 10px; float: right; width: 70%;">
    <div id="info" style="background: #CCC; padding: 20px;"></div>
</div>
</body>
</html>

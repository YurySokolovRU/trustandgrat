<!DOCTYPE html>

<%@ page import="ru.sokolov.jz.thegame.entities.Player" %>
<%@ page import="ru.sokolov.jz.thegame.servlet.PlayServlet" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>The Game</title>
    <script>
        function onSubmit(step, form) {
            // Проверка ввода
            var doSubmit = checkInput(step, form);
            if (doSubmit) {
                // Действия перед отправкой
                beforeSubmit(step, form);
            }
            return doSubmit;
        }

        function checkInput(step, form) {
            var doSubmit = true;
            if (step == '<%=PlayServlet.STAGE1_INSTRUCTION%>') {
                if (checkRadio(form, 'alreadyPlayed', 2, 'alreadyPlayed_warning') == false) {
                    doSubmit = false;
                }
            } else if (step == '<%=PlayServlet.STAGE2_ROZENBERG%>') {
                for (var i = 1; i <= 3; i++) {
                    if (checkRadio(form, 'roz_q' + i, 2, 'rozenberg_warning' + i) == false) {
                        doSubmit = false;
                    }
                }
            } else if (step.indexOf('<%=PlayServlet.STAGE3%>') == 0 && step.indexOf('after') > 0) {
                if (checkRadio(form, step + '_q', 3, step + '_warning') == false) {
                    doSubmit = false;
                }
            } else if (step == '<%=PlayServlet.STAGE4%>_before') {
                if (checkRadio(form, step + '_q', 2, step + '_warning') == false) {
                    doSubmit = false;
                }
            } else if (step == '<%=PlayServlet.STAGE4%>_after2') {
                if (checkRadio(form, step + '_q', 3, step + '_warning') == false) {
                    doSubmit = false;
                }
            } else if (step == '<%=PlayServlet.STAGE5%>') {
                for (var i = 1; i < 7; i++) {
                    if (checkRadio(form, 'stage5_' + i, 5, 'stage5_warning' + i) == false) {
                        doSubmit = false;
                    }
                }
            } else if (step == '<%=PlayServlet.STAGE6%>') {
                for (var i = 1; i <= 3; i++) {
                    if (checkRadio(form, '<%=PlayServlet.STAGE6%>_q' + i, 2, '<%=PlayServlet.STAGE6%>_warning' + i) == false) {
                        doSubmit = false;
                    }
                }
            } else if (step == '<%=PlayServlet.STAGE8%>') {
                for (var i = 1; i < 7; i++) {
                    if (checkRadio(form, 'stage8_' + i, 5, 'stage8_warning' + i) == false) {
                        doSubmit = false;
                    }
                }
            } else if (step == '<%=PlayServlet.STAGE8%>_after') {
                if (checkRadio(form, step + '_q', 2, step + '_q_warning') == false) {
                    doSubmit = false;
                }
            } else if (step == '<%=PlayServlet.STAGE9%>') {
                if (checkRadio(form, 'stage9_sex', 2, 'stage9_sex_warning') == false) {
                    doSubmit = false;
                }
                if (checkRadio(form, 'stage9_income', 5, 'stage9_income_warning') == false) {
                    doSubmit = false;
                }
            } else if (step == '<%=PlayServlet.STAGE10%>') {
                for (var i = 1; i <= 4; i++) {
                    if (checkRadio(form, '<%=PlayServlet.STAGE10%>_q' + i, 4, '<%=PlayServlet.STAGE10%>_warning' + i) == false) {
                        doSubmit = false;
                    }
                }
            }
            return doSubmit;
        }

        function checkRadio(form, radioName, count, warning) {
            var result = false;
            for (var i = 0; i < count; i++) {
                if (form.elements[radioName][i].checked) {
                    result = true;
                    break;
                }
            }
            if (result == false) {
                document.getElementById(warning).style.display = 'block';
            } else {
                document.getElementById(warning).style.display = 'none'
            }
            return result;
        }

        function beforeSubmit(step, form) {
            // stage3_1,...,stage3_5
            // пауза после каждой ставки игры 3 этапа
            if (step.indexOf('<%=PlayServlet.STAGE3%>') == 0 && step.length == '<%=PlayServlet.STAGE3%>'.length + 2) {
                var betPause = document.getElementById(step + '_pause');
                if (betPause != null) {
                    betPause.style.display = 'block';
                }
            }
            if (step == '<%=PlayServlet.STAGE7_SUPER%>') {
                var betPause = document.getElementById(step + '_pause');
                if (betPause != null) {
                    betPause.style.display = 'block';
                }
            }
            document.getElementById('submit_button').disabled = true;
        }

        function submitForm() {
            document.forms[0].submit();
        }

        function selectInput(element) {
            element.childNodes[0].checked=true;
        }
    </script>
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
    <%
        if (request.getParameter("set_step") != null && !request.getParameter("set_step").trim().equals("")) {
            session.setAttribute("step", request.getParameter("set_step"));
        }
        String userLogin = (String) session.getAttribute("user_login");
        Player player = (Player) session.getAttribute("player");
        String step = (String) session.getAttribute("step");
        if (userLogin == null || userLogin.trim().length() == 0) {
    %>
    <script type="text/javascript">
        location.href = "/game/login";
    </script>
    <%
        } else if ("true".equals(session.getAttribute("admin"))) {
            out.println("user: " + player.getUser().hashCode() + "<br/>");
            out.println("player: " + player.getTimestamp() + "<br/>");
            out.println("step: " + step + "<br/>");
            out.println("<hr>");
        }
    %>
    <table width="100%" style="font-size: 20px;">
        <tr align="right">
            <td width="100%"></td>
            <td>Логин:</td>
            <td><%=player.getUser().getLogin() %>
            </td>
        </tr>
        <% Integer wallet = PlayServlet.getPlayerWallet(step, player);
            if (wallet != null) { %>
                <tr align="right">
                    <td width="100%"></td>
                    <td>Кошелёк:</td>
                    <td><%=wallet.intValue() %>
                    </td>
                </tr>
        <% } %>
    </table>
    <form action="play" method="post" onsubmit="return onSubmit('<%=step%>', this);">
        <input type="hidden" name="<%=step%>_passed" value="true"/>
        <c:choose>
            <c:when test='<%=step.equals(PlayServlet.STAGE3 + "_1") || step.equals(PlayServlet.STAGE3 + "_2") ||
                    step.equals(PlayServlet.STAGE3 + "_3") || step.equals(PlayServlet.STAGE3 + "_4") || step.equals(PlayServlet.STAGE3 + "_5") %>'>
                <jsp:include page="include/stage3_bet.jsp"/>
            </c:when>

            <c:when test='<%=step.startsWith(PlayServlet.STAGE3) && step.contains("after") %>'>
                <jsp:include page="include/stage3_bet_after.jsp"/>
            </c:when>

            <c:when test='<%=step.startsWith(PlayServlet.STAGE7 + "_wait") %>'>
                <jsp:include page="include/stage7_wait.jsp"/>
            </c:when>

            <c:when test='<%=step.startsWith(PlayServlet.STAGE7 + "_return") %>'>
                <jsp:include page="include/stage7_return.jsp"/>
            </c:when>

            <c:otherwise>
                <jsp:include page='<%=("include/" + step + ".jsp")%>'/>
            </c:otherwise>
        </c:choose>
        <br>
        <button type="submit" id="submit_button">Далее</button>
    </form>
</div>

</body>
</html>
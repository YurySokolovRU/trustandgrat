<%@ page import="ru.sokolov.jz.thegame.entities.Player" %>
<%@ page import="ru.sokolov.jz.thegame.servlet.PlayServlet" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<% Player player = (Player) session.getAttribute("player");

    int betsSum = player.getRatingBetsSum();
    int betsPercent = player.getRatingBetsPercent();
    int returnsSum = player.getRatingReturnsSum();
    int returnsPercent = player.getRatingReturnsPercent();

    PlayServlet.TotalData data = new PlayServlet.TotalData(player);
    int gamesCount = data.getGamesCount();
%>
<div>
    За 5 раундов Вы суммарно доверили <span style="font-weight:bold;"><%=PlayServlet.getCreditSpelling(betsSum)%></span>,
    средний процент доверенной суммы от имевшейся в вашем распоряжении: <span style="font-weight:bold;"><%=betsPercent%></span>%
    <br>Вы на <span style="font-weight:bold;"><%=data.getBetsSumRatingPosition(betsSum)%></span> месте из <%=gamesCount%> по сумме и на
    <span style="font-weight:bold;"><%=data.getBetsPercentRatingPosition(betsPercent)%></span> месте из <%=gamesCount%> по проценту доверенного.
</div>
<br><br>
<div>
    За 5 раундов в качестве благодарности за доверие Вы вознаградили партнера суммой в <span style="font-weight:bold;"><%=PlayServlet.getCreditSpelling(returnsSum)%></span>,
    это составило <span style="font-weight:bold;"><%=returnsPercent%></span>% от доверенного вам.
    <br>Вы на <span style="font-weight:bold;"><%=data.getReturnsSumRatingPosition(returnsSum)%></span> месте из <%=gamesCount%> по сумме и на
    <span style="font-weight:bold;"><%=data.getReturnsPercentRatingPosition(returnsPercent)%></span> месте из <%=gamesCount%> по проценту возвращенного.
</div>
<br><br>
<div>
    Ваш выигрыш на момент финала игры: <span style="font-weight:bold;"><%=PlayServlet.getCreditSpelling(player.getStagesResultWallet())%></span>
    <br>Вы на <span style="font-weight:bold;"><%=data.getResultWalletRatingPosition(player.getStagesResultWallet())%></span> месте из <%=gamesCount%>
</div>
<br>
<div>
    <% String[][] topUsers = data.getWalletTopUsers(); %>
    Наиболее эффективные игроки, набравшие максимальную сумму за игру:
    <%
        out.println("<ol>");
        for (String[] topUser : topUsers) {
            if (topUser[0] != null) {
                out.print("<li>" + topUser[0] + " - " + topUser[1] + "</li>");
            } else {
                out.print("<li>......</li>");
            }
        }
        out.println("</ol>");
    %>
</div>
<br>
<div>
    <%
        boolean played4 = player.isWantStage4();
        boolean played7after = player.getStage7SuperReturn() > 0;
        String adverb = played4 ? (played7after ? "дважды" : "один раз") : (played7after ? "один раз" : "ни разу не");
        int sum = (played4 ? player.getStage3Wallet() : 0) + player.getStage7SuperReturn();
        String totalSumPhrase = "";
        if (sum > 0) {
            totalSumPhrase += ", на общую сумму " + PlayServlet.getCreditSpelling(sum) + (played4 && played7after ? "(" + player.getStage3Wallet() + " и " + player.getStage7SuperReturn() + ")" : "");
        };
    %>
    Вы <%=adverb%> согласились сыграть в супер-игру<%=totalSumPhrase%>.
    <% if (sum > 0) { %>
        По сравнению с другими игроками, вы на <%=data.getSuperSumsRatingPosition(sum) %> месте.
    <% } %>
</div>
<br>
<div>
    <% topUsers = data.getTrustTopUsers(); %>
    Самые доверяющие:
    <%
        out.println("<ol>");
        for (String[] topUser : topUsers) {
            if (topUser[0] != null) {
                out.print("<li>" + topUser[0] + " - " + topUser[1] + "%</li>");
            } else {
                out.print("<li>......</li>");
            }
        }
        out.println("</ol>");
    %>
</div>
<br>
<div>
    <% topUsers = data.getGratitudeTopUsers(); %>
    Самые благодарные:
    <%
        out.println("<ol>");
        for (String[] topUser : topUsers) {
            if (topUser[0] != null) {
                out.print("<li>" + topUser[0] + " - " + topUser[1] + "%</li>");
            } else {
                out.print("<li>......</li>");
            }
        }
        out.println("</ol>");
    %>
</div>
<br>
<div>
    <% topUsers = data.getRiskTopUsers(); %>
    Самые рисковые:
    <%
        out.println("<ol>");
        for (String[] topUser : topUsers) {
            if (topUser[0] != null) {
                out.print("<li>" + topUser[0] + " - " + topUser[1] + "</li>");
            } else {
                out.print("<li>......</li>");
            }
        }
        out.println("</ol>");
    %>
</div>
<br><br>

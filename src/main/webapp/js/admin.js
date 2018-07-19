function showUsers() {
    var users = document.getElementById('users');
    if (users.style.display == 'block') {
        users.style.display = 'none';
    } else {
        $.post('admin', {action: 'getUsers'}, function (responseJson) {
            var usersHtml = '<table>';
            $.each(responseJson, function (index, item) {
                usersHtml += "<tr><td><span style='padding-left:20px; cursor: pointer;text-decoration: underline' onclick='showUPlayers(\"" + item + "\")' onmouseover='this.style.color = \"red\"' onmouseout='this.style.color = \"black\"' title='Показать игры'>" + item + "</span>&nbsp;";
                usersHtml += "</td><td><img style='cursor:pointer' src='images/deleteU.png' onclick='deleteUser(\"" + item + "\")' title='удалить все игры пользователя'/></td></tr>";
                usersHtml += "<tr><td colspan=2><span id='" + item + "_players' style='display:none' class='hideable'></span></td></tr>";
            });
            users.innerHTML = usersHtml + '</table>';
            users.style.display = 'block';
        });
    }
}

function showUPlayers(login) {
    var uPlayers = document.getElementById(login + '_players');
    if (uPlayers.style.display == 'block') {
        uPlayers.style.display = 'none';
    } else {
        $.post('admin', {action: 'getUPlayers', login: login}, function (responseJson) {
            var els = document.getElementsByClassName('hideable');
            for (var i = 0; i < els.length; i++) {
                els[i].style.display = 'none';
            }
            var uPlayerHtml = '';
            $.each(responseJson, function (index, item) {
                uPlayerHtml += "<span style='padding-left:40px; cursor: pointer;text-decoration: underline' onclick='showPlayerInfo(\"" + login + "\",\"" + item + "\")' onmouseover='this.style.color = \"red\"' onmouseout='this.style.color = \"black\"'>" + item + "</span>&nbsp;";
                uPlayerHtml += "<img style='cursor:pointer' src='images/deleteP.png' onclick='deleteUPlayer(\"" + login + "\",\"" + item + "\")' title='удалить игру'/><br>";
            });
            uPlayers.innerHTML = uPlayerHtml;
            uPlayers.style.display = 'block';
        });
    }
    var info = document.getElementById('info');
    $.post('admin', {action: 'getUserInfo', login: login}, function (responseJson) {
        var userInfoHtml = '<table>';
        $.each(responseJson, function (key, value) {
            userInfoHtml += '<tr><td>' + key + '</td><td>' + value + '</td></tr>';
        });
        info.innerHTML = userInfoHtml + '</table>';
    });
}

function showPlayerInfo(login, timestamp) {
    var info = document.getElementById('info');
    $.post('admin', {action: 'getPlayerInfo', login: login, timestamp: timestamp}, function (responseJson) {
        var playerInfoHtml = '<table>';
        $.each(responseJson, function (key, value) {
            playerInfoHtml += '<tr><td>' + key + '</td><td>' + value + '</td></tr>';
        });
        info.innerHTML = playerInfoHtml + '</table>';
    });
}

function deleteUser(login) {
    if (confirm('Удалить все игры ' + login + '?')) {
        var users = document.getElementById('users');
        $.post('admin', {action: 'deleteUser', login: login}, function (responseJson) {
            var usersHtml = '';
            $.each(responseJson, function (index, item) {
                usersHtml += "<span style='padding-left:20px; cursor:pointer; text-decoration:underline' onclick='showUPlayers(\"" + item + "\")' onmouseover='this.style.color = \"red\"' onmouseout='this.style.color = \"black\"' title='Показать игры'>" + item + "</span>&nbsp;";
                usersHtml += "<img style='cursor:pointer' src='images/deleteU.png' onclick='deleteUser(\"" + item + "\")' title='удалить все игры пользователя'/><br>";
                usersHtml += "<span id='" + item + "_players' style='display:none' class='hideable'></span>";
            });
            users.innerHTML = usersHtml;
            users.style.display = 'block';
            var info = document.getElementById('info');
            info.innerHTML = "Удалено";
        });
    }
}

function deleteUPlayer(login, timestamp) {
    if (confirm('Удалить игру ' + timestamp + ' (' + login + ')?')) {
        var uPlayers = document.getElementById(login + '_players');
        $.post('admin', {action: 'deleteUPlayer', login: login, timestamp: timestamp}, function (responseJson) {
            var els = document.getElementsByClassName('hideable');
            for (var i = 0; i < els.length; i++) {
                els[i].style.display = 'none';
            }
            var uPlayerHtml = '';
            $.each(responseJson, function (index, item) {
                uPlayerHtml += "<span style='padding-left:40px; cursor: pointer;text-decoration: underline' onclick='showPlayerInfo(\"" + login + "\",\"" + item + "\")' onmouseover='this.style.color = \"red\"' onmouseout='this.style.color = \"black\"'>" + item + "</span>&nbsp;";
                uPlayerHtml += "<img style='cursor:pointer' src='images/deleteP.png' onclick='deleteUPlayer(\"" + login + "\",\"" + item + "\")' title='удалить игру'/><br>";
            });
            uPlayers.innerHTML = uPlayerHtml;
            uPlayers.style.display = 'block';
            var info = document.getElementById('info');
            info.innerHTML = "Удалено";
        });
    }
}
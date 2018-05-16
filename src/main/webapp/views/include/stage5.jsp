<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<p class="instructions" style="text-align: center">Оцените какими были ваши решения доверять или не доверять партнеру</p>
<br>
<table>
    <tr align="center" valign="top">
        <td width="45%"></td>
        <td style="padding-left: 50px">1</td>
        <td>2</td>
        <td>3</td>
        <td>4</td>
        <td style="padding-right: 50px">5</td>
        <td width="45%"></td>
    </tr>
    <tr><td colspan="7"><hr/></td></tr>
    <tr valign="top">
        <td align="right">Я принимал решения обдуманно, размышлял над ними.<br>Я ответственно подходил к выбору</td>
        <td style="padding-left: 50px">
            <input type=radio name="stage5_1" value="1">
        </td>
        <td>
            <input type=radio name="stage5_1" value="2">
        </td>
        <td>
            <input type=radio name="stage5_1" value="3">
        </td>
        <td>
            <input type=radio name="stage5_1" value="4">
        </td>
        <td style="padding-right: 50px">
            <input type=radio name="stage5_1" value="5">
        </td>
        <td>Это было спонтанное, импульсивное решение</td>
    </tr>
    <tr align="center" valign="top">
        <td></td>
        <td colspan="5">
            <div id="stage5_warning1" style='display:none; color:red;'>Сделайте выбор</div>
        </td>
        <td></td>
    </tr>
    <tr><td colspan="7"><hr/></td></tr>
    <tr valign="top">
        <td align="right">Мне было приятно принимать такие решения.<br>Выбор вызывал позитивные эмоции</td>
        <td style="padding-left: 50px">
            <input type=radio name="stage5_2" value="1">
        </td>
        <td>
            <input type=radio name="stage5_2" value="2">
        </td>
        <td>
            <input type=radio name="stage5_2" value="3">
        </td>
        <td>
            <input type=radio name="stage5_2" value="4">
        </td>
        <td style="padding-right: 50px">
            <input type=radio name="stage5_2" value="5">
        </td>
        <td>Мне было трудно.<br>Выбор вызывал противоречивые эмоции</td>
    </tr>
    <tr align="center" valign="top">
        <td></td>
        <td colspan="5">
            <div id="stage5_warning2" style='display:none; color:red;'>Сделайте выбор</div>
        </td>
        <td></td>
    </tr>
    <tr><td colspan="7"><hr/></td></tr>
    <tr valign="top">
        <td align="right">Я сам так решил.<br>Это был мой свободный выбор</td>
        <td style="padding-left: 50px">
            <input type=radio name="stage5_3" value="1">
        </td>
        <td>
            <input type=radio name="stage5_3" value="2">
        </td>
        <td>
            <input type=radio name="stage5_3" value="3">
        </td>
        <td>
            <input type=radio name="stage5_3" value="4">
        </td>
        <td style="padding-right: 50px">
            <input type=radio name="stage5_3" value="5">
        </td>
        <td>Мне приходилось так поступать.<br>Это был вынужденный выбор</td>
    </tr>
    <tr align="center" valign="top">
        <td></td>
        <td colspan="5">
            <div id="stage5_warning3" style='display:none; color:red;'>Сделайте выбор</div>
        </td>
        <td></td>
    </tr>
    <tr><td colspan="7"><hr/></td></tr>
    <tr valign="top">
        <td align="right">Я уверен в своих решениях.<br>Это был хороший выбор</td>
        <td style="padding-left: 50px">
            <input type=radio name="stage5_4" value="1">
        </td>
        <td>
            <input type=radio name="stage5_4" value="2">
        </td>
        <td>
            <input type=radio name="stage5_4" value="3">
        </td>
        <td>
            <input type=radio name="stage5_4" value="4">
        </td>
        <td style="padding-right: 50px">
            <input type=radio name="stage5_4" value="5">
        </td>
        <td>Мне не всегда нравились мои решения.<br>Я сомневался в своем выборе</td>
    </tr>
    <tr align="center" valign="top">
        <td></td>
        <td colspan="5">
            <div id="stage5_warning4" style='display:none; color:red;'>Сделайте выбор</div>
        </td>
        <td></td>
    </tr>
    <tr><td colspan="7"><hr/></td></tr>
    <tr valign="top">
        <td align="right">Я скорее доверял своим партнерам по игре</td>
        <td style="padding-left: 50px">
            <input type=radio name="stage5_5" value="1">
        </td>
        <td>
            <input type=radio name="stage5_5" value="2">
        </td>
        <td>
            <input type=radio name="stage5_5" value="3">
        </td>
        <td>
            <input type=radio name="stage5_5" value="4">
        </td>
        <td style="padding-right: 50px">
            <input type=radio name="stage5_5" value="5">
        </td>
        <td>Я скорее не доверял своим партнерам по игре</td>
    </tr>
    <tr align="center" valign="top">
        <td></td>
        <td colspan="5">
            <div id="stage5_warning5" style='display:none; color:red;'>Сделайте выбор</div>
        </td>
        <td></td>
    </tr>
    <tr><td colspan="7"><hr/></td></tr>
    <tr valign="top">
        <td align="right">Я выиграл</td>
        <td style="padding-left: 50px">
            <input type=radio name="stage5_6" value="1">
        </td>
        <td>
            <input type=radio name="stage5_6" value="2">
        </td>
        <td>
            <input type=radio name="stage5_6" value="3">
        </td>
        <td>
            <input type=radio name="stage5_6" value="4">
        </td>
        <td style="padding-right: 50px">
            <input type=radio name="stage5_6" value="5">
        </td>
        <td>Я проиграл</td>
    </tr>
    <tr align="center" valign="top">
        <td></td>
        <td colspan="5">
            <div id="stage5_warning6" style='display:none; color:red;'>Сделайте выбор</div>
        </td>
        <td></td>
    </tr>
</table>
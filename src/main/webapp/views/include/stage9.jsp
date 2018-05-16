<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<p class="instructions">Для осуществления всесторонней оценки итогов игры, укажите, пожалуйста:</p>
<ol>
    <li>Ваш пол
        <ul>
            <li><input type="radio" name="stage9_sex" value="1">Мужчина</li>
            <li><input type="radio" name="stage9_sex" value="2">Женщина</li>
        </ul>
        <div id="stage9_sex_warning" style='display:none; color:red;'>Сделайте выбор</div>
    </li>
    <li>Возраст (полных лет)&nbsp;&nbsp;<input name="stage9_age" required pattern="^[1-9]{1}[0-9]{1}$"/>
    </li>
    <li>Примерный средний ежемесячный доход
        <ul>
            <li><input type="radio" name="stage9_income" value="1">&lt; 15 т.р.</li>
            <li><input type="radio" name="stage9_income" value="2">16-40 т.р.</li>
            <li><input type="radio" name="stage9_income" value="3">41-70 т.р.</li>
            <li><input type="radio" name="stage9_income" value="4">71-120 т.р.</li>
            <li><input type="radio" name="stage9_income" value="5">&gt; 121 т.р.</li>
        </ul>
        <div id="stage9_income_warning" style='display:none; color:red;'>Сделайте выбор</div>
    </li>
</ol>
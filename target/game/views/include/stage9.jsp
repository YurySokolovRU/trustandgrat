<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<p class="instructions">Для осуществления всесторонней оценки итогов игры, укажите, пожалуйста:</p>
<ol>
    <li>Ваш пол
        <ul>
            <li onclick="selectInput(this)"><input type="radio" name="stage9_sex" value="1">Мужчина</li>
            <li onclick="selectInput(this)"><input type="radio" name="stage9_sex" value="2">Женщина</li>
        </ul>
        <div id="stage9_sex_warning" class="red" style='display:none;'>Сделайте выбор</div>
    </li>
    <li>Возраст (полных лет)&nbsp;&nbsp;<input type="number" style="width: 100px" name="stage9_age" min="10" max="100" step="1" required>
    </li>
    <li>Примерный средний ежемесячный доход
        <ul>
            <li onclick="selectInput(this)"><input type="radio" name="stage9_income" value="1">&lt; 15 т.р.</li>
            <li onclick="selectInput(this)"><input type="radio" name="stage9_income" value="2">16-40 т.р.</li>
            <li onclick="selectInput(this)"><input type="radio" name="stage9_income" value="3">41-70 т.р.</li>
            <li onclick="selectInput(this)"><input type="radio" name="stage9_income" value="4">71-120 т.р.</li>
            <li onclick="selectInput(this)"><input type="radio" name="stage9_income" value="5">&gt; 121 т.р.</li>
        </ul>
        <div id="stage9_income_warning" class="red" style='display:none;'>Сделайте выбор</div>
    </li>
</ol>
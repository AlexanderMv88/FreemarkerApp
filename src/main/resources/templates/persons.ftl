<#import "ui.ftl" as ui/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Person</title>
    <link href="style/my.css" rel="stylesheet">
</head>
<body>

<div>
    <fieldset>
        <legend>Добавить лицо</legend>
        <form firstName="person" action="save" method="POST">
            <@ui.formInput id="t1" firstName="firstName" label="Имя"/> <br/>
            <@ui.formInput id="t2" firstName="age" label="Возраст"/> <br/>
            <input type="submit" value="Save" />
        </form>
    </fieldset>
</div>

<div>
    <fieldset>
        <legend>Поиск</legend>
        <form firstName="searchForm" action="search" method="POST">
        <@ui.formInput id="t3" firstName="searchName" label="Поиск"/> <br/>
            <input type="submit" value="Search" />
        </form>
    </fieldset>
</div>
<p><#if lastSearch??>Поиск для: ${lastSearch}<#else></#if></p>

<@ui.table id="table1" rows=persons![]/>

</body>
</html>
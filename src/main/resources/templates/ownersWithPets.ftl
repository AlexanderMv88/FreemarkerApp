
<!DOCTYPE html>
<html>
<head>
    <title>Owners with pets</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="css/style.css"/>
</head>
<body>

<div>
    <fieldset>
        <legend>Добавить владельца</legend>
        <form name="owner" action="addOwner" method="POST">

            <label for="t1">Имя</label>
            <input type="text" id="t1" name="firstName" value="">
            <input type="submit" value="Добавить владельца" />
        </form>
    </fieldset>
</div>

<h2>Владельцы</h2>
<table>
    <tr>
        <th>Id</th>
        <th>Имя</th>
    </tr>
    <#list owners as owner>
        <tr>
            <td>${owner.id}</td>
            <td>${owner.firstName}</td>
        </tr>
    </#list>
</table>

<div>
    <fieldset>
        <legend>Добавить животное</legend>
        <form name="pet1" action="addPet" method="POST">
            <label for="t2">Владелец:</label>
            <select name="petOwner" id="t2">
                <#list owners as owner>
                    <option value="${owner.firstName}">${owner.firstName}</option>
                </#list>
            </select>
            <label for="t3">Кличка:</label>
            <input type="text" id="t3" name="name" value="">
            <input type="submit" value="Add" />
        </form>
    </fieldset>
</div>
<h2>Питомцы</h2>
<table>
    <tr>
        <th>Id</th>
        <th>Кличка</th>
        <th>Владелец</th>
    </tr>
    <#list pets as pet>
        <tr>
            <td>${pet.id}</td>
            <td>${pet.name}</td>
            <td>${pet.ownerName}</td>
        </tr>
    </#list>
</table>
</body>
</html>
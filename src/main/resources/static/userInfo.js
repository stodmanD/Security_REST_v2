userInfo();
//запрос на получение данных в виде json
function userInfo(user) {
        fetch('http://localhost:8080/users/us')
        .then(response => response.json())
        .then(user => {
            //по id получаем юзера и заполняем таблицу
            let tBody = document.getElementById("userInfo");
            console.log(tBody)


            //добавляем строку в таблицу <tr> и возвращаем ее индекс
            var row = tBody.insertRow(0);
            //добавляем ячейку <td> в строке таблицы <tr>
            var cell0 = row.insertCell(0);
            cell0.innerHTML = user.id;
            var cell1 = row.insertCell(1);
            cell1.innerHTML = user.name;
            var cell2 = row.insertCell(2);
            cell2.innerHTML = user.surname;
            var cell3 = row.insertCell(3);
            cell3.innerHTML = user.department;
            var cell4 = row.insertCell(4);
            cell4.innerHTML = user.salary;
            var cell5 = row.insertCell(5);
            cell5.innerHTML = user.username;
            var cell6 = row.insertCell(6);
            cell6.innerHTML = allRoles(user).textContent;
        });
}
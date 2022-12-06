function newUser() {
    let form = window.formNewUser.newRoles;
    let new_Roles = "";
    let rolesList = document.createElement('ul');

    for (var i = 0; i < form.length; i++) {
        var option = form.options[i];
        let role = document.createElement('li');
        if (option.selected) {
            new_Roles = new_Roles.concat(option.value + (i != (form.length - 1) ? "," : ""));

            role.textContent = option.value + " ";
            rolesList.appendChild(role);
        }
    }

    fetch('http://localhost:8080/api/users', {
        method: 'POST',
        body: JSON.stringify({
            name: window.formNewUser.newName.value,
            surname: window.formNewUser.newSurname.value,
            department: window.formNewUser.newDepartment.value,
            salary: window.formNewUser.newSalary.value,
            username: window.formNewUser.newUsername.value,
            password: window.formNewUser.newPassword.value,
            roles: new_Roles
        }),
        headers: {"Content-type": "application/json; charset=UTF-8"}
    })
        .then(response => response.json())
        .then(user => {
            $('#tBody tr:last').after('<tr id=' + user.id + '>' +
                '<td>' + user.id + '</td>' +
                '<td>' + window.formNewUser.newName.value + '</td>' +
                '<td>' + window.formNewUser.newSurname.value + '</td>' +
                '<td>' + window.formNewUser.newDepartment.value + '</td>' +
                '<td>' + window.formNewUser.newSalary.value + '</td>' +
                '<td>' + window.formNewUser.newUsername.value + '</td>' +
                '<td>' + rolesList.textContent + '</td>' +
                '<td> <button type="button" onclick="modalEdit(' + user.id + ')" class="btn btn-info">Edit</button> </td>' +
                '<td> <button type="button" onclick="modalDelete(' + user.id + ')" class="btn btn-danger">Delete</button> </td>' +
                '</tr>');

            $('#NewUserCreated').modal();
        });
}
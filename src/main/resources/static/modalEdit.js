function modalEdit(id) {

    fetch('http://localhost:8080/api/users/' + id)
        .then(response => response.json())
        .then(user => {

            let adminSelect = "";
            let userSelect = "";

            for (let i = 0; i < user.roles.length; i++) {
                if (user.roles[i].role == "ROLE_ADMIN") {
                    adminSelect = "selected";
                }
                if (user.roles[i].role == "ROLE_USER") {
                    userSelect = "selected";
                }
            }

            let modal = document.getElementById('modalWindow');

            modal.innerHTML =
                '<div id="modalEdit"' +
                '     class="modal fade" tabindex="-1" role="dialog"' +
                '     aria-labelledby="TitleModalLabel" aria-hidden="true"' +
                '     data-backdrop="static" data-keyboard="false">' +
                '    <div class="modal-dialog modal-dialog-scrollable">' +
                '        <div class="modal-content">' +
                '            <div class="modal-header">' +
                '                <h5 class="modal-title" id="TitleModalLabel">Edit user</h5>' +
                '                <button type="button" class="close" data-dismiss="modal" aria-label="Close">' +
                '                    <span aria-hidden="true">x</span>' +
                '                </button>' +
                '            </div>' +
                '            <div class="modal-body bg-white">' +
                '                <form id="formEditUser" style="width: 200px;"' +
                '                       class="form-signin mx-auto font-weight-bold text-center">' +
                '                    <p>' +
                '                        <label>ID</label>' +
                '                        <input class="form-control form-control-sm" type="text"' +
                '                               id="editID" name="id" value="' + user.id + '" readonly>' +
                '                    </p>' +
                '                    <p>' +
                '                        <label>Name</label>' +
                '                        <input class="form-control form-control-sm" type="text"' +
                '                               id="editName" value="' + user.name + '"' +
                '                               placeholder="name" required>' +
                '                    </p>' +
                '                    <p>' +
                '                        <label>Last name</label>' +
                '                        <input class="form-control form-control-sm" type="text"' +
                '                               id="editSurname" value="' + user.surname + '" ' +
                '                               placeholder="surname" required>' +
                '                    </p>' +
                '                    <p>' +
                '                        <label>Department</label>' +
                '                        <input class="form-control form-control-sm" type="text"' +
                '                               id="editDepartment" value="' + user.department + '" ' +
                '                               placeholder="department" required>' +
                '                    </p>' +
                '                    <p>' +
                '                        <label>Salary</label>' +
                '                        <input class="form-control form-control-sm" type="number"' +
                '                               id="editSalary" value="' + user.salary + '" ' +
                '                               placeholder="salary" required>' +
                '                    </p>' +
                '                    <p>' +
                '                        <label>Username</label>' +
                '                        <input class="form-control form-control-sm" type="text"' +
                '                               id="editUsername" value="' + user.username + '"' +
                '                               placeholder="username" required>' +
                '                    </p>' +
                '                    <p>' +
                '                        <label>Password</label>' +
                '                        <input class="form-control form-control-sm" type="password"' +
                '                               id="editPassword" placeholder="Password">' +
                '                    </p>' +
                '                    <p>' +
                '                        <label>Role</label>' +
                '                        <select id="editRoles" name="roles" multiple size="2" required ' +
                '                               class="form-control form-control-sm">' +
                '                            <option value="ROLE_ADMIN"' + adminSelect + '>ROLE_ADMIN</option>' +
                '                            <option value="ROLE_USER"' + userSelect + '>ROLE_USER</option>' +
                '                        </select>' +
                '                    </p>' +
                '                </form>' +
                '            </div>' +
                '            <div class="modal-footer">' +
                '                <button type="button" class="btn btn-secondary"' +
                '                        data-dismiss="modal">Close</button>' +
                '                <button class="btn btn-primary" data-dismiss="modal"' +
                '                        onclick="editUser()">Edit</button>' +
                '            </div>' +
                '        </div>' +
                '    </div>' +
                '</div>';

            $("#modalEdit").modal();

        });
}
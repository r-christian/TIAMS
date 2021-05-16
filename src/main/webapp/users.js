loadUsers();
clearInputFields();


function clearInputFields(){
    document.getElementById("firstname").value = "";
    document.getElementById("lastname").value = "";
    document.getElementById("email").value = "";
    document.getElementById("password").value = "";
    document.getElementById("id").value = "-1";
}

function save() {
    var hiddenId = document.getElementById("id");
    if(hiddenId.value == -1) {
        let user = {"userID": null, "firstName": document.getElementById("firstname").value,
                    "lastName": document.getElementById("lastname").value,
                    "email": document.getElementById("email").value,
                    "password": document.getElementById("password").value}
        var xmlhttp = new XMLHttpRequest();
        xmlhttp.open("POST", "/rest/user/add", true);
        xmlhttp.onreadystatechange = function () {
            if (xmlhttp.readyState > 3 && xmlhttp.status == 200) {
                clearInputFields();
                loadUsers();
            }
        };
        xmlhttp.setRequestHeader("Content-Type", "application/json");
        xmlhttp.send(JSON.stringify(user));
    }else{
        //update
        let user = {"userID": hiddenId.value, "firstName": document.getElementById("firstname").value,
            "lastName": document.getElementById("lastname").value,
            "email": document.getElementById("email").value,
            "password": document.getElementById("password").value}
        var xmlhttp = new XMLHttpRequest();
        xmlhttp.open("PUT", "/rest/user/update", true);
        xmlhttp.onreadystatechange = function () {
            if (xmlhttp.readyState > 3 && xmlhttp.status == 200) {
                clearInputFields();
                loadUsers();
            }
        };
        xmlhttp.setRequestHeader("Content-Type", "application/json");
        xmlhttp.send(JSON.stringify(user));
    }
}

function loadUsers() {
    var contentTable = document.getElementById("tableMain");
    let xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            var userDataList = JSON.parse(this.responseText);

            var paras = document.getElementsByClassName('data-row');
            while (paras[0]) {
                paras[0].parentNode.removeChild(paras[0]);
            }

            for (let index = 0; index < userDataList.length; index++) {
                var tableRow = document.createElement("tr");
                var userID = userDataList[index].userID;
                tableRow.setAttribute("class", "data-row");
                tableRow.id = userDataList[index].userID;
                tableRow.innerHTML = '<td class="data-field-id">' + userDataList[index].userID + '</td>' +
                    '<td class="data-field-firstname" >' + userDataList[index].firstName + '</td>' +
                    '<td class="data-field-lastname" >' + userDataList[index].lastName + '</td>' +
                    '<td class="data-field-email" >' + userDataList[index].email + '</td>' +
                    '<td class="data-field-password" >*****</td>';

                var actions = document.createElement("td");
                actions.setAttribute("class","row-actions");

                const btnEdit = document.createElement("button");
                btnEdit.setAttribute("class", "btn-action btn-edit");
                btnEdit.textContent = "Edit";
                btnEdit.id = tableRow.id;
                btnEdit.addEventListener( 'click', function(){
                    editItemClicked(this.id);
                } );
                const btnDelete = document.createElement("button");
                btnDelete.setAttribute("class", "btn-action btn-delete");
                btnDelete.textContent = "Delete";
                btnDelete.id = tableRow.id;
                btnDelete.onclick = function (){
                    deleteItemClicked(this.id);
                }
                actions.append(btnEdit, btnDelete);
                tableRow.append(actions);

                contentTable.appendChild(tableRow);
            }
        }
    };
    xhttp.open("GET", "/rest/user/list", true);
    xhttp.send();
}


function editItemClicked(userID){
    var hiddenId = document.getElementById("id");
    hiddenId.value = userID;
    var txtFirstName = document.getElementById("firstname");
    var txtLastName = document.getElementById("lastname");
    var txtEmail = document.getElementById("email");
    var txtPassword = document.getElementById("password");

    var rows = document.querySelectorAll("tr");
    for (i = 0; i < rows.length; i++) {
        if(rows[i].id == userID){
            txtFirstName.value = rows[i].querySelector("td.data-field-firstname").textContent;
            txtLastName.value = rows[i].querySelector("td.data-field-lastname").textContent;
            txtEmail.value = rows[i].querySelector("td.data-field-email").textContent;
            txtPassword.value = rows[i].querySelector("td.data-field-password").textContent;
        }
    }
}


function deleteItemClicked(userID) {
    var xhttp = new XMLHttpRequest();

    const params = new URLSearchParams();
    params.set('id', userID);

    xhttp.open("DELETE", "/rest/user/delete?" + params.toString(), true);
    xhttp.onreadystatechange = function () {
        if (xhttp.readyState > 3 && xhttp.status == 204) {
            clearInputFields();
            loadUsers();
        }
    };
    xhttp.setRequestHeader("Content-Type", "application/json");
    xhttp.send();
}
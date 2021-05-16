loadCompanies();
clearInputFields();

function clearInputFields(){
    document.getElementById("name").value = "";
    document.getElementById("id").value = "-1";
}

function save() {
    var name = document.getElementById("name").value;
    if(name === "" || name == "")
    {
        alert("Name cannot be empty");
        return;
    }
    var hiddenId = document.getElementById("id");
    if(hiddenId.value == -1) {
        let company = {"companyID": null, "name": document.getElementById("name").value}
        var xmlhttp = new XMLHttpRequest();
        xmlhttp.open("POST", "/rest/company/add", true);
        xmlhttp.onreadystatechange = function () {
            if (xmlhttp.readyState > 3 && xmlhttp.status == 200) {
                clearInputFields();
                loadCompanies();
            }
        };
        xmlhttp.setRequestHeader("Content-Type", "application/json");
        xmlhttp.send(JSON.stringify(company));
    }else{
        //update
        let company = {"companyID":hiddenId.value, "name": document.getElementById("name").value}
        var xmlhttp = new XMLHttpRequest();
        xmlhttp.open("PUT", "/rest/company/update", true);
        xmlhttp.onreadystatechange = function () {
            if (xmlhttp.readyState > 3 && xmlhttp.status == 200) {
                clearInputFields();
                loadCompanies();
            }
        };
        xmlhttp.setRequestHeader("Content-Type", "application/json");
        xmlhttp.send(JSON.stringify(company));
    }
}

function loadCompanies() {
    var contentTable = document.getElementById("tableMain");
    let xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            var companyDataList = JSON.parse(this.responseText);

            var paras = document.getElementsByClassName('data-row');
            while (paras[0]) {
                paras[0].parentNode.removeChild(paras[0]);
            }

            for (let index = 0; index < companyDataList.length; index++) {
                var tableRow = document.createElement("tr");
                var companyID = companyDataList[index].companyID;
                tableRow.setAttribute("class", "data-row");
                tableRow.id = companyDataList[index].companyID;
                tableRow.innerHTML = '<td class="data-field-id">' + companyDataList[index].companyID + '</td>' +
                    '<td class="data-field-name" >' + companyDataList[index].name + '</td>';

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
    xhttp.open("GET", "/rest/company/list", true);
    xhttp.send();
}


function editItemClicked(companyID){
    var hiddenId = document.getElementById("id");
    hiddenId.value = companyID;
    var txtName = document.getElementById("name");
    var companyName;

    var rows = document.querySelectorAll("tr");
    for (i = 0; i < rows.length; i++) {
        if(rows[i].id == companyID){
            companyName = rows[i].querySelector("td.data-field-name").textContent;
        }
    }
    txtName.value = companyName;
}


function deleteItemClicked(companyID){
    var xhttp = new XMLHttpRequest();

    const params = new URLSearchParams();
    params.set('id', companyID);

    xhttp.open("DELETE", "/rest/company/delete?" + params.toString(), true);
    xhttp.onreadystatechange = function() {
        if (xhttp.readyState>3 && xhttp.status==204) {
            clearInputFields();
            loadCompanies(); }
    };
    xhttp.setRequestHeader("Content-Type", "application/json");
    xhttp.send();

}
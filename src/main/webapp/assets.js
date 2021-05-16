loadAssets();
clearInputFields();


function clearInputFields(){
    document.getElementById("companies").selectedIndex = -1;
    document.getElementById("assetTypes").selectedIndex  = -1;
    document.getElementById("companiesHidden").value = "";
    document.getElementById("assetTypesHidden").value = "";
    document.getElementById("id").value = -1;
    document.getElementById("name").value = "";
    document.getElementById("manufacturer").value = "";
    document.getElementById("quantity").value = 1;
}

function save() {
    var hiddenId = document.getElementById("id");
    if(hiddenId.value == -1) {
        let asset = {"assetID": null, "company" : document.getElementById("companies").value,
                        "assetType": document.getElementById("assetTypes").value,
                        "name": document.getElementById("name").value,
                        "manufacturer": document.getElementById("manufacturer").value,
                        "quantity":document.getElementById("quantity").value }
        var xmlhttp = new XMLHttpRequest();
        xmlhttp.open("POST", "/rest/asset/add", true);
        xmlhttp.onreadystatechange = function () {
            if (xmlhttp.readyState > 3 && xmlhttp.status == 200) {
                clearInputFields();
                loadAssets();
            }
        };
        xmlhttp.setRequestHeader("Content-Type", "application/json");
        xmlhttp.send(JSON.stringify(asset));
    }else{
        //update
        let asset = {"assetID": hiddenId.value, "company" : document.getElementById("companies").value,
            "assetType": document.getElementById("assetTypes").value,
            "name": document.getElementById("name").value,
            "manufacturer": document.getElementById("manufacturer").value,
            "quantity":document.getElementById("quantity").value }
        var xmlhttp = new XMLHttpRequest();
        xmlhttp.open("PUT", "/rest/asset/update", true);
        xmlhttp.onreadystatechange = function () {
            if (xmlhttp.readyState > 3 && xmlhttp.status == 200) {
                clearInputFields();
                loadAssets();
            }
        };
        xmlhttp.setRequestHeader("Content-Type", "application/json");
        xmlhttp.send(JSON.stringify(asset));
    }
}

function loadAssets() {
    var contentTable = document.getElementById("tableMain");
    let xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            var assetDataList = JSON.parse(this.responseText);

            var paras = document.getElementsByClassName('data-row');
            while (paras[0]) {
                paras[0].parentNode.removeChild(paras[0]);
            }

            for (let index = 0; index < assetDataList.length; index++) {
                var tableRow = document.createElement("tr");
                var assetID = assetDataList[index].assetID;
                tableRow.setAttribute("class", "data-row");
                tableRow.id = assetDataList[index].assetID;
                tableRow.innerHTML = '<td class="data-field-id">' + assetDataList[index].assetID + '</td>' +
                    '<td class="data-field-company" >' + assetDataList[index].company.name + '</td>' +
                    '<td class="data-field-asset-type" >' + assetDataList[index].assetType.name + '</td>' +
                    '<td class="data-field-name" >' + assetDataList[index].name + '</td>' +
                    '<td class="data-field-manufacturer" >' + assetDataList[index].manufacturer + '</td>' +
                    '<td class="data-field-quantity" >' + assetDataList[index].quantity + '</td>';

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
    xhttp.open("GET", "/rest/asset/list", true);
    xhttp.send();
}


function editItemClicked(assetID){
    var hiddenId = document.getElementById("id");
    hiddenId.value = assetID;
    var txtName = document.getElementById("name");
    var companiesSelect = document.getElementById("companies");
    var assetTypesSelect = document.getElementById("assetTypes");
    var txtManufacturer = document.getElementById("manufacturer");
    var txtQuantity = document.getElementById("quantity");

    var rows = document.querySelectorAll("tr");
    for (i = 0; i < rows.length; i++) {
        if(rows[i].id == assetID){
            companiesSelect.value = rows[i].querySelector("td.data-field-company").textContent;
            assetTypesSelect.value = rows[i].querySelector("td.data-field-asset-type").textContent;
            txtName.value = rows[i].querySelector("td.data-field-name").textContent;
            txtQuantity.value = rows[i].querySelector("td.data-field-quantity").textContent;
            txtManufacturer.value = rows[i].querySelector("td.data-field-manufacturer").textContent;
        }
    }
}


function deleteItemClicked(assetID){
    var xhttp = new XMLHttpRequest();

    const params = new URLSearchParams();
    params.set('id', assetID);

    xhttp.open("DELETE", "/rest/asset/delete?" + params.toString(), true);
    xhttp.onreadystatechange = function() {
        if (xhttp.readyState>3 && xhttp.status==204) {
            clearInputFields();
            loadAssets(); }
    };
    xhttp.setRequestHeader("Content-Type", "application/json");
    xhttp.send();

}


function loadRelatedData() {
    getCompanies();
    getAssetTypes();
}

function getCompanies() {
    var companySelect = document.getElementById("companies");
    let xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            var companyList = JSON.parse(this.responseText);

            var paras = document.getElementsByClassName('company-option');
            while (paras[0]) {
                paras[0].parentNode.removeChild(paras[0]);
            }

            for (let index = 0; index < companyList.length; index++) {
                var option = document.createElement("option");
                option.setAttribute("class", "company-option");
                option.value = companyList[index];
                option.textContent = companyList[index];
                companySelect.appendChild(option);
            }
        }
    };
    xhttp.open("GET", "/rest/company/listNames", true);
    xhttp.send();
}

function getAssetTypes() {
    var assetTypeSelect = document.getElementById("assetTypes");
    let xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            var assetTypeList = JSON.parse(this.responseText);

            var paras = document.getElementsByClassName('assetType-option');
            while (paras[0]) {
                paras[0].parentNode.removeChild(paras[0]);
            }

            for (let index = 0; index < assetTypeList.length; index++) {
                var option = document.createElement("option");
                option.setAttribute("class", "assetType-option");
                option.value = assetTypeList[index];
                option.textContent = assetTypeList[index];
                assetTypeSelect.appendChild(option);
            }
        }
    };
    xhttp.open("GET", "/rest/assetType/listNames", true);
    xhttp.send();
}
loadAssetTypes();
clearInputFields();


function clearInputFields(){
    document.getElementById("name").value = "";
    document.getElementById("id").value = "-1";
}

function save() {
    var hiddenId = document.getElementById("id");
    if(hiddenId.value == -1) {
        let assetType = {"assetTypeID": null, "name": document.getElementById("name").value}
        var xmlhttp = new XMLHttpRequest();
        xmlhttp.open("POST", "/rest/assetType/add", true);
        xmlhttp.onreadystatechange = function () {
            if (xmlhttp.readyState > 3 && xmlhttp.status == 200) {
                clearInputFields();
                loadAssetTypes();
            }
        };
        xmlhttp.setRequestHeader("Content-Type", "application/json");
        xmlhttp.send(JSON.stringify(assetType));
    }else{
        //update
        let assetType = {"assetTypeID":hiddenId.value, "name": document.getElementById("name").value}
        var xmlhttp = new XMLHttpRequest();
        xmlhttp.open("PUT", "/rest/assetType/update", true);
        xmlhttp.onreadystatechange = function () {
            if (xmlhttp.readyState > 3 && xmlhttp.status == 200) {
                clearInputFields();
                loadAssetTypes();
            }
        };
        xmlhttp.setRequestHeader("Content-Type", "application/json");
        xmlhttp.send(JSON.stringify(assetType));
    }
}

function loadAssetTypes() {
    var contentTable = document.getElementById("tableMain");
    let xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            var assetTypeDataList = JSON.parse(this.responseText);

            var paras = document.getElementsByClassName('data-row');
            while (paras[0]) {
                paras[0].parentNode.removeChild(paras[0]);
            }

            for (let index = 0; index < assetTypeDataList.length; index++) {
                var tableRow = document.createElement("tr");
                var assetTypeID = assetTypeDataList[index].assetTypeID;
                tableRow.setAttribute("class", "data-row");
                tableRow.id = assetTypeDataList[index].assetTypeID;
                tableRow.innerHTML = '<td class="data-field-id">' + assetTypeDataList[index].assetTypeID + '</td>' +
                    '<td class="data-field-name" >' + assetTypeDataList[index].name + '</td>';

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
    xhttp.open("GET", "/rest/assetType/list", true);
    xhttp.send();
}


function editItemClicked(assetTypeID){
    var hiddenId = document.getElementById("id");
    hiddenId.value = assetTypeID;
    var txtName = document.getElementById("name");
    var assetTypeName;

    var rows = document.querySelectorAll("tr");
    for (i = 0; i < rows.length; i++) {
        if(rows[i].id == assetTypeID){
            assetTypeName = rows[i].querySelector("td.data-field-name").textContent;
        }
    }
    txtName.value = assetTypeName;
}


function deleteItemClicked(assetTypeID){
    var xhttp = new XMLHttpRequest();

    const params = new URLSearchParams();
    params.set('id', assetTypeID);

    xhttp.open("DELETE", "/rest/assetType/delete?" + params.toString(), true);
    xhttp.onreadystatechange = function() {
        if (xhttp.readyState>3 && xhttp.status==204) {
            clearInputFields();
            loadAssetTypes(); }
    };
    xhttp.setRequestHeader("Content-Type", "application/json");
    xhttp.send();

}
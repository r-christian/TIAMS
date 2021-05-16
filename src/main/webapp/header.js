createHeader();

function createHeader() {
    headerDiv = document.createElement("div");
    headerDiv.id = "topnav";
    headerDiv.setAttribute("class", "topnav");
    headerDiv.innerHTML = "<a class=\"btn active\" href=\"http://localhost:8080/\">DLMS</a>\n" +
        "    <a class=\"btn\" href=\"http://localhost:8080/\">Companies</a>\n" +
        "    <a class=\"btn\" href=\"http://localhost:8080/assetTypes.html\">Asset Types</a>\n" +
        "    <a class=\"btn\" href=\"http://localhost:8080/assets.html\">Assets</a>\n" +
        "    <a class=\"btn\" href=\"http://localhost:8080/users.html\">Users</a>\n" +
        "    <a class=\"btn\" href=\"/logout.jsp\">Log out</a>";

    const body = document.getElementById("main-body");
    body.insertAdjacentElement("afterbegin", headerDiv);
}
"use strict"
window.onload = function() {
    getReim();
};

function getReim() {
    console.log("Func reim");

    fetch("http://localhost:7000/getreimbursements", {
        method: "GET",
        credentials: "include"
    }).then((data) => {

        return data.json();
    }).then((response) => {

        console.log("Display");
        displayTable(response, "All");

        let filter = document.querySelector("#filterByStatus");
        filter.addEventListener("change", function() {
            displayTable(response, filter.value);
        })
    })
}

function getCookie(cname) {
    var name = cname + "=";
    var decodedCookie = decodeURIComponent(document.cookie);
    var ca = decodedCookie.split(';');
    for (var i = 0; i < ca.length; i++) {
        var c = ca[i];
        while (c.charAt(0) == ' ') {
            c = c.substring(1);
        }
        if (c.indexOf(name) == 0) {
            return c.substring(name.length, c.length);
        }
    }
    return "";
}

function displayTable(response, status) {
    console.log("displayTable");
    let tbody = document.querySelector("table tbody");
    tbody.id = "tbody";
    tbody.innerHTML = "";


    let role = getCookie("userRole");
    console.log(role);

    for (let i = 0; i < response.length; i++) {
        if (status == "All" || response[i].status == status) {
            let row = document.createElement("tr");
            for (let val in response[i]) {
                let td = document.createElement("td");

                var sub;
                var rec;
                if (val == "submitted") {
                    sub = new Date(response[i][val]);

                    td.innerHTML = sub;
                    row.appendChild(td);
                } else if (val == "resolved") {
                    if (response[i][val] == null) {
                        rec = "-";
                        td.innerHTML = rec;
                        row.appendChild(td);
                    } else {
                        rec = new Date(response[i][val]);
                        td.innerHTML = rec;
                        row.appendChild(td);
                    }
                } else if (val == "resolverName") {
                    if (response[i][val] == null) {
                        rec = "-";
                        td.innerHTML = rec;
                        row.appendChild(td);
                    } else {
                        td.innerHTML = response[i][val]
                    }
                } else if (response[i][val] == "Pending" && role == "2") {
                    let id = response[i]['id'];
                    console.log(id);
                    td.innerHTML = `<button onclick='approveReim(${id})'>Approve</button> <br> <button onclick = 'deniedReim(${id})'>Denied</button>`;
                } else if (val == "receipt") {
                    //let file = `<td><input type="file" name="fileUpload"/></td>`;
                    if (response[i][val] == [] || response[i][val] == null) {
                        td.innerHTML = "file";
                    } else {
                        let image = document.createElement("img");
                        image.src = `data:image/png;base64,${response[i][val]}`;
                        image.style = `width:50px; height:50px`;
                        td.appendChild(image);
                    }
                } else {
                    td.innerHTML = response[i][val];
                }
                row.appendChild(td);
                //console.log(row);
            }
            tbody.appendChild(row);
            //console.log(tbody);
        }
    }
}

function approveReim(id) {
    fetch(`http://localhost:7000/reimbursement/${id}/approveReimbursement`, {
        method: "POST",
        credentials: "include"
    }).then((data) => {
        return data.json();
    }).then((response) => {
        getReim();
    })
}

function deniedReim(id) {
    fetch(`http://localhost:7000/reimbursement/${id}/deniedReimbursement`, {
        method: "POST",
        credentials: "include"
    }).then((data) => {
        return data.json();
    }).then((response) => {
        getReim();
    })
}
"use strict"
window.onload = function() {
    logout();
};

function logout() {
    fetch(`http://localhost:7000/logout`, {
        method: "POST",
        credentials: "include"
    }).then((data) => {
        console.log(data);
        if (data.status === 200) {
            window.location.href = '/index.html';
            deleteCookies();
        }
        return data.json();
    }).then((response) => {
        console.log(response);
    })
}

function deleteCookies() {
    var allCookies = document.cookie.split(';');

    for (var i = 0; i < allCookies.length; i++)
        document.cookie = allCookies[i] + "=;expires=" +
        new Date(0).toUTCString();

    displayCookies.innerHTML = document.cookie;
}
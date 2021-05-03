window.onload = function() {
    renderCurrentUser();
}

function renderCurrentUser() {
    fetch('http://localhost:7000/current_user', {
        method: 'GET',
        credentials: 'include'
    }).then((response) => {
        if (response.status === 400) {
            window.location.href = '/';
            //back to homepage for login
        }
        return response.json();

    }).then((data) => {
        let username = data.ers_userName;
        let password = data.ers_password;

        let userInfoElement = document.querySelector('#user');
        userInfoElement.innerHTML = `ers_userName: ${username}, ers_password: ${password}`
    })
}
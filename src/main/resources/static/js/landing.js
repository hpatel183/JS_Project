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
        console.log(response);
        return response.json();

    }).then((data) => {
        let firstname = data.userFirstName;
        // let password = data.ers_password;
        let role = data.user_roleId.ers_user_roles_id;

        let stringRole = data.user_roleId.user_role;

        let userInfoElement = document.querySelector('#user');
        userInfoElement.innerHTML = `Hello, ${firstname}`;

        console.log(stringRole);
        let roleDisplay = document.querySelector('#role');
        roleDisplay.innerHTML = `${stringRole}`;

        document.cookie = "userRole=" + role;
        //alert(document.cookie);


        console.log("userRole" + role);
    })
}
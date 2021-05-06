document.querySelector('#login').addEventListener('click', login);

function login() {
    let adduserInput = {
        ers_userName: document.querySelector('#ers_userName').value,
        ers_password: document.querySelector('#ers_password').value,
        userEmail: document.querySelector('#userEmail').value,
        userFirstName: document.querySelector('#userFirstName').value,
        userLastName: document.querySelector('#userLastName').value,
        userRole: document.querySelector('#userRole').value
    }
    console.log(adduserInput);

    fetch('http://localhost:7000/addUser', {
        method: 'POST',
        credentials: 'include', // this specifies that when you receive cookies, you should include them in future requests. So in our case, it's important so that the backend can identify if we are logged in or not.
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(adduserInput)
    }).then((data) => {
        console.log(data);
        if (data.status === 400) {
            alert("Invalid Entry");
        } else if (data.status === 201) {

            alert("User Successfully added");
        }
        return data.json();

    }).then((response) => {
        console.log(response);
    })

}
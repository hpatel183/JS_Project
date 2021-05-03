document.querySelector('#login').addEventListener('click', login);

function login() {
    let loginInput = {
        ers_userName: document.querySelector('#ers_userName').value,
        ers_password: document.querySelector('#ers_password').value
    }
    console.log(loginInput);

    fetch('http://localhost:7000/login', {
        method: 'POST',
        credentials: 'include', // this specifies that when you receive cookies, you should include them in future requests. So in our case, it's important so that the backend can identify if we are logged in or not.
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(loginInput)
    }).then((data) => {
        console.log(data);
        if (data.status === 400) {
            displayInvalidLogin();
        } else if (data.status === 200) {
            window.location.href = '/landing.html';
        }
        return data.json();

    }).then((response) => {

        console.log(response);

    })
}

function displayInvalidLogin() {
    let bodyElement = document.querySelector('body');

    let pElement = document.createElement('p');
    pElement.style.color = 'red';
    pElement.innerHTML = 'Invalid login!';

    bodyElement.appendChild(pElement);
}
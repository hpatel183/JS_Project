document.querySelector('#addReim').addEventListener('click', addReim);

function addReim() {
    let receiptUpload = new FormData();

    receiptUpload.append("reimb_amount", document.querySelector('#reimb_amount').value);
    receiptUpload.append("reimb_description", document.querySelector('#reimb_description').value);

    let receiptFile = document.getElementById("reimb_receipt").files[0];
    if (receiptFile) {
        receiptUpload.append("reimb_receipt", receiptFile, receiptFile.name);
    } else {
        receiptUpload.append("reimb_receipt", "");
    }
    receiptUpload.append("reimType", document.querySelector('#reimType').value)

    for (let item of receiptUpload.entries()) {
        console.log("form data ", item);
    }

    fetch('http://localhost:7000/addreimbursement', {
        method: 'POST',
        credentials: 'include', // this specifies that when you receive cookies, you should include them in future requests. So in our case, it's important so that the backend can identify if we are logged in or not.
        body: receiptUpload
    }).then((data) => {
        console.log(data);
        if (data.status === 400) {
            alert("Invalid Entry");
        } else if (data.status === 201) {
            alert("Reimbursement Successfully added");
        }
        return data.json();

    }).then((response) => {
        console.log(response);
    })

}
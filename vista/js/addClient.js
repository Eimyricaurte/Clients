function addClient(){
    let id = document.getElementById("input-code-client").value;
    let name = document.getElementById("input-name-client").value;
    let date = document.getElementById("input-date").value;
    let phone = document.getElementById("input-phone").value;
    let email = document.getElementById("input-email").value;
    let clientData = {
        id: id,
        name: name,
        subscriptionDate: date,
        phoneNumber: phone,
        email: email
    };

    let url = 'http://localhost:8080/Clients/rest/ManagementClient/createClient';
    fetch(url, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(clientData)
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('Ocurrió un error en la respuesta del servidor: ' + response.statusText);
        }
        return response.json();
    })
    .then(data => {
        alert("Se agregó el registro.");
        window.location.href = "./dashboard.html";
    })
    .catch(error => {
        console.error('Ocurrió el siguiente error con la operación: ', error);
    });
}
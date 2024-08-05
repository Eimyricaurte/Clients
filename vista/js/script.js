function validateUser(){
    let userName = document.getElementById("name").value;
    let password = document.getElementById("password").value;

    /* Propiedad para redirigir */
    //window.location.href = "nueva_pagina.html";

    fetch('http://localhost:8080/Clients/rest/ManagementUser/validateUser?nameUser='+ userName +'&password='+ password)
    .then(response => response.json())
    .then(response => {
        if (response ){
            window.location.href = "./../dashboard.html";
        } else{
            alert("Usuario no encontrado");
        }
    })
    .catch(error => console.error('Error:', error));

}

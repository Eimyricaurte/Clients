document.addEventListener('DOMContentLoaded', function () {
  const menuLinks = document.querySelectorAll('.nav-link');

  menuLinks.forEach(link => {
      link.addEventListener('click', function () {
          menuLinks.forEach(item => item.classList.remove('active'));
          this.classList.add('active');
      });
  });
});

document.getElementById('button-clients').addEventListener('click', function(event) {
  event.preventDefault();
  loadClients();
});

document.getElementById('button-services').addEventListener('click', function(event) {
  event.preventDefault();
  loadServices(); 
});
document.getElementById('button-user').addEventListener('click', function(event) {
  event.preventDefault();
  loadUsers(); 
});


function loadClients(){
  const content = document.getElementById('content');

  const cardAdd = document.createElement('div');
  cardAdd.className = 'card';

  const cardBodyAdd = document.createElement('div');
  cardBodyAdd.className = 'card-body';

  const btnAdd = document.createElement('a');
  btnAdd.className = 'btn btn-primary';
  btnAdd.href = './addClient.html';

  const imgAdd = document.createElement('img');
  imgAdd.src = 'resources/icons/Add_User_icon-icons.com_55971.png';

  const lblAdd = document.createElement('h3');
  lblAdd.textContent = '¡Agregar cliente!';

  /** Se agrega el ícono el botón */
  btnAdd.appendChild(imgAdd);

  /** Se agrega botón y título al cuerpo de la carta */
  cardBodyAdd.appendChild(btnAdd);
  cardBodyAdd.appendChild(lblAdd);

  cardAdd.appendChild(cardBodyAdd);
  content.appendChild(cardAdd);
  
  fetch('http://localhost:8080/Clients/rest/ManagementClient/getClients')
  .then(response => response.json())
  .then((data) => {
      const content = document.getElementById('content');
      data.forEach(client => {
          const card = document.createElement('div');
          card.className = 'card';

          const cardBody = document.createElement('div');
          cardBody.className = 'card-body';

          /** Se hace la creación de cada componente */
          /** Creamos la sección de título */
          const name = document.createElement('h2');
          name.className = 'card-title';
          name.textContent = client.name;
          
          /** Creamos la sección de Autor */
          const date = document.createElement('p');
          date.className = 'card-text';
          date.textContent = `Fecha de suscripción: ${client.subscriptionDate}`;

          /** Creamos la sección de Género */
          const phone = document.createElement('p');
          phone.className = 'card-text';
          phone.textContent = `Número de telefono: ${client.phoneNumber}`;

          /** Creamos la sección de la editorial */
          const email = document.createElement('p');
          email.className = 'card-text';
          email.textContent = `Correo electronico: ${client.email}`;


          /* Creación de botones de eliminar */
          const btnEliminar = document.createElement('button');
          btnEliminar.className = 'btn-danger';
          btnEliminar.id = `btn-delete-${client.id}`;
          btnEliminar.textContent = `Eliminar`;
          btnEliminar.setAttribute('data-code', client.id);

          // Agregar event listener al botón
          btnEliminar.addEventListener('click', function() {
              const clientId = this.getAttribute('data-code');
              deleteClientById(clientId);
          });

          /* Creación del botón de actualizar */
          const btnActualizar = document.createElement('a');
          btnActualizar.className = 'btn-success margin';
          btnActualizar.id = `btn-delete-${client.id}`;
          btnActualizar.textContent = `Actualizar`;

          // Agregar event listener al botón
          btnActualizar.addEventListener('click', function() {
              localStorage.setItem("clientData", JSON.stringify(client));
              window.location.href = "./updateClient.html";
          });

          /** Agregamos los componentes al body */
          cardBody.appendChild(name);
          cardBody.appendChild(date);
          cardBody.appendChild(phone);
          cardBody.appendChild(email);

          /* Agregamos el botón eliminar */
          cardBody.appendChild(btnEliminar);

          /* Agregamos el botón eliminar */
          cardBody.appendChild(btnActualizar);

          /** Agregamos el body al card */
          card.appendChild(cardBody);

          /** Agregamos el card al content */
          content.appendChild(card);
      })
  })
  .catch(error => console.error('Error:', error));
}

loadClients();

function cleanContent(){
  const content = document.getElementById('content');
  content.innerHTML = "";
}

function deleteClientById(code){
  let url = 'http://localhost:8080/Clients/rest/ManagementClient/deleteClient?id='+code;
  fetch(url, {
      method: 'DELETE'
  })
  .then(response => {
      if (!response.ok) {
          throw new Error('Ocurrió un error en la respuesta del servidor: ' + response.statusText);
      }
      return response.json();
  })
  .then(data => {
      alert("Se eliminó el registro");
      cleanContent();
      loadClients();
  })
  .catch(error => {
      console.error('Ocurrió el siguiente error con la operación: ', error);
  });
}
/**------------------------------------------------------------------------------------------------------------ */

function loadServices(){
  const content = document.getElementById('content');

  const cardAdd = document.createElement('div');
  cardAdd.className = 'card';

  const cardBodyAdd = document.createElement('div');
  cardBodyAdd.className = 'card-body';

  const btnAdd = document.createElement('a');
  btnAdd.className = 'btn btn-primary';
  btnAdd.href = './addService.html';

  const imgAdd = document.createElement('img');
  imgAdd.src = 'resources/icons/add.png';

  const lblAdd = document.createElement('h3');
  lblAdd.textContent = '¡Agregar Servicio!';

  btnAdd.appendChild(imgAdd);

  cardBodyAdd.appendChild(btnAdd);
  cardBodyAdd.appendChild(lblAdd);

  cardAdd.appendChild(cardBodyAdd);
  content.appendChild(cardAdd);
  
  fetch('http://localhost:8080/Clients/rest/ManagementService/getServices')
  .then(response => response.json())
  .then((data) => {
      const content = document.getElementById('content');
      data.forEach(service => {
          const card = document.createElement('div');
          card.className = 'card';

          const cardBody = document.createElement('div');
          cardBody.className = 'card-body';

          const name = document.createElement('h2');
          name.className = 'card-title';
          name.textContent = service.name;

          const idClient = document.createElement('p');
          idClient.className = 'card-text';
          idClient.textContent = `Cliente: ${service.idClient}`;

          const code = document.createElement('p');
          code.className = 'card-text';
          code.textContent = `Código: ${service.code}`;

          const price = document.createElement('p');
          price.className = 'card-text';
          price.textContent = `Precio: ${service.price}`;

          const btnEliminar = document.createElement('button');
          btnEliminar.className = 'btn-danger';
          btnEliminar.id = `btn-delete-${service.idClient}`;
          btnEliminar.textContent = `Eliminar`;
          btnEliminar.setAttribute('data-code', service.idClient);

          btnEliminar.addEventListener('click', function() {
              const clientId = this.getAttribute('data-code');
              deleteServiceByCode(clientId);
          });

          const btnActualizar = document.createElement('a');
          btnActualizar.className = 'btn-success margin';
          btnActualizar.id = `btn-delete-${service.idClient}`;
          btnActualizar.textContent = `Actualizar`;

          btnActualizar.addEventListener('click', function() {
              localStorage.setItem("serviceData", JSON.stringify(service.idClient));
              window.location.href = "./updateServce.html";
          });

          /** Agregamos los componentes al body */
          cardBody.appendChild(name);
          cardBody.appendChild(idClient);
          cardBody.appendChild(code);
          cardBody.appendChild(price);

          /* Agregamos el botón eliminar */
          cardBody.appendChild(btnEliminar);

          /* Agregamos el botón eliminar */
          cardBody.appendChild(btnActualizar);

          /** Agregamos el body al card */
          card.appendChild(cardBody);

          /** Agregamos el card al content */
          content.appendChild(card);
      })
  })
  .catch(error => console.error('Error:', error));
}

function deleteServiceByCode(code){
  let url = 'http://localhost:8080/Clients/rest/ManagementService/deleteService?idClient='+code;
  fetch(url, {
      method: 'DELETE'
  })
  .then(response => {
      if (!response.ok) {
          throw new Error('Ocurrió un error en la respuesta del servidor: ' + response.statusText);
      }
      return response.json();
  })
  .then(data => {
      alert("Se eliminó el registro");
      cleanContent();
      loadClients();
  })
  .catch(error => {
      console.error('Ocurrió el siguiente error con la operación: ', error);
  });
}

/**------------------------------------------------------------------------------------------------------------- */
function loadUsers(){
  const content = document.getElementById('content');

  const cardAdd = document.createElement('div');
  cardAdd.className = 'card';

  const cardBodyAdd = document.createElement('div');
  cardBodyAdd.className = 'card-body';

  const btnAdd = document.createElement('a');
  btnAdd.className = 'btn btn-primary';
  btnAdd.href = './addClient.html';

  const imgAdd = document.createElement('img');
  imgAdd.src = 'resources/icons/add.png';

  const lblAdd = document.createElement('h3');
  lblAdd.textContent = '¡Agregar Usuario!';

  btnAdd.appendChild(imgAdd);

  cardBodyAdd.appendChild(btnAdd);
  cardBodyAdd.appendChild(lblAdd);

  cardAdd.appendChild(cardBodyAdd);
  content.appendChild(cardAdd);
  
  fetch('http://localhost:8080/Clients/rest/ManagementUser/getUsers')
  .then(response => response.json())
  .then((data) => {
      const content = document.getElementById('content');
      data.forEach(user => {
          const card = document.createElement('div');
          card.className = 'card';

          const cardBody = document.createElement('div');
          cardBody.className = 'card-body';

          const name = document.createElement('h2');
          name.className = 'card-title';
          name.textContent = user.name;

          const password = document.createElement('p');
          password.className = 'card-text';
          password.textContent = `Cliente: ${user.password}`;

          const btnEliminar = document.createElement('button');
          btnEliminar.className = 'btn-danger';
          btnEliminar.id = `btn-delete-${user.name}`;
          btnEliminar.textContent = `Eliminar`;
          btnEliminar.setAttribute('data-code', user.name);

          btnEliminar.addEventListener('click', function() {
              const nameUser = this.getAttribute('data-code');
              deleteServiceByCode(nameUser);
          });

          const btnActualizar = document.createElement('a');
          btnActualizar.className = 'btn-success margin';
          btnActualizar.id = `btn-delete-${user.name}`;
          btnActualizar.textContent = `Actualizar`;

          btnActualizar.addEventListener('click', function() {
              localStorage.setItem("serviceData", JSON.stringify(user.name));
              window.location.href = "./updateServce.html";
          });

          /** Agregamos los componentes al body */
          cardBody.appendChild(name);
          cardBody.appendChild(password);

          /* Agregamos el botón eliminar */
          cardBody.appendChild(btnEliminar);

          /* Agregamos el botón eliminar */
          cardBody.appendChild(btnActualizar);

          /** Agregamos el body al card */
          card.appendChild(cardBody);

          /** Agregamos el card al content */
          content.appendChild(card);
      })
  })
  .catch(error => console.error('Error:', error));
}
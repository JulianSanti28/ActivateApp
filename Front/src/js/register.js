$(document).ready(function () {
    //Pendiente al envio del formulario
    const form = document.getElementById("formulario");
    form.addEventListener("submit", e => {
        //Validacion del formulario antes de realizar la peticion
        e.preventDefault();
        const password = document.getElementById('password');
        const validPassword = document.getElementById('validPassword');

        if (password.value == validPassword.value) {
            registrarUsuario(password);
        } else {
            alert("Las contraseñas no coinciden")
        }
    })
});

async function registrarUsuario(password) {

    //Construimos el JSON de usuario
    let datos = {};
    datos.name = document.getElementById('name').value;
    datos.lastName = document.getElementById('lastName').value;
    datos.email = document.getElementById('mail').value;
    datos.password = document.getElementById('password').value;

    const image_input = document.getElementById('inputFile');
    
    var myHeaders = new Headers();
    myHeaders.append("Content-Type", "application/json");

    const request = await fetch('http://localhost:8081/activate/create', {
        method: 'POST',
        headers: myHeaders,
        body: JSON.stringify(datos)
    }).then(response => response.json())
    .then(result => {
        if(result.id != null && image_input.files[0] != null){
            addImage(result.id,image_input.files[0]);
        }else{
            if(result.id == null){
                alert("La cuenta no fue creada!");
            }else{
                alert("La cuenta fue creada con exito!");
                window.location.href = 'login.html';
            }
        }
    });
}

function addImage(user_id,image_input){
    
    var myHeaders = new Headers();
    myHeaders.append("user_id", user_id);
    
    var formdata = new FormData();
    formdata.append("image",image_input);

    // console.log(formdata.get("image"))
    
    var requestOptions = {
      method: 'POST',
      headers: myHeaders,
      body: formdata,
      redirect: 'follow'
    };
    
    fetch("http://localhost:8081/activate/create/image", requestOptions)
      .then(response => response.text())
      .then(result => console.log(result))
      .catch(error => console.log('error', error));

    // console.log(formdata);
    window.location.href = 'login.html';
}
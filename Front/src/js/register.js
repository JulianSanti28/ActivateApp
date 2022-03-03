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
    datos.photo = document.getElementById('inputFile').value;
    datos.password = document.getElementById('password').value;

    //Realizamos la peticion al servidor
    const request = await fetch('http://localhost:8081/activate/create', {
        method: 'POST',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(datos)
    });

    alert("La cuenta fue creada con exito!");
    window.location.href = 'login.html'
}
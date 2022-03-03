
// const respuesta = "Correct";
// const peticion = {
//     "Token": "abcdefgh",
//     "usuario": "miguel",
//     "id": 1,
//     "img":"dist/img/user2-160x160.jpg"
// }

$(document).ready(function () {
    //Se limpia cualquier sesion iniciada anteriormente
    localStorage.removeItem("token");
    localStorage.removeItem("user");
    localStorage.removeItem("id");
    localStorage.removeItem("img");
    
    //Pendiente al envio del formulario
    // const form = document.getElementById("formulario");
    // form.addEventListener("submit", e => {
    //     e.preventDefault();
    // });
    // iniciarSesion();
});

async function iniciarSesion() {
    //Construimos el JSON para validar Usuario
    let datos = {};
    datos.email = document.getElementById('mail').value;
    datos.password = document.getElementById('password').value;

    // //Realizamos la peticion al servidor
    const request = await fetch('http://localhost:8081/activate/login', {
        method: 'POST',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(datos)
    });

    const respuesta = await request.text(); //Obtenemos la respuesta del Servidor en String
    const peticion = JSON.parse(respuesta); //Parseamos dicha respuesta a objeto JSON

    //Se inicia sesion y se almacenan los Token respectivos
    if (respuesta != 'FAIL') {
        localStorage.token = peticion.Token;
        localStorage.user = peticion.Usuario;
        localStorage.email = datos.email;
        localStorage.id = peticion.Id;
        //localStorage.img = peticion.img;
        window.location.href = 'profile.html'
    } else {
        alert("Las credenciales son incorrectas. Por favor intente nuevamente.");
    }


    
}
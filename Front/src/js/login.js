
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

    const respuesta = await request.json(); //Obtenemos la respuesta del Servidor en String
    console.log(respuesta);

    //Se inicia sesion y se almacenan los Token respectivos
    if (respuesta != 'FAIL') {
        localStorage.token = respuesta.token;
        localStorage.user = respuesta.user.name;
        localStorage.id = respuesta.user.id;
        localStorage.img = "data:image/jpg;base64," + respuesta.user.image;
        localStorage.removeItem("verEvento");
        window.location.href = 'profile.html'
    } else {
        alert("Las credenciales son incorrectas. Por favor intente nuevamente.");
    }


    
}
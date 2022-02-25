const form = document.getElementById("formulario");
const nombre = document.getElementById("nombre");
const contrasenia = document.getElementById("contrasenia");

form.addEventListener("submit", e=>{
    e.preventDefault();
    iniciarSesion();
});

async function iniciarSesion(){
    alert("Iniciando Sesion");
    window.location.href = 'profile.html';
}
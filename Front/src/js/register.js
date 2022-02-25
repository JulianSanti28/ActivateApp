
const nombre = document.getElementById('nombre');
const correo = document.getElementById('correo');
const password = document.getElementById('contrasenia');
const validContrasenia = document.getElementById('validContrasenia');

const form = document.getElementById("formulario");
form.addEventListener("submit", e=>{
    e.preventDefault();
    let valid = false;

    if(password.value == validContrasenia.value){
        valid = true
    }else{
        alert("Verifique las contraseñas")
    }

    if(valid){
        registrarUsuario();
    }
})

 
async function registrarUsuario() {
    
    window.location.href = 'login.html';
  
}
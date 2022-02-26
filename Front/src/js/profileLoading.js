// Evento para verificar que una sesion se encuentre activa
if (!localStorage.getItem("user")) {
    alert("Se debe Iniciar Sesion")
    window.location.href = 'login.html'
}
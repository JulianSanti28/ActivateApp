$(document).ready(function () {

    //Pendiente al cerrar Evento limpiar el localStorage
    const closeEvent = document.getElementById("goBack");
    closeEvent.addEventListener("click", e => {
        localStorage.removeItem('verEvento');
    });

});
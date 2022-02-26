//Pendiente al envio del formulario
const form = document.getElementById("eventForm");
form.addEventListener("submit", e => {
    //Validacion del formulario antes de realizar la peticion
    e.preventDefault();
    registrarEvento()
})


// function getHeaders() {
//   return {
//    'Accept': 'application/json',
//    'Content-Type': 'application/json',
//    'Authorization': localStorage.token
//  };
// }

async function registrarEvento() {
    let datos = {};
    datos.titulo = document.getElementById('input_titulo').value;
    datos.descripcion = document.getElementById('input_descripcion').value;
    datos.ubicacion = document.getElementById('input_ubicacion').value;
    datos.date = document.getElementById('reservationtime').value;
    // datos.fecha_inicio = document.getElementById('input_inicio').value;
    // datos.fecha_final = document.getElementById('input_fin').value;
    datos.date = document.getElementById('input_img').value;



    // const request = await fetch('http://localhost:8082/activate/event/create', {
    //     method: 'POST',
    //     headers: getHeaders(),
    //     body: JSON.stringify(datos)
    // });

    console.log(datos);
    alert("Evento creado con exito!");
    // window.location.href = 'user.html';


}
$(document).ready(function () {

    //Pendiente al envio del formulario
    const form = document.getElementById("PQRSForm");
    form.addEventListener("submit", e => {
        e.preventDefault();
        registrarPQRS();       
    })
});

function getHeaders() {
    return {
     'Accept': 'application/json',
     'Content-Type': 'application/json',
     'Authorization': localStorage.token
   };
}

async function registrarPQRS(){
    let datos = {};

    var select_request_type = document.getElementById("select_request_type"); /*Obtener el SELECT de tipo de Request*/
    datos.type = select_request_type.options[select_request_type.selectedIndex].value; /*Obtener id de la opción destino*/
    datos.description = document.getElementById('compose-textarea').value;
    datos.title = document.getElementById('request_tittle').value;
    // private String title;
    // private String description;
    // private String type;

    const request = await fetch('http://localhost:8081/activate/claim/create', {
        method: 'POST',
        headers: getHeaders(),
        body: JSON.stringify(datos)
    }).then(response => response.json())
    .then(result => {
        const contenedor_pqrs = document.getElementById("contenedor_pqrs");
        contenedor_pqrs.style.display = 'none';

        const contenedor_response = document.getElementById("contenedor_response");
        contenedor_response.style.display = 'block';
        let date = result.date;
        date = date.toString().replace(/\,/g,'-');
        let mensaje = "Claim Request Created Successfully<br>"+"Atendida por: " + result.email+"<br>"+"Fecha Registro: "+date;
        document.getElementById("request_responsable").innerHTML = mensaje;

    });

    
}
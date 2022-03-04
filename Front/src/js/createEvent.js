

$(document).ready(function () {
    //Cargar Opciones de Ciudad
    cargarCiudades();

    //Cargar Opciones de Categoria
    cargarCategorias();

    //Pendiente al envio del formulario
    const form = document.getElementById("eventForm");
    form.addEventListener("submit", e => {
        //Validacion del formulario antes de realizar la peticion
        e.preventDefault();
        registrarEvento()       
    })
});

async function cargarCiudades() {
    const request = await fetch('http://localhost:8081/activate/city/cities/all', {
        method: 'GET',
        //headers: getHeaders()
    });
    const ciudades = await request.json();

    for (let ciudad of ciudades) {
        var option = document.createElement("option");
        option.setAttribute('id',ciudad.ciu_id);
        var text = document.createTextNode(ciudad.ciu_name);
        option.appendChild(text);
        document.getElementById("input_ubicacion").appendChild(option);
    }
    
}

async function cargarCategorias(){
    const request = await fetch('http://localhost:8081/activate/category/categories/all', {
        method: 'GET',
        // headers: getHeaders()
    });
    const categorias = await request.json();

    for (let categoria of categorias) {
        var option = document.createElement("option");
        option.setAttribute('id',categoria.id);
        var text = document.createTextNode(categoria.nombre);
        option.appendChild(text);
        document.getElementById("input_categoria").appendChild(option);
    }
}

function getHeaders() {
  return {
   'Accept': 'application/json',
   'Content-Type': 'application/json',
   'Authorization': localStorage.token
 };
}

async function registrarEvento() {
    let datos = {};
    datos.titulo = document.getElementById('input_titulo').value;
    datos.descripcion = document.getElementById('input_descripcion').value;
    datos.ubicacion = "";
    let fecha = document.getElementById('reservationtime').value;
    let parts = fecha.split("-");
    datos.fecha_inicio = parts[0];
    datos.fecha_final = parts[1];
    //datos.idCategory = document.getElementById('input_ubicacion').value;
    var select_destination = document.getElementById("input_ubicacion"); /*Obtener el SELECT de Destino*/
    datos.idCity = select_destination.options[select_destination.selectedIndex].id; /*Obtener id de la opción destino*/
    console.log("numero de ciudad: =>"+datos.idCity);
    var select_category = document.getElementById("input_categoria"); /*Obtener el SELECT de Destino*/
    datos.idCategory = select_category.options[select_category.selectedIndex].id; /*Obtener id de la opción destino*/
    const image_input = document.getElementById('input_img');
    
    

    //console.log(datos);
    const request = await fetch('http://localhost:8081/activate/event/create', {
        method: 'POST',
        headers: getHeaders(),
        body: JSON.stringify(datos)
    }).then(response => response.json())
    .then(result => {
        if(result.id != null && image_input.files[0] != null){
            addImage(result.id,image_input.files[0]);
        }else{
            if(result.id == null){
                alert("Evento no fue Creado");
            }else{
                alert("Evento Creado sin imagen");
                window.location.href = 'profile.html';
            }
        }
    });

    
    
}

function addImage(event_id,image_input){
    
    var myHeaders = new Headers();
    myHeaders.append("event_id", event_id);
    
    var formdata = new FormData();
    formdata.append("image",image_input);

    // console.log(formdata.get("image"))
    
    var requestOptions = {
      method: 'POST',
      headers: myHeaders,
      body: formdata,
      redirect: 'follow'
    };
    
    fetch("http://localhost:8081/activate/event/create/image", requestOptions)
      .then(response => response.text())
      .then(result => console.log(result))
      .catch(error => console.log('error', error));


    // console.log(formdata);
    alert("Evento creado con exito!");
    window.location.href = 'profile.html';
}
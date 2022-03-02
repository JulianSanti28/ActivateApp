const ciudades = [
    {
        "id": 1,
        "nombre": "Ucrania",
    },
    {
        "id": 2,
        "nombre": "Popayan",
    }
]

const categorias = [
    {
        "id": 1,
        "nombre": "Guerra",
    },
    {
        "id": 2,
        "nombre": "Diversion",
    }
]

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

function cargarCiudades() {
    // const request = await fetch('http://localhost:8082/activate/event/', {
    //     method: 'GET',
    //     // headers: getHeaders()
    // });
    // const ciudades = await request.json();

    for (let ciudad of ciudades) {
        var option = document.createElement("option");
        option.setAttribute('id',ciudad.id);
        var text = document.createTextNode(ciudad.nombre);
        option.appendChild(text);
        document.getElementById("input_ubicacion").appendChild(option);
    }
    
}

function cargarCategorias(){
    // const request = await fetch('http://localhost:8082/activate/event/', {
    //     method: 'GET',
    //     // headers: getHeaders()
    // });
    // const categorias = await request.json();

    for (let categoria of categorias) {
        var option = document.createElement("option");
        option.setAttribute('id',categoria.id);
        var text = document.createTextNode(categoria.nombre);
        option.appendChild(text);
        document.getElementById("input_categoria").appendChild(option);
    }
}

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
    
    const image_input = document.getElementById('input_img').value;

    

    console.log(image_input);

    // const request = await fetch('http://localhost:8082/activate/event/create', {
    //     method: 'POST',
    //     headers: getHeaders(),
    //     body: JSON.stringify(datos)
    // });


    var myHeaders = new Headers();
    myHeaders.append("Authorization", "1");
    
    var formdata = new FormData();
    formdata.append("image", "/C:/Users/Sebastian_Arenas/Downloads/cachorros.jpg");
    formdata.append("event", JSON.stringify(datos));

    // console.log(formdata.get("image"))
    
    // var requestOptions = {
    //   method: 'POST',
    //   headers: myHeaders,
    //   body: formdata,
    //   redirect: 'follow'
    // };
    
    // fetch("localhost:8081/activate/event/create", requestOptions)
    //   .then(response => response.text())
    //   .then(result => console.log(result))
    //   .catch(error => console.log('error', error));




    // console.log(formdata);
    alert("Evento creado con exito!");
    // window.location.href = 'user.html';
}
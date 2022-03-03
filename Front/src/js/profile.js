// const eventos = [
//     {
//         "id": 1,
//         "titulo": "Guerra Mundial",
//         "descripcion": "Hagamos la Paz no la guerra",
//         "ubicacion": "Ucrania",
//         "fecha_inicio": "28/05/1998",
//         "fecha_final": "28/05/1998",
//         "img": "dist/img/photo2.png",
//         "category": {
//             "id": 1,
//             "nombre": "MASCOTAS",
//             "descripcion": "Espacio para temas de mÃ¡scotas"
//         },
//         "user": {
//             "id": 100,
//             "name": "Vladimir",
//             "lastName": "Putin",
//             "email": "JulianSmartusisossaa@gmail.com",
//             "img": "dist/img/user6-128x128.jpg",
//             "password": "$argon2id$v=19$m=1024,t=1,p=1$8dUsFXKXf/WLx6TywnA9Zg$EuDR6dA7+W32dwp1er+MPJ6TGgIKTXbAb1IsJTa3/hE"
//         },
//         "comments": [
//             {
//                 "id": 1,
//                 "descripcion": "Me gustó",
//                 "fechaComentario": "01/02/2022",
//                 "score": 2,
//                 "user": {
//                     "id": 1,
//                     "name": "Pruebas",
//                     "lastName": "Pruebas",
//                     "email": "JulianSmartusisossaa",
//                     "password": "$argon2id$v=19$m=1024,t=1,p=1$8dUsFXKXf/WLx6TywnA9Zg$EuDR6dA7+W32dwp1er+MPJ6TGgIKTXbAb1IsJTa3/hE"
//                 }
//             }
//         ],
//         "assistences": []
//     },
//     {
//         "id": 2,
//         "titulo": "Rumbita",
//         "descripcion": "Hola",
//         "ubicacion": "Popayan",
//         "fecha_inicio": "28/05/1998",
//         "fecha_final": "28/05/1998",
//         "img": "dist/img/photo2.png",
//         "category": {
//             "id": 1,
//             "nombre": "MASCOTAS",
//             "descripcion": "Espacio para temas de mÃ¡scotas"
//         },
//         "user": {
//             "id": 200,
//             "name": "Paula",
//             "lastName": "Peña",
//             "email": "JulianSmartusisossaa@gmail.com",
//             "img": "dist/img/user6-128x128.jpg",
//             "password": "$argon2id$v=19$m=1024,t=1,p=1$8dUsFXKXf/WLx6TywnA9Zg$EuDR6dA7+W32dwp1er+MPJ6TGgIKTXbAb1IsJTa3/hE"
//         },
//         "comments": [
//             {
//                 "id": 1,
//                 "descripcion": "Me gustó",
//                 "fechaComentario": "01/02/2022",
//                 "score": 2,
//                 "user": {
//                     "id": 1,
//                     "name": "Pruebas",
//                     "lastName": "Pruebas",
//                     "email": "JulianSmartusisossaa",
//                     "password": "$argon2id$v=19$m=1024,t=1,p=1$8dUsFXKXf/WLx6TywnA9Zg$EuDR6dA7+W32dwp1er+MPJ6TGgIKTXbAb1IsJTa3/hE"
//                 }
//             }
//         ],
//         "assistences": []
//     }

// ]


$(document).ready(function () {
    //Limpiar Locarl Storage
    localStorage.removeItem('user_profile_id');
    
    //Situar nombre del usuario Logeado
    document.getElementById("profileName").innerHTML = localStorage.user;
    document.getElementById("userImg").setAttribute("src", localStorage.img);
    //Cargar todos los eventos
    cargarEventos();

    //Pendiente al Cierre de Sesion
    const closeSession = document.getElementById("closeSession");
    closeSession.addEventListener("click", e => {
        localStorage.removeItem("token");
        localStorage.removeItem("user");
        localStorage.removeItem("id");
        localStorage.removeItem("img");
    });

});

// function getHeaders() {
//   return {
//    'Accept': 'application/json',
//    'Content-Type': 'application/json',
//    'Authorization': localStorage.token
//  };
// }

async function cargarEventos() {

    //Realizamos la peticion al servidor
    const request = await fetch('http://localhost:8081/activate/event/events/all', {
        method: 'GET',
        // headers: getHeaders()
    });
    const eventos = await request.json();

    let listadoHtml = '';
    for (let evento of eventos) {
        
        evento.image = "data:image/jpg;base64," + evento.image;
        listadoHtml += eventBody(evento);

        //console.log(evento.image);
    }
    document.querySelector('#eventList').innerHTML = listadoHtml;

}

function eventBody(evento) {
    console.log(evento);
    let eventoHtml = '<div class="col-12 col-sm-6 col-md-6 d-flex align-items-stretch flex-column">'
        + '<div class="card bg-light d-flex flex-fill">'
        + '<div class="card-body pt-1">'
        + '<div class="row">'
        + '<div class="col-7">'
        + '<div class="row">'
        + '<div class="user-block">'

        + '<img class="img-circle img-bordered-sm" src="'+evento.user.image+'"'
        + 'alt="X">'
        + '<span class="username">'
        + '<a onclick="seeUser(event)" id='+evento.user.id+' href="user-profile.html">'+evento.user.name+ ' '+evento.user.lastName+'</a>'
        + '</span>'

        + '</div>'
        + '</div>'

        + '<div class="text-center">'
        + '<h3 class=""><b>'+evento.titulo+'</b></h3>'
        + '</div>'
        + '<div class="row ">'
        + '<i class="fas fa-calendar"> '+evento.fecha_inicio+' / '+evento.fecha_final+'</i><br>'
        + '</div>'
        + '<div class="row ">'
        + '<i class="fas fa-map-marker-alt"> '+evento.city.ciu_name+'</i>'
        + '</div>'

        + '</div>'
        + '<div class="col-5 text-center">'

        + '<img src="'+evento.image+'" alt="X" class="img-circle img-fluid">'

        + '</div>'
        + '</div>'
        + '<div class="row pt-1">'

        + ''+evento.descripcion+''

        + '</div>'
        + '</div>'
        + '<div class="card-footer">'
        + '<div class="text-right">'
        + '<a onclick="verEvento(event)" href="event.html" class="btn btn-sm btn-primary">'
        + '<i class="fas fa-user"></i> Ver evento'
        + '</a>'
        + '<span id="demo" hidden="hidden">' + evento.id + '</span>'
        + '</div>'
        + '</div>'
        + '</div>'
        + '</div>'

    return eventoHtml;
}

function verEvento(event) {
    // console.log(event.target);
    const hijo = event.target.nextElementSibling;
    // console.log(hijo);
    localStorage.removeItem('verEvento');
    localStorage.verEvento = hijo.innerHTML;
}


function seeUser(event){
    const user_profile_id = event.target.id;

    localStorage.removeItem('user_profile_id');
    localStorage.user_profile_id = user_profile_id;
}

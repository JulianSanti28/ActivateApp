const eventosEJM = [
    {
        "id": 1,
        "titulo": "Guerra Mundial",
        "descripcion": "Hagamos la Paz no la guerra",
        "ubicacion": "Ucrania",
        "fecha_inicio": "28/05/1998",
        "fecha_final": "28/05/1998",
        "img": "dist/img/photo2.png",
        "category": {
            "id": 1,
            "nombre": "MASCOTAS",
            "descripcion": "Espacio para temas de mÃ¡scotas"
        },
        "user": {
            "id": 1,
            "name": "Vladimir",
            "lastName": "Putin",
            "email": "JulianSmartusisossaa@gmail.com",
            "img": "dist/img/user6-128x128.jpg",
            "password": "$argon2id$v=19$m=1024,t=1,p=1$8dUsFXKXf/WLx6TywnA9Zg$EuDR6dA7+W32dwp1er+MPJ6TGgIKTXbAb1IsJTa3/hE"
        },
        "comments": [
            {
                "id": 1,
                "descripcion": "Me gustó",
                "fechaComentario": "01/02/2022",
                "score": 2,
                "user": {
                    "id": 1,
                    "name": "Pruebas",
                    "lastName": "Pruebas",
                    "email": "JulianSmartusisossaa",
                    "password": "$argon2id$v=19$m=1024,t=1,p=1$8dUsFXKXf/WLx6TywnA9Zg$EuDR6dA7+W32dwp1er+MPJ6TGgIKTXbAb1IsJTa3/hE"
                }
            }
        ],
        "assistences": []
    },
    {
        "id": 2,
        "titulo": "Rumbita",
        "descripcion": "Hola",
        "ubicacion": "Popayan",
        "fecha_inicio": "28/05/1998",
        "fecha_final": "28/05/1998",
        "img": "dist/img/photo2.png",
        "category": {
            "id": 1,
            "nombre": "MASCOTAS",
            "descripcion": "Espacio para temas de mÃ¡scotas"
        },
        "user": {
            "id": 1,
            "name": "Paula",
            "lastName": "Peña",
            "email": "JulianSmartusisossaa@gmail.com",
            "img": "dist/img/user6-128x128.jpg",
            "password": "$argon2id$v=19$m=1024,t=1,p=1$8dUsFXKXf/WLx6TywnA9Zg$EuDR6dA7+W32dwp1er+MPJ6TGgIKTXbAb1IsJTa3/hE"
        },
        "comments": [
            {
                "id": 1,
                "descripcion": "Me gustó",
                "fechaComentario": "01/02/2022",
                "score": 2,
                "user": {
                    "id": 1,
                    "name": "Pruebas",
                    "lastName": "Pruebas",
                    "email": "JulianSmartusisossaa",
                    "password": "$argon2id$v=19$m=1024,t=1,p=1$8dUsFXKXf/WLx6TywnA9Zg$EuDR6dA7+W32dwp1er+MPJ6TGgIKTXbAb1IsJTa3/hE"
                }
            }
        ],
        "assistences": []
    }

]

let followFlag;

$(document).ready(function () {
    //Cargar Info del Usuario
    cargarUsuario();
    //Verificar un/follow
    verificarfollow()
    //Cargar todos los eventos creador por usuario
    //cargarEventosUsuario();
    //Pendiente al Follow
    const btnfollow = document.getElementById("followbtn");
    btnfollow.addEventListener("click", e => {
        console.log("aqui-> " + followFlag);
        if (followFlag) {
            console.log("follow");
            unfollow();
        } else {
            console.log("unfollow");
            follow();
        }

    });

});

function getHeaders() {
    return {
        'Accept': 'application/json',
        'Content-Type': 'application/json',
        'Authorization': localStorage.token,
        'Access-Control-Allow-Origin': '*'
    };
}

async function cargarUsuario() {

    // //Realizamos la peticion al servidor
    const request = await fetch('http://localhost:8081/activate/user/' + Number(localStorage.user_profile_id), {
        method: 'GET'
    });

    const respuesta = await request.json(); //Obtenemos la respuesta del Servidor en String
    console.log(respuesta);

    //Situar nombre del usuario Logeado
    document.getElementById("profileName").innerHTML = respuesta.name;
    document.getElementById("followers").innerHTML = respuesta.followerCount;
    document.getElementById("following").innerHTML = respuesta.followingCount;
    document.getElementById("userImg").setAttribute("src", "data:image/jpg;base64," + respuesta.image);

}

async function cargarEventosUsuario() {

    //Realizamos la peticion al servidor
    const request = await fetch('http://localhost:8081/activate/event/UserCreate/all/' + Number(localStorage.id), {
        method: 'GET',
        // headers: getHeaders()
    });
    const eventos = await request.json();

    let listadoHtml = '';
    for (let evento of eventos) {

        evento.image = "data:image/jpg;base64," + evento.image;
        evento.user.image = "data:image/jpg;base64," + evento.user.image;
        listadoHtml += eventBody(evento);

        //console.log(evento.image);
    }
    document.querySelector('#eventList').innerHTML = listadoHtml;

}

function eventBody(evento) {
    let eventoHtml = '<div class="col-12 col-sm-6 col-md-6 d-flex align-items-stretch flex-column">'
        + '<div class="card bg-light d-flex flex-fill">'
        + '<div class="card-body pt-1">'
        + '<div class="row">'
        + '<div class="col-7">'
        + '<div class="row">'
        + '<div class="user-block">'

        + '<img class="img-circle img-bordered-sm" src="' + evento.user.img + '"'
        + 'alt="User Image">'
        + '<span class="username">'
        + '<a href="user-profile.html">' + evento.user.name + ' ' + evento.user.lastName + '</a>'
        + '</span>'

        + '</div>'
        + '</div>'

        + '<div class="text-center">'
        + '<h3 class=""><b>' + evento.titulo + '</b></h3>'
        + '</div>'
        + '<div class="row ">'
        + '<i class="fas fa-calendar"> ' + evento.fecha_inicio + ' / ' + evento.fecha_final + '</i><br>'
        + '</div>'
        + '<div class="row ">'
        + '<i class="fas fa-map-marker-alt"> ' + evento.ubicacion + '</i>'
        + '</div>'

        + '</div>'
        + '<div class="col-5 text-center">'

        + '<img src="' + evento.img + '" alt="user-avatar" class="img-circle img-fluid">'

        + '</div>'
        + '</div>'
        + '<div class="row pt-1">'

        + '' + evento.descripcion + ''

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

async function verificarfollow() {
    let datos = {};
    datos.id = localStorage.user_profile_id;

    //Api para verificar si un usuario puede editar un evento. 
    const request = await fetch('http://localhost:8081/activate/user/follow/' + Number(localStorage.user_profile_id), {
        method: 'GET',
        headers: getHeaders()
    }).then(response => response.json())
        .then(result => {
            const btnFollow = document.getElementById("followtxt");
            if (result) {
                btnFollow.innerHTML = "UnFollow";
                followFlag = result;
            } else {
                btnFollow.innerHTML = "Follow";
                followFlag = result;
            }
        });

}

async function follow() {
    var myHeaders = new Headers();
    myHeaders.append("from_user", localStorage.id);
    myHeaders.append("to_user", localStorage.user_profile_id);
    fetch("http://localhost:8081/activate/follow/create",
        {
            method: 'POST',
            headers: myHeaders
        })
        .then(response => response.text())
        .then(result => console.log(result))
        .catch(error => console.log('error', error));

    window.location.href = 'user-profile.html'
}

async function unfollow() {
    let datos = {};
    datos.from_user = localStorage.id;
    datos.to_user = localStorage.user_profile_id;

    var requestOptions = {
        mode: 'cors',
        method: 'DELETE',
        headers: getHeaders(),
        redirect: 'follow'
        
    };

    fetch("http://localhost:8081/activate/follow/remove/"+ Number(localStorage.user_profile_id), requestOptions)
        .then(response => response.text())
        .then(result => console.log(result))
        .catch(error => console.log('error', error));

    //window.location.href = 'user-profile.html'
}

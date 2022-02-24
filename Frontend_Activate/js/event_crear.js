$(document).ready(function() {
    document.getElementById("user_front_name").innerHTML = localStorage.usuario;
    // on ready
 });

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
    datos.ubicacion = document.getElementById('input_ubicacion').value;
    datos.fecha_inicio = document.getElementById('input_inicio').value;
    datos.fecha_final = document.getElementById('input_fin').value;

    if(datos.titulo=="" || datos.descripcion=="" || datos.fecha_inicio=="" || datos.fecha_fin=="" || datos.ubicacion=="" ){
        alert("Debe completar todos los campos");
    }else{
  
        const request = await fetch('http://localhost:8082/activate/event/create', {
            method: 'POST',
            headers: getHeaders(),
            body: JSON.stringify(datos)
        });
        console.log(datos);
        alert("Evento creado con exito!");
        window.location.href = 'user.html';
    }
  
}
  
{
    "id": 1,
    "titulo": "Rumbitaaa",
    "descripcion": "Hola",
    "ubicacion": "Popayan",
    "fecha_inicio": "28/05/1998",
    "fecha_final": "28/05/1998",
    "category": {
        "id": 1,
        "nombre": "MASCOTAS",
        "descripcion": "Espacio para temas de mÃ¡scotas"
    },
    "user": {
        "id": 1,
        "name": "Pruebas",
        "lastName": "Pruebas",
        "email": "JulianSmartusisossaa",
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
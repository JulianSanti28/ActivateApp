// Call the dataTables jQuery plugin
$(document).ready(function () {
    cargarEventosRegistrados();
    $('#usuarios').DataTable();
});

// function getHeaders() {
//   return {
//    'Accept': 'application/json',
//    'Content-Type': 'application/json',
//    'Authorization': localStorage.token
//  };
// }

async function cargarEventosRegistrados() {

    const request = await fetch('http://localhost:8081/activate/assist/eventsUser/all/'+ localStorage.id, {
        method: 'GET',
        // headers: getHeaders()
    });
    const eventosRegistrados = await request.json();

    let listadoHtml = '';
    for (let evento of eventosRegistrados) {
        let usuarioHtml = '<tr><td>' + evento.id + '</td><td>' + evento.titulo  + '</td><td>'
            + evento.city.ciuName + '</td><td>' + evento.fechaInicio + '</td> <td>' + evento.fechaFinal
            + '</td>' + '</tr>';
        listadoHtml += usuarioHtml;
    }

    document.querySelector('#example1 tbody').innerHTML = listadoHtml;

}


async function eliminarUsuario(id) {
    if (!confirm('¿Desea eliminar este usuario?')) {
        return;
    }

    const request = await fetch('api/user/' + id, {
        method: 'DELETE',
        // headers: getHeaders()
    });

    location.reload()
}
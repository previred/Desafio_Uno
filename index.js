//Clase principal
const express = require('express')
const api_helper = require('./API_helper')
const app = express()
const port = 3000

app.get('/getNivel3', (req, res) => {
    let respuesta = {};
    api_helper.make_API_call('http://127.0.0.1:8080/periodos/api')
        .then(response => {
            //se transforman las fechas de str a date para poder trabajar con ellas
            let fechaInicio = new Date(response.fechaCreacion);
            let fechaFin = new Date(response.fechaFin);
            let fechas = diasEntreFechas(fechaInicio, fechaFin);
            let finalArray = compareArrays(fechas, response.fechas);
            //se construye la respuesta
            respuesta = {
                'error': false,
                'codigo': 200,
                'id': response.id,
                'fechaCreacion': response.fechaCreacion,
                'fechaFin': response.fechaFin,
                'totalFechasEntrePeriodo': fechas.length,
                'totalFechasEntregadas': response.fechas.length,
                'totalFechasFaltantes': finalArray.length,
                'fechas': response.fechas,
                'fechasFaltantes': finalArray
            }
            res.json(respuesta)
        })
        .catch(error => {
            respuesta = {
                'error': true,
                'codigo': 501,
                'mensaje': 'Ha ocurrido un error al consultar el ApiRest de Periodos'
            }
            res.send(respuesta)
        })
})

//metodo que devuelve un arreglo con todos los periodos entre la 
//fecha de Creacion y la fecha de fin
var diasEntreFechas = function (desde, hasta) {
    let fechaInicio = desde;
    let fechaFin = hasta;
    let fechas = [];

    while (fechaFin.getTime() >= fechaInicio.getTime()) {
        let fecha = fechaInicio.getUTCFullYear() + '-' +
            ((fechaInicio.getUTCMonth() + 1) < 10 ? '0' + (fechaInicio.getUTCMonth() + 1) : (fechaInicio.getUTCMonth() + 1))
            + '-' +
            (fechaInicio.getUTCDate() < 10 ? ('0' + fechaInicio.getUTCDate()) : fechaInicio.getUTCDate());
        fechas.push(fecha);
        fechaInicio.setUTCMonth(fechaInicio.getUTCMonth() + 1);
    }
    return fechas;
}

//metodo que devuelve los periodos no encontrados entre
//los arreglos de fechas
var compareArrays = function (arr1, arr2) {
    var finalArray = [];
    arr1.forEach((e1) => {
        let validador = 0;
        for (let i = 0; i < arr2.length; i++) {
            validador = e1.localeCompare(arr2[i]);
            if (validador === 0) {
                break;
            }
        }
        if (validador !== 0) {
            finalArray.push(e1);
        }
    });
    return finalArray;
}

app.listen(port, () => console.log(`Servicio levantado en el puerto: ${port}!`))
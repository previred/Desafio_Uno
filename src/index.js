var express = require("express");
var request = require("request");
var util = require("util");

var app = express();

var url = "http://127.0.0.1:8080/periodos/api";

request({ url: url, json: true }, (error, response, body) => {

    if (!error && response.statusCode === 200) {

        var fecCreacion = body.fechaCreacion;
        var fecFin = body.fechaFin;
        var fechas = body.fechas;

        console.log(fecCreacion + " hasta " + fecFin);
        var fechasOrdenadas = fechas.sort();

        var xFecCrea = fecCreacion.split("-");
        var xFecFin = fecFin.split("-");

        var ini = xFecCrea[0];
        var fin = xFecFin[0];
        var mes1 = xFecCrea[1];
        var indice = 0;

        var fechasExcluidas = new Array();

        for (i = ini; i <= fin; i++) {
            for (j = mes1; j <= 12; j++) {
                var dia = "0" + j;
                var laFecha = i + "-" + dia.substr(-2) + "-01";
                if (!fechasOrdenadas.includes(laFecha)) {
                    fechasExcluidas.push(laFecha);
                }
            }
        }

        var respuesta = {
            "id": 6,
            "fechaCreacion": fecCreacion,
            "fechaFin": fecFin,
            "fechas": fechas,
            "fechasFaltantes": fechasExcluidas
        }

        app.get('/respuesta', (req, res) => {
            res.send(respuesta);
        });
        app.listen(8180, () => {
            console.log('Servidor Iniciado');
        })
    }
});
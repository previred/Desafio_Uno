var express = require('express')
var bodyParser = require('body-parser')
var app = express()
app.use(bodyParser.json())
app.post('/get_date', function (req, res) {
    try {
        const meses = ['01', '02', '03', '04', '05', '06', '07', '08', '09', '10', '11', '12'];
        console.log(req.body);
        let response = req.body;

        //Validaciones
        if (!req.body.fechaCreacion) {
            throw "No viene fecha de creacion";
        }

        if (!req.body.fechaFin) {
            throw "No viene fecha fin";
        }

        //Obtengo el año inicial
        const initYear = parseInt(req.body.fechaCreacion.split('-')[0]);
        //Obtengo el año Tèrmino
        const endYear = parseInt(req.body.fechaFin.split('-')[0]);

        // Si no vienen listado de fechas, lo inicializo como vacio. 
        const fechas = req.body.fechas || [];

        let fechasFaltantes = [];
        // La fecha de inicio no puede ser posterior a la de termino y viceversa
        if (initYear > endYear) {
            throw "La fecha de inicio no puede ser mayor a la de termino";
        }

        //Ciclo los años
        for (let ini = initYear; ini <= endYear; ini++) {
            //Ciclo los meses
            for (let mes = 0; mes <= 11; mes++) {
                let fecha = `${String(ini)}-${meses[mes]}-01`;
                /** Solo incluyo las fechas que no existen ten el listado de fecnas entrate */
                if (!fechas.includes(fecha)) {
                    fechasFaltantes.push(fecha);
                }
            }
        }

        response.fechasFaltantes = fechasFaltantes;

        res.status(200)
            .json(response);
        return;
    } catch (e) {
        res.status(400)
            .json(e);
        return;
    }
})

app.listen(3000, function () {
    console.log('Example app listening on port 3000!');
});
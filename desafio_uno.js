// Importa
fs = require("fs");
const axios = require("axios").default;

// Variables
let data_range = [];

// Arguments
const input = process.argv[2];
const output = process.argv[3];

// Read GDD API with Axios
axios
  .get("http://127.0.0.1:8080/periodos/api")
  .then(function (response) {
    // Write Input
    let date = Date.now();
    fs.writeFile(
      "./assets/json/input-" + date + ".json",
      JSON.stringify(response.data),
      function (err) {
        if (err) return console.log(err);
        console.log("desafio_uno.js: escrito input-" + date + ".json");
      }
    );
    // Parsed data
    const input_data = response.data;

    // Fechas tope
    var min_year = Number(input_data.fechaCreacion.split("-")[0]);
    var max_year = Number(input_data.fechaFin.split("-")[0]);
    var min_month = Number(input_data.fechaCreacion.split("-")[1]);
    var max_month = Number(input_data.fechaFin.split("-")[1]);

    // Escribe fechas, push a rango a substraer según año
    for (y = min_year; y <= max_year; y++) {
      switch (y) {
        case min_year:
          for (m = min_month; m <= 12; m++) {
            var gen_date = y + "-" + ("0" + m).slice(-2) + "-01";
            data_range.push(gen_date);
          }
          break;
        case max_year:
          for (m = 1; m <= max_month; m++) {
            var gen_date = y + "-" + ("0" + m).slice(-2) + "-01";
            data_range.push(gen_date);
          }
          break;
        default:
          for (m = 1; m <= 12; m++) {
            var gen_date = y + "-" + ("0" + m).slice(-2) + "-01";
            data_range.push(gen_date);
          }
          break;
      }
    }

    // Substracción fechas otorgadas de generadas
    let lost_periods = data_range.filter((x) => !input_data.fechas.includes(x));

    // Objeto salida
    let output_data = {
      id: input_data.id,
      fechaCreacion: input_data.fechaCreacion,
      fechaFin: input_data.fechaFin,
      fechas: input_data.fechas,
      fechasFaltantes: lost_periods,
    };

    // Write File
    fs.writeFile(
      "./assets/json/" + output,
      JSON.stringify(output_data),
      function (err) {
        if (err) return console.log(err);
        console.log("desafio_uno.js: exito! escrito " + output);
      }
    );
  })
  .catch(function (error) {
    console.log(error);
  })
  .then(function () {});

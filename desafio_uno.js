// Importa
fs = require("fs");

// Variables
let input_data;
let data_range = [];

// Arguments
const input = process.argv[2];
const output = process.argv[3];

// Read File
fs.readFile(input, "utf8", function (err, data) {
  if (err) {
    throw err;
  }
  // Data
  const input_data = JSON.parse(data);

  // Fechas Tope
  var min_year = Number(input_data.fechaCreacion.split("-")[0]);
  var max_year = Number(input_data.fechaFin.split("-")[0]);
  var min_month = Number(input_data.fechaCreacion.split("-")[1]);
  var max_month = Number(input_data.fechaFin.split("-")[1]);

  // Escribe años
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
  let lost_periods = data_range.filter((x) => !input_data.fechas.includes(x));
  let output_data = {
    id: input_data.id,
    fechaCreacion: input_data.fechaCreacion,
    fechaFin: input_data.fechaFin,
    fechasFaltantes: lost_periods,
  };
  // Write File
  fs.writeFile(
    "./assets/json/" + output,
    JSON.stringify(output_data),
    function (err) {
      if (err) return console.log(err);
      console.log("exito!");
    }
  );
});

package main;

import com.google.gson.*;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Principal {
    public static void main(String[] args) {
        File input = new File("resources/entrada.json");
        String  fechaCreacion, fechaFin;
        int id;
        JsonArray fechas;
        List<JsonElement> localDatesList = new ArrayList<>();

        try {
            JsonElement fileElement = JsonParser.parseReader(new FileReader(input));
            JsonObject fileObject = fileElement.getAsJsonObject();
            JsonObject fileResult =  new JsonObject();

            id = fileObject.get("id").getAsInt();
            fechaCreacion = fileObject.get("fechaCreacion").getAsString();
            fechaFin = fileObject.get("fechaFin").getAsString();
            fechas = fileObject.get("fechas").getAsJsonArray();

            localDatesList.addAll(getFechasFaltantes(fechaCreacion,fechaFin,fechas));

            fileResult.addProperty("id",id);
            fileResult.addProperty("fechaCreacion",fechaCreacion);
            fileResult.addProperty("fechaFin",fechaFin);
            fileResult.addProperty("fechasFaltantes", String.valueOf(localDatesList).replace("\\",""));

            try{
                FileWriter file = new FileWriter("resources/salida.json");
                file.write(fileResult.toString());
                file.flush();
                file.close();
            }catch(Exception ex){
                System.out.println("Error: "+ex.toString());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<JsonElement> getFechasFaltantes(String fechaCreacion, String fechaFin, JsonArray fechas) {
        List<JsonElement> localDates = new ArrayList<>();

        LocalDate fechaEval = LocalDate.parse(fechaCreacion);
        JsonElement date = new JsonPrimitive(fechaEval.toString());
        int cont = 0;

        while (fechaEval.isBefore(LocalDate.parse(fechaFin))) {
            date = new JsonPrimitive(fechaEval.toString());
            if (!fechas.contains(date)) {
                localDates.add(date);
            }
            fechaEval = fechaEval.plusDays(31);
            fechaEval = fechaEval.parse(fechaEval.withDayOfMonth(1).toString());
        }

        fechaEval = fechaEval.parse(fechaEval.withDayOfMonth(1).toString());
        date = new JsonPrimitive(fechaEval.toString());
        localDates.add(date);
        return  localDates;
    }

}

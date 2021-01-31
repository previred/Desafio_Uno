
package creative;

import DTO.Entrada;
import DTO.Salida;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

@Path("red")
public class prueba {

    @Context
    private UriInfo context;

    public prueba() {
    }

    @GET
    @Path("info")
    @Produces(MediaType.TEXT_HTML)
    public String getInfo() throws MalformedURLException, IOException {
       URL url = new URL("http://127.0.0.1:8080/periodos/api");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");

        con.setRequestProperty("Content-Type", "application/json");
        con.setRequestProperty("Accept", "application/json");
        
        con.setInstanceFollowRedirects(false);
        StringBuffer content;
        try (BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()))) {
            String inputLine;
            content = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
        }
        
        con.disconnect();
        Gson gson = new Gson();
        Entrada datosEntrada = gson.fromJson(content.toString(), Entrada.class);
        
        ArrayList<String> fechas = datosEntrada.getFechas();
        String[] fechaInicioArray = datosEntrada.getFechaCreacion().split("-");
        String[] fechaFinArray = datosEntrada.getFechaFin().split("-");
        Integer yearInicio = Integer.parseInt(fechaInicioArray[0]);
        Integer mesInicio = Integer.parseInt(fechaInicioArray[1]);
        Integer yearFin = Integer.parseInt(fechaFinArray[0]);
        Integer mesFin = Integer.parseInt(fechaFinArray[1]);
        ArrayList<String> fechasFaltantes = new ArrayList<>();
        
        for (Integer i= yearInicio; i <= yearFin; i++){ 
            if(i == yearInicio){
                if(i < yearFin){
                    for(Integer x =mesInicio; x <= 12; x++){
                        if(!fechas.contains(i.toString() + "-" + (x < 10 ? "0" + x.toString(): x.toString()) + "-01")){
                            fechasFaltantes.add(i.toString() + "-" + (x < 10 ? "0" + x.toString(): x.toString()) + "-01");
                        }
                    }
                }else{
                    for(Integer x =mesInicio; x <= mesFin; x++){
                        if(!fechas.contains(i.toString() + "-" + (x < 10 ? "0" + x.toString(): x.toString()) + "-01")){
                            fechasFaltantes.add(i.toString() + "-" + (x < 10 ? "0" + x.toString(): x.toString()) + "-01");
                        }
                    }
                }
            }else if(i >= yearFin){
                for(Integer x =1; x <= mesFin; x++){
                    if(!fechas.contains(i.toString() + "-" + (x < 10 ? "0" + x.toString(): x.toString()) + "-01")){
                        fechasFaltantes.add(i.toString() + "-" + (x < 10 ? "0" + x.toString(): x.toString()) + "-01");
                    }
                }
            }else{
                for(Integer x =1; x <= 12; x++){
                    if(!fechas.contains(i.toString() + "-" + (x < 10 ? "0" + x.toString(): x.toString()) + "-01")){
                        fechasFaltantes.add(i.toString() + "-" + (x < 10 ? "0" + x.toString(): x.toString()) + "-01");
                    }
                }
            }
        }

        
        Salida salida = new Salida();
        salida.setId(datosEntrada.getId());
        salida.setFechaCreacion(datosEntrada.getFechaCreacion());
        salida.setFechaFin(datosEntrada.getFechaFin());
        salida.setFechas(datosEntrada.getFechas());
        salida.setFechasFaltantes(fechasFaltantes);
        
        String resp = gson.toJson(salida);
        
        return resp;
    }

}

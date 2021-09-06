/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testprevired;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import org.json.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;

/**
 *
 * @author Armando Rodriguez
 */
public class TestPrevired {
    
    private final static int MIN = 90;
    private final static int MAX = 100;
    /**
     * @param args the command line arguments
     * @throws java.text.ParseException
     */
    public static void main(String[] args) throws ParseException {
      
       //Argumentos de entrada
      String archivoEntrada = args[0];
      String archivoSalida = args[1];

      JSONArray leng = null;
      long id = 0;
      String fechaCreacion = null;
      String fechaFin = null;
      Collection<String> different = new HashSet<String>();
      
        JSONParser parser = new JSONParser();
		try{
			Object obj = parser.parse(new FileReader(archivoEntrada));
			 
			org.json.simple.JSONObject jsonObject = (org.json.simple.JSONObject) obj;
	 
                        id = (Long) jsonObject.get("id");
			System.out.println("id:"+id);
                        
			fechaCreacion = (String) jsonObject.get("fechaCreacion");
			System.out.println("fechaCreacion:"+fechaCreacion);
	 
			fechaFin = (String) jsonObject.get("fechaFin");
			System.out.println("fechaFin:"+fechaFin);

			leng = (JSONArray) jsonObject.get("fechas");
			System.out.println("fechas:");
			Iterator iterator =leng.iterator();
			while (iterator.hasNext()) {
				System.out.println(iterator.next());
			}
                        
		}catch(IOException | org.json.simple.parser.ParseException ex){
			System.err.println("Error: "+ex.toString());
		}finally{
			
		}

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");    
        List<String> dates = new ArrayList<String>();
        Calendar cini = Calendar.getInstance();
        try {
            cini.setTime(formatter.parse(fechaCreacion));

            Calendar cfin = Calendar.getInstance();
            cfin.setTime(formatter.parse(fechaFin));
            while (cfin.after(cini) || cfin.equals(cini)) {
                if (cini.get(Calendar.DAY_OF_MONTH) == Calendar.MONDAY) {
                    dates.add(formatter.format(cini.getTime()));
                }
                cini.add(Calendar.DATE, 1);
            }
            dates.remove(0);
            
        Collection<String> similar = new HashSet<String>(leng);
        
        
        different.addAll(dates); 
        different.addAll(leng);
        
        similar.retainAll(leng); 
        different.removeAll(similar);


        System.out.println("FechaFaltante: " + different);
       
        } 
        catch (ParseException e) {
        }
        
        JSONObject myObject = new JSONObject();
        myObject.put("id", id);
        myObject.put("fechaCreacion", fechaCreacion);
        myObject.put("fechaFin", fechaFin);
        myObject.put("fechas", leng);
        
        myObject.put("fechasFaltantes", different);

		//genero un archivo json con la salida
		try{
			FileWriter file = new FileWriter(archivoSalida);
			file.write(myObject.toString());
			file.flush();
			file.close();
			
			
		}catch(IOException ex){
			System.out.println("Error: "+ex.toString());
		}
		finally{
			System.out.print(myObject);
		}
    }
}

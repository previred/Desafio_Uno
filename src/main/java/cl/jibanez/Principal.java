/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.jibanez;

import com.fasterxml.jackson.annotation.JsonValue;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

// import javax.json.JsonValue;


/*
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
*/

// import org.json.JSONObject;


 

/**
 *
 * @author jibanez
 */


public class Principal {
    
    
    public static void ReadInputStandard() throws IOException {
    
        try {
              
            FileInputStream stream = new FileInputStream("entrada_normal.txt");
            System.setIn(stream);

            System.out.println("Leyendo desde archivo de entrada");           
            // System.out.println(stream.read());
            System.out.println("Esta es la Entrada estandar normal");
            
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
  
    }
    
    public static void WriteOutputStandard() throws IOException {
    
        try {
            System.setOut(new PrintStream(new FileOutputStream("salida_normal.txt")));
      
            System.out.println("Esta es la salida estandar normal");
            
         } catch (FileNotFoundException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static <T> List<T> getListFromIterator(Iterator<T> iterator) 
    { 
  
        // Create an empty list 
        List<T> list = new ArrayList<>(); 
  
        // Add each element of iterator to the List 
        iterator.forEachRemaining(list::add); 
  
        // Return the List 
        return list; 
    } 
  

        
    public static void main(String[] args) throws IOException 
    {    
        String streamString="";
            
        try {
            System.out.print("running...!");
            
           // definiendo SALIDA estandar al archivo especifico
            System.setOut(new PrintStream(new FileOutputStream("output_file.json")));
       
            // definiendo ENTRADA estadar del archivo especifico
            FileInputStream stream = new FileInputStream("input_file.json");
            System.setIn(stream);

            
            try {    
                int i=0;    
                while((i=stream.read())!=-1) {    
                 // System.out.print((char)i);
                 streamString+=(char)i;
                }    
            } catch(Exception e){System.out.println(e);}    
         
            
            // convert a string to jsonObject
            JSONObject json = new JSONObject(streamString);

            String fechaCreacion = (String) json.get("fechaCreacion");

            String fechaFin = (String) json.get("fechaFin");


            // Parametros del proceso
            int index_start_year = Integer.parseInt(fechaCreacion.substring(0,4));
            int index_start_month = Integer.parseInt(fechaCreacion.substring(5,7));

            int index_end_year = Integer.parseInt(fechaFin.substring(0,4));
            int index_end_month = Integer.parseInt(fechaFin.substring(5,7));

//            System.out.println("index_start_year:"+index_start_year);
//            System.out.println("index_start_month:"+index_start_month);
//            System.out.println("index_end_year:"+index_end_year);
//            System.out.println("index_end_month:"+index_end_month);
//            System.out.println("___________________________________________________");

            // A JSON array. JSONObject supports java.util.List interface.
            JSONArray fechasList = (JSONArray) json.get("fechas");
            /*
            System.out.println("fechas...");
            Iterator<Object> iterator = fechasList.iterator();
            while (iterator.hasNext()) {
                 System.out.println(iterator.next());
             }*/
            // System.out.println("fechasList->"+fechasList.toString());
            int i,j;
            System.out.println("fechasFaltantes: [");
            for(i=index_start_year; i<=index_end_year ;i++) {
                
                if (i==index_start_year) {
                    for(j=index_start_month;j<=12;j++) {
                        String mes_string = (Integer.toString(j).length()==2)?Integer.toString(j):"0"+Integer.toString(j);
                        String periodo_search =  Integer.toString(i) + "-" + mes_string;
                        if (!fechasList.toString().contains(periodo_search)) {
                           System.out.println("["+periodo_search+"-01],"); 
                        }
                    }
                }
                if (i==index_end_year) {
                    for(j=1;j<index_end_month;j++) {
                        String mes_string = (Integer.toString(j).length()==2)?Integer.toString(j):"0"+Integer.toString(j);
                        String periodo_search =  Integer.toString(i) + "-" + mes_string;
                        if (!fechasList.toString().contains(periodo_search)) {
                           System.out.println("["+periodo_search+"-01],"); 
                        }
                    }
                
                }
                else {
                    for(j=1;j<=12;j++) {
                        String mes_string = (Integer.toString(j).length()==2)?Integer.toString(j):"0"+Integer.toString(j);
                        String periodo_search =  Integer.toString(i) + "-" + mes_string;
                        if (!fechasList.toString().contains(periodo_search)) {
                           System.out.println("["+periodo_search+"-01],"); 
                        }
                    }
                }
            }
            System.out.println("]");

            
            /*
            System.out.println("recorriendo fechasList");
            fechasList.forEach(item -> {
                System.out.println(item.toString());
                String candidate_element = item.toString();
                System.out.println("year_candidate_element:"+candidate_element.substring(0, 4));
                System.out.println("month_candidate_element:"+candidate_element.substring(5, 7));
            });
            */
            // System.out.println("___________________________________________________");
            // System.out.print("ending OK!");          
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}

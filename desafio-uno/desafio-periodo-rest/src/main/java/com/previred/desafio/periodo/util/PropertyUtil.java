package com.previred.desafio.periodo.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Roderick Rangel
 */
public class PropertyUtil {
    private final static Logger LOG = Logger.getLogger(PropertyUtil.class.getName());
    private static Properties properties;
    
    public static String getProperty(String key){
        properties = new Properties();
        InputStreamReader isr = null;
        InputStream is = null;
        try{
            isr = new InputStreamReader(new FileInputStream("C://config.properties"), "UTF8");
            if (isr != null) {
                properties.load(isr);
            }
        }catch(FileNotFoundException e){
            LOG.log(Level.SEVERE, "No existe archivo C://config.properties");
        }catch(Exception y){
            LOG.log(Level.SEVERE, "Hubo un problema al leer la configuracion de: 'C://config.properties'");
        }
        return properties.getProperty(key);
    }
}

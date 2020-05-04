package com.previred.desafio.periodo.core;

import com.previred.desafio.periodo.bean.Periodo;
import com.previred.desafio.periodo.util.FechaUtil;
import java.util.ArrayList;
import java.util.List;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Roderick Rangel
 */
public class InfoPeriodo {
    private final static Logger LOG = Logger.getLogger(InfoPeriodo.class.getName());
    
    private Periodo periodo;
    
    public InfoPeriodo(Periodo periodo)
    {
        this.periodo = periodo;
        LOG.log(Level.INFO, "Fecha creación: {0}", this.periodo.getFechaCreacion());
        LOG.log(Level.INFO, "Fecha fin: {0}", this.periodo.getFechaFin());
        
    }
    
    /**
     * 
     * @return Lista fechas faltantes
     */
    public List<String> listarFechasFaltantes()
    {
        List<String> fechasFaltantes = this.listarFechasPeriodo();
        
        for(String fecha : this.periodo.getFechas())
        {
        
            fechasFaltantes.remove(fecha);
        }
        
        LOG.log(Level.INFO, "Fechas faltantes: {0}", fechasFaltantes.size());
        
        return fechasFaltantes;
    } 
    
    /**
     * 
     * @return Lista de todas las fechas que deben estar en la lista.
     */
    private List<String> listarFechasPeriodo()
    {
        List<String> fechasTodas = new ArrayList();
        
        String fecha = this.periodo.getFechaCreacion();
        fechasTodas.add(fecha);
        
        while(!fecha.equalsIgnoreCase(this.periodo.getFechaFin()))
        {
            fecha = FechaUtil.nextFirst(fecha);
            
            fechasTodas.add(fecha);
        }
        
        LOG.log(Level.INFO, "Fechas requeridas: {0}", fechasTodas.size());
                        
        return fechasTodas;        
    }
    
    /**
     * 
     * @return 
     */
    private boolean contieneFechaCreacion()
    {
         if(this.periodo.getFechas() != null)
         {
             return this.periodo.getFechaCreacion().equalsIgnoreCase(this.periodo.getFechas().get(0));
         }
         
         return false;
    }

    /**
     * @return the periodo
     */
    public Periodo getPeriodo() {
        return periodo;
    }

    /**
     * @param periodo the periodo to set
     */
    public void setPeriodo(Periodo periodo) {
        this.periodo = periodo;
    }
}

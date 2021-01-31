/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import java.util.ArrayList;

/**
 *
 * @author 56991
 */
public class Salida {
    
    private int id;
    private String fechaCreacion, fechaFin;
    private ArrayList<String> fechas, fechasFaltantes;

    public Salida() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }

    public ArrayList<String> getFechas() {
        return fechas;
    }

    public void setFechas(ArrayList<String> fechas) {
        this.fechas = fechas;
    }

    public ArrayList<String> getFechasFaltantes() {
        return fechasFaltantes;
    }

    public void setFechasFaltantes(ArrayList<String> fechasFaltantes) {
        this.fechasFaltantes = fechasFaltantes;
    }
    
    
}

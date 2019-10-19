package com.desafio.desafioCayo.bsd;

import com.desafio.desafioCayo.dto.FechasGeneradorDTO;

public interface BSDLeerArchivoInterface {

    boolean leerARchivo(String archivo, String archivoSal);

    public FechasGeneradorDTO generarSalida(FechasGeneradorDTO entrada);
}

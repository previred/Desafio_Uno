package com.previred.periodosPerdidos.Service.Impl;

import com.previred.periodosPerdidos.Exception.RepositoryException;
import com.previred.periodosPerdidos.Exception.ServiceException;
import com.previred.periodosPerdidos.Helpers.Utils;
import com.previred.periodosPerdidos.Model.Periodo;
import com.previred.periodosPerdidos.Repository.PreviredRepository;
import com.previred.periodosPerdidos.Service.PreviredService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Matias Arce on 11/23/2019.
 */
@Service
public class PreviredServiceImpl implements PreviredService {

    private static PreviredRepository previredRepository;

    @Autowired
    public PreviredServiceImpl(PreviredRepository previredRepository) {
        this.previredRepository=previredRepository;
    }

    /**
     * Metodo que procesa la respuesta de servicio REST Generador De Datos o GDD para determinar los periodos que faltan.
     * @return
     * @throws Exception
     */
    @Override
    public Periodo getPeriodosPerdidos() throws Exception {
        List<LocalDate> totalFechas=new ArrayList<LocalDate>();
        Periodo periodoResponse=new Periodo();
        try{
            periodoResponse= previredRepository.getPeriodos();
            if(periodoResponse.getFechaCreacion().isBefore(periodoResponse.getFechaFin())){
                totalFechas= Utils.getFechasMesesEntreFechas(periodoResponse.getFechaCreacion(),periodoResponse.getFechaFin());
                for (LocalDate d : periodoResponse.getFechas()) {
                    if(totalFechas.contains(d)){
                        totalFechas.remove(d);
                    }
                }
                periodoResponse.setFechasFaltantes(totalFechas);
            }else{
                throw new Exception("Error al procesar respuesta servicio: fecha de creacion no puedes ser posterior a fecha final");
            }
        }catch (RepositoryException e){
            throw new ServiceException("Respuesta de servicio inesperada",e);
        }

        return periodoResponse;
    }

}


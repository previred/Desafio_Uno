package com.previred.lost.periods.domain.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import com.previred.lost.periods.domain.entities.LostPeriods;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents a response entity to map a GDD REST service response. All these
 * field values are generated randomly by the service.
 * 
 * @author Carlos Izquierdo
 * @author izqunited@gmail.com
 * @see LostPeriods
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PeriodDTO implements Serializable {

    private static final long serialVersionUID = 4734880018963148328L;

    private Long id;
    private LocalDate fechaCreacion;
    private LocalDate fechaFin;
    private List<LocalDate> fechas;

}

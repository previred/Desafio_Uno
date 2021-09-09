package cl.previred.gdd.services;

import cl.previred.gdd.dto.GddRequest;
import cl.previred.gdd.dto.GddResponse;

/**
 * Interface que define el método calculateMissingPeriods para calcular períodos no existentes en un listado de entrada
 */
public interface IGddService {
  /**
   * Método
   * @param request
   * @return Retorna un objeto que contiene los períodos faltantes
   * @throws ServiceException
   */
  GddResponse calculateMissingPeriods(GddRequest request) throws ServiceException;
}

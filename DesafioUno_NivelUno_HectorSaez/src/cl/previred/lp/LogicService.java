package cl.previred.lp;

import java.util.TreeSet;

/**
 * Interfaz de la logica de negocio
 * @author hector saez
 *
 */
public interface LogicService {
	public TreeSet<String> getLostPeriods() throws Exception;
}

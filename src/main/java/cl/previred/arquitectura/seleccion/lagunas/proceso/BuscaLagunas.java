package cl.previred.arquitectura.seleccion.lagunas.proceso;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import cl.previred.arquitectura.seleccion.lagunas.exceptions.EntradaException;
import cl.previred.arquitectura.seleccion.lagunas.exceptions.SalidaException;


/**
 * Esta clase se encarga de buscar las fechas faltantes, los metodos de entrada y salida se obtienen por inyeccion de dependencia. 
 * @author Juan Villablanca
 *
 */
public class BuscaLagunas {

	private BuscaLagunas() {
		//evitamos que se instancie
		
	}

	/**
	 * Recupera los datos desde algun metodo de entrada, valida, calcula las fechas faltantes y manda el resultado a algun metodo de salida
	 * @param metodoEntrada
	 * @param metodoSalida
	 * @throws EntradaException
	 * @throws SalidaException 
	 */
	public static void busca(InputData metodoEntrada,OutputData metodoSalida) throws EntradaException, SalidaException
	{
		InputVO datai = metodoEntrada.getData();
		valida(datai);
		OutputVO datao = calcula(datai);
		metodoSalida.putLagunas(datao);
	}

	/**
	 * Realiza las operaciones para obter la lista de fechas faltantes
	 * @param datai Objeto de entrada con fechas presentes
	 * @return Objeto con periodo y fechas faltantes
	 */
	protected static OutputVO calcula(InputVO datai)
	{
		//Primero genero una lista con el periodo completo
		List<LocalDate> periodoCompleto = new ArrayList<>();
		Period per=Period.between(datai.getFechaCreacion(), datai.getFechaFin());
		int meses = (per.getYears()*12)+per.getMonths();
		LocalDate fec=datai.getFechaCreacion();
		for(int i=0;i<=meses;i++)
		{
			periodoCompleto.add(fec);
			fec=fec.plusMonths(1);
		}

		//Al periodo completo le resto las fechas que ya tenemos (si es que tenemos)
		List<LocalDate> ff=null;
		if(datai.getFechas()!=null&&!datai.getFechas().isEmpty()){		
			Collection<LocalDate> fechasFaltantes = CollectionUtils.subtract(periodoCompleto, datai.getFechas());
			ff=new ArrayList<>(fechasFaltantes);
			Collections.sort(ff);
		}
		else
		{
			ff=periodoCompleto;
		}

		//Preparo el objeto de salida
		OutputVO datao = new OutputVO();
		datao.setId(datai.getId());
		datao.setFechaCreacion(datai.getFechaCreacion());
		datao.setFechaFin(datai.getFechaFin());
		datao.setFechasFaltantes(ff);

		return datao;
	}
	
	/**
	 * Realiza validaciones sobre la estructura de datos
	 * @param datai estructura de datos
	 * @throws EntradaException
	 */
	private static void valida(InputVO datai) throws EntradaException
	{
		if(datai==null)
		{
			throw new EntradaException("El objeto para los datos de entrada es nulo");
		}
		
		if(datai.getFechaCreacion()==null)
		{
			throw new EntradaException("La fecha de creacion es nula");
		}
		
		if(datai.getFechaFin()==null)
		{
			throw new EntradaException("La fecha de fin es nula");
		}
		
		if(datai.getFechaCreacion().compareTo(datai.getFechaFin())>=0)
		{
			throw new EntradaException("La fecha de creacion no es menor que la fecha de fin");
		}
		

		
	}






}

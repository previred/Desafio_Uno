package cl.previred.infraestructure.intercerptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Permite interceptar los logs y formatearlos
 * 
 * @author wmunoz
 *
 */
@Component
@Order(0)
public class ServiceMetricsIntercertor  {

	Logger logger = LoggerFactory.getLogger(ServiceMetricsIntercertor.class );

	/*@Override
	 
	 Se puede realizar un metodo para el filtrado de peticiones
	 
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		long start_time,end_time;

		logger.info("Date management#StartTransaction");

		start_time= System.nanoTime();

		filterChain.doFilter(request, response);

		end_time = System.nanoTime();
		logger.info(String.format("responseTime=%.3f [ms]",(end_time - start_time) / 1e6));
		logger.info("Date management#FinishTransaction");

	}*/
}

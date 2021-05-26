package cl.previred.infraestructure.intercerptor;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.MDC;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * @author wmunoz
 * @since 1.0
 * @version 1.0
 */
@Component
@Order(1)
public class LoggerIntercertor extends OncePerRequestFilter {

	LoggerIntercertor() {
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String threaId = Long.toString(Thread.currentThread().getId());

		MDC.put("ThreaId", threaId);

		filterChain.doFilter(request, response);
	}
}

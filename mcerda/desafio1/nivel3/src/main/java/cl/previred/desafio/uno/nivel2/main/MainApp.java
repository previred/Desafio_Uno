package cl.previred.desafio.uno.nivel2.main;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.time.Duration;
import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import cl.previred.desafio.uno.nivel2.service.DesafioUnoService;
import cl.previred.desafio.uno.nivel2.service.OneLevelCallGDDService;
import lombok.extern.log4j.Log4j2;

@Component
@Log4j2
public class MainApp implements CommandLineRunner {
	@Value("${app.env}")
	private String appEnv;

	@Autowired
	private OneLevelCallGDDService oneLevelCallGDDService;

	@Autowired
	private DesafioUnoService desafioUnoService;

	@Override
	public void run(String... args) throws Exception {
		Instant start = Instant.now();
		loggerInitProcess(start);
		
		desafioUnoService.print(oneLevelCallGDDService.callGetGDD());

		loggerFinishedProcess(start);
		System.exit(0);
	}

	/**
	 * Método que deja registro del inicio del proceso
	 * 
	 * @param start tiempo de inicio
	 */
	private void loggerInitProcess(Instant start) {
		log.info("Inicio en entorno: {}, proceso: {}, en el tiempo: {}", appEnv, getPID(), start);

	}

	/**
	 * Método que deja registro del término del proceso y duración del proceso
	 * 
	 * @param start tiempo de inicio
	 */
	private void loggerFinishedProcess(Instant start) {
		Instant finish = Instant.now();
		long timeElapsed = Duration.between(start, finish).toMillis();
		log.info("Fin en entorno: {}, proceso: {}, en el tiempo: {}", appEnv, getPID(), finish);
		log.info("Tiempo total: {} milisegundos.", timeElapsed);
	}

	/**
	 * Método que obtiene el número de proceso actual
	 * 
	 * @return número de proceso
	 */
	private long getPID() {
		RuntimeMXBean runtimeBean = ManagementFactory.getRuntimeMXBean();
		String jvmName = runtimeBean.getName();
		return Long.parseLong(jvmName.split("@")[0]);
	}

}

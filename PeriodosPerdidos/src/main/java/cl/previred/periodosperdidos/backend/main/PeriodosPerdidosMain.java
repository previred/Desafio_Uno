
package cl.previred.periodosperdidos.backend.main;

import cl.previred.periodosperdidos.backend.main.server.JettyInit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author Matias
 */
public class PeriodosPerdidosMain {

    private static final Logger LOGGER = LoggerFactory.getLogger(PeriodosPerdidosMain.class);

    public static void main(String args[]) {

        LOGGER.info("..:: Inicializando Backend de Previred ::..");
        try {
            ApplicationContext context = new ClassPathXmlApplicationContext("classpath:previred-backend-context.xml");
            JettyInit jettyInit = context.getBean(JettyInit.class);
            jettyInit.start();
            LOGGER.info("..:: Jetty Inicializado ::..");
        } catch (Exception e) {
            LOGGER.error("..:: Error al Inicializar Backend ::.. ERROR: ", e);
        }
        
    }
    
}

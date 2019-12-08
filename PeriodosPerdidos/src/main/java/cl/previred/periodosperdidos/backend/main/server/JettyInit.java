
package cl.previred.periodosperdidos.backend.main.server;

import java.util.EnumSet;
import javax.servlet.DispatcherType;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.session.HashSessionIdManager;
import org.eclipse.jetty.server.session.HashSessionManager;
import org.eclipse.jetty.server.session.SessionHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.webapp.WebAppContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.filter.DelegatingFilterProxy;

/**
 *
 * @author Matias
 */
@Service("jettyInit")
public class JettyInit {

    private static final Logger LOGGER = LoggerFactory.getLogger(JettyInit.class);

    @Value("${jetty.port}")
    private int lockPort;

    @Value("${rest.security.enabled}")
    private boolean restSecurityEnabled;

    public void start() throws Exception {
        LOGGER.info("..:: Inicializando Jetty ::..");
        Server server = new Server();
        ServerConnector connector = new ServerConnector(server);
        connector.setHost("127.0.0.1");
        connector.setPort(lockPort);
        server.addConnector(connector);
        WebAppContext webapp = new WebAppContext();
        webapp.setContextPath("/");
        final ServletContextHandler context = new ServletContextHandler();
        final ServletHolder servletHolder = new ServletHolder(new CXFServlet());
        context.setContextPath("/");
        context.setInitParameter("contextConfigLocation", "classpath:rest-server-context.xml");
        context.addEventListener(new ContextLoaderListener());
        context.addServlet(servletHolder, "/cxf/*");
        // Habilita autenticacion mediante properties
        if (restSecurityEnabled) {
            context.addFilter(DelegatingFilterProxy.class, "/*",
                    EnumSet.allOf(DispatcherType.class)).setInitParameter("targetBeanName", "delegatingFilterProxy");
        }
        server.setHandler(context);
        HashSessionManager manager = new HashSessionManager();
        SessionHandler sessions = new SessionHandler(manager);
        context.setHandler(sessions);
        HashSessionIdManager idmanager = new HashSessionIdManager();
        server.setSessionIdManager(idmanager);
        server.start();
    }

}

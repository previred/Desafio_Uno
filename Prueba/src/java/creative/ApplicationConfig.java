
package creative;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author Rodrigo Urrea
 */
@javax.ws.rs.ApplicationPath("previ")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(creative.prueba.class);
    }
    
}

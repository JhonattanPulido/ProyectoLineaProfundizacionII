// Paquete
package com.udecsanitas.udecsanitasweb.pojo;

// Librerías
import java.io.IOException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.ext.Provider;

/**
 * Cors policy
 *
 * @author Jhonattan Pulido
 * @version 1.0.0
 * @since 19/05/2021
 */
@Provider
public class CorsPolicy implements ContainerResponseFilter {

    @Override
    public void filter(final ContainerRequestContext requestContext, final ContainerResponseContext cres)
            throws IOException {
        cres.getHeaders().add("Access-Control-Allow-Origin", "*"); // Update specific domains instead of giving to all
        cres.getHeaders().add("Access-Control-Allow-Headers", "Origin,Content-Type,Accept,Authorization,content-type");
        cres.getHeaders().add("Access-Control-Allow-Methods", "GET,POST,PUT,DELETE");
        cres.getHeaders().add("Access-Control-Max-Age", "1209500");
    }

}

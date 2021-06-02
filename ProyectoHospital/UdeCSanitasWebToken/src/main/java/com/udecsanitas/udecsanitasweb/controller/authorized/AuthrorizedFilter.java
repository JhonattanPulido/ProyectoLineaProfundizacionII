// Paquete
package com.udecsanitas.udecsanitasweb.controller.authorized;

// Librerías
import javax.ejb.EJB;
import java.io.IOException;
import javax.ws.rs.ext.Provider;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.MediaType;
import com.udecsanitas.udecsanitasweb.pojo.ErrorDto;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ContainerRequestContext;
import com.udecsanitas.exception.UnauthorizedException;
import com.udecsanitas.service.interfaz.ISeguridadService;

/**
 * Filtro de autorización
 * @author Jhonattan Pulido
 * @version 1.0.0
 * @since 01/06/2021
 */
@Provider
public class AuthrorizedFilter implements ContainerRequestFilter {

    // Variables
    @EJB
    private ISeguridadService seguridadService;
    
    // Métodos
    
    /**
     * Filtrar consumo de servicios
     * @param requestContext
     * @throws IOException 
     */
    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        
        String url = requestContext.getUriInfo().getAbsolutePath().toString();        
        
        if (url.contains("seguridad") == false && url.contains("cerrar-sesion") == false) { // Validar que el servicio necesita token
            
            String token = requestContext.getHeaderString("Authorization");
            
            if (token != null) { // Verificar que exista un token                
                
                try {
                    
                    seguridadService.validarToken(token, url); // Validar token
                    
                } catch (UnauthorizedException ex) {
                    ErrorDto error = new ErrorDto(ex.getMessage(), ex.getStackTrace()[0].toString());
                    requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED)
                        .type(MediaType.APPLICATION_JSON)
                        .entity(error)
                        .build());
                }
                
            } else { // No hay token en el header
                ErrorDto error = new ErrorDto("Debe iniciar sesión para continuar", url);
                requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED)
                    .type(MediaType.APPLICATION_JSON)
                    .entity(error)
                    .build());
            }                            
        }                    
    }
    
}

// Paquete
package com.udecsanitas.udecsanitasweb.controller;

// Librerías
import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ejb.Stateless;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import com.udecsanitas.exception.BadRequestException;
import com.udecsanitas.exception.UnauthorizedException;
import com.udecsanitas.service.interfaz.ISeguridadService;

/**
 * Controlador de seguridad
 * @author Jhonattan Pulido
 * @version 1.0.0
 * @since 01/06/2021
 */
@Stateless
@Path("/seguridad")
public class SeguridadController {
    
    // Variables
    
    @EJB
    private ISeguridadService seguridadService;
    
    // Métodos
    
    /**
     * Iniciar sesión
     * @param correoElectronico
     * @param clave
     * @return 201
     * @throws UnauthorizedException
     * @throws BadRequestException 
     */
    @GET
    @Path("/{email}/{clave}")
    public Response IniciarSesion(@PathParam("email") String correoElectronico, @PathParam("clave") String clave) throws    UnauthorizedException,
                                                                                                                            BadRequestException {
        return Response.status(Response.Status.OK)
                        .entity(seguridadService.iniciarSesion(correoElectronico, clave))
                        .build();
    }
    
}

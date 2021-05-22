// Paquete
package com.udecsanitas.udecsanitasweb.controller;

// Librerías
import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.DELETE;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.MediaType;
import com.udecsanitas.entity.Examen;
import com.udecsanitas.exception.NotFoundException;
import com.udecsanitas.exception.NoContentException;
import com.udecsanitas.exception.IntegridadException;
import com.udecsanitas.service.interfaz.IExamenService;

/**
 * Controlador de examen
 * @author Jhonattan Pulido
 * @version 1.0.0
 * @since 22/05/2021
 */
@Stateless
@Path("/examenes")
public class ExamenController {

    // Variables
    
    @EJB
    private IExamenService examenService;
    
    // Métodos
    
    /**
     * Crear examen
     * @param examen
     * @return 201
     * @throws IntegridadException 
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response crear(Examen examen) throws IntegridadException {
        examenService.crear(examen);
        return Response.status(Response.Status.CREATED)
                        .build();
    }
    
    /**
     * Leer examen
     * @param id
     * @return Examen
     * @throws NotFoundException 
     */
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response leer(@PathParam("id") Short id) throws   NotFoundException {
        return Response.status(Response.Status.OK)
                        .entity(examenService.leer(id))
                        .build();
    }
    
    /**
     * Paginar exámenes
     * @param inicio - Registro de inicio
     * @param cantidad - Cantidad a obtener
     * @return Lista de exámenes
     * @throws NoContentException 
     */
    @GET
    @Path("/{inicio}/{cantidad}")    
    @Produces(MediaType.APPLICATION_JSON)
    public Response leer(@PathParam("inicio") Short inicio, @PathParam("cantidad") Short cantidad) throws   NoContentException {
        return Response.status(Response.Status.OK)
                        .entity(examenService.leer(inicio, cantidad))
                        .build();
    }
    
    /**
     * Actualizar examen
     * @param examen
     * @return 200
     * @throws NotFoundException
     * @throws IntegridadException 
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response actualizar(Examen examen) throws    NotFoundException,
                                                        IntegridadException {
        examenService.actualizar(examen);
        return Response.status(Response.Status.OK)
                        .build();
    }
    
    /**
     * Eliminar examen
     * @param id - ID del examen
     * @return 200
     * @throws NotFoundException 
     */
    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response eliminar(@PathParam("id") Short id) throws  NotFoundException {
        examenService.eliminar(id);
        return Response.status(Response.Status.OK)
                        .build();
    }
    
}

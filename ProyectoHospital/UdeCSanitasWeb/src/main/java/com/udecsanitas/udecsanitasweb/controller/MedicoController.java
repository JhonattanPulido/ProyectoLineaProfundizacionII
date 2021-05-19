// Paquete
package com.udecsanitas.udecsanitasweb.controller;

// Librerías
import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.MediaType;
import com.udecsanitas.entity.Medico;
import javax.ws.rs.core.NoContentException;
import com.udecsanitas.exception.NotFoundException;
import com.udecsanitas.exception.IntegridadException;
import com.udecsanitas.service.interfaz.IMedicoService;

/**
 * Controlador de médico
 * @author Jhonattan Pulido
 * @version 1.0.0
 * @since 18/05/2021
 */
@Stateless
@Path("/medicos")
public class MedicoController {
    
    // Variables
    
    @EJB
    private IMedicoService medicoService;
    
    // Métodos
    
    /**
     * Crear médico
     * @param medico - Datos del médico
     * @return 201 - CREATED
     * @throws IntegridadException 
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response crear(Medico medico) throws IntegridadException {
        medicoService.crear(medico);
        return Response.status(Response.Status.CREATED)
                        .build();
    }
    
    /**
     * Leer médico
     * @param id - ID del médico
     * @param direccion - ¿Desea obtener la dirección?
     * @return Médico
     * @throws NotFoundException 
     */
    @GET
    @Path("/{id}/{direccion}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response leer(@PathParam("id") Short id, @PathParam("direccion") String direccion) throws   NotFoundException {        
        return Response.status(Response.Status.OK)
                        .entity(medicoService.leer(id, direccion))
                        .build();
    }
    
    /**
     * Leer registros paginando
     * @param inicio - Registro de inicio
     * @param cantidad - Cantidad a obtener
     * @return Lista de registros
     * @throws NoContentException
     */
    @GET
    @Path("/pag/{inicio}/{cantidad}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response leer(@PathParam("inicio") Short inicio, @PathParam("cantidad") Short cantidad) throws   NoContentException {
        return Response.status(Response.Status.OK)
                        .entity(medicoService.leer(inicio, cantidad))
                        .build();
    }
    
}
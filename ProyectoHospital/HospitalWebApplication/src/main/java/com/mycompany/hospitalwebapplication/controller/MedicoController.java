// Paquete
package com.mycompany.hospitalwebapplication.controller;

// Librerías
import com.hospitalejbmodule.entity.Medico;
import com.hospitalejbmodule.excepciones.IntegridadException;
import com.hospitalejbmodule.excepciones.NotFoundException;
import com.hospitalejbmodule.service.interfaz.IMedicoService;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.NoContentException;
import javax.ws.rs.core.Response;

/**
 * Controlado de médico
 * @author Jhonattan Pulido
 * @version 1.0.0
 * @since 05/05/2021
 */
@Stateless
@Path("/medicos")
public class MedicoController implements Serializable {
    
    // Variables
    
    /**
     * Servicio de médico
     */
    @EJB
    private IMedicoService medicoService;
    
    // Métodos
    
    /**
     * Crear médico
     * @param medico
     * @return
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
     * @param id
     * @return
     * @throws NotFoundException 
     */
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response leer(@PathParam("id") Short id) throws  NotFoundException {
    
        return Response.status(Response.Status.OK)
                        .entity(medicoService.leer(id))
                        .build();
        
    }
    
    /**
     * Leer todos los médicos
     * @return
     * @throws NoContentException 
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response leer() throws   NoContentException {
    
        return Response.status(Response.Status.OK)
                        .entity(medicoService.leer())
                        .build();
        
    }
    
    /**
     * Actualizar médico
     * @param medico
     * @return
     * @throws NotFoundException 
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response actualizar(Medico medico) throws    NotFoundException {
    
        medicoService.actualizar(medico);
        
        return Response.status(Response.Status.OK)
                        .build();
        
    }
    
}

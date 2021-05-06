// Paquete
package com.mycompany.hospitalwebapplication.controller;

// Librerías
import com.hospitalejbmodule.entity.Consulta;
import com.hospitalejbmodule.excepciones.IntegridadException;
import com.hospitalejbmodule.excepciones.NotFoundException;
import com.hospitalejbmodule.service.interfaz.IConsultaService;
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
 * Controlador de consulta
 * @author Jhonattan Pulido
 * @version 1.0.0
 * @since 05/05/2021
 */
@Stateless
@Path("/consultas")
public class ConsultaController implements Serializable {
    
    // Variables
    
    /**
     * Servicios de consulta
     */
    @EJB
    private IConsultaService consultaService;
    
    // Métodos
    
    /**
     * Crear consulta
     * @param consulta
     * @return
     * @throws IntegridadException 
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response crear(Consulta consulta) throws IntegridadException {
        
        consultaService.crear(consulta);
        
        return Response.status(Response.Status.CREATED)
                        .build();
        
    }
    
    /**
     * Leer consulta
     * @param id
     * @return
     * @throws NotFoundException 
     */
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response leer(@PathParam("id") Short id) throws  NotFoundException {
    
        return Response.status(Response.Status.OK)
                        .entity(consultaService.leer(id))
                        .build();
        
    }
    
    /**
     * Leer todas las consultas
     * @return
     * @throws NoContentException 
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response leer() throws   NoContentException {
    
        return Response.status(Response.Status.OK)
                        .entity(consultaService.leer())
                        .build();
        
    }
    
    /**
     * Actualizar consulta
     * @param consulta
     * @return
     * @throws NotFoundException 
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response actualizar(Consulta consulta) throws    NotFoundException {
    
        consultaService.actualizar(consulta);
        
        return Response.status(Response.Status.OK)
                        .build();
        
    }
    
}

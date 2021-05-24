// Paquete
package com.udecsanitas.udecsanitasweb.controller;

// Librerías
import javax.ejb.EJB;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.DELETE;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.MediaType;
import com.udecsanitas.entity.Consulta;
import com.udecsanitas.exception.NotFoundException;
import com.udecsanitas.exception.NoContentException;
import com.udecsanitas.service.interfaz.IConsultaService;
import javax.ws.rs.PUT;

/**
 * Controlador de consulta
 * @author Jhonattan Pulido
 * @version 1.0.0
 * @since 23/05/2021
 */
@Stateless
@Path("/consultas")
public class ConsultaController {
 
    // Variables
    
    @EJB
    private IConsultaService consultaService;
    
    // Métodos
    
    /**
     * Crear consulta
     * @param listaConsultas
     * @param medicoId
     * @return 201
     * @throws NoContentException
     * @throws NotFoundException 
     */
    @POST
    @Path("/{medicoId}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response crear(List<Consulta> listaConsultas, @PathParam("medicoId") Short medicoId) throws  NoContentException,
                                                                                                        NotFoundException {
        consultaService.crear(listaConsultas, medicoId);
        return Response.status(Response.Status.CREATED)
                        .build();
    }
    
    /**
     * Leer consulta
     * @param id
     * @return 200
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
     * Leer consultas filtradas por médico
     * @param inicio
     * @param cantidad
     * @param medicoId
     * @return Lista de consultas
     * @throws NotFoundException
     * @throws NoContentException 
     */
    @GET
    @Path("/pag/{inicio}/{cantidad}/{medicoId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response leer(
            @PathParam("inicio") Short inicio,
            @PathParam("cantidad") Short cantidad,
            @PathParam("medicoId") Short medicoId) throws   NotFoundException,
                                                            NoContentException {
        return Response.status(Response.Status.OK)
                        .entity(consultaService.leer(inicio, cantidad, medicoId))
                        .build();
    }
    
    /**
     * Actualizar consulta
     * @param consulta
     * @return 200
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
    
    /**
     * Eliminar consulta
     * @param id
     * @return 200
     * @throws NotFoundException 
     */
    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response eliminar(@PathParam("id") Short id) throws  NotFoundException {
        
        consultaService.eliminar(id);
        return Response.status(Response.Status.OK)
                        .build();
    }
    
}

// Paquete
package com.udecsanitas.udecsanitasweb.controller;

// Librerías
import javax.ejb.EJB;
import java.util.List;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
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
import javax.ws.rs.GET;

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
    
}

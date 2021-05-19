// Paquete
package com.udecsanitas.udecsanitasweb.controller.exception.filter;

// Librerías
import javax.ws.rs.ext.Provider;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import com.udecsanitas.udecsanitasweb.pojo.ErrorDto;

/**
 * Filtro de exception
 * @author Jhonattan Pulido
 * @version 1.0.0
 * @since 18/05/2021
 */
@Provider
public class ExceptionFilter implements ExceptionMapper<Exception>{

    /**
     * INTERNAL SERVER ERROR
     * @param exception
     * @return 500
     */
    @Override
    public Response toResponse(Exception exception) {
         ErrorDto error = new ErrorDto("Ha ocurrido un error inesperado", exception.getStackTrace()[0].toString());
         return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                        .entity(error)
                        .build();
    }
            
}

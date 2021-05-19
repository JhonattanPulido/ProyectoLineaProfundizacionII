// Paquete
package com.udecsanitas.udecsanitasweb.controller.exception.filter;

// Librerías
import com.udecsanitas.exception.IntegridadException;
import com.udecsanitas.udecsanitasweb.pojo.ErrorDto;
import javax.ws.rs.ext.Provider;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

/**
 * Filtro de integridad exception
 * @author Jhonattan Pulido
 * @version 1.0.0
 * @since 18/05/2021
 */
@Provider
public class IntegridadExceptionFilter implements ExceptionMapper<IntegridadException> {

    /**
     * CONFLICT
     * @param exception
     * @return 409
     */
    @Override
    public Response toResponse(IntegridadException exception) {
        ErrorDto error = new ErrorDto(exception.getMessage(), exception.getStackTrace()[0].toString());
        return Response.status(Response.Status.CONFLICT)
                        .entity(error)
                        .build();
    }
            
}

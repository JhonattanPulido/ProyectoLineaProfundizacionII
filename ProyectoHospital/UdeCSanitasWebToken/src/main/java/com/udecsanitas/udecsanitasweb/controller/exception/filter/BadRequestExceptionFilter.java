// Paquete
package com.udecsanitas.udecsanitasweb.controller.exception.filter;

// Librerías
import javax.ws.rs.ext.Provider;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import com.udecsanitas.exception.BadRequestException;
import com.udecsanitas.udecsanitasweb.pojo.ErrorDto;

/**
 * Filtro de bad request exception
 * @author Jhonattan Pulido
 * @version 1.0.0
 * @since 01/06/2021
 */
@Provider
public class BadRequestExceptionFilter implements ExceptionMapper<BadRequestException> {

    /**
     * BAD REQUEST EXCEPTION
     * @param exception
     * @return 400
     */
    @Override
    public Response toResponse(BadRequestException exception) {
        ErrorDto error = new ErrorDto(exception.getMessage(), exception.getStackTrace()[0].toString());
        return Response.status(Response.Status.BAD_REQUEST)
                        .entity(error)
                        .build();
    }
    
}

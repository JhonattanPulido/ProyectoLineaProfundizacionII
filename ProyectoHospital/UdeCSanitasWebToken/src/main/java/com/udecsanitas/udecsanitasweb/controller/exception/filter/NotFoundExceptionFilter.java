// Paquete
package com.udecsanitas.udecsanitasweb.controller.exception.filter;

// Librerías
import javax.ws.rs.ext.Provider;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import com.udecsanitas.exception.NotFoundException;
import com.udecsanitas.udecsanitasweb.pojo.ErrorDto;

/**
 * Filtro de not found exception
 * @author Jhonattan Pulido
 * @version 1.0.0
 * @since 18/05/2021
 */
@Provider
public class NotFoundExceptionFilter implements ExceptionMapper<NotFoundException> {

    /**
     * NOT FOUND EXCEPTION
     * @param exception
     * @return 404
     */
    @Override
    public Response toResponse(NotFoundException exception) {
        ErrorDto error = new ErrorDto(exception.getMessage(), exception.getStackTrace()[0].toString());
        return Response.status(Response.Status.NOT_FOUND)
                        .entity(error)
                        .build();
    }        
    
}

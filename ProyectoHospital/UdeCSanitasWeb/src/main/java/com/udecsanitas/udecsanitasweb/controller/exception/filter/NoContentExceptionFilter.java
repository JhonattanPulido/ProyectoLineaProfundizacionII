// Paquete
package com.udecsanitas.udecsanitasweb.controller.exception.filter;

// Librerías
import javax.ws.rs.ext.Provider;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import com.udecsanitas.udecsanitasweb.pojo.ErrorDto;
import com.udecsanitas.exception.NoContentException;

/**
 * Filtro de NoContentException 
 * @author Jhonattan Pulido
 * @version 1.0.0
 * @since 19/05/2021
 */
@Provider
public class NoContentExceptionFilter implements ExceptionMapper<NoContentException> {

    /**
     * NO CONTENT EXCEPTION
     * @param exception
     * @return 204
     */
    @Override
    public Response toResponse(NoContentException exception) {
        ErrorDto error = new ErrorDto(exception.getMessage(), exception.getStackTrace()[0].toString());
        return Response.status(Response.Status.NO_CONTENT)
                        .entity(error)
                        .build();
    }
            
}

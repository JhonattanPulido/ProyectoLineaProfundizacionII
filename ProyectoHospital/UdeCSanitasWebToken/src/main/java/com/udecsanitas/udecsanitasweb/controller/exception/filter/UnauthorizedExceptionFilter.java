// Paquete
package com.udecsanitas.udecsanitasweb.controller.exception.filter;

// Librerías
import javax.ws.rs.ext.Provider;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import com.udecsanitas.udecsanitasweb.pojo.ErrorDto;
import com.udecsanitas.exception.UnauthorizedException;

/**
 * Filtro de unauthorized exception
 * @author Jhonattan Pulido
 * @version 1.0.0
 * @since 01/06/2021
 */
@Provider
public class UnauthorizedExceptionFilter implements ExceptionMapper<UnauthorizedException> {

    /**
     * UNAUTHORIZED EXCEPTION
     * @param exception
     * @return 401
     */
    @Override
    public Response toResponse(UnauthorizedException exception) {
        ErrorDto error = new ErrorDto(exception.getMessage(), exception.getStackTrace()[0].toString());
        return Response.status(Response.Status.UNAUTHORIZED)
                        .entity(error)
                        .build();
    }
    
}

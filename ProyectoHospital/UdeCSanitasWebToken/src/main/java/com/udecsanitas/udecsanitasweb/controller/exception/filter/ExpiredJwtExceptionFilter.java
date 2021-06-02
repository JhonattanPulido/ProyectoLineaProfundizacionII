// Paquete
package com.udecsanitas.udecsanitasweb.controller.exception.filter;

// Librerías
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import io.jsonwebtoken.ExpiredJwtException;
import com.udecsanitas.udecsanitasweb.pojo.ErrorDto;

/**
 * Filtro de expired jwt exception
 * @author Jhonattan Pulido
 * @version 1.0.0
 * @since 01/06/2021
 */
public class ExpiredJwtExceptionFilter implements ExceptionMapper<ExpiredJwtException> {

    /**
     * UNAUTHORIZED EXCEPTION
     * @param exception
     * @return 401
     */
    @Override
    public Response toResponse(ExpiredJwtException exception) {
        ErrorDto error = new ErrorDto("El token ya expiró, debe iniciar sesión nuevamente", exception.getStackTrace()[0].toString());
        return Response.status(Response.Status.UNAUTHORIZED)
                        .entity(error)
                        .build();
    }
    
}

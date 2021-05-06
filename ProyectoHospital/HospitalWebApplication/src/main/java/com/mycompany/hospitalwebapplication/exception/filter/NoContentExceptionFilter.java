// Paquete
package com.mycompany.hospitalwebapplication.exception.filter;

// Librerías
import com.mycompany.hospitalwebapplication.pojo.ErrorDto;
import javax.ws.rs.core.NoContentException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Filtro de NoContentException
 * @author Jhonattan Pulido
 * @version 1.0.0
 * @since 05/05/2021
 */
@Provider
public class NoContentExceptionFilter implements ExceptionMapper<NoContentException> {

    /**
     * NO CONTENT
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

// Paquete
package com.mycompany.hospitalwebapplication.exception.filter;

// Librerías
import com.hospitalejbmodule.excepciones.IntegridadException;
import com.mycompany.hospitalwebapplication.pojo.ErrorDto;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Filtro de IntegridadException
 * @author Jhonattan Pulido
 * @version 1.0.0
 * @since 05/05/2021
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

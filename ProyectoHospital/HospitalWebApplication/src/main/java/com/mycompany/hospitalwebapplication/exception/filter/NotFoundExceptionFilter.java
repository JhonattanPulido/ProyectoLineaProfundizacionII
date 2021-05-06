// Paquete
package com.mycompany.hospitalwebapplication.exception.filter;

// Librerías
import com.hospitalejbmodule.excepciones.NotFoundException;
import com.mycompany.hospitalwebapplication.pojo.ErrorDto;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Filtro de NotFoundException
 * @author Jhonattan Pulido
 * @version 1.0.0
 * @since 05/05/2021
 */
@Provider
public class NotFoundExceptionFilter implements ExceptionMapper<NotFoundException> {

    /**
     * NOT FOUND
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

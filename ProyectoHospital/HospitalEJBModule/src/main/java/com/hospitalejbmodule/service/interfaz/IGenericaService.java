// Paquete
package com.hospitalejbmodule.service.interfaz;

// Librerías
import com.hospitalejbmodule.excepciones.IntegridadException;
import com.hospitalejbmodule.excepciones.NotFoundException;
import java.util.List;
import javax.ws.rs.core.NoContentException;

/**
 * Interfaz de la capa de servicios de genérica
 * @author Jhonattan Pulido
 * @version 1.0.0
 * @since 05/05/2021
 * @param <T>
 * @param <U>
 */
public interface IGenericaService<T, U> {
    
    // Métodos
    
    /**
     * Crear registro
     * @param t 
     * @throws IntegridadException
     * @throws NotFoundException
     */
    public void crear(T t) throws   IntegridadException, NotFoundException;
    
    /**
     * Leer registro
     * @param id
     * @return 
     * @throws NotFoundException
     */
    public U leer(short id) throws  NotFoundException;
    
    /**
     * Leer todos los registros
     * @return
     * @throws NoContentException 
     */
    public List<U> leer() throws    NoContentException;
    
    /**
     * Actualizar registro
     * @param t
     * @throws NotFoundException 
     */
    public void actualizar(T t) throws NotFoundException;
        
}

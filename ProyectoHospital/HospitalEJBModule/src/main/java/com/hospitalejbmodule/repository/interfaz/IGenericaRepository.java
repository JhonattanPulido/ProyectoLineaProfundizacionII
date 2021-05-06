// Paquete
package com.hospitalejbmodule.repository.interfaz;

// Librerías
import com.hospitalejbmodule.excepciones.NotFoundException;
import java.util.List;

/**
 * Interfaz de la capa de datos de genérica
 * @author Jhonattan Pulido
 * @version 1.0.0
 * @since 05/05/2021
 * @param <T>
 */
public interface IGenericaRepository<T> {
    
    // Métodos
    
    /**
     * Crear registro
     * @param t 
     */
    public void crear(T t);
    
    /**
     * Leer un registro
     * @param id
     * @param queryName 
     * @return 
     * @throws NotFoundException
     */
    public T leer(String queryName, short id) throws    NotFoundException;
    
    /**
     * Leer todos los registros
     * @return 
     */
    public List<T> leer();
        
    /**
     * Actualizar registro
     * @param t 
     */
    public void actualizar(T t);
    
    /**
     * Eliminar registro
     * @param t 
     */
    public void eliminar(T t);
    
    /**
     * Cantidad de registros con un ID
     * @param queryName
     * @param id
     * @return 
     */
    public long cantidadId(String queryName, short id);
    
}

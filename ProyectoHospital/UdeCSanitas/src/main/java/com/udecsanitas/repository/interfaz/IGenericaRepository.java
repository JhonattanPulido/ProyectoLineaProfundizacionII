// Paquete
package com.udecsanitas.repository.interfaz;

// Librerías

import com.udecsanitas.exception.NotFoundException;
import java.util.List;


/**
 * Interfaz de la capa de datos de genérica
 * @author Jhonattan Pulido
 * @version 1.0.0
 * @since 18/05/2021
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
     * Leer registro
     * @param queryName - Nombre del query
     * @param id - ID del registro
     * @return Registro
     * @throws NotFoundException 
     */
    public T leer(String queryName, short id) throws    NotFoundException;
    
    /**
     * Leer registros paginados
     * @param queryName - Nombre del query
     * @param inicio - Registro de inicio
     * @param cantidad - Cantidad a leer
     * @return Lista de registros
     */
    public List<T> leer(String queryName, short inicio, short cantidad);        
    
    /**
     * Actualizar
     * @param t - Registro
     */
    public void actualizar(T t);
    
    /**
     * Cantidad total de registros con un id
     * @param queryName
     * @param id
     * @return 
     */
    public long cantidadId(String queryName, short id);
    
    /**
     * Cantidad total de registros
     * @param queryName - Nombre del query
     * @return Cantidad de registros
     */
    public long cantidadTotal(String queryName);
    
}

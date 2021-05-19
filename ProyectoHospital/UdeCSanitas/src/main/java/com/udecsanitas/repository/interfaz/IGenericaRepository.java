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
     * Cantidad total de registros
     * @param queryName - Nombre del query
     * @return Cantidad de registros
     */
    public long cantidadTotal(String queryName);
    
}

// Paquete
package com.udecsanitas.service.interfaz;

// Librerías
import com.udecsanitas.exception.NoContentException;
import com.udecsanitas.exception.IntegridadException;
import com.udecsanitas.exception.NotFoundException;

/**
 * Interfaz de la capa de servicios de genérica
 * @author Jhonattan Pulido
 * @version 1.0.0
 * @since 18/05/2021
 * @param <T> - Entidad
 * @param <U> - Utilitario  
 */
public interface IGenericaService<T, U> {
    
    // Métodos
    
    /**
     * Crear registro
     * @param t - Objeto
     * @throws IntegridadException 
     */
    public void crear(T t) throws   IntegridadException;        
    
    /**
     * Leer registros paginando
     * @param inicio - Registro de inicio
     * @param cantidad - Cantidad a obtener
     * @return Lista de registros
     * @throws NoContentException
     */
    public String leer(short inicio, short cantidad) throws NoContentException;        
    
    /**
     * Eliminar registro
     * @param id - ID del registro
     * @throws NotFoundException 
     */
    public void eliminar(short id) throws    NotFoundException;
    
}

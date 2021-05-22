// Paquete
package com.udecsanitas.repository.interfaz;

// Librerías
import javax.ejb.Local;
import com.udecsanitas.entity.Examen;

/**
 * Interfaz de la capa de datos de examen
 * @author Jhonattan Pulido
 * @version 1.0.0
 * @since 22/05/2021
 */
@Local
public interface IExamenRepository extends IGenericaRepository<Examen> {
    
    // Métodos
    
    /**
     * Eliminar examen
     * @param examen 
     */
    public void eliminar(Examen examen);
    
    /**
     * Cantidad de exámenes con un nombre
     * @param nombre
     * @return Cantidad de exámenes
     */
    public long cantidadNombre(String nombre);
    
    /**
     * Validar nombre de examen para actualizar
     * @param nombre
     * @param id
     * @return cantidad de exámenes
     */
    public long validarNombre(String nombre, short id);       
    
}

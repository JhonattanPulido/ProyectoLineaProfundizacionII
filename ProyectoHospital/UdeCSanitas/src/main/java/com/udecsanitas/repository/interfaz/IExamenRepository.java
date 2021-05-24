// Paquete
package com.udecsanitas.repository.interfaz;

// Librerías
import javax.ejb.Local;
import com.udecsanitas.entity.Examen;
import java.util.List;

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
     * Leer todos los exámenes
     * @return lista de exámenes
     */
    public List<Examen> leer();
    
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
     * Cantidad de consultas examenes que tienen asociado un examen
     * @param id
     * @return Cantidad de exámenes
     */
    public long cantidadConsultasExamenes(short id);
    
    /**
     * Validar nombre de examen para actualizar
     * @param nombre
     * @param id
     * @return cantidad de exámenes
     */
    public long validarNombre(String nombre, short id);       
    
}

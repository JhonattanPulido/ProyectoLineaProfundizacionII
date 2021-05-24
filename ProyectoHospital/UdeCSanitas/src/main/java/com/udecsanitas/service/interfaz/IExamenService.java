// Paquetes
package com.udecsanitas.service.interfaz;

// Librerías
import javax.ejb.Local;
import com.udecsanitas.entity.Examen;
import com.udecsanitas.utilitarie.UExamen;
import com.udecsanitas.exception.NotFoundException;
import com.udecsanitas.exception.IntegridadException;
import com.udecsanitas.exception.NoContentException;
import java.util.List;

/**
 * Interfaz de la capa de servicios de examen
 * @author Jhonattan Pulido
 * @version 1.0.0
 * @since 22/05/2021
 */
@Local
public interface IExamenService extends IGenericaService<Examen, UExamen> {        
    
    // Métodos
        
    /**
     * Leer examen filtrado por id
     * @param id
     * @return Examen
     * @throws NotFoundException
     */
    public UExamen leer(short id) throws    NotFoundException;
    
    /**
     * Leer todos los exámenes
     * @return Lista de exámenes
     * @throws NoContentException 
     */
    public List<UExamen> leer() throws NoContentException;
    
    /**
     * Actualizar examen
     * @param examen
     * @throws NotFoundException
     * @throws IntegridadException 
     */
    public void actualizar(Examen examen) throws    NotFoundException,
                                                    IntegridadException;
    
}

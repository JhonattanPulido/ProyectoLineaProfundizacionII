// Paquete
package com.udecsanitas.service.interfaz;

// Librerías
import java.util.List;
import javax.ejb.Local;
import com.udecsanitas.entity.Consulta;
import com.udecsanitas.exception.NotFoundException;
import com.udecsanitas.exception.NoContentException;

/**
 * Interfaz de la capa de servicios de consulta
 * @author Jhonattan Pulido
 * @version 1.0.0
 * @since 23/05/2021
 */
@Local
public interface IConsultaService {        
    
    // Métodos
    
    /**
     * Crear consulta
     * @param listaConsultas 
     * @param medicoId
     * @throws NoContentException
     * @throws NotFoundException     
     */
    public void crear(List<Consulta> listaConsultas, short medicoId) throws NoContentException,
                                                                            NotFoundException;
    
}

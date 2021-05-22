// Paquete
package com.udecsanitas.service.interfaz;

// Librerías
import javax.ejb.Local;
import com.udecsanitas.entity.Medico;
import com.udecsanitas.utilitarie.UMedico;
import com.udecsanitas.exception.NotFoundException;
import com.udecsanitas.utilitarie.UMedicoPaginador;

/**
 * Interfaz de la capa de datos de médico
 * @author Jhonattan Pulido
 * @version 1.0.0
 * @since 18/05/2021
 */
@Local
public interface IMedicoService extends IGenericaService<Medico, UMedico> {        
    
    // Métodos
    
    /**
     * Leer registro
     * @param id - ID del registro
     * @param direccion - ¿Desea retornar la dirección?
     * @return Registro
     * @throws NotFoundException 
     */
    public UMedico leer(short id, String direccion) throws  NotFoundException;
    
}

// Paquete
package com.hospitalejbmodule.service.interfaz;

// Librerías
import com.hospitalejbmodule.entity.Medico;
import javax.ejb.Local;

/** 
 * Interfaz de la capa de servicios de médico
 * @author Jhonattan Pulido
 * @version 1.0.0
 * @since 05/05/2021
 */
@Local
public interface IMedicoService extends IGenericaService<Medico> {
    
}

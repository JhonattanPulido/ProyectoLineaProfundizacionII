// Paquete
package com.hospitalejbmodule.service.interfaz;

// Librerías
import com.hospitalejbmodule.entity.Consulta;
import com.hospitalejbmodule.utilitarie.UConsulta;
import javax.ejb.Local;

/**
 * Interfaz de la capa de servicios de consulta
 * @author Jhonattan Pulido
 * @version 1.0.0
 * @since 05/05/2021
 */
@Local
public interface IConsultaService extends IGenericaService<Consulta, UConsulta> {
                    
}

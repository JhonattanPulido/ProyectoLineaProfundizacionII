// Paquete
package com.hospitalejbmodule.repository.interfaz;

// Librerías
import com.hospitalejbmodule.entity.Consulta;
import javax.ejb.Local;

/**
 * Interfaz de la capa de datos de consulta
 * @author Jhonattan Pulido
 * @version 1.0.0
 * @since 05/05/2021
 */
@Local
public interface IConsultaRepository extends IGenericaRepository<Consulta> {
    
}

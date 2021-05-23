// Paquete
package com.udecsanitas.repository.interfaz;

// Librerías
import javax.ejb.Local;
import com.udecsanitas.entity.Consulta;

/**
 * Interfaz de la capa de datos de consulta
 * @author Jhonattan Pulido
 * @version 1.0.0
 * @since 23/05/2021
 */
@Local
public interface IConsultaRepository extends IGenericaRepository<Consulta> {
    
}

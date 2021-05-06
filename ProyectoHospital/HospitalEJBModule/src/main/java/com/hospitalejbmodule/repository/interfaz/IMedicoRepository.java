// Paquete
package com.hospitalejbmodule.repository.interfaz;

// Librerías
import javax.ejb.Local;
import com.hospitalejbmodule.entity.Medico;

/**
 * Interfaz de la capa de datos de médico
 * @author Jhonattan Pulido
 * @version 1.0.0
 * @since 05/05/2021
 */
@Local
public interface IMedicoRepository extends IGenericaRepository<Medico> {
    
    // Métodos
    
    /**
     * Cantidad de médicos con un correo electrónico
     * @param correoElectronico
     * @return 
     */
    public long cantidadEmail(String correoElectronico);
    
}

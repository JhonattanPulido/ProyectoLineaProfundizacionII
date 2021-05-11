// Paquete
package com.hospitalejbmodule.repository;

// Librerías
import com.hospitalejbmodule.entity.Medico;
import com.hospitalejbmodule.repository.interfaz.IMedicoRepository;
import javax.ejb.Stateless;

/**
 * Capa de datos de médico
 * @author Jhonattan Pulido
 * @version 1.0.0
 * @since 05/05/2021
 */
@Stateless
public class MedicoRepository extends GenericaRepository<Medico> implements IMedicoRepository {

    // Métodos
    
    /**
     * Cantidad de médicos con un correo electrónico
     * @param correoElectronico
     * @return 
     */
    @Override
    public long cantidadEmail(String correoElectronico) {
        return (long) em.createNamedQuery("QMedicosEmail", long.class).setParameter("correo_electronico", correoElectronico).getSingleResult();
    }       
    
}

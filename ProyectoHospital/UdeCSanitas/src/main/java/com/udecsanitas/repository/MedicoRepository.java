// Paquete
package com.udecsanitas.repository;

// Librerías
import javax.ejb.Stateless;
import com.udecsanitas.entity.Medico;
import com.udecsanitas.repository.interfaz.IMedicoRepository;

/**
 * Capa de datos de médico
 * @author Jhonattan Pulido
 * @version 1.0.0
 * @since 18/05/2021
 */
@Stateless
public class MedicoRepository extends GenericaRepository<Medico> implements IMedicoRepository {

    // Métodos
    
    /**
     * Cantidad de médicos con un correo electrónico
     * @param correoElectronico
     * @return cantidad de registros con ID
     */
    @Override
    public long cantidadEmail(String correoElectronico) {
        return (long) em.createNamedQuery("QMedicosEmail", long.class).setParameter("correo_electronico", correoElectronico).getSingleResult();
    }
    
}

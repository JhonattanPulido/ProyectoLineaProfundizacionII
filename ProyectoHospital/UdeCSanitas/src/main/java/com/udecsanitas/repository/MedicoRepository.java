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
     * Eliminar médico
     * @param medico 
     */
    @Override
    public void eliminar(Medico medico) {
        em.createNamedQuery("EliminarDireccion").setParameter("medico_id", medico.getId()).executeUpdate();
        em.createNamedQuery("EliminarMedico").setParameter("id", medico.getId()).executeUpdate();
    }
    
    /**
     * Cantidad de médicos con un correo electrónico
     * @param correoElectronico
     * @return cantidad de registros con ID
     */
    @Override
    public long cantidadEmail(String correoElectronico) {
        return (long) em.createNamedQuery("QMedicosEmail", long.class).setParameter("correo_electronico", correoElectronico).getSingleResult();
    }    

    /**
     * Validar correo electrónico para actualizar
     * @param correoElectronico
     * @param id
     * @return cantidad de registros
     */
    @Override
    public long validarEmail(String correoElectronico, short id) {
        return (long) em.createNamedQuery("ValidarEmail", long.class)
                .setParameter("correo_electronico", correoElectronico)
                .setParameter("id", id)
                .getSingleResult();
    }   
    
}

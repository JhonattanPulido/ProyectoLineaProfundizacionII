// Paquete
package com.udecsanitas.repository;

// Librerías
import javax.ejb.Stateless;
import com.udecsanitas.entity.Medico;
import javax.persistence.NoResultException;
import com.udecsanitas.exception.NotFoundException;
import com.udecsanitas.exception.UnauthorizedException;
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
     * Leer médico filtrado por correo electrónico y clave
     * @param correoElectronico
     * @param clave
     * @return Médico
     * @throws UnauthorizedException
     */
    @Override
    public Medico leer(String correoElectronico, String clave) throws   UnauthorizedException {        
        try {
            return em.createNamedQuery("LoginMedico", Medico.class)
                    .setParameter("correo_electronico", correoElectronico)
                    .setParameter("clave", clave)
                    .getSingleResult();
        } catch (NoResultException ex) {
            throw new UnauthorizedException("Datos incorrectos");
        }
    }
    
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
     * Cantidad de consultas pertenecientes a un médico
     * @param id
     * @return Cantidad registros
     */
    @Override
    public long cantidadConsultas(short id) {
        return (long) em.createNamedQuery("QMedicosConsulta", long.class)
                .setParameter("id", id)
                .getSingleResult();
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

    @Override
    public String leerRol(Short id) {
        return em.createNamedQuery("LeerRol", String.class)
                .setParameter("id", id)
                .getSingleResult();
    }
    
}

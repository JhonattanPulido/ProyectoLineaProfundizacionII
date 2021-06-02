// Paquete
package com.udecsanitas.repository;

// Librerías
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import com.udecsanitas.entity.Seguridad;
import javax.persistence.PersistenceContext;
import com.udecsanitas.exception.NotFoundException;
import com.udecsanitas.repository.interfaz.ISeguridadRepository;
import javax.persistence.NoResultException;

/**
 * Capa de datos de seguridad
 * @author Jhonattan Pulido
 * @version 1.0.0
 * @since 01/06/2021
 */
@Stateless
public class SeguridadRepository implements ISeguridadRepository {

    // Variables
    @PersistenceContext(unitName = "com.udecsanitas_UdeCSanitas_ejb_1.0-SNAPSHOTPU")
    protected EntityManager em;
    
    // Métodos
    
    /**
     * Crear registro de inicio de sesión
     * @param seguridad 
     */
    @Override
    public void crear(Seguridad seguridad) {
        em.persist(seguridad);
    }

    /**
     * Leer registro de inicio de sesión
     * @param token
     * @return Registro
     * @throws NotFoundException 
     */
    @Override
    public Seguridad leer(String token) throws  NotFoundException {
        try {
            return em.createNamedQuery("LeerToken", Seguridad.class)
                    .setParameter("token", token)
                    .getSingleResult();
        } catch (NoResultException ex) {
            throw new NotFoundException("No se encontró el registro");
        }
    }

    /**
     * Eliminar registro de inicio de sesión
     * @param token 
     */
    @Override
    public void eliminar(String token) {
        em.createNamedQuery("EliminarSeguridad").setParameter("token", token).executeUpdate();
    }
    
    /**
     * Cantidad de registros que tienen un token
     * @param token
     * @param  correoElectronico 
     * @return Cantidad de registros
     */
    @Override
    public long cantidad(String token, String correoElectronico) {        
        return (long) em.createNamedQuery("QSeguridadToken", long.class)
                .setParameter("token", token)
                .setParameter("correo_electronico", correoElectronico)
                .getSingleResult();
    }

    /**
     * Cantidad de registros que tienen un correo electrónico
     * @param correoElectronico
     * @return Cantidad de registros
     */
    @Override
    public long cantidadEmail(String correoElectronico) {
        return (long) em.createNamedQuery("QSeguridadEmail", long.class)
                .setParameter("correo_electronico", correoElectronico)
                .getSingleResult();
    }    

    /**
     * Cantidad de registros con un token
     * @param token
     * @return Cantidad de registros
     */
    @Override
    public long cantidadToken(String token) {
        return (long) em.createNamedQuery("QSeguridad", long.class)
                    .setParameter("token", token)
                    .getSingleResult();
    }
    
}

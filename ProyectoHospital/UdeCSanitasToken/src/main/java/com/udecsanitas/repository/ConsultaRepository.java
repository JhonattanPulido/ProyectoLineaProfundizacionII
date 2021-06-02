// Paquete
package com.udecsanitas.repository;

// Librerías
import javax.ejb.Stateless;
import com.udecsanitas.entity.Consulta;
import com.udecsanitas.exception.NotFoundException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.udecsanitas.repository.interfaz.IConsultaRepository;
import java.util.List;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 * Capa de datos de consulta
 * @author Jhonattan Pulido
 * @version 1.0.0
 * @since 23/05/2021
 */
@Stateless
public class ConsultaRepository implements IConsultaRepository {

    // Variables    
    @PersistenceContext(unitName = "com.udecsanitas_UdeCSanitas_ejb_1.0-SNAPSHOTPU")
    protected EntityManager em;
    
    // Métodos
    
    /**
     * Crear consulta
     * @param consulta
     * @return consulta
     */
    @Override
    public Consulta crear(Consulta consulta) {
        em.persist(consulta);
        em.flush();
        return consulta;
    }
    
    /**
     * Leer consulta por ID
     * @param id
     * @return Consulta
     * @throws NotFoundException
     */
    @Override
    public Consulta leer(short id) throws   NotFoundException {
        try {
            return em.createNamedQuery("LeerConsulta", Consulta.class).setParameter("id", id).getSingleResult();
        } catch (NoResultException ex) {
            throw new NotFoundException("No se encontró la consulta");
        }
    }

    /**
     * Leer consultas filtradas por médico
     * @param inicio
     * @param cantidad
     * @param medicoId
     * @return Lista de consultas
     */
    @Override
    public List<Consulta> leer(short inicio, short cantidad, short medicoId) {
        Query query = em.createNamedQuery("LeerConsultasXMedico", Consulta.class)
            .setParameter("medico_id", medicoId);
        query.setFirstResult(inicio);
        query.setMaxResults(cantidad);
        return query.getResultList();
    }
    
    /**
     * Actualizar consulta
     * @param consulta
     */
    @Override
    public void actualizar(Consulta consulta) {
        em.merge(consulta);
    }
    
    /**
     * Eliminar consulta
     * @param consulta 
     */
    @Override
    public void eliminar(Consulta consulta) {
        em.createNamedQuery("EliminarConsultaExamen").setParameter("consulta_id", consulta.getId()).executeUpdate();
        em.createNamedQuery("EliminarDetalleConsulta").setParameter("consulta_id", consulta.getId()).executeUpdate();
        em.createNamedQuery("EliminarConsulta").setParameter("id", consulta.getId()).executeUpdate();
    }

    /**
     * Cantidad de consultas con un id
     * @param id
     * @return Cantidad de registros
     */
    @Override
    public long cantidadId(short id) {
        return (long) em.createNamedQuery("QConsultas", long.class)
                .setParameter("id", id)
                .getSingleResult();
    }
    
    /**
     * Cantidad de consultas con un médico asociado
     * @param medicoId
     * @return cantidad de registros
     */
    @Override
    public long cantidadMedicoId(short medicoId) {
        return (long) em.createNamedQuery("QConsultasXMedico", long.class)
                .setParameter("medico_id", medicoId)
                .getSingleResult();
    }        
          
}

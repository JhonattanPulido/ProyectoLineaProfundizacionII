// Paquete
package com.udecsanitas.repository;

// Librerías
import javax.ejb.Stateless;
import com.udecsanitas.entity.Consulta;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.udecsanitas.repository.interfaz.IConsultaRepository;
import java.util.List;
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

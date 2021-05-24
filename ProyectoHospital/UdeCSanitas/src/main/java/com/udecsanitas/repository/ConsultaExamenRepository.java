// Paquete
package com.udecsanitas.repository;

// Librerías
import javax.ejb.Stateless;
import com.udecsanitas.entity.ConsultaExamen;
import com.udecsanitas.repository.interfaz.IConsultaExamenRepository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Capa de datos de consulta examen
 * @author Jhonattan Pulido
 * @version 1.0.0
 * @since 23/05/2021
 */
@Stateless
public class ConsultaExamenRepository implements IConsultaExamenRepository {

    // Variables    
    @PersistenceContext(unitName = "com.udecsanitas_UdeCSanitas_ejb_1.0-SNAPSHOTPU")
    protected EntityManager em;
    
    // Métodos
    
    /**
     * Crear consulta examen
     * @param consultaExamen
     * @return consulta examen
     */
    @Override
    public ConsultaExamen crear(ConsultaExamen consultaExamen) {
        em.persist(consultaExamen);
        em.flush();
        return consultaExamen;
    }

    /**
     * Cantidad de consultas examen con una consulta y un examen
     * @param consultaId
     * @param examenId
     * @return cantidad de registros
     */
    @Override
    public long cantidadId(short consultaId, short examenId) {
        return (long) em.createNamedQuery("QConsultaExamen", long.class)
                .setParameter("consulta_id", consultaId)
                .setParameter("examen_id", examenId)
                .getFirstResult();
    }
    
}

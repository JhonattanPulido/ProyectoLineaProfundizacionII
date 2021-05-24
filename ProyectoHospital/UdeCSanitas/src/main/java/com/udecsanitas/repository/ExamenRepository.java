// Paquete
package com.udecsanitas.repository;

// Librerías
import javax.ejb.Stateless;
import com.udecsanitas.entity.Examen;
import com.udecsanitas.repository.interfaz.IExamenRepository;
import java.util.List;

/**
 * Capa de datos de examen
 * @author Jhonatttan Pulido
 * @version 1.0.0
 * @since 22/05/2021
 */
@Stateless
public class ExamenRepository extends GenericaRepository<Examen> implements IExamenRepository {

    // Métodos
    
    /**
     * Leer todos los exámenes
     * @return Lista de exámenes
     */
    @Override
    public List<Examen> leer() {
        return em.createNamedQuery("LeerExamenes", Examen.class).getResultList();
    }
    
    /**
     * Eliminar examen
     * @param examen 
     */
    @Override
    public void eliminar(Examen examen) {
        em.createNamedQuery("EliminarExamen").setParameter("id", examen.getId()).executeUpdate();
    }
    
    /**
     * Cantidad de exámenes con un nombre
     * @param nombre
     * @return Cantidad de exámenes
     */
    @Override
    public long cantidadNombre(String nombre) {
        return (long) em.createNamedQuery("QExamenesNombre", long.class).setParameter("nombre", nombre).getSingleResult();
    }

    /**
     * Validar nombre de examen para actualizar
     * @param nombre
     * @param id
     * @return cantidad de exámenes
     */
    @Override
    public long validarNombre(String nombre, short id) {
        return (long) em.createNamedQuery("ValidarNombre", long.class)
                .setParameter("nombre", nombre)
                .setParameter("id", id)
                .getSingleResult();
    }        
    
}

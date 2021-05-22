// Paquete
package com.udecsanitas.repository;

// Libreías
import java.util.List;
import java.lang.reflect.Type;
import javax.persistence.Query;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.lang.reflect.ParameterizedType;
import javax.persistence.PersistenceContext;
import com.udecsanitas.exception.NotFoundException;
import com.udecsanitas.repository.interfaz.IGenericaRepository;

/**
 * Capa de datos de genérica
 * @author Jhonattan Pulido
 * @version 1.0.0
 * @since 18/05/2021
 * @param <T>
 */
public class GenericaRepository<T> implements IGenericaRepository<T> {
    
    // Variables
    @PersistenceContext(unitName = "com.udecsanitas_UdeCSanitas_ejb_1.0-SNAPSHOTPU")
    protected EntityManager em;
    
    private final Class<T> classType;

    /**
     * Constructor
     */
    public GenericaRepository() {
        Type t = getClass().getGenericSuperclass();
        ParameterizedType pt = (ParameterizedType) t;
        classType = (Class) pt.getActualTypeArguments()[0];
    }
    
    // Métodos
    
    /**
     * Crear registro
     * @param t 
     */
    @Override
    public void crear(T t) {
        em.persist(t);
    }

    /**
     * Leer registro
     * @param queryName - Nombre del query
     * @param id - ID del registro
     * @return Registro
     * @throws NotFoundException 
     */
    @Override
    public T leer(String queryName, short id) throws    NotFoundException {
        try {
            return (T) em.createNamedQuery(queryName, classType).setParameter("id", id).getSingleResult();
        } catch (NoResultException ex) {
            throw new NotFoundException("No se encontró el registro");
        }
    }

    /**
     * Leer registros paginados
     * @param queryName - Nombre del query
     * @param inicio - Registro de inicio
     * @param cantidad - Cantidad a leer
     * @return Lista de registros
     */
    @Override
    public List<T> leer(String queryName, short inicio, short cantidad) {
        Query query = em.createNamedQuery(queryName, classType);
        query.setFirstResult(inicio);
        query.setMaxResults(cantidad);
        return query.getResultList();
    }        
    
    /**
     * Cantidad de registros con un ID     
     * @param id 
     * @param queryName 
     * @return 
     */
    @Override
    public long cantidadId(String queryName, short id) {
        return (long) em.createNamedQuery(queryName, long.class).setParameter("id", id).getSingleResult();
    }

    /**
     * Cantidad total de registros
     * @param queryName - Nombre del query
     * @return Cantidad de registros
     */
    @Override
    public long cantidadTotal(String queryName) {
        return (long) em.createNamedQuery(queryName, long.class).getSingleResult();
    }    
    
}

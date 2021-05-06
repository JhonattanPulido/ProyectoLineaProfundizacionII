// Paquete
package com.hospitalejbmodule.repository;

// Librerías
import com.hospitalejbmodule.excepciones.NotFoundException;
import com.hospitalejbmodule.repository.interfaz.IGenericaRepository;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 * Capa de datos de genérica
 * @author Jhonattan Pulido
 * @version 1.0.0
 * @since 05/05/2021
 * @param <T>
 */
public class GenericaRepository<T> implements IGenericaRepository<T> {

    // Variables
    
    /**
     * Vaiable de persistencia contra la BD
     */
    @PersistenceContext(unitName = "com.mycompany_HospitalEJBModule_ejb_1.0-SNAPSHOTPU")
    protected EntityManager em;
    
    /**
     * Almacena el tipo de clase que se desea usar
     */
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
     * Leer un registro
     * @param id
     * @param queryName 
     * @return 
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
     * Leer todos los registros
     * @return 
     */
    @Override
    public List<T> leer() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = cb.createQuery(classType);
        Root<T> root = criteriaQuery.from(classType);
        criteriaQuery.select(root);
        TypedQuery<T> query = em.createQuery(criteriaQuery);
        return query.getResultList();
    }

    /**
     * Actualizar registro
     * @param t 
     */
    @Override
    public void actualizar(T t) {
        em.merge(t);
    }

    /**
     * Cantidad de registros con un ID
     * @param queryName
     * @param id
     * @return 
     */
    @Override
    public long cantidadId(String queryName, short id) {
        return (long) em.createNamedQuery(queryName, long.class).setParameter("id", id).getSingleResult();
    }
    
}

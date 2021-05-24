// Paquete
package com.udecsanitas.repository.interfaz;

// Librerías
import javax.ejb.Local;
import com.udecsanitas.entity.Consulta;
import com.udecsanitas.exception.NotFoundException;
import java.util.List;

/**
 * Interfaz de la capa de datos de consulta
 * @author Jhonattan Pulido
 * @version 1.0.0
 * @since 23/05/2021
 */
@Local
public interface IConsultaRepository {
    
    // Métodos
    
    /**
     * Crear consulta
     * @param consulta
     * @return consulta
     */
    public Consulta crear(Consulta consulta);
    
    /**
     * Leer consulta por ID
     * @param id
     * @return Consulta
     * @throws NotFoundException
     */
    public Consulta leer(short id) throws   NotFoundException;
    
    /**
     * Leer consultas filtradas por médico
     * @param inicio
     * @param cantidad
     * @param medicoId
     * @return Lista de consultas
     */
    public List<Consulta> leer(short inicio, short cantidad, short medicoId);
    
    /**
     * Actualizar consulta
     * @param consulta 
     */
    public void actualizar(Consulta consulta);
    
    /**
     * Eliminar consulta
     * @param consulta 
     */
    public void eliminar(Consulta consulta);
    
    /**
     * Cantidad de consultas con un id
     * @param id
     * @return Cantidad de registros
     */
    public long cantidadId(short id);
    
    /**
     * Cantidad de consultas con un médico asociado
     * @param medicoId
     * @return cantidad de registros
     */
    public long cantidadMedicoId(short medicoId);
    
}

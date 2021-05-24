// Paquete
package com.udecsanitas.repository.interfaz;

// Librerías
import javax.ejb.Local;
import com.udecsanitas.entity.Consulta;
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
     * Leer consultas filtradas por médico
     * @param inicio
     * @param cantidad
     * @param medicoId
     * @return Lista de consultas
     */
    public List<Consulta> leer(short inicio, short cantidad, short medicoId);
    
    /**
     * Cantidad de consultas con un médico asociado
     * @param medicoId
     * @return cantidad de registros
     */
    public long cantidadMedicoId(short medicoId);
    
}

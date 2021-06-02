// Paquete
package com.udecsanitas.repository.interfaz;

// Librerías
import java.util.List;
import javax.ejb.Local;
import com.udecsanitas.entity.ConsultaExamen;

/**
 * Interfaz de la capa de datos de consulta examen
 * @author Jhonattan Pulido
 * @version 1.0.0
 * @since 23/05/2021
 */
@Local
public interface IConsultaExamenRepository {
    
    // Métodos
    
    /**
     * Crear consulta examen
     * @param consultaExamen
     * @return consulta examen
     */
    public ConsultaExamen crear(ConsultaExamen consultaExamen);
    
    /**
     * Leer consultas examen filtradas por consulta
     * @param consultaId
     * @return Lista de consultas examen
     */
    public List<Short> leer(short consultaId);
    
    /**
     * Eliminar consulta examen filtrado por examen
     * @param examenId 
     */
    public void eliminarExamen(short examenId);
    
    /**
     * Cantidad de consultas examen con una consulta y un examen
     * @param consultaId
     * @param examenId
     * @return cantidad de registros
     */
    public long cantidadId(short consultaId, short examenId);
    
}

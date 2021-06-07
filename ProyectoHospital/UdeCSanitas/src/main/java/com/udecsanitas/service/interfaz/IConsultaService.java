// Paquete
package com.udecsanitas.service.interfaz;

// Librerías
import java.util.List;
import javax.ejb.Local;
import com.udecsanitas.entity.Consulta;
import com.udecsanitas.utilitarie.UConsulta;
import com.udecsanitas.exception.NotFoundException;
import com.udecsanitas.exception.NoContentException;

/**
 * Interfaz de la capa de servicios de consulta
 * @author Jhonattan Pulido
 * @version 1.0.0
 * @since 23/05/2021
 */
@Local
public interface IConsultaService {        
    
    // Métodos
    
    /**
     * Crear consulta
     * @param listaConsultas 
     * @param medicoId
     * @throws NoContentException
     * @throws NotFoundException     
     */
    public void crear(List<Consulta> listaConsultas, short medicoId) throws NoContentException,
                                                                            NotFoundException,
                                                                            Exception;
    
    /**
     * Leer consulta
     * @param id
     * @return Consulta
     * @throws NotFoundException 
     */
    public UConsulta leer(short id) throws   NotFoundException;
    
    /**
     * Leer consultas paginadas
     * @param inicio
     * @param cantidad
     * @param medicoId
     * @return Lista de registros
     * @throws NotFoundException
     * @throws NoContentException 
     */
    public String leer(short inicio, short cantidad, short medicoId) throws NotFoundException,
                                                                            NoContentException;
    
    /**
     * Actualizar  consulta
     * @param consulta
     * @throws NotFoundException 
     */
    public void actualizar(Consulta consulta) throws    NotFoundException;
    
    /**
     * Eliminar consulta
     * @param consultaId
     * @throws NotFoundException 
     */
    public void eliminar(short consultaId) throws   NotFoundException;
    
}

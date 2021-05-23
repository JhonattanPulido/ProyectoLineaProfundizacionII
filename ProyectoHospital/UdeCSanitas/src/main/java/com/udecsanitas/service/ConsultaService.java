// Paquete
package com.udecsanitas.service;

// Librerías
import javax.ejb.EJB;
import java.util.List;
import javax.ejb.Stateless;
import com.udecsanitas.entity.Medico;
import com.udecsanitas.entity.Consulta;
import com.udecsanitas.entity.DetalleConsulta;
import com.udecsanitas.exception.NotFoundException;
import com.udecsanitas.exception.NoContentException;
import com.udecsanitas.service.interfaz.IConsultaService;
import com.udecsanitas.repository.interfaz.IExamenRepository;
import com.udecsanitas.repository.interfaz.IMedicoRepository;
import com.udecsanitas.repository.interfaz.IConsultaRepository;

/**
 * Capa de servicios de consulta
 * @author Jhonattan Pulido
 * @version 1.0.0
 * @since 23/05/2021
 */
@Stateless
public class ConsultaService implements IConsultaService {

    // Variables
    
    @EJB
    private IConsultaRepository consultaRepository;
    
    @EJB
    private IMedicoRepository medicoRepository;
    
    @EJB
    private IExamenRepository examenRepository;

    // Métodos

    /**
     * Crear consultas
     * @param listaConsultas
     * @param medicoId 
     * @throws NoContentException
     * @throws NotFoundException     
     */
    @Override
    public void crear(List<Consulta> listaConsultas, short medicoId) throws NoContentException,
                                                                            NotFoundException {                
        
        if (listaConsultas.size() > 0)
            if (medicoRepository.cantidadId("QMedicos", medicoId) == 1) {
            
                Medico medico = medicoRepository.leer("LeerMedico", medicoId);
                
                for (Consulta consulta: listaConsultas) {
                    
                    consulta.setMedico(medico);
                    
                    for (Short examenId: consulta.getListaExamenes())
                        if (examenRepository.cantidadId("QExamenes", examenId) == 0)
                            throw new NotFoundException("No se encontró el examen");
                    
                    for (DetalleConsulta detalleConsulta: consulta.getListaDetallesConsultas())
                        detalleConsulta.setConsulta(consulta);
                    
                    consultaRepository.crear(consulta);
                }                                
                
            } else
                throw new NotFoundException("No se encontró el médico asociado");
        else
            throw new NoContentException("");
        
    }
    
}

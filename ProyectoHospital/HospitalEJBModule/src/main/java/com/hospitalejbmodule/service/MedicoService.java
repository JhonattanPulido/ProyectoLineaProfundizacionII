// Paquete
package com.hospitalejbmodule.service;

// Librerías
import com.hospitalejbmodule.entity.Medico;
import com.hospitalejbmodule.excepciones.IntegridadException;
import com.hospitalejbmodule.excepciones.NotFoundException;
import com.hospitalejbmodule.repository.interfaz.IMedicoRepository;
import com.hospitalejbmodule.service.interfaz.IMedicoService;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.core.NoContentException;

/**
 * Capa de servicios de médico
 * @author Jhonattan Pulido
 * @version 1.0.0
 * @since 05/05/2021
 */
@Stateless
public class MedicoService implements IMedicoService {
    
    // Variables
    
    /**
     * Datos de médico
     */
    @EJB
    private IMedicoRepository medicoRepository;
    
    // Métodos

    /**
     * Crear médico
     * @param medico
     * @throws IntegridadException 
     */
    @Override
    public void crear(Medico medico) throws IntegridadException {
        if (medicoRepository.cantidadEmail(medico.getCorreoElectronico()) == 0) {
            
            medico.getDireccion().setMedico(medico);
            medicoRepository.crear(medico);
            
        } else
            throw new IntegridadException("El correo electrónico ya está en uso");
    }

    /**
     * Leer médico
     * @param id
     * @return
     * @throws NotFoundException 
     */
    @Override
    public Medico leer(short id) throws NotFoundException {
        return medicoRepository.leer("LeerMedico", id);
    }

    /**
     * Leer todos los médicos
     * @return
     * @throws NoContentException 
     */
    @Override
    public List<Medico> leer() throws NoContentException {
        return medicoRepository.leer();
    }

    /**
     * Actualizar médico
     * @param medico
     * @throws NotFoundException 
     */
    @Override
    public void actualizar(Medico medico) throws NotFoundException {
        if (medicoRepository.cantidadId("QMedicos", medico.getId()) == 1) {
            
            medico.getDireccion().setMedico(medico);
            medicoRepository.actualizar(medico);
            
        } else
            throw new NotFoundException("No se encontró el médico");
    }
    
}

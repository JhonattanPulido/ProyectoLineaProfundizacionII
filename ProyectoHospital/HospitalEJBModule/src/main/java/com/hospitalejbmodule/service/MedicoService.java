// Paquete
package com.hospitalejbmodule.service;

// Librerías
import com.hospitalejbmodule.entity.Medico;
import com.hospitalejbmodule.excepciones.IntegridadException;
import com.hospitalejbmodule.excepciones.NotFoundException;
import com.hospitalejbmodule.repository.interfaz.IMedicoRepository;
import com.hospitalejbmodule.service.interfaz.IMedicoService;
import com.hospitalejbmodule.utilitarie.UMedico;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.core.NoContentException;
import org.modelmapper.ModelMapper;

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
            
            //medico.getDireccion().setMedico(medico);
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
    public UMedico leer(short id) throws NotFoundException {
        Medico medico = medicoRepository.leer("LeerMedico", id);                                
        return new ModelMapper().map(medico, UMedico.class);        
    }

    /**
     * Leer todos los médicos
     * @return
     * @throws NoContentException 
     */
    @Override
    public List<UMedico> leer() throws NoContentException {
        
        List<Medico> listaMedicos = medicoRepository.leer();
        
        if (listaMedicos.size() > 0) {
            
            List<UMedico> lista = new ArrayList<>();
            ModelMapper modelMapper = new ModelMapper();
            
            for (Medico medico: listaMedicos)
                lista.add(modelMapper.map(medico, UMedico.class));
            
            return lista;
            
        } else
            throw new NoContentException("");                
            
    }

    /**
     * Actualizar médico
     * @param medico
     * @throws NotFoundException 
     */
    @Override
    public void actualizar(Medico medico) throws NotFoundException {
        if (medicoRepository.cantidadId("QMedicos", medico.getId()) == 1) {
            
            //medico.getDireccion().setMedico(medico);
            medicoRepository.actualizar(medico);
            
        } else
            throw new NotFoundException("No se encontró el médico");
    }
    
}

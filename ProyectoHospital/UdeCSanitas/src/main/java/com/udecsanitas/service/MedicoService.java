// Paquete
package com.udecsanitas.service;

// Librerías
import javax.ejb.EJB;
import java.util.List;
import javax.ejb.Stateless;
import org.modelmapper.ModelMapper;
import com.udecsanitas.entity.Medico;
import com.udecsanitas.utilitarie.UMedico;
import com.udecsanitas.utilitarie.UPaginador;
import com.udecsanitas.exception.NotFoundException;
import com.udecsanitas.utilitarie.UMedicoPaginador;
import com.udecsanitas.exception.IntegridadException;
import com.udecsanitas.exception.NoContentException;
import com.udecsanitas.service.interfaz.IMedicoService;
import com.udecsanitas.repository.interfaz.IMedicoRepository;

/**
 * Capa de servicios de médico
 * @author Jhonattan Pulido
 * @version 1.0.0
 * @since 18/05/2021
 */
@Stateless
public class MedicoService implements IMedicoService {

    // Variables
    
    @EJB
    private IMedicoRepository medicoRepository;
    
    /**
     * Crear médico
     * @param medico
     * @throws IntegridadException 
     */
    @Override
    public void crear(Medico medico) throws     IntegridadException {
        if (medicoRepository.cantidadEmail(medico.getCorreoElectronico()) == 0) {
            medico.getDireccion().setMedico(medico);
            medicoRepository.crear(medico);
        } else
            throw new IntegridadException("El correo electrónico ya está en uso");
    }

    /**
     * Leer registro
     * @param id - ID del registro
     * @param direccion - ¿Desea retornar la dirección?
     * @return Registro
     * @throws NotFoundException 
     */
    @Override
    public UMedico leer(short id, String direccion) throws    NotFoundException {        
        Medico med = medicoRepository.leer("LeerMedico", id);
        UMedico medico = new ModelMapper().map(med, UMedico.class);            
        
        if (direccion.compareTo("n") == 0)
            medico.setDireccion(null);
        
        return medico;
    }

    /**
     * Leer registros paginando
     * @param inicio - Registro de inicio
     * @param cantidad - Cantidad a obtener
     * @return Lista de registros
     * @throws NoContentException
     */
    @Override
    public UPaginador leer(short inicio, short cantidad) throws  NoContentException {
        
        List<Medico> listaMedicos = medicoRepository.leer("LeerMedicos", (short) (inicio * cantidad), cantidad);
        
        if (listaMedicos.size() > 0) {
            
            UMedicoPaginador medicos = new UMedicoPaginador(
                inicio,
                (short) medicoRepository.cantidadTotal("QMedicosT"),
                cantidad
            ){};
            ModelMapper modelMapper = new ModelMapper();            
            UMedico med;                     
            
            for (Medico medico: listaMedicos) {
                med = modelMapper.map(medico, UMedico.class);
                med.setDireccion(null);
                //med.getDireccion().setMedico(null);
                medicos.getLista().add(med);
            }                                 
            
            System.out.println("Total páginas: " + medicos.getCantidadPaginas());
            
            return medicos;
            
        } else
            throw new NoContentException("");
        
    }
    
}

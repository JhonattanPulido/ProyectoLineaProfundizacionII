// Paquete
package com.udecsanitas.service;

// Librerías
import javax.ejb.EJB;
import java.util.List;
import java.util.ArrayList;
import org.json.JSONObject;
import javax.ejb.Stateless;
import org.modelmapper.ModelMapper;
import com.udecsanitas.entity.Medico;
import com.udecsanitas.utilitarie.UMedico;
import com.udecsanitas.utilitarie.UPaginador;
import com.udecsanitas.exception.NotFoundException;
import org.apache.commons.codec.digest.DigestUtils;
import com.udecsanitas.exception.IntegridadException;
import com.udecsanitas.exception.NoContentException;
import com.udecsanitas.exception.UnauthorizedException;
import com.udecsanitas.service.interfaz.IMedicoService;
import com.udecsanitas.repository.interfaz.IMedicoRepository;
import com.udecsanitas.repository.interfaz.ISeguridadRepository;

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
    
    @EJB
    private ISeguridadRepository seguridadRepository;
    
    /**
     * Crear médico
     * @param medico
     * @throws IntegridadException 
     */
    @Override
    public void crear(Medico medico) throws     IntegridadException {
        if (medicoRepository.cantidadEmail(medico.getCorreoElectronico()) == 0) {
            medico.setClave(DigestUtils.shaHex(medico.getClave())); // Encriptando clave - SHA - HEX            
            medico.getDireccion().setMedico(medico);
            medico.getRol().setMedico(medico);
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
    public String leer(short inicio, short cantidad) throws  NoContentException {
        
        List<Medico> listaMedicos = medicoRepository.leer("LeerMedicos", (short) (inicio * cantidad), cantidad);
        
        if (listaMedicos.size() > 0) {                        
            
            UMedico med;                     
            ModelMapper modelMapper = new ModelMapper(); 
            List<UMedico> lista = new ArrayList<>();
            
            for (Medico medico: listaMedicos) {
                med = modelMapper.map(medico, UMedico.class);
                med.getDireccion().setMedico(null);                
                lista.add(med);
            }    
            
            UPaginador medicos = new UPaginador(
                inicio,
                (short) medicoRepository.cantidadTotal("QMedicosT"),
                cantidad,
                lista                    
            ){};
            
            JSONObject json = new JSONObject();                        
        
            json.put("cantidadTotal", medicos.getCantidadTotal());
            json.put("cantidadPaginas", medicos.getCantidadPaginas());
            json.put("cantidadMostrar", medicos.getCantidadMostrar());
            json.put("paginaActual", medicos.getPaginaActual());
            json.put("lista", lista);             
            
            return json.toString();                       
            
        } else
            throw new NoContentException("");
        
    }
    
    /**
     * Actualizar médico
     * @param medico
     * @throws NotFoundException
     */
    @Override
    public void actualizar(Medico medico) throws    NotFoundException,
                                                    IntegridadException {
        
        if (medicoRepository.cantidadId("QMedicos", medico.getId()) == 1) {
            if (medicoRepository.validarEmail(medico.getCorreoElectronico(), medico.getId()) == 0) {
                medico.getDireccion().setMedico(medico);
                medicoRepository.actualizar(medico);
            } else
                throw new IntegridadException("El correo electrónico ya está en uso");
        } else
            throw new NotFoundException("No se encontró el médico");
        
    }

    /**
     * Eliminar médico
     * @param id - ID del médico
     * @throws NotFoundException 
     * @throws IntegridadException
     */
    @Override
    public void eliminar(short id) throws   NotFoundException,
                                            IntegridadException {
        
        if (medicoRepository.cantidadId("QMedicos", id) == 1)
            if (medicoRepository.cantidadConsultas(id) == 0)
                medicoRepository.eliminar(medicoRepository.leer("LeerMedico", id));
            else
                throw new IntegridadException("No se puede eliminar el médico");
        else
            throw new NotFoundException("No se encontró el médico");
        
    }    

    /**
     * Cerrar sesión
     * @param token
     * @throws UnauthorizedException 
     */
    @Override
    public void cerrarSesion(String token) throws UnauthorizedException {
        
        if (seguridadRepository.cantidadToken(token) == 1)
            seguridadRepository.eliminar(token);
        else
            throw new UnauthorizedException("No se encontró el token");
        
    }
    
}

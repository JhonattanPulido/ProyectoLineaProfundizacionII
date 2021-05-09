// Paquete
package com.hospitalejbmodule.service;

// Librerías
import com.hospitalejbmodule.entity.Consulta;
import com.hospitalejbmodule.entity.DetalleConsulta;
import com.hospitalejbmodule.excepciones.IntegridadException;
import com.hospitalejbmodule.excepciones.NotFoundException;
import com.hospitalejbmodule.repository.interfaz.IConsultaRepository;
import com.hospitalejbmodule.repository.interfaz.IMedicoRepository;
import com.hospitalejbmodule.service.interfaz.IConsultaService;
import com.hospitalejbmodule.utilitarie.UConsulta;
import com.hospitalejbmodule.utilitarie.UDetalleConsulta;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.core.NoContentException;
import org.modelmapper.ModelMapper;

/**
 * Capa de servicios de consulta
 * @author Jhonattan Pulido
 * @version 1.0.0
 * @since 05/05/2021
 */
@Stateless
public class ConsultaService implements IConsultaService {
    
    // Variables
    
    /**
     * Datos de consulta
     */
    @EJB
    private IConsultaRepository consultaRepository;
    
    /**
     * Datos de médico
     */
    @EJB
    private IMedicoRepository medicoRepository;

    // Métodos
    
    /**
     * Crear consulta
     * @param consulta
     * @throws IntegridadException 
     */
    @Override
    public void crear(Consulta consulta) throws IntegridadException, NotFoundException {
        
        consulta.setMedico(medicoRepository.leer("LeerMedico", consulta.getMedico().getId()));
        
        for (DetalleConsulta detalleConsulta: consulta.getListaDetallesConsultas())
            detalleConsulta.setConsulta(consulta);
        
        consultaRepository.crear(consulta);
    }

    /**
     * Leer consulta
     * @param id
     * @return
     * @throws NotFoundException 
     */
    @Override
    public UConsulta leer(short id) throws NotFoundException {
        Consulta consulta = consultaRepository.leer("LeerConsulta", id);
        return new ModelMapper().map(consulta, UConsulta.class);
    }

    /**
     * Leer todas las consultas
     * @return
     * @throws NoContentException 
     */
    @Override
    public List<UConsulta> leer() throws NoContentException {
        
        List<Consulta> listaConsultas = consultaRepository.leer();
        
        if (listaConsultas.size() > 0) {
            
            UConsulta uConsulta = new UConsulta();
            UDetalleConsulta uDetalleConsulta = new UDetalleConsulta();
            List<UConsulta> lista = new ArrayList<>();
            List<UDetalleConsulta> listaDC = new ArrayList<>();
            ModelMapper modelMapper = new ModelMapper();
            
            for (Consulta consulta: listaConsultas) {
                
                listaDC.clear();
                
                for (DetalleConsulta detalleConsulta: consulta.getListaDetallesConsultas()) {
                    uDetalleConsulta = modelMapper.map(detalleConsulta, UDetalleConsulta.class);
                    uDetalleConsulta.setConsulta(null);
                    listaDC.add(uDetalleConsulta);
                }                    
                
                uConsulta = modelMapper.map(consulta, UConsulta.class);
                uConsulta.setListaDetallesConsultas(listaDC);
                
                lista.add(uConsulta);
                
            }                
                        
            return lista;
            
        } else
            throw new NoContentException("");
    }

    /**
     * Actualizar consulta
     * @param consulta
     * @throws NotFoundException 
     */
    @Override
    public void actualizar(Consulta consulta) throws NotFoundException {        
        if (consultaRepository.cantidadId("QConsultas", consulta.getId()) == 1) {
        
            for (DetalleConsulta detalleConsulta: consulta.getListaDetallesConsultas())
                detalleConsulta.setConsulta(consulta);
            
            consultaRepository.actualizar(consulta);
            
        } else
            throw new NotFoundException("No se encontró la consulta");
                
    }
    
}

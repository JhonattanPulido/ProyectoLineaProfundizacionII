// Paquete
package com.udecsanitas.service;

// Librerías
import javax.ejb.EJB;
import java.util.List;
import javax.ejb.Stateless;
import java.util.ArrayList;
import org.json.JSONObject;
import javax.ejb.SessionContext;
import javax.annotation.Resource;
import org.modelmapper.ModelMapper;
import com.udecsanitas.entity.Medico;
import com.udecsanitas.entity.Examen;
import javax.ejb.TransactionAttribute;
import com.udecsanitas.entity.Consulta;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionAttributeType;
import com.udecsanitas.utilitarie.UExamen;
import javax.ejb.TransactionManagementType;
import com.udecsanitas.utilitarie.UConsulta;
import com.udecsanitas.utilitarie.UPaginador;
import com.udecsanitas.entity.ConsultaExamen;
import com.udecsanitas.entity.DetalleConsulta;
import com.udecsanitas.utilitarie.UDetalleConsulta;
import com.udecsanitas.exception.NotFoundException;
import com.udecsanitas.exception.NoContentException;
import com.udecsanitas.service.interfaz.IConsultaService;
import com.udecsanitas.repository.interfaz.IExamenRepository;
import com.udecsanitas.repository.interfaz.IMedicoRepository;
import com.udecsanitas.repository.interfaz.IConsultaRepository;
import com.udecsanitas.repository.interfaz.IConsultaExamenRepository;

/**
 * Capa de servicios de consulta
 * @author Jhonattan Pulido
 * @version 1.0.0
 * @since 23/05/2021
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class ConsultaService implements IConsultaService {

    // Variables
    
    @EJB
    private IConsultaRepository consultaRepository;
    
    @EJB
    private IMedicoRepository medicoRepository;
    
    @EJB
    private IExamenRepository examenRepository;
    
    @EJB
    private IConsultaExamenRepository consultaExamenRepository;
    
    @Resource
    private SessionContext userTransaction;

    // Métodos

    /**
     * Crear consultas
     * @param listaConsultas
     * @param medicoId 
     * @throws NoContentException
     * @throws NotFoundException     
     */
    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void crear(List<Consulta> listaConsultas, short medicoId) throws NoContentException,
                                                                            NotFoundException,
                                                                            Exception {                
        
        try {                       
            
            if (listaConsultas.size() > 0)
                if (medicoRepository.cantidadId("QMedicos", medicoId) == 1) {

                    Medico medico = medicoRepository.leer("LeerMedico", medicoId);
                    Consulta consultaAux;
                    Examen examenAux = new Examen();

                    for (Consulta consulta: listaConsultas) {

                        consulta.setMedico(medico);

                        for (DetalleConsulta detalleConsulta: consulta.getListaDetallesConsultas())
                            detalleConsulta.setConsulta(consulta);

                        // Verificando que los exámenes existan
                        for (Short examenId: consulta.getListaExamenes())
                            if (examenRepository.cantidadId("QExamenes", examenId) == 0)
                                throw new NotFoundException("No se encontró el examen");                                        

                        consultaAux = consultaRepository.crear(consulta);                    

                        for (Short examenId: consulta.getListaExamenes()) {
                            examenAux.setId(examenId);
                            consultaExamenRepository.crear(new ConsultaExamen(consultaAux, examenAux));
                        }                 

                    }                                

                } else
                    throw new NotFoundException("No se encontró el médico asociado");
            else
                throw new NoContentException("");                        
            
        } catch (NoContentException nce) {
            userTransaction.setRollbackOnly();         
            throw new NoContentException(nce.getMessage());
        } catch(NotFoundException nfe) {
            userTransaction.setRollbackOnly();
            throw new NotFoundException(nfe.getMessage());
        } catch (Exception e) {            
            userTransaction.setRollbackOnly(); 
            throw new Exception("Ha ocurrido un error inesperado, inténtelo nuevamente");
        }          
    }
    
    /**
     * Leer consulta
     * @param id
     * @return Consulta
     * @throws NotFoundException 
     */
    @Override
    public UConsulta leer(short id) throws   NotFoundException {
        
        Consulta cons = consultaRepository.leer(id);
        ModelMapper modelMapper = new ModelMapper();        
        
        UDetalleConsulta detalleConsultaAux;
        List<Short> listaExamenes = consultaExamenRepository.leer(id);
        List<UExamen> examenes = new ArrayList<>();        
        List<UDetalleConsulta> detallesConsultas = new ArrayList<>();
        
        for (Short examenId: listaExamenes)
            examenes.add(modelMapper.map(examenRepository.leer("LeerExamen", examenId), UExamen.class));
        
        for (DetalleConsulta detalleConsulta: cons.getListaDetallesConsultas()) {
            detalleConsultaAux = modelMapper.map(detalleConsulta, UDetalleConsulta.class);
            detalleConsultaAux.setConsulta(null);
            detallesConsultas.add(detalleConsultaAux);
        }
        
        UConsulta consulta = modelMapper.map(cons, UConsulta.class);
        consulta.setListaExamenes(examenes);
        consulta.setListaDetallesConsultas(detallesConsultas);
        consulta.setMedico(null);
        
        return consulta;
    }

    /**
     * Leer consultas paginadas
     * @param inicio
     * @param cantidad
     * @param medicoId
     * @return Lista de registros
     * @throws NotFoundException
     * @throws NoContentException 
     */
    @Override
    public String leer(short inicio, short cantidad, short medicoId) throws NotFoundException,
                                                                            NoContentException {
        
        if (medicoRepository.cantidadId("QMedicos", medicoId) == 1) {
            
            List<Consulta> listaConsultas = consultaRepository.leer((short) (inicio * cantidad), cantidad, medicoId);

            if (listaConsultas.size() > 0) {

                UExamen examenAux;
                UConsulta consultaAux;
                UDetalleConsulta detalleConsultaAux;
                ModelMapper modelMapper = new ModelMapper();
                List<UExamen> examenes;
                List<UConsulta> consultas = new ArrayList<>();
                List<UDetalleConsulta> detallesConsultas; 
                //List<ConsultaExamen> listaConsultasExamenes = new ArrayList<>();
                List<Short> listaConsultasExamenes;

                for (Consulta consulta: listaConsultas) {                    

                    listaConsultasExamenes = consultaExamenRepository.leer(consulta.getId());
                    examenes = new ArrayList<>();
                    detallesConsultas = new ArrayList<>();
                    
                    /*for (ConsultaExamen consultaExamen: listaConsultasExamenes) {
                        examenAux = modelMapper.map(examenRepository.leer("LeerExamen", consultaExamen.getExamen().getId()), UExamen.class);
                        examenes.add(examenAux);
                    }*/
                    
                    for (Short consultaExamen: listaConsultasExamenes) {
                        examenAux = modelMapper.map(examenRepository.leer("LeerExamen", consultaExamen), UExamen.class);
                        examenes.add(examenAux);
                    }
                    
                    for (DetalleConsulta detalleConsulta: consulta.getListaDetallesConsultas()) {
                        detalleConsultaAux = modelMapper.map(detalleConsulta, UDetalleConsulta.class);
                        detalleConsultaAux.setConsulta(null);
                        detallesConsultas.add(detalleConsultaAux);
                    }

                    consultaAux = modelMapper.map(consulta, UConsulta.class);
                    consultaAux.setListaExamenes(examenes);
                    consultaAux.setListaDetallesConsultas(detallesConsultas);
                    consultaAux.setMedico(null);
                    consultas.add(consultaAux);

                }

                UPaginador paginador = new UPaginador(
                    inicio,
                    (short) consultaRepository.cantidadMedicoId(medicoId),
                    cantidad,
                    consultas                    
                ){};

                JSONObject json = new JSONObject();                        

                json.put("cantidadTotal", paginador.getCantidadTotal());
                json.put("cantidadPaginas", paginador.getCantidadPaginas());
                json.put("cantidadMostrar", paginador.getCantidadMostrar());
                json.put("paginaActual", paginador.getPaginaActual());
                json.put("lista", consultas);             

                return json.toString();  

            } else
                throw new NoContentException("");
        } else
            throw new NotFoundException("No se encontró el médico");
        
    }
    
    /**
     * Actualizar consulta
     * @param consulta
     * @throws NotFoundException 
     */
    @Override
    public void actualizar(Consulta consulta) throws NotFoundException {
        
        if (consultaRepository.cantidadId(consulta.getId()) == 1) {
            
            for (DetalleConsulta detalleConsulta: consulta.getListaDetallesConsultas())
                detalleConsulta.setConsulta(consulta);
            
            consultaRepository.actualizar(consulta);
                        
            List<Short> examenes = consultaExamenRepository.leer(consulta.getId());
            eliminarExamenes(consulta.getListaExamenes(), examenes);
            crearExamenes(consulta.getListaExamenes(), examenes, consulta);                       
            
        } else
            throw new NotFoundException("No se encontró la consulta");
        
    }
    
    /**
     * Eliminar exámenes no deseados
     * @param listaExamenes - Nuevos exámenes
     * @param examenes - Id de los exámenes asociados a consulta almacenados en base de datos
     * @throws NotFoundException
     */
    private void eliminarExamenes(List<Short> listaExamenes, List<Short> examenes) throws   NotFoundException {
        
        boolean eliminarExamen;
        
        for (short exId: examenes) {
            
            eliminarExamen = true;
            for (short examenId: listaExamenes)
                if (exId == examenId) {
                    eliminarExamen = false;
                    break;
                }
            
            if (eliminarExamen == true) consultaExamenRepository.eliminarExamen(exId);
            
        }
    }
    
    /**
     * Añadir nuevos exámenes
     * @param listaExamenes - Nuevos exámenes
     * @param examenes - Id de los exámenes asociados a consulta almacenados en base de datos
     * @param consultaId - ID de la consulta
     */
    private void crearExamenes(List<Short> listaExamenes, List<Short> examenes, Consulta consulta) {
        
        boolean crearExamen;
        
        for (short examenId: listaExamenes) {
            
            crearExamen = true;            
            for (short exId: examenes)
                if (examenId == exId) {
                    crearExamen = false;
                    break;
                }
            
            if (crearExamen == true) {
                Examen examen = new Examen();
                examen.setId(examenId);                
                consultaExamenRepository.crear(new ConsultaExamen(consulta, examen));
            }
            
        }
        
    }

    /**
     * Eliminar consulta
     * @param consultaId
     * @throws NotFoundException 
     */
    @Override
    public void eliminar(short consultaId) throws   NotFoundException {
        
        if (consultaRepository.cantidadId(consultaId) == 1)
            consultaRepository.eliminar(consultaRepository.leer(consultaId));
        else
            throw new NotFoundException("No se encontró la consulta");
        
    }        
    
}

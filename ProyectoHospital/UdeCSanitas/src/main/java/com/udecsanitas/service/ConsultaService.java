// Paquete
package com.udecsanitas.service;

// Librerías
import javax.ejb.EJB;
import java.util.List;
import javax.ejb.Stateless;
import java.util.ArrayList;
import org.json.JSONObject;
import org.modelmapper.ModelMapper;
import com.udecsanitas.entity.Medico;
import com.udecsanitas.entity.Examen;
import com.udecsanitas.entity.Consulta;
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
import com.udecsanitas.utilitarie.UExamen;

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
    
    @EJB
    private IConsultaExamenRepository consultaExamenRepository;

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
    
}

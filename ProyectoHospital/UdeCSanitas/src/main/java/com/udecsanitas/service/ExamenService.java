// Paquete
package com.udecsanitas.service;

// Librerías
import javax.ejb.EJB;
import java.util.List;
import java.util.ArrayList;
import org.json.JSONObject;
import javax.ejb.Stateless;
import org.modelmapper.ModelMapper;
import com.udecsanitas.entity.Examen;
import com.udecsanitas.utilitarie.UExamen;
import com.udecsanitas.utilitarie.UPaginador;
import com.udecsanitas.exception.NotFoundException;
import com.udecsanitas.exception.NoContentException;
import com.udecsanitas.exception.IntegridadException;
import com.udecsanitas.service.interfaz.IExamenService;
import com.udecsanitas.repository.interfaz.IExamenRepository;

/**
 * Capa de servicios de examen
 * @author Jhonattan Pulido
 * @version 1.0.0
 * @since 22/05/2021
 */
@Stateless
public class ExamenService implements IExamenService {

    // Variables
    
    @EJB
    private IExamenRepository examenRepository;
    
    // Métodos
    
    /**
     * Crear examen
     * @param examen
     * @throws IntegridadException 
     */
    @Override
    public void crear(Examen examen) throws IntegridadException {
        if (examenRepository.cantidadNombre(examen.getNombre()) == 0)
            examenRepository.crear(examen);
        else
            throw new IntegridadException("Ya existé un examen con el nombre: " + examen.getNombre());
    }

    /**
     * Leer examen filtrado por id
     * @param id
     * @return Examen
     */
    @Override
    public UExamen leer(short id) throws    NotFoundException {
        
        Examen exam = examenRepository.leer("LeerExamen", id);
        UExamen examen = new ModelMapper().map(exam, UExamen.class);
        
        // Todos los objetos anidados ponerlos null
        
        return examen;
        
    }
    
    /**
     * Leer todos los exámenes
     * @return Lista de exámenes
     * @throws NoContentException 
     */
    @Override
    public List<UExamen> leer() throws  NoContentException {
        
        List<Examen> listaExamenes = examenRepository.leer();
        
        if (listaExamenes.size() > 0) {
        
            ModelMapper modelMapper = new ModelMapper();
            List<UExamen> examenes = new ArrayList<>();
            
            for (Examen examen: listaExamenes)
                examenes.add(modelMapper.map(examen, UExamen.class)); 
            
            return examenes;
            
        } else
            throw new NoContentException("");
        
    }
    
    /**
     * Paginar exámenes
     * @param inicio - Registro de inicio
     * @param cantidad - Cantidad de registros
     * @return Lista de exámenes
     * @throws NoContentException 
     */
    @Override
    public String leer(short inicio, short cantidad) throws NoContentException {
        
        List<Examen> listaExamenes = examenRepository.leer("LeerExamenes", (short) (inicio * cantidad), cantidad);
        
        if (listaExamenes.size() > 0) {
            
            ModelMapper modelMapper = new ModelMapper();
            List<UExamen> examenes = new ArrayList<>();
            
            for (Examen examen: listaExamenes)
                examenes.add(modelMapper.map(examen, UExamen.class));            
            
            UPaginador medicos = new UPaginador(
                inicio,
                (short) examenRepository.cantidadTotal("QExamenesT"),
                cantidad,
                examenes                    
            ){};
            
            JSONObject json = new JSONObject();                        
        
            json.put("cantidadTotal", medicos.getCantidadTotal());
            json.put("cantidadPaginas", medicos.getCantidadPaginas());
            json.put("cantidadMostrar", medicos.getCantidadMostrar());
            json.put("paginaActual", medicos.getPaginaActual());
            json.put("lista", examenes);
            
            return json.toString();
            
        } else
            throw new NoContentException("");
        
    }
    
    /**
     * Actualizar examen
     * @param examen
     * @throws NotFoundException
     * @throws IntegridadException 
     */
    @Override
    public void actualizar(Examen examen) throws    NotFoundException, 
                                                    IntegridadException {
        
        if (examenRepository.cantidadId("QExamenes", examen.getId()) == 1) {
            if (examenRepository.validarNombre(examen.getNombre(), examen.getId()) == 0)
                examenRepository.actualizar(examen);
            else
                throw new IntegridadException("Ya existe un exámen con el nombre: " + examen.getNombre());
        } else
            throw new NotFoundException("No se econtró el exámen");
    }

    /**
     * Eliminar examen
     * @param id
     * @throws NotFoundException 
     */
    @Override
    public void eliminar(short id) throws   NotFoundException {
        
        if (examenRepository.cantidadId("QExamenes", id) == 1)
            examenRepository.eliminar(examenRepository.leer("LeerExamen", id));
        else
            throw new NotFoundException("No se encontró el examen");
    }            
            
}

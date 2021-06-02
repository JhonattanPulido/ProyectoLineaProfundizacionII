// Paquete
package com.udecsanitas.utilitarie;

// Librerías
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.io.Serializable;

/**
 * Utilitario de consulta
 * @author Jhonattan Pulido
 * @version 1.0.0
 * @since 23/05/2021
 */
public class UConsulta implements Serializable {
    
    // Variables
    
    private Short id;
    
    private Date fecha;
    
    private UMedico medico;
    
    private List<UDetalleConsulta> listaDetallesConsultas;       
    
    private List<UExamen> listaExamenes;

    /**
     * Constructor
     */
    public UConsulta() {
        listaExamenes = new ArrayList<>();
        listaDetallesConsultas = new ArrayList<>();
    }        

    // Métodos get & set
    
    public Short getId() {
        return id;
    }

    public void setId(Short id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public UMedico getMedico() {
        return medico;
    }

    public void setMedico(UMedico medico) {
        this.medico = medico;
    }

    public List<UDetalleConsulta> getListaDetallesConsultas() {
        return listaDetallesConsultas;
    }

    public void setListaDetallesConsultas(List<UDetalleConsulta> listaDetallesConsultas) {
        this.listaDetallesConsultas = listaDetallesConsultas;
    }   

    public List<UExamen> getListaExamenes() {
        return listaExamenes;
    }

    public void setListaExamenes(List<UExamen> listaExamenes) {
        this.listaExamenes = listaExamenes;
    }
        
}

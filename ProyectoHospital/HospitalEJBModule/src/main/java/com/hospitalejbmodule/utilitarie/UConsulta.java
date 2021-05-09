// Paquete
package com.hospitalejbmodule.utilitarie;

// Librerías
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Clase consulta
 * @author Jhonattan Pulido
 * @version 1.0.0
 * @since 08/05/2021
 */
public class UConsulta implements Serializable {
    
    // Variables
    
    /**
     * ID
     */
    private Short id;
    
    /**
     * Fecha
     */
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    
    /**
     * Medico asociado
     */
    private UMedico medico;
    
    /**
     * Detalles consultas asociadas
     */
    private List<UDetalleConsulta> listaDetallesConsultas;

    /**
     * Constructor
     */
    public UConsulta() {
        
    }

    // Métodos Set & Get
    
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
    
}

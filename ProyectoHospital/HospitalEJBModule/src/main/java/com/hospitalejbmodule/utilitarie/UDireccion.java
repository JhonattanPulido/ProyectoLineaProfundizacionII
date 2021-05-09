// Paquete
package com.hospitalejbmodule.utilitarie;

// Librerías
import java.io.Serializable;

/**
 * Clase dirección
 * @author Jhonattan Pulido
 * @version 1.0.0
 * @since 06/05/2021
 */
public class UDireccion implements Serializable {
    
    // Variables
    
    /**
     * ID
     */
    private Short id;
    
    /**
     * Código postal
     */
    private String codigoPostal;
    
    /**
     * Dirección detallada
     */
    private String direccionDetallada;
    
    /**
     * Médico asociado
     */
    private UMedico medico;

    /**
     * Constructor
     */
    public UDireccion() {
        
    }

    // Métodos Set & Get
    
    public Short getId() {
        return id;
    }

    public void setId(Short id) {
        this.id = id;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public String getDireccionDetallada() {
        return direccionDetallada;
    }

    public void setDireccionDetallada(String direccionDetallada) {
        this.direccionDetallada = direccionDetallada;
    }

    public UMedico getMedico() {
        return medico;
    }

    public void setMedico(UMedico medico) {
        this.medico = medico;
    }        
    
}

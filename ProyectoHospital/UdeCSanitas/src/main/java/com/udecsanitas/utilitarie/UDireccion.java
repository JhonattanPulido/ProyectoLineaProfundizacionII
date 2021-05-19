// Paquete
package com.udecsanitas.utilitarie;

// Librerías
import java.io.Serializable;
import javax.json.bind.annotation.JsonbTransient;

/**
 * Utilitario de dirección
 * @author Jhonattan Pulido
 * @version 1.0.0
 * @since 18/05/2021
 */
public class UDireccion implements Serializable {

    // Variables
    
    private Short id;
    
    private String codigoPostal;
    
    private String direccionDetallada;
        
    private UMedico medico;

    /**
     * Constructor
     */
    public UDireccion() {
        
    }

    // Métodos set & get
    
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

    @JsonbTransient
    public UMedico getMedico() {
        return medico;
    }

    public void setMedico(UMedico medico) {
        this.medico = medico;
    }        
    
}

// Paquete
package com.udecsanitas.utilitarie;

// Librerías
import java.io.Serializable;
import javax.json.bind.annotation.JsonbTransient;

/**
 * Utilitario rol
 * @author Jhonattan Pulido
 * @version 1.0.0
 * @since 01/06/2021
 */
public class URol implements Serializable {
    
    // Variables
    
    private Short id;
    
    private String nombre;
    
    private UMedico medico;

    /**
     * Constructor
     */
    public URol() {
        
    }   
    
    public Short getId() {
        return id;
    }

    public void setId(Short id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @JsonbTransient
    public UMedico getMedico() {
        return medico;
    }

    public void setMedico(UMedico medico) {
        this.medico = medico;
    }        
    
}

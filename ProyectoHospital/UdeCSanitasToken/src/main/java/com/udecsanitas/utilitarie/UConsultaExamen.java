// Paquete
package com.udecsanitas.utilitarie;

// Librerías
import java.io.Serializable;

/**
 * Utilitario de consulta examen
 * @author Jhonattan Pulido
 * @version 1.0.0
 * @since 23/05/2021
 */
public class UConsultaExamen implements Serializable {
    
    // Variables
    
    private Short consultaId;
    
    private Short examenId;

    /**
     * Constructor
     */
    public UConsultaExamen() {
        
    }
    
    // Métodos get & set

    public Short getConsultaId() {
        return consultaId;
    }

    public void setConsultaId(Short consultaId) {
        this.consultaId = consultaId;
    }

    public Short getExamenId() {
        return examenId;
    }

    public void setExamenId(Short examenId) {
        this.examenId = examenId;
    }        
    
}

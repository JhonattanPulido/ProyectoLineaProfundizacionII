// Paquete
package com.udecsanitas.entity;

// Librerías
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * Entidad consulta examen PK
 * @author Jhonattan Pulido
 * @version 1.0.0
 * @since 23/05/2021
 */
@Embeddable
public class ConsultaExamenPK implements Serializable {
    
    // Variables
    
    @Column(name = "consulta_id")
    private Short consultaId;
    
    @Column(name = "examen_id")
    private Short examenId;

    /**
     * Constructor
     */
    public ConsultaExamenPK() {
        
    }

    // Métodos set & get
    
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

// Paquete
package com.udecsanitas.entity;

// Librerías
import java.io.Serializable;
import java.util.Objects;
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

    /**
     * Constructor con parámetros
     * @param consultaId
     * @param examenId 
     */
    public ConsultaExamenPK(Short consultaId, Short examenId) {
        this.consultaId = consultaId;
        this.examenId = examenId;
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
    
    // Equals & hash code
    
     @Override
    public int hashCode() {
        int hash = 5;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ConsultaExamenPK other = (ConsultaExamenPK) obj;
        if (!Objects.equals(this.consultaId, other.consultaId)) {
            return false;
        }
        if (!Objects.equals(this.examenId, other.examenId)) {
            return false;
        }
        return true;
    }
    
}

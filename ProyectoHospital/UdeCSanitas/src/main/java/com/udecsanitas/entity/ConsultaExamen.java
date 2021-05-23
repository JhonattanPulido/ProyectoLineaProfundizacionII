// Paquete
package com.udecsanitas.entity;

// Librerías
import java.io.Serializable;
import javax.persistence.Table;
import javax.persistence.Entity;
import javax.persistence.MapsId;
import javax.persistence.ManyToOne;
import javax.persistence.EmbeddedId;

/**
 * Entidad consulta examen
 * @author Jhonattan Pulido
 * @version 1.0.0
 * @since 23/05/2021
 */
@Entity
@Table(name = "consultas_examenes", schema = "public")
public class ConsultaExamen implements Serializable {
    
    // Variables        
    
    @EmbeddedId
    private ConsultaExamenPK consultaExamenPK;
    
    @ManyToOne
    @MapsId("consultaId")
    private Consulta consulta;
    
    @ManyToOne
    @MapsId("examenId")
    private Examen examen;

    /**
     * Constructor
     */
    public ConsultaExamen() {
        
    }
    
    // Métodos get & set

    public ConsultaExamenPK getConsultaExamenPK() {
        return consultaExamenPK;
    }

    public void setConsultaExamenPK(ConsultaExamenPK consultaExamenPK) {
        this.consultaExamenPK = consultaExamenPK;
    }

    public Consulta getConsulta() {
        return consulta;
    }

    public void setConsulta(Consulta consulta) {
        this.consulta = consulta;
    }

    public Examen getExamen() {
        return examen;
    }

    public void setExamen(Examen examen) {
        this.examen = examen;
    }        
    
}

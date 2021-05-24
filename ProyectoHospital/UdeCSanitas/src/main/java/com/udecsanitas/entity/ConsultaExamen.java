// Paquete
package com.udecsanitas.entity;

// Librerías
import java.io.Serializable;
import javax.persistence.Table;
import javax.persistence.Entity;
import javax.persistence.MapsId;
import javax.persistence.ManyToOne;
import javax.persistence.EmbeddedId;
import javax.persistence.NamedQuery;
import javax.persistence.NamedQueries;

/**
 * Entidad consulta examen
 * @author Jhonattan Pulido
 * @version 1.0.0
 * @since 23/05/2021
 */
@Entity
@Table(name = "consultas_examenes", schema = "public")
@NamedQueries({
    @NamedQuery(name = "QConsultaExamen", query = "SELECT COUNT(ce) FROM ConsultaExamen ce WHERE ce.consulta.id = :consulta_id AND ce.examen.id = :examen_id"), // Leer consultas examenes filtradas por id de consulta        
    @NamedQuery(name = "LeerXConsulta", query = "SELECT ce.examen.id FROM ConsultaExamen ce WHERE ce.consulta.id = :consulta_id ORDER BY ce.consulta.id ASC")
})  
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

    /**
     * Constructor con parámetros
     * @param consulta
     * @param examen 
     */
    public ConsultaExamen(Consulta consulta, Examen examen) {
        this.consulta = consulta;
        this.examen = examen;
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

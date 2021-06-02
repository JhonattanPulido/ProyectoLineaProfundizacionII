// Paquete
package com.udecsanitas.entity;

// Librerías
import java.io.Serializable;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.validation.constraints.Size;
import javax.validation.constraints.NotNull;
import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 * Entidad detalle consulta
 * @author Jhonattan Pulido
 * @version 1.0.0
 * @since 23/05/2021
 */
@Entity
@Table(name = "detalles_consultas", schema = "public")
@NamedQueries({
    @NamedQuery(name = "EliminarDetalleConsulta", query = "DELETE FROM DetalleConsulta dc WHERE dc.consulta.id = :consulta_id")
})
public class DetalleConsulta implements Serializable {
    
    // Variables
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Short id;
    
    @Column(name = "diagnostico")
    @NotNull(message = "El diagnóstico es requerido")
    @Size(min = 2, max = 120, message = "El diagnóstico debe tener mínimo 2 y máximo 120 caracteres")
    private String diagnostico;
    
    @Column(name = "tratamiento")
    @NotNull(message = "El tratamiento es requerido")
    @Size(min = 2, max = 120, message = "El tratamiento debe tener mínimo 2 y máximo 120 caracteres")
    private String tratamiento;
    
    @ManyToOne
    @JoinColumn(name = "consulta_id")
    private Consulta consulta;

    /**
     * Constructor
     */
    public DetalleConsulta() {
        
    }

    // Métodos get & set
    
    public Short getId() {
        return id;
    }

    public void setId(Short id) {
        this.id = id;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public String getTratamiento() {
        return tratamiento;
    }

    public void setTratamiento(String tratamiento) {
        this.tratamiento = tratamiento;
    }

    @JsonbTransient
    public Consulta getConsulta() {
        return consulta;
    }

    public void setConsulta(Consulta consulta) {
        this.consulta = consulta;
    }
    
}

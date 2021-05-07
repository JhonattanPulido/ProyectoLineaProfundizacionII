// Paquete
package com.hospitalejbmodule.entity;

// Librerías
import java.io.Serializable;
import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Entidad detalle consulta
 * @author Jhonattan Pulido
 * @version 1.0.0
 * @since 05/05/2021
 */
@Entity
@Table(name = "detalles_consultas", schema = "public")
public class DetalleConsulta implements Serializable {
    
    // Variables
    
    /**
     * ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Short id;
    
    /**
     * Diagnóstico
     */
    @Column(name = "diagnostico")
    @NotNull(message = "El diagnóstico es requerido")
    @Size(min = 2, max = 120, message = "El diagnóstico debe tener mínimo 2 y máximo 120 caracteres")
    private String diagnostico;
    
    /**
     * Tratamiento
     */
    @Column(name = "tratamiento")
    @NotNull(message = "El tratamiento es requerido")
    @Size(min = 2, max = 120, message = "El tratamiento debe tener mínimo 2 y máximo 120 caracteres")
    private String tratamiento;
    
    /**
     * Consulta asociada
     */
    @ManyToOne  
    @JsonbTransient
    @JoinColumn(name = "consulta_id", referencedColumnName = "id")
    private Consulta consulta;

    /**
     * Constructor
     */
    public DetalleConsulta() {
        
    }        
    
    // Métodos Set & Get

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

    public Consulta getConsulta() {
        return consulta;
    }

    public void setConsulta(Consulta consulta) {
        this.consulta = consulta;
    }        
    
}

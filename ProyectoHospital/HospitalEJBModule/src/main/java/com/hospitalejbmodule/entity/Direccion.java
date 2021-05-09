// Paquete
package com.hospitalejbmodule.entity;

// Librerías
import com.fasterxml.jackson.annotation.JsonBackReference;
import java.io.Serializable;
import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Entidad dirección
 * @author Jhonattan Pulido
 * @version 1.0.0
 * @since 05/05/2021
 */
@Entity
@Table(name = "direcciones", schema = "public")
public class Direccion implements Serializable {
        
    // Variables
    
    /**
     * ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Short id;
    
    /**
     * Código postal
     */
    @Column(name = "codigo_postal")
    @NotNull(message = "El código postal es requerido")
    @Size(min = 6, max = 6, message = "El código postal debe tener 6 dígitos")
    private String codigoPostal;
    
    /**
     * Dirección detallada
     */
    @Column(name = "direccion_detallada")
    @NotNull(message = "La dirección detallada es requerida")
    @Size(min = 2, max = 250, message = "La dirección detallada debe tener mínimo 2 y máximo 250 caracteres")
    private String direccionDetallada;
    
    /**
     * Médico asociado
     */
    @OneToOne      
    @JoinColumn(name = "medico_id")       
    private Medico medico;

    /**
     * Constructor
     */
    public Direccion() {
        
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

    @JsonbTransient
    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }
    
}

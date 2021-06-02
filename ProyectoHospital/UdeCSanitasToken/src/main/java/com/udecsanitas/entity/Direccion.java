// Paquete
package com.udecsanitas.entity;

// Librerías
import java.io.Serializable;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.NamedQueries;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.validation.constraints.Size;
import javax.validation.constraints.NotNull;
import javax.json.bind.annotation.JsonbTransient;

/**
 * Entidad dirección
 * @author Jhonattan Pulido
 * @version 1.0.0
 * @since 18/05/2021
 */
@Entity
@Table(name = "direcciones", schema = "public")
@NamedQueries({
    @NamedQuery(name = "EliminarDireccion", query = "DELETE FROM Direccion dir WHERE dir.medico.id = :medico_id")
})
public class Direccion implements Serializable {
    
    // Variables
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Short id;
    
    @Column(name = "codigo_postal")
    @NotNull(message = "El código postal es requerido")
    @Size(min = 6, max = 6, message = "El código postal debe tener 6 dígitos")
    private String codigoPostal;
    
    @Column(name = "direccion_detallada")
    @NotNull(message = "La dirección detallada es requerida")
    @Size(min = 2, max = 80, message = "La dirección detallada debe tener mínimo 2 y máximo 250 caracteres")
    private String direccionDetallada;
    
    @OneToOne
    @JoinColumn(name = "medico_id")       
    private Medico medico;

    /**
     * Constructor
     */
    public Direccion() {
        
    }
    
    // Métodos get & set

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

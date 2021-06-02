// Paquete
package com.udecsanitas.entity;

// Librerías
import javax.persistence.Id;
import java.io.Serializable;
import javax.persistence.Table;
import javax.persistence.Entity;
import javax.persistence.Column;
import javax.persistence.OneToOne;
import javax.persistence.FetchType;
import javax.persistence.CascadeType;
import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 * Entidad rol
 * @author Jhonattan Pulido
 * @version 1.0.0
 * @since 01/06/2021
 */
@Entity
@Table(name = "roles", schema = "public")
@NamedQueries({
    @NamedQuery(name = "LeerRol", query = "SELECT r.nombre FROM Rol r WHERE r.id = :id")
})
public class Rol implements Serializable {
    
    // Variables
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Short id;
    
    @Column(name = "nombre")
    private String nombre;
        
    @OneToOne(mappedBy = "rol", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Medico medico;

    /**
     * Constructor
     */
    public Rol() {
        
    }

    // Métodos get & set
    
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
    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }
    
}

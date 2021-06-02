// Paquete
package com.udecsanitas.entity;

// Libreráis
import javax.persistence.Id;
import java.io.Serializable;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.validation.constraints.NotNull;

/**
 * Entidad seguridad
 * @author Jhonattan Pulidio
 * @version 1.0.0
 * @since 01/06/2021
 */
@Entity
@Table(name = "seguridad", schema = "public")
@NamedQueries({
    @NamedQuery(name = "LeerToken", query = "SELECT s FROM Seguridad s WHERE s.token = :token"),
    @NamedQuery(name = "QSeguridad", query = "SELECT COUNT(s) FROM Seguridad s WHERE s.token = :token"),
    @NamedQuery(name = "QSeguridadToken", query = "SELECT COUNT(s) FROM Seguridad s WHERE s.token = :token AND s.correoElectronico = :correo_electronico"),
    @NamedQuery(name = "QSeguridadEmail", query = "SELECT COUNT(s) FROM Seguridad s WHERE s.correoElectronico = :correo_electronico"),
    @NamedQuery(name = "EliminarSeguridad", query = "DELETE FROM Seguridad s WHERE s.token = :token")
})
public class Seguridad implements Serializable {
    
    // Variables
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Short id;    
    
    @Column(name = "token")
    @NotNull(message = "El token es requerido")
    private String token;
    
    @Column(name = "correo_electronico")
    @NotNull(message = "El correo electrónico del usuario es requerido")
    private String correoElectronico;

    /**
     * Constructor
     */
    public Seguridad() {
        
    }   
    
    // Métodos get & set
    
    public Short getId() {
        return id;
    }

    public void setId(Short id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }        
    
}

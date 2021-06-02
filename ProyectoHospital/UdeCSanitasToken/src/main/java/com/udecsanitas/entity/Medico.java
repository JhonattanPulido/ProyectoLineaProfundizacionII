// Paquetes
package com.udecsanitas.entity;

// Librerías
import java.util.List;
import java.io.Serializable;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Entity;
import javax.persistence.Column;
import javax.persistence.OneToOne;
import javax.persistence.OneToMany;
import javax.persistence.FetchType;
import javax.persistence.NamedQuery;
import javax.persistence.CascadeType;
import javax.persistence.NamedQueries;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.validation.constraints.Size;
import javax.validation.constraints.NotNull;

/**
 * Entidad médico
 * @author Jhonattan Pulido
 * @version 1.0.0
 * @since 18/05/2021
 */
@Entity
@Table(name = "medicos", schema = "public")
@NamedQueries({
    @NamedQuery(name = "LoginMedico", query = "SELECT m FROM Medico m WHERE m.correoElectronico = :correo_electronico AND m.clave = :clave"),
    @NamedQuery(name = "QMedicos", query = "SELECT COUNT(m) FROM Medico m WHERE m.id = :id"), // Cantidad de médicos con ID
    @NamedQuery(name = "QMedicosT", query = "SELECT COUNT(m) FROM Medico m"), // Cantidad total de médicos
    @NamedQuery(name = "QMedicosEmail", query = "SELECT COUNT(m) FROM Medico m WHERE m.correoElectronico = :correo_electronico"), // Cantidad de médicos con correo electónico
    @NamedQuery(name = "QMedicosConsulta", query = "SELECT COUNT(c) FROM Consulta c WHERE c.medico.id = :id"),
    @NamedQuery(name = "LeerMedico", query = "SELECT m FROM Medico m WHERE m.id = :id"), // Leer médico por ID
    @NamedQuery(name = "LeerMedicos", query = "SELECT m FROM Medico m ORDER BY m.id ASC"), // Leer todos los médicos
    @NamedQuery(name = "ValidarEmail", query = "SELECT COUNT(m) FROM Medico m WHERE m.correoElectronico = :correo_electronico AND m.id <> :id"),
    @NamedQuery(name = "EliminarMedico", query = "DELETE FROM Medico m WHERE m.id = :id") // Eliminar médico
})
public class Medico implements Serializable {
    
    // Variables
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Short id;
    
    @Column(name = "nombre")
    @NotNull(message = "El nombre es requerido")
    @Size(min = 2, max = 26, message = "El nombre debe tener mínimo 2 y máximo 26 letras")
    private String nombre;
    
    @Column(name = "apellido")
    @NotNull(message = "El apellido es requerido")
    @Size(min = 2, max = 26, message = "El apellido debe tener mínimo 2 y máximo 26 letras")
    private String apellido;
    
    @Column(name = "correo_electronico")
    @NotNull(message = "El correo electrónico es requerido")
    private String correoElectronico;
    
    @Column(name = "clave")
    @NotNull(message = "La clave es requerida")
    private String clave;
    
    @OneToOne
    @JoinColumn(name = "rol_id")
    private Rol rol;    
    
    @OneToOne(mappedBy = "medico", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)        
    private Direccion direccion;
    
    @OneToMany(mappedBy = "medico", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Consulta> listaConsultas;

    /**
     * Constructor
     */
    public Medico() {
        
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

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }        

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public List<Consulta> getListaConsultas() {
        return listaConsultas;
    }

    public void setListaConsultas(List<Consulta> listaConsultas) {
        this.listaConsultas = listaConsultas;
    }
    
}

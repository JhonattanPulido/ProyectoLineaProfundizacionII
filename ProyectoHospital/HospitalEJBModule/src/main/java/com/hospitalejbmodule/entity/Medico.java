// Paquete
package com.hospitalejbmodule.entity;

// Librerías
import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Entidad médico
 * @author Jhonattan Pulido
 * @version 1.0.0
 * @since 05/05/2021
 */
@Entity
@Table(name = "medicos", schema = "public")
@NamedQueries({
    @NamedQuery(name = "QMedicos", query = "SELECT COUNT(m.id) FROM Medico m WHERE m.id = :id"),
    @NamedQuery(name = "QMedicosEmail", query = "SELECT COUNT(m.correoElectronico) FROM Medico m WHERE m.correoElectronico = :correo_electronico"),
    @NamedQuery(name = "LeerMedico", query = "SELECT m FROM Medico m WHERE m.id = :id")
})
public class Medico implements Serializable {
    
    // Variables
    
    /**
     * ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Short id;
    
    /**
     * Nombre
     */
    @Column(name = "nombre")
    @NotNull(message = "El nombre es requerido")
    @Size(min = 2, max = 26, message = "El nombre debe tener mínimo 2 y máximo 26 letras")
    private String nombre;
    
    /**
     * Apellido
     */
    @Column(name = "apellido")
    @NotNull(message = "El apellido es requerido")
    @Size(min = 2, max = 26, message = "El apellido debe tener mínimo 2 y máximo 26 letras")
    private String apellido;
    
    /**
     * Correo electrónico
     */
    @Column(name = "correo_electronico")
    @NotNull(message = "El correo electrónico es requerido")
    private String correoElectronico;
    
    /**
     * Dirección asociada
     */
    @OneToOne(mappedBy = "medico", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)    
    private Direccion direccion;
    
    /**
     * Lista de consultas
     */
    @OneToMany(mappedBy = "medico", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Consulta> listaConsultas;

    /**
     * Constructor
     */
    public Medico() {
        
    }        
    
    // Métodos Set & Get

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

    public Direccion getDireccion() {
        return direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }        

    public List<Consulta> getListaConsultas() {
        return listaConsultas;
    }

    public void setListaConsultas(List<Consulta> listaConsultas) {
        this.listaConsultas = listaConsultas;
    }        
    
}

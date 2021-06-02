// Paquete
package com.udecsanitas.entity;

// Librerías
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Entity;
import javax.persistence.Column;
import javax.persistence.NamedQuery;
import javax.persistence.NamedQueries;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.validation.constraints.Size;
import javax.validation.constraints.NotNull;

/**
 * Entidad examen
 * @author Jhonattan Pulido
 * @version 1.0.0
 * @since 22/05/2021
 */
@Entity
@Table(name = "examenes", schema = "public")
@NamedQueries({
    @NamedQuery(name = "QExamenes", query = "SELECT COUNT(e) FROM Examen e WHERE e.id = :id"), // Cantidad de exámenes con ID
    @NamedQuery(name = "QExamenesT", query = "SELECT COUNT(e) FROM Examen e"), // Cantidad total de médicos
    @NamedQuery(name = "QExamenesNombre", query = "SELECT COUNT(e) FROM Examen e WHERE e.nombre = :nombre"), // Cantidad de exámenes con un nombre
    @NamedQuery(name = "QExamenesConsultaExamen", query = "SELECT COUNT (ce) FROM ConsultaExamen ce WHERE ce.examen.id = :id"),
    @NamedQuery(name = "LeerExamen", query = "SELECT e FROM Examen e WHERE e.id = :id"), // Leer examen filtrado por id
    @NamedQuery(name = "LeerExamenes", query = "SELECT e FROM Examen e ORDER BY e.id"), // Leer todos los exámenes existentes
    @NamedQuery(name = "ValidarNombre", query = "SELECT COUNT(e) FROM Examen e WHERE e.nombre = :nombre AND e.id <> :id"),
    @NamedQuery(name = "EliminarExamen", query = "DELETE FROM Examen e WHERE e.id = :id") // Eliminar un exámen
})
public class Examen implements Serializable {
    
    // Variables
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Short id;
    
    @Column(name = "nombre")
    @NotNull(message = "El nombre del examen es requerido")
    @Size(min = 2, max = 26, message = "El nombre del examen debe tener mínimo 2 y máximo 26 letras")
    private String nombre;
    
    @Column(name = "descripcion")
    @NotNull(message = "La descripción del examen es requerida")
    @Size(min = 2, max = 120, message = "La descripción del examen debe tener mínimo 2 y máximo 120 letras")
    private String descripcion;

    /**
     * Constructor
     */
    public Examen() {
        
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    // Equals & hash code
    
    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Examen other = (Examen) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
}

// Paquete
package com.udecsanitas.utilitarie;

// Librerías
import java.io.Serializable;

/**
 * Utilitario de examen
 * @author Jhonattan Pulido
 * @version 1.0.0
 * @since 22/05/2021
 */
public class UExamen implements Serializable {
    
    // Variables
    
    private Short id;
    
    private String nombre;
    
    private String descripcion;
    
    /**
     * Constructor
     */
    public UExamen() {
        
    }

    // Métodos set & get
    
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
    
}

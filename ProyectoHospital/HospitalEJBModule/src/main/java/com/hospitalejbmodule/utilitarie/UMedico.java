// Paquete
package com.hospitalejbmodule.utilitarie;

// Librería
import java.io.Serializable;

/**
 * Clase médico
 * @author Jhonattan Pulido
 * @version 1.0.0
 * @since 06/05/2021
 */
public class UMedico implements Serializable {
    
    // Variables
    
    /**
     * ID
     */
    private Short id;
    
    /**
     * Nombre
     */
    private String nombre;
    
    /**
     * Apellido
     */
    private String apellido;
    
    /**
     * Correo electrónico
     */
    private String correoElectronico;   
    
    /**
     * Dirección asociada
     */
    private UDireccion direccion;

    /**
     * Constructor
     */
    public UMedico() {
        
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

    public UDireccion getDireccion() {
        return direccion;
    }

    public void setDireccion(UDireccion direccion) {
        this.direccion = direccion;
    }
    
}

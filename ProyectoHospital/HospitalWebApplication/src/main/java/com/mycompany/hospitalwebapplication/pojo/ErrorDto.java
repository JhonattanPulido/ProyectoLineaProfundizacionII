// Paquete
package com.mycompany.hospitalwebapplication.pojo;

// Librerías
import java.io.Serializable;
import java.util.Date;

/**
 * Error dto
 * @author Jhonattan Pulido
 * @version 1.0.0
 * @since 05/05/2021
 */
public class ErrorDto implements Serializable {
    
    // Variables
    
    /**
     * Mensaje de error
     */
    private final String mensaje;
    
    /**
     * Fecha en la que se ejecuta el error
     */
    private final String fecha;
    
    /**
     * Path del error que se genera
     */
    private final String path;        

    /**
     * Constructor     
     * @param mensaje
     * @param path      
     */    
    public ErrorDto(String mensaje, String path) {        
        this.path = path;        
        this.mensaje = mensaje;
        fecha = new Date().toString();
    }

    public String getMensaje() {
        return mensaje;
    }

    public String getFecha() {
        return fecha;
    }

    public String getPath() {
        return path;
    }    
    
}

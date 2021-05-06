// Paquete
package com.hospitalejbmodule.excepciones;

// Librerías

/**
 * NotFoundException
 * @author Jhonattan Pulido
 * @version 1.0.0
 * @since 05/05/2021
 */
public class NotFoundException extends Exception {

    /**
     * Constructor
     * @param mensaje
     */
    public NotFoundException(String mensaje) {
        super(mensaje);
    }        
    
}

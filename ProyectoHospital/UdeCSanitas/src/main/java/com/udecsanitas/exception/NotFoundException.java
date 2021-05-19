// Paquete
package com.udecsanitas.exception;

// Librerías

/**
 * Not found exception
 * @author Jhonattan Pulido
 * @version 1.0.0
 * @since 18/05/2021
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

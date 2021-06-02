// Paquete
package com.udecsanitas.exception;

// Librerías

/**
 * Unauthorized exception
 * @author Jhonattan Pulido
 * @version 1.0.0
 * @since 01/06/2021
 */
public class UnauthorizedException extends Exception {

    /**
     * Constructor
     * @param mensaje 
     */
    public UnauthorizedException(String mensaje) {
        super(mensaje);
    }        
    
}

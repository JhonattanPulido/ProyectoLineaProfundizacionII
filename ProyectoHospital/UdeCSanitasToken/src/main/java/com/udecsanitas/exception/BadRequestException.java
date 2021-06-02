// Paquete
package com.udecsanitas.exception;

/**
 * Bad request exception
 * @author Jhonattan Pulido
 * @version 1.0.0
 * @since 01/06/2021
 */
public class BadRequestException extends Exception {

    /**
     * Constructor
     * @param mensaje 
     */
    public BadRequestException(String mensaje) {
        super(mensaje);
    }        
}

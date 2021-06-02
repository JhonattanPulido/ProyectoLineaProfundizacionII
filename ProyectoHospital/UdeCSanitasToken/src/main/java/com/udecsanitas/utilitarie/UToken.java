// Paquete
package com.udecsanitas.utilitarie;

// Librerías
import java.io.Serializable;

/**
 * Utilitario token
 * @author Jhonattan Pulido
 * @version 1.0.0
 * @since 01/06/2021
 */
public class UToken implements Serializable {
    
    // Variables
    private String token;

    /**
     * Constructor
     */
    public UToken() {
        
    }

    /**
     * Constructor con parámetros
     * @param token 
     */
    public UToken(String token) {
        this.token = token;
    }    
    
    // Métodos get

    public String getToken() {
        return token;
    }        
    
}

// Paquete
package com.udecsanitas.service.interfaz;

// Librerías
import com.udecsanitas.utilitarie.UToken;
import com.udecsanitas.exception.BadRequestException;
import com.udecsanitas.exception.UnauthorizedException;

/**
 * Interfaz de la capa de servicios de seguridad
 * @author Jhonattan Pulido
 * @since 01/06/2021
 * @version 1.0.0
 */
public interface ISeguridadService {
    
    // Variables
    public static String secretKey = "maXHECz7hLuJG3vKKMWAArjvb";
    
    // Métodos
    
    /**
     * Iniciar sesión
     * @param correoElectronico
     * @param clave
     * @return Token
     * @throws UnauthorizedException 
     * @throws BadRequestException
     */
    public UToken iniciarSesion(String correoElectronico, String clave) throws  UnauthorizedException,
                                                                                BadRequestException;
    
    /**
     * Validar integridad del token
     * @param token 
     * @param url
     * @throws UnauthorizedException
     */
    public void validarToken(String token, String url) throws   UnauthorizedException;
}

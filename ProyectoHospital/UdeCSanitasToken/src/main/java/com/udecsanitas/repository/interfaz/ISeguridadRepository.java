// Paquete
package com.udecsanitas.repository.interfaz;

// Librerías
import javax.ejb.Local;
import com.udecsanitas.entity.Seguridad;
import com.udecsanitas.exception.NotFoundException;

/**
 * Interfaz de la capa de datos de seguridad
 * @author Jhonattan Pulido
 * @version 1.0.0
 * @since 01/06/2021
 */
@Local
public interface ISeguridadRepository {
    
    // Métodos
    
    /**
     * Crear registro de inicio de sesión
     * @param seguridad 
     */
    public void crear(Seguridad seguridad);
    
    /**
     * Leer registro de inicio de sesión
     * @param token
     * @return Registro
     * @throws NotFoundException 
     */
    public Seguridad leer(String token) throws  NotFoundException;
    
    /**
     * Eliminar registro de inicio de sesión
     * @param token 
     */
    public void eliminar(String token);
    
    /**
     * Cantidad de registros que tienen un token y un correo electrónico
     * @param token
     * @param correoElectronico 
     * @return Cantidad de registros
     */
    public long cantidad(String token, String correoElectronico);
    
    /**
     * Cantidad de registros que tienen un token
     * @param token
     * @return Cantidad de registros
     */
    public long cantidadToken(String token);
    
    /**
     * Cantidad de registros que tienen un correo electrónico
     * @param correoElectronico
     * @return Cantidad de registros
     */
    public long cantidadEmail(String correoElectronico);
    
}

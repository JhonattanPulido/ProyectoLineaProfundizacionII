// Paquete
package com.udecsanitas.repository.interfaz;

// Librerías
import javax.ejb.Local;
import com.udecsanitas.entity.Medico;
import com.udecsanitas.exception.NotFoundException;
import com.udecsanitas.exception.UnauthorizedException;

/**
 * Interfaz de la capa de datos de médico
 * @author Jhonattan Pulido
 * @version 1.0.0
 * @since 18/05/2021
 */
@Local
public interface IMedicoRepository extends IGenericaRepository<Medico> {
    
    // Métodos
    
    /**
     * Leer médico filtrado por correo electrónico y clave
     * @param correoElectronico
     * @param clave
     * @return Médico
     * @throws UnauthorizedException
     */
    public Medico leer(String correoElectronico, String clave) throws   UnauthorizedException;
    
    /**
     * Leer nombre de un rol
     * @param id
     * @return Nombre     
     */
    public String leerRol(Short id);
    
    /**
     * Eliminar un médico
     * @param medico 
     */
    public void eliminar(Medico medico);
    
    /**
     * Cantidad de médicos con un correo electrónico
     * @param correoElectronico
     * @return cantidad de registros con ID
     */
    public long cantidadEmail(String correoElectronico);
    
    /**
     * Cantidad de consultas pertenecientes a un médico
     * @param id
     * @return Cantidad registros
     */
    public long cantidadConsultas(short id);
    
    /**
     * Validar correo electrónico para actualizar
     * @param correoElectronico
     * @param id
     * @return cantidad de registros
     */
    public long validarEmail(String correoElectronico, short id);
    
}

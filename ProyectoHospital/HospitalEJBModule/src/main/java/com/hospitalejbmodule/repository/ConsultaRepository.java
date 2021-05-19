// Paquete
package com.hospitalejbmodule.repository;

// Librerías
import com.hospitalejbmodule.entity.Consulta;
import com.hospitalejbmodule.repository.interfaz.IConsultaRepository;
import javax.ejb.Stateless;

/**
 * Capa de datos de consulta
 * @author Jhonattan Pulido
 * @version 1.0.0
 * @since 05/05/2021
 */
@Stateless
public class ConsultaRepository extends GenericaRepository<Consulta> implements IConsultaRepository {

    @Override
    public void eliminar(Consulta consulta) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}

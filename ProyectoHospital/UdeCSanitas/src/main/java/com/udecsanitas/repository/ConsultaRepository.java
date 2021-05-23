// Paquete
package com.udecsanitas.repository;

// Librerías
import javax.ejb.Stateless;
import com.udecsanitas.entity.Consulta;
import com.udecsanitas.repository.interfaz.IConsultaRepository;

/**
 * Capa de datos de consulta
 * @author Jhonattan Pulido
 * @version 1.0.0
 * @since 23/05/2021
 */
@Stateless
public class ConsultaRepository extends GenericaRepository<Consulta> implements IConsultaRepository {
          
}

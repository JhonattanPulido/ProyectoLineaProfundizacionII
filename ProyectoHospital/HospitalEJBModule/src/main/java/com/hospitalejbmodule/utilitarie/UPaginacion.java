// Paquete
package com.hospitalejbmodule.utilitarie;

// Librerías
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Jhonattan Pulido
 * @since 10/05/2021
 * @version 1.0.0
 */
public class UPaginacion<T> implements Serializable {
    
    // Variables
    
    /**
     * Cantidad total
     */
    private Long cantidadTotal;
    
    /**
     * Lista
     */
    private List<T> lista;

    /**
     * Constructor
     */
    public UPaginacion() {
    }

    // Métoso Set & Get
    
    public Long getCantidadTotal() {
        return cantidadTotal;
    }

    public void setCantidadTotal(Long cantidadTotal) {
        this.cantidadTotal = cantidadTotal;
    }

    public List<T> getLista() {
        return lista;
    }

    public void setLista(List<T> lista) {
        this.lista = lista;
    }        
    
}

// Paquete
package com.udecsanitas.utilitarie;

// Librerías
import java.util.List;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Utilitario de paginador
 * @author Jhonattan Pulido
 * @version 1.0.0
 * @since 18/05/2021
 * @param <T>
 */
public class UPaginador<T> implements Serializable {

    // Variables
    
    private Short paginaActual;
    
    private Short cantidadTotal;
    
    private List<T> lista;

    /**
     * Constructor
     */
    public UPaginador() {
        
    }   
    
    /**
     * Construtor
     * @param paginaActual
     * @param cantidadTotal 
     */
    public UPaginador(short paginaActual, short cantidadTotal) {
        lista = new ArrayList<>();
        this.paginaActual = paginaActual;
        this.cantidadTotal = cantidadTotal;
    }

    // Métodos get & set
    
    public Short getPaginaActual() {
        return paginaActual;
    }

    public void setPaginaActual(Short paginaActual) {
        this.paginaActual = paginaActual;
    }

    public Short getCantidadTotal() {
        return cantidadTotal;
    }

    public void setCantidadTotal(Short cantidadTotal) {
        this.cantidadTotal = cantidadTotal;
    }

    public List<T> getLista() {
        return lista;
    }

    public void setLista(List<T> lista) {
        this.lista = lista;
    }        
    
}

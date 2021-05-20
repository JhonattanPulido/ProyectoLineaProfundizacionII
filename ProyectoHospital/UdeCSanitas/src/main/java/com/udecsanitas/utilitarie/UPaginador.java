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
    
    private Double cantidadPaginas;
    
    private Short paginaActual;
    
    private Short cantidadTotal;
    
    private Short cantidadMostrar;
    
    private List<T> lista;

    /**
     * Constructor
     */
    public UPaginador() {
        
    }   
    
    /**
     * Constructor
     * @param paginaActual
     * @param cantidadTotal  
     * @param cantidadMostrar 
     */
    public UPaginador(short paginaActual, short cantidadTotal, short cantidadMostrar) {
        lista = new ArrayList<>();
        this.paginaActual = paginaActual;
        this.cantidadTotal = cantidadTotal;
        this.cantidadMostrar = cantidadMostrar;
        cantidadPaginas = (Math.ceil(cantidadTotal / cantidadMostrar) + 1);
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

    public double getCantidadPaginas() {
        return cantidadPaginas;
    }    
    
    public Short getCantidadMostrar() {
        return cantidadMostrar;
    }
    
    public List<T> getLista() {
        return lista;
    }

    public void setLista(List<T> lista) {
        this.lista = lista;
    }            
    
}

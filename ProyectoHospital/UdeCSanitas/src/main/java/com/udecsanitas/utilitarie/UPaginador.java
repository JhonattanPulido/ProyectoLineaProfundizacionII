// Paquete
package com.udecsanitas.utilitarie;

// Librerías
import java.util.List;
import java.io.Serializable;

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
     * @param lista 
     */
    public UPaginador(short paginaActual, short cantidadTotal, short cantidadMostrar, List<T> lista) {
        this.lista = lista;
        this.paginaActual = paginaActual;
        this.cantidadTotal = cantidadTotal;
        this.cantidadMostrar = cantidadMostrar;        
        cantidadPaginas = Math.ceil((double) cantidadTotal / (double) cantidadMostrar);
    }

    // Métodos get & set

    public Double getCantidadPaginas() {
        return cantidadPaginas;
    }

    public void setCantidadPaginas(Double cantidadPaginas) {
        this.cantidadPaginas = cantidadPaginas;
    }

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

    public Short getCantidadMostrar() {
        return cantidadMostrar;
    }

    public void setCantidadMostrar(Short cantidadMostrar) {
        this.cantidadMostrar = cantidadMostrar;
    }

    public List<T> getLista() {
        return lista;
    }

    public void setLista(List<T> lista) {
        this.lista = lista;
    }              
        
}

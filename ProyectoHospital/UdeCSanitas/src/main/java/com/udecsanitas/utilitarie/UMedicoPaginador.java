// Paquete
package com.udecsanitas.utilitarie;

/**
 * Paginador de médico
 * @author Jhonattan Pulido
 * @version 1.0.0
 * @since 19/05/2021
 */
public class UMedicoPaginador extends  UPaginador<UMedico>{

    /**
     * Constructor
     */
    public UMedicoPaginador() {
        
    }

    /**
     * Constructor
     * @param paginaActual
     * @param cantidadTotal 
     * @param cantidadMostrar
     */
    public UMedicoPaginador(short paginaActual, short cantidadTotal, short cantidadMostrar) {
        super(paginaActual, cantidadTotal, cantidadMostrar);
    }        
    
}

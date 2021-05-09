// Paquete
package com.hospitalejbmodule.utilitarie;

// Librerías
import java.io.Serializable;

/**
 * Clase detalle consulta
 * @author Jhonattan Pulido
 * @version 1.0.0
 * @since 08/05/2021
 */
public class UDetalleConsulta implements Serializable {
    
    // Variables
    
    /**
     * ID
     */
    private Short id;
    
    /**
     * Diagnóstico
     */
    private String diagnostico;
    
    /**
     * Tratamiento
     */
    private String tratamiento;
    
    /**
     * Consulta
     */
    private UConsulta consulta;

    /**
     * Constructor
     */
    public UDetalleConsulta() {
        
    }

    // Métodos Set & Get
    
    public Short getId() {
        return id;
    }

    public void setId(Short id) {
        this.id = id;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public String getTratamiento() {
        return tratamiento;
    }

    public void setTratamiento(String tratamiento) {
        this.tratamiento = tratamiento;
    }

    public UConsulta getConsulta() {
        return consulta;
    }

    public void setConsulta(UConsulta consulta) {
        this.consulta = consulta;
    }        
    
}

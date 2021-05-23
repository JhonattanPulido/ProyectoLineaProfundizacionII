// Paquete
package com.udecsanitas.utilitarie;

// Librerías
import java.io.Serializable;
import javax.json.bind.annotation.JsonbTransient;

/**
 * Utilitario de detalle consulta
 * @author Jhonattan Pulido
 * @version 1.0.0
 * @since 23/05/2021
 */
public class UDetalleConsulta implements Serializable {

    // Variables
    
    private Short id;
    
    private String diagnostico;
    
    private String tratamiento;
    
    private UConsulta consulta;

    /**
     * Constructor
     */
    public UDetalleConsulta() {
        
    }

    // Métodos get & set
    
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

    @JsonbTransient
    public UConsulta getConsulta() {
        return consulta;
    }

    public void setConsulta(UConsulta consulta) {
        this.consulta = consulta;
    }        
    
}

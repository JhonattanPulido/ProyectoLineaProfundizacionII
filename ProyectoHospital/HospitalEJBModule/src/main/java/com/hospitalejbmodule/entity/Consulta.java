// Paquete
package com.hospitalejbmodule.entity;

// Librerías
import java.io.Serializable;
import java.util.List;
import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * Entidad consulta
 * @author Jhonattan Pulido
 * @version 1.0.0
 * @since 05/05/2021
 */
@Entity
@Table(name = "consultas", schema = "public")
@NamedQueries({
    @NamedQuery(name = "QConsultas", query = "SELECT COUNT(c.id) FROM Consulta c WHERE c.id = :id"),
    @NamedQuery(name = "LeerConsulta", query = "SELECT c FROM Consulta c WHERE c.id = :id")
})
public class Consulta implements Serializable {
    
    // Variables
    
    /**
     * ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Short id;
    
    /**
     * Médico asociado
     */
    @OneToOne
    @JsonbTransient 
    @JoinColumn(name = "medico_id")
    @NotNull(message = "El médico asociado es requerido")
    private Medico medico;
    
    /**
     * Detalles consultas asociadas
     */
    @OneToOne(mappedBy = "consulta", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<DetalleConsulta> listaDetallesConsultas;

    /**
     * Constructor
     */
    public Consulta() {
        
    }        

    // Métodos Set & Get
    
    public Short getId() {
        return id;
    }

    public void setId(Short id) {
        this.id = id;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }       

    public List<DetalleConsulta> getListaDetallesConsultas() {
        return listaDetallesConsultas;
    }

    public void setListaDetallesConsultas(List<DetalleConsulta> listaDetallesConsultas) {
        this.listaDetallesConsultas = listaDetallesConsultas;
    }        
    
}

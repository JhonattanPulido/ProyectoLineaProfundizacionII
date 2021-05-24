// Paquete
package com.udecsanitas.entity;

// Librerías
import java.util.List;
import java.util.Date;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import javax.persistence.NamedQuery;
import javax.persistence.JoinColumn;
import javax.persistence.CascadeType;
import javax.persistence.TemporalType;
import javax.persistence.NamedQueries;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.json.bind.annotation.JsonbTransient;

/**
 * Entidad consulta
 * @author Jhonattan Pulido
 * @version 1.0.0
 * @since 23/05/2021
 */
@Entity
@Table(name = "consultas", schema = "public")
@NamedQueries({
    @NamedQuery(name = "QConsultas", query = "SELECT COUNT(c) FROM Consulta c WHERE c.id = :id"),
    @NamedQuery(name = "QConsultasXMedico", query = "SELECT COUNT(c) FROM Consulta c WHERE c.medico.id = :medico_id"),
    @NamedQuery(name = "LeerConsulta", query = "SELECT c FROM Consulta c WHERE c.id = :id"),
    @NamedQuery(name = "LeerConsultasXMedico", query = "SELECT c FROM Consulta c WHERE c.medico.id = :medico_id ORDER BY c.id ASC"),
    @NamedQuery(name = "EliminarConsulta", query = "DELETE FROM Consulta c WHERE c.id = :id")
})
public class Consulta implements Serializable {
    
    // Variables
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Short id;
    
    @Column(name = "fecha")    
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    
    @ManyToOne
    @JoinColumn(name = "medico_id")
    private Medico medico;
    
    @OneToMany(mappedBy = "consulta", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<DetalleConsulta> listaDetallesConsultas;
    
    @Transient
    private List<Short> listaExamenes;

    /**
     * Constructor
     */
    public Consulta() {
        fecha = new Date();
    }
    
    // Métodos get & set

    public Short getId() {
        return id;
    }

    public void setId(Short id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    @JsonbTransient
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

    public List<Short> getListaExamenes() {
        return listaExamenes;
    }

    public void setListaExamenes(List<Short> listaExamenes) {
        this.listaExamenes = listaExamenes;
    }

    // Equals & hash code
    
    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Consulta other = (Consulta) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
        
}

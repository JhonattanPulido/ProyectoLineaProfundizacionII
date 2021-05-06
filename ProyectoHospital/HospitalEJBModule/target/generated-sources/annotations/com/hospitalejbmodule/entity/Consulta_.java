package com.hospitalejbmodule.entity;

import com.hospitalejbmodule.entity.DetalleConsulta;
import com.hospitalejbmodule.entity.Medico;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-05-05T21:53:55")
@StaticMetamodel(Consulta.class)
public class Consulta_ { 

    public static volatile SingularAttribute<Consulta, Medico> medico;
    public static volatile SingularAttribute<Consulta, Short> id;
    public static volatile ListAttribute<Consulta, DetalleConsulta> listaDetallesConsultas;

}
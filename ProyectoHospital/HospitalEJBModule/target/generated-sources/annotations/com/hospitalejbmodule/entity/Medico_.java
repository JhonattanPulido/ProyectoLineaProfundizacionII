package com.hospitalejbmodule.entity;

import com.hospitalejbmodule.entity.Consulta;
import com.hospitalejbmodule.entity.Direccion;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-05-11T10:55:18")
@StaticMetamodel(Medico.class)
public class Medico_ { 

    public static volatile SingularAttribute<Medico, String> apellido;
    public static volatile SingularAttribute<Medico, Direccion> direccion;
    public static volatile SingularAttribute<Medico, Short> id;
    public static volatile SingularAttribute<Medico, String> nombre;
    public static volatile SingularAttribute<Medico, String> correoElectronico;
    public static volatile SingularAttribute<Medico, Consulta> consulta;

}
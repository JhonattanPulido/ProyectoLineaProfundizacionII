package com.udecsanitas.entity;

import com.udecsanitas.entity.Consulta;
import com.udecsanitas.entity.Direccion;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-06-07T19:00:13")
@StaticMetamodel(Medico.class)
public class Medico_ { 

    public static volatile ListAttribute<Medico, Consulta> listaConsultas;
    public static volatile SingularAttribute<Medico, String> apellido;
    public static volatile SingularAttribute<Medico, Direccion> direccion;
    public static volatile SingularAttribute<Medico, Short> id;
    public static volatile SingularAttribute<Medico, String> nombre;
    public static volatile SingularAttribute<Medico, String> correoElectronico;

}
package com.udecsanitas.entity;

import com.udecsanitas.entity.Medico;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-06-01T23:23:00")
@StaticMetamodel(Direccion.class)
public class Direccion_ { 

    public static volatile SingularAttribute<Direccion, String> codigoPostal;
    public static volatile SingularAttribute<Direccion, String> direccionDetallada;
    public static volatile SingularAttribute<Direccion, Medico> medico;
    public static volatile SingularAttribute<Direccion, Short> id;

}
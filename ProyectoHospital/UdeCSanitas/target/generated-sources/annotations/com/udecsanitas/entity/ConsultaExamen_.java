package com.udecsanitas.entity;

import com.udecsanitas.entity.Consulta;
import com.udecsanitas.entity.ConsultaExamenPK;
import com.udecsanitas.entity.Examen;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-06-07T19:00:13")
@StaticMetamodel(ConsultaExamen.class)
public class ConsultaExamen_ { 

    public static volatile SingularAttribute<ConsultaExamen, ConsultaExamenPK> consultaExamenPK;
    public static volatile SingularAttribute<ConsultaExamen, Examen> examen;
    public static volatile SingularAttribute<ConsultaExamen, Consulta> consulta;

}
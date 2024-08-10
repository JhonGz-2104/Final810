package com.jp.entity;

import com.jp.entity.Articulos;
import com.jp.entity.TipoTransaccion;
import java.util.Date;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2024-08-10T11:02:36", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(Transacciones.class)
public class Transacciones_ { 

    public static volatile SingularAttribute<Transacciones, Integer> idAsiento;
    public static volatile SingularAttribute<Transacciones, Date> fecha;
    public static volatile SingularAttribute<Transacciones, Articulos> articuloId;
    public static volatile SingularAttribute<Transacciones, Double> monto;
    public static volatile SingularAttribute<Transacciones, Integer> id;
    public static volatile SingularAttribute<Transacciones, Integer> cantidad;
    public static volatile SingularAttribute<Transacciones, TipoTransaccion> tipoTransaccionId;

}
package com.jp.entity;

import com.jp.entity.Transacciones;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2024-08-10T11:02:36", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(TipoTransaccion.class)
public class TipoTransaccion_ { 

    public static volatile SingularAttribute<TipoTransaccion, String> descripcion;
    public static volatile CollectionAttribute<TipoTransaccion, Transacciones> transaccionesCollection;
    public static volatile SingularAttribute<TipoTransaccion, Integer> id;

}
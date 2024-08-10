package com.jp.entity;

import com.jp.entity.Almacen;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2024-08-10T11:02:36", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(EstadoAlmacen.class)
public class EstadoAlmacen_ { 

    public static volatile SingularAttribute<EstadoAlmacen, String> descripcion;
    public static volatile CollectionAttribute<EstadoAlmacen, Almacen> almacenCollection;
    public static volatile SingularAttribute<EstadoAlmacen, Integer> id;

}
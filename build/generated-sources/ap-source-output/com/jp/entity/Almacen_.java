package com.jp.entity;

import com.jp.entity.Articulos;
import com.jp.entity.EstadoAlmacen;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2024-08-10T11:02:36", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(Almacen.class)
public class Almacen_ { 

    public static volatile SingularAttribute<Almacen, String> descripcion;
    public static volatile SingularAttribute<Almacen, Integer> id;
    public static volatile CollectionAttribute<Almacen, Articulos> articulosCollection;
    public static volatile SingularAttribute<Almacen, EstadoAlmacen> estadoAlmacenId;

}
package com.jp.entity;

import com.jp.entity.Almacen;
import com.jp.entity.EstadoArticulo;
import com.jp.entity.TipoInventario;
import com.jp.entity.Transacciones;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2024-08-10T11:02:36", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(Articulos.class)
public class Articulos_ { 

    public static volatile SingularAttribute<Articulos, String> descripcion;
    public static volatile SingularAttribute<Articulos, Integer> existencias;
    public static volatile SingularAttribute<Articulos, Almacen> almacenId;
    public static volatile CollectionAttribute<Articulos, Transacciones> transaccionesCollection;
    public static volatile SingularAttribute<Articulos, EstadoArticulo> estadoArticuloId;
    public static volatile SingularAttribute<Articulos, Double> precioCompra;
    public static volatile SingularAttribute<Articulos, TipoInventario> tipoInventarioId;
    public static volatile SingularAttribute<Articulos, Integer> id;
    public static volatile SingularAttribute<Articulos, Double> precioVenta;

}
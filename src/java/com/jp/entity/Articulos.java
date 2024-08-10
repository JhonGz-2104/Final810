/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jp.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author LENOVO
 */
@Entity
@Table(name = "Articulos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Articulos.findAll", query = "SELECT a FROM Articulos a"),
    @NamedQuery(name = "Articulos.findById", query = "SELECT a FROM Articulos a WHERE a.id = :id"),
    @NamedQuery(name = "Articulos.findByDescripcion", query = "SELECT a FROM Articulos a WHERE a.descripcion = :descripcion"),
    @NamedQuery(name = "Articulos.findByExistencias", query = "SELECT a FROM Articulos a WHERE a.existencias = :existencias"),
    @NamedQuery(name = "Articulos.findByPrecioVenta", query = "SELECT a FROM Articulos a WHERE a.precioVenta = :precioVenta"),
    @NamedQuery(name = "Articulos.findByPrecioCompra", query = "SELECT a FROM Articulos a WHERE a.precioCompra = :precioCompra")})
public class Articulos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "existencias")
    private Integer existencias;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "precio_venta")
    private double precioVenta;
    @Column(name = "precio_compra")
    private double precioCompra;
    @JoinColumn(name = "almacen_id", referencedColumnName = "id")
    @ManyToOne
    private Almacen almacenId;
    @JoinColumn(name = "estado_articulo_id", referencedColumnName = "id")
    @ManyToOne
    private EstadoArticulo estadoArticuloId;
    @JoinColumn(name = "tipo_inventario_id", referencedColumnName = "id")
    @ManyToOne
    private TipoInventario tipoInventarioId;
    @OneToMany(mappedBy = "articuloId")
    private Collection<Transacciones> transaccionesCollection;

    public Articulos() {
    }

    public Articulos(Integer id) {
        this.id = id;
    }

    public Articulos(Integer id, String descripcion) {
        this.id = id;
        this.descripcion = descripcion;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getExistencias() {
        return existencias;
    }

    public void setExistencias(Integer existencias) {
        this.existencias = existencias;
    }

    public double getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(double precioVenta) {
        this.precioVenta = precioVenta;
    }

    public double getPrecioCompra() {
        return precioCompra;
    }

    public void setPrecioCompra(double precioCompra) {
        this.precioCompra = precioCompra;
    }

    public Almacen getAlmacenId() {
        return almacenId;
    }

    public void setAlmacenId(Almacen almacenId) {
        this.almacenId = almacenId;
    }

    public EstadoArticulo getEstadoArticuloId() {
        return estadoArticuloId;
    }

    public void setEstadoArticuloId(EstadoArticulo estadoArticuloId) {
        this.estadoArticuloId = estadoArticuloId;
    }

    public TipoInventario getTipoInventarioId() {
        return tipoInventarioId;
    }

    public void setTipoInventarioId(TipoInventario tipoInventarioId) {
        this.tipoInventarioId = tipoInventarioId;
    }

    @XmlTransient
    public Collection<Transacciones> getTransaccionesCollection() {
        return transaccionesCollection;
    }

    public void setTransaccionesCollection(Collection<Transacciones> transaccionesCollection) {
        this.transaccionesCollection = transaccionesCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Articulos)) {
            return false;
        }
        Articulos other = (Articulos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.jp.entity.Articulos[ id=" + id + " ]";
    }
    
}

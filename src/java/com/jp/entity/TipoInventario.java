/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jp.entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "tipo_inventario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoInventario.findAll", query = "SELECT t FROM TipoInventario t"),
    @NamedQuery(name = "TipoInventario.findById", query = "SELECT t FROM TipoInventario t WHERE t.id = :id"),
    @NamedQuery(name = "TipoInventario.findByDescripcion", query = "SELECT t FROM TipoInventario t WHERE t.descripcion = :descripcion"),
    @NamedQuery(name = "TipoInventario.findByCuentaContable", query = "SELECT t FROM TipoInventario t WHERE t.cuentaContable = :cuentaContable")})
public class TipoInventario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "descripcion")
    private String descripcion;
    @Basic(optional = false)
    @Column(name = "cuenta_contable")
    private String cuentaContable;
    @OneToMany(mappedBy = "tipoInventarioId")
    private Collection<Articulos> articulosCollection;

    public TipoInventario() {
    }

    public TipoInventario(Integer id) {
        this.id = id;
    }

    public TipoInventario(Integer id, String descripcion, String cuentaContable) {
        this.id = id;
        this.descripcion = descripcion;
        this.cuentaContable = cuentaContable;
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

    public String getCuentaContable() {
        return cuentaContable;
    }

    public void setCuentaContable(String cuentaContable) {
        this.cuentaContable = cuentaContable;
    }

    @XmlTransient
    public Collection<Articulos> getArticulosCollection() {
        return articulosCollection;
    }

    public void setArticulosCollection(Collection<Articulos> articulosCollection) {
        this.articulosCollection = articulosCollection;
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
        if (!(object instanceof TipoInventario)) {
            return false;
        }
        TipoInventario other = (TipoInventario) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.jp.entity.TipoInventario[ id=" + id + " ]";
    }
    
}

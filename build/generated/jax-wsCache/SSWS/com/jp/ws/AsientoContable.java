
package com.jp.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para anonymous complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="idAuxiliarOrigen" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="descripcion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="cuentaDB" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="cuentaCR" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="monto" type="{http://www.w3.org/2001/XMLSchema}double"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "idAuxiliarOrigen",
    "descripcion",
    "cuentaDB",
    "cuentaCR",
    "monto"
})
@XmlRootElement(name = "AsientoContable")
public class AsientoContable {

    protected int idAuxiliarOrigen;
    protected String descripcion;
    protected int cuentaDB;
    protected int cuentaCR;
    protected double monto;

    /**
     * Obtiene el valor de la propiedad idAuxiliarOrigen.
     * 
     */
    public int getIdAuxiliarOrigen() {
        return idAuxiliarOrigen;
    }

    /**
     * Define el valor de la propiedad idAuxiliarOrigen.
     * 
     */
    public void setIdAuxiliarOrigen(int value) {
        this.idAuxiliarOrigen = value;
    }

    /**
     * Obtiene el valor de la propiedad descripcion.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Define el valor de la propiedad descripcion.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescripcion(String value) {
        this.descripcion = value;
    }

    /**
     * Obtiene el valor de la propiedad cuentaDB.
     * 
     */
    public int getCuentaDB() {
        return cuentaDB;
    }

    /**
     * Define el valor de la propiedad cuentaDB.
     * 
     */
    public void setCuentaDB(int value) {
        this.cuentaDB = value;
    }

    /**
     * Obtiene el valor de la propiedad cuentaCR.
     * 
     */
    public int getCuentaCR() {
        return cuentaCR;
    }

    /**
     * Define el valor de la propiedad cuentaCR.
     * 
     */
    public void setCuentaCR(int value) {
        this.cuentaCR = value;
    }

    /**
     * Obtiene el valor de la propiedad monto.
     * 
     */
    public double getMonto() {
        return monto;
    }

    /**
     * Define el valor de la propiedad monto.
     * 
     */
    public void setMonto(double value) {
        this.monto = value;
    }

}


package com.jp.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
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
 *         &lt;element name="CuentasDebitoResult" type="{http://tempuri.org/}ArrayOfTiposCuentasLista" minOccurs="0"/&gt;
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
    "cuentasDebitoResult"
})
@XmlRootElement(name = "CuentasDebitoResponse")
public class CuentasDebitoResponse {

    @XmlElement(name = "CuentasDebitoResult")
    protected ArrayOfTiposCuentasLista cuentasDebitoResult;

    /**
     * Obtiene el valor de la propiedad cuentasDebitoResult.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfTiposCuentasLista }
     *     
     */
    public ArrayOfTiposCuentasLista getCuentasDebitoResult() {
        return cuentasDebitoResult;
    }

    /**
     * Define el valor de la propiedad cuentasDebitoResult.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfTiposCuentasLista }
     *     
     */
    public void setCuentasDebitoResult(ArrayOfTiposCuentasLista value) {
        this.cuentasDebitoResult = value;
    }

}

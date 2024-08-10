
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
 *         &lt;element name="AsientoContableResult" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
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
    "asientoContableResult"
})
@XmlRootElement(name = "AsientoContableResponse")
public class AsientoContableResponse {

    @XmlElement(name = "AsientoContableResult")
    protected int asientoContableResult;

    /**
     * Obtiene el valor de la propiedad asientoContableResult.
     * 
     */
    public int getAsientoContableResult() {
        return asientoContableResult;
    }

    /**
     * Define el valor de la propiedad asientoContableResult.
     * 
     */
    public void setAsientoContableResult(int value) {
        this.asientoContableResult = value;
    }

}

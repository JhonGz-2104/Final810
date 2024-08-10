
package com.jp.ws;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para ArrayOfTiposCuentasLista complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfTiposCuentasLista"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="TiposCuentasLista" type="{http://tempuri.org/}TiposCuentasLista" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfTiposCuentasLista", propOrder = {
    "tiposCuentasLista"
})
public class ArrayOfTiposCuentasLista {

    @XmlElement(name = "TiposCuentasLista", nillable = true)
    protected List<TiposCuentasLista> tiposCuentasLista;

    /**
     * Gets the value of the tiposCuentasLista property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the tiposCuentasLista property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTiposCuentasLista().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TiposCuentasLista }
     * 
     * 
     */
    public List<TiposCuentasLista> getTiposCuentasLista() {
        if (tiposCuentasLista == null) {
            tiposCuentasLista = new ArrayList<TiposCuentasLista>();
        }
        return this.tiposCuentasLista;
    }

}

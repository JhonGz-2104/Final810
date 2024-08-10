
package com.jp.ws;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.jp.ws package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.jp.ws
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link AsientoContable }
     * 
     */
    public AsientoContable createAsientoContable() {
        return new AsientoContable();
    }

    /**
     * Create an instance of {@link AsientoContableResponse }
     * 
     */
    public AsientoContableResponse createAsientoContableResponse() {
        return new AsientoContableResponse();
    }

    /**
     * Create an instance of {@link Cuentas }
     * 
     */
    public Cuentas createCuentas() {
        return new Cuentas();
    }

    /**
     * Create an instance of {@link CuentasResponse }
     * 
     */
    public CuentasResponse createCuentasResponse() {
        return new CuentasResponse();
    }

    /**
     * Create an instance of {@link ArrayOfTiposCuentasLista }
     * 
     */
    public ArrayOfTiposCuentasLista createArrayOfTiposCuentasLista() {
        return new ArrayOfTiposCuentasLista();
    }

    /**
     * Create an instance of {@link CuentasDebito }
     * 
     */
    public CuentasDebito createCuentasDebito() {
        return new CuentasDebito();
    }

    /**
     * Create an instance of {@link CuentasDebitoResponse }
     * 
     */
    public CuentasDebitoResponse createCuentasDebitoResponse() {
        return new CuentasDebitoResponse();
    }

    /**
     * Create an instance of {@link CuentasCredito }
     * 
     */
    public CuentasCredito createCuentasCredito() {
        return new CuentasCredito();
    }

    /**
     * Create an instance of {@link CuentasCreditoResponse }
     * 
     */
    public CuentasCreditoResponse createCuentasCreditoResponse() {
        return new CuentasCreditoResponse();
    }

    /**
     * Create an instance of {@link TiposCuentasLista }
     * 
     */
    public TiposCuentasLista createTiposCuentasLista() {
        return new TiposCuentasLista();
    }

}

//
// Diese Datei wurde mit der JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.6 generiert 
// Siehe <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Änderungen an dieser Datei gehen bei einer Neukompilierung des Quellschemas verloren. 
// Generiert: 2013.04.21 um 09:49:20 PM CEST 
//


package de.fhkoeln.gm.wba2.phase2.jersey.jaxb;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the generated package. 
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

    private final static QName _TemperaturenEl_QNAME = new QName("", "temperaturen_el");
    private final static QName _Einheit_QNAME = new QName("", "einheit");
    private final static QName _Wert_QNAME = new QName("", "wert");
    private final static QName _TemperaturEl_QNAME = new QName("", "temperatur_el");
    private final static QName _Raum_QNAME = new QName("", "raum");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: generated
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Temperaturen }
     * 
     */
    public Temperaturen createTemperaturen() {
        return new Temperaturen();
    }

    /**
     * Create an instance of {@link Temperatur }
     * 
     */
    public Temperatur createTemperatur() {
        return new Temperatur();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Temperaturen }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "temperaturen_el")
    public JAXBElement<Temperaturen> createTemperaturenEl(Temperaturen value) {
        return new JAXBElement<Temperaturen>(_TemperaturenEl_QNAME, Temperaturen.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "einheit")
    public JAXBElement<String> createEinheit(String value) {
        return new JAXBElement<String>(_Einheit_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "wert")
    public JAXBElement<String> createWert(String value) {
        return new JAXBElement<String>(_Wert_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Temperatur }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "temperatur_el")
    public JAXBElement<Temperatur> createTemperaturEl(Temperatur value) {
        return new JAXBElement<Temperatur>(_TemperaturEl_QNAME, Temperatur.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "raum")
    public JAXBElement<String> createRaum(String value) {
        return new JAXBElement<String>(_Raum_QNAME, String.class, null, value);
    }

}

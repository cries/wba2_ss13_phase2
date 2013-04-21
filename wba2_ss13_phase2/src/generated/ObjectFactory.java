//
// Diese Datei wurde mit der JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.6 generiert 
// Siehe <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Änderungen an dieser Datei gehen bei einer Neukompilierung des Quellschemas verloren. 
// Generiert: 2013.04.21 um 07:28:28 PM CEST 
//


package generated;

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

    private final static QName _Wert_QNAME = new QName("", "wert");
    private final static QName _Temperaturen_QNAME = new QName("", "temperaturen");
    private final static QName _Raum_QNAME = new QName("", "raum");
    private final static QName _Temperatur_QNAME = new QName("", "temperatur");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: generated
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link WertType }
     * 
     */
    public WertType createWertType() {
        return new WertType();
    }

    /**
     * Create an instance of {@link TemperaturenType }
     * 
     */
    public TemperaturenType createTemperaturenType() {
        return new TemperaturenType();
    }

    /**
     * Create an instance of {@link TemperaturType }
     * 
     */
    public TemperaturType createTemperaturType() {
        return new TemperaturType();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link WertType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "wert")
    public JAXBElement<WertType> createWert(WertType value) {
        return new JAXBElement<WertType>(_Wert_QNAME, WertType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TemperaturenType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "temperaturen")
    public JAXBElement<TemperaturenType> createTemperaturen(TemperaturenType value) {
        return new JAXBElement<TemperaturenType>(_Temperaturen_QNAME, TemperaturenType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "raum")
    public JAXBElement<String> createRaum(String value) {
        return new JAXBElement<String>(_Raum_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TemperaturType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "temperatur")
    public JAXBElement<TemperaturType> createTemperatur(TemperaturType value) {
        return new JAXBElement<TemperaturType>(_Temperatur_QNAME, TemperaturType.class, null, value);
    }

}

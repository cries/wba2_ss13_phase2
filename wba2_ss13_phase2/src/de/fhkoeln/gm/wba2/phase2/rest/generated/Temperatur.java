//
// Diese Datei wurde mit der JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.6 generiert 
// Siehe <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Änderungen an dieser Datei gehen bei einer Neukompilierung des Quellschemas verloren. 
// Generiert: 2013.06.23 um 06:29:50 PM CEST 
//


package de.fhkoeln.gm.wba2.phase2.rest.generated;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse für temperatur complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="temperatur">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{}temperaturIstEl" minOccurs="0"/>
 *         &lt;element ref="{}temperaturSollEl" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "temperatur", propOrder = {
    "temperaturIstEl",
    "temperaturSollEl"
})
public class Temperatur {

    protected TemperaturIst temperaturIstEl;
    protected TemperaturSoll temperaturSollEl;

    /**
     * Ruft den Wert der temperaturIstEl-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link TemperaturIst }
     *     
     */
    public TemperaturIst getTemperaturIstEl() {
        return temperaturIstEl;
    }

    /**
     * Legt den Wert der temperaturIstEl-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link TemperaturIst }
     *     
     */
    public void setTemperaturIstEl(TemperaturIst value) {
        this.temperaturIstEl = value;
    }

    /**
     * Ruft den Wert der temperaturSollEl-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link TemperaturSoll }
     *     
     */
    public TemperaturSoll getTemperaturSollEl() {
        return temperaturSollEl;
    }

    /**
     * Legt den Wert der temperaturSollEl-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link TemperaturSoll }
     *     
     */
    public void setTemperaturSollEl(TemperaturSoll value) {
        this.temperaturSollEl = value;
    }

}

//
// Diese Datei wurde mit der JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.6 generiert 
// Siehe <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Änderungen an dieser Datei gehen bei einer Neukompilierung des Quellschemas verloren. 
// Generiert: 2013.06.03 um 05:00:30 PM CEST 
//


package de.fhkoeln.gm.wba2.phase2.jersey.generated;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse für heizung complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="heizung">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="info" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element ref="{}heizungIstEl" minOccurs="0"/>
 *         &lt;element ref="{}heizungSollEl" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "heizung", propOrder = {
    "info",
    "heizungIstEl",
    "heizungSollEl"
})
public class Heizung {

    @XmlElement(required = true)
    protected String info;
    protected HeizungIst heizungIstEl;
    protected HeizungSoll heizungSollEl;

    /**
     * Ruft den Wert der info-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInfo() {
        return info;
    }

    /**
     * Legt den Wert der info-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInfo(String value) {
        this.info = value;
    }

    /**
     * Ruft den Wert der heizungIstEl-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link HeizungIst }
     *     
     */
    public HeizungIst getHeizungIstEl() {
        return heizungIstEl;
    }

    /**
     * Legt den Wert der heizungIstEl-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link HeizungIst }
     *     
     */
    public void setHeizungIstEl(HeizungIst value) {
        this.heizungIstEl = value;
    }

    /**
     * Ruft den Wert der heizungSollEl-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link HeizungSoll }
     *     
     */
    public HeizungSoll getHeizungSollEl() {
        return heizungSollEl;
    }

    /**
     * Legt den Wert der heizungSollEl-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link HeizungSoll }
     *     
     */
    public void setHeizungSollEl(HeizungSoll value) {
        this.heizungSollEl = value;
    }

}

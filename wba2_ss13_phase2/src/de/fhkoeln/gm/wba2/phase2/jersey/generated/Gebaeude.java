//
// Diese Datei wurde mit der JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.6 generiert 
// Siehe <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Änderungen an dieser Datei gehen bei einer Neukompilierung des Quellschemas verloren. 
// Generiert: 2013.06.03 um 05:00:30 PM CEST 
//


package de.fhkoeln.gm.wba2.phase2.jersey.generated;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse für gebaeude complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="gebaeude">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{}klingelEl" minOccurs="0"/>
 *         &lt;element ref="{}energieEl" minOccurs="0"/>
 *         &lt;element ref="{}heizungEl" minOccurs="0"/>
 *         &lt;element ref="{}kamerasEl" minOccurs="0"/>
 *         &lt;element ref="{}etagenEl" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "gebaeude", propOrder = {
    "klingelEl",
    "energieEl",
    "heizungEl",
    "kamerasEl",
    "etagenEl"
})
public class Gebaeude {

    protected Klingel klingelEl;
    protected Energie energieEl;
    protected Heizung heizungEl;
    protected Kameras kamerasEl;
    protected Etagen etagenEl;

    /**
     * Ruft den Wert der klingelEl-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Klingel }
     *     
     */
    public Klingel getKlingelEl() {
        return klingelEl;
    }

    /**
     * Legt den Wert der klingelEl-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Klingel }
     *     
     */
    public void setKlingelEl(Klingel value) {
        this.klingelEl = value;
    }

    /**
     * Ruft den Wert der energieEl-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Energie }
     *     
     */
    public Energie getEnergieEl() {
        return energieEl;
    }

    /**
     * Legt den Wert der energieEl-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Energie }
     *     
     */
    public void setEnergieEl(Energie value) {
        this.energieEl = value;
    }

    /**
     * Ruft den Wert der heizungEl-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Heizung }
     *     
     */
    public Heizung getHeizungEl() {
        return heizungEl;
    }

    /**
     * Legt den Wert der heizungEl-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Heizung }
     *     
     */
    public void setHeizungEl(Heizung value) {
        this.heizungEl = value;
    }

    /**
     * Ruft den Wert der kamerasEl-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Kameras }
     *     
     */
    public Kameras getKamerasEl() {
        return kamerasEl;
    }

    /**
     * Legt den Wert der kamerasEl-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Kameras }
     *     
     */
    public void setKamerasEl(Kameras value) {
        this.kamerasEl = value;
    }

    /**
     * Ruft den Wert der etagenEl-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Etagen }
     *     
     */
    public Etagen getEtagenEl() {
        return etagenEl;
    }

    /**
     * Legt den Wert der etagenEl-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Etagen }
     *     
     */
    public void setEtagenEl(Etagen value) {
        this.etagenEl = value;
    }

}

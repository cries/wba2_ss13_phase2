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
 * <p>Java-Klasse für gebaeude complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="gebaeude">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
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
    "etagenEl"
})
public class Gebaeude {

    protected Etagen etagenEl;

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

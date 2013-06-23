//
// Diese Datei wurde mit der JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.6 generiert 
// Siehe <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Änderungen an dieser Datei gehen bei einer Neukompilierung des Quellschemas verloren. 
// Generiert: 2013.06.23 um 06:29:50 PM CEST 
//


package de.fhkoeln.gm.wba2.phase2.rest.generated;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse für raum complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="raum">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="info" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element ref="{}feuchtigkeitEl" minOccurs="0"/>
 *         &lt;element ref="{}energieRaumEl" minOccurs="0"/>
 *         &lt;element ref="{}temperaturEl" minOccurs="0"/>
 *         &lt;element ref="{}lichterEl" minOccurs="0"/>
 *         &lt;element ref="{}verschattungenEl" minOccurs="0"/>
 *         &lt;element ref="{}steckdosenEl" minOccurs="0"/>
 *         &lt;element ref="{}kontakteEl" minOccurs="0"/>
 *         &lt;element ref="{}bewegungenEl" minOccurs="0"/>
 *         &lt;element ref="{}feuermelderEl" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}positiveInteger" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "raum", propOrder = {
    "info",
    "feuchtigkeitEl",
    "energieRaumEl",
    "temperaturEl",
    "lichterEl",
    "verschattungenEl",
    "steckdosenEl",
    "kontakteEl",
    "bewegungenEl",
    "feuermelderEl"
})
public class Raum {

    @XmlElement(required = true)
    protected String info;
    protected Feuchtigkeit feuchtigkeitEl;
    protected EnergieRaum energieRaumEl;
    protected Temperatur temperaturEl;
    protected Lichter lichterEl;
    protected Verschattungen verschattungenEl;
    protected Steckdosen steckdosenEl;
    protected Kontakte kontakteEl;
    protected Bewegungen bewegungenEl;
    protected Feuermelder feuermelderEl;
    @XmlAttribute(name = "id")
    @XmlSchemaType(name = "positiveInteger")
    protected BigInteger id;

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
     * Ruft den Wert der feuchtigkeitEl-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Feuchtigkeit }
     *     
     */
    public Feuchtigkeit getFeuchtigkeitEl() {
        return feuchtigkeitEl;
    }

    /**
     * Legt den Wert der feuchtigkeitEl-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Feuchtigkeit }
     *     
     */
    public void setFeuchtigkeitEl(Feuchtigkeit value) {
        this.feuchtigkeitEl = value;
    }

    /**
     * Ruft den Wert der energieRaumEl-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link EnergieRaum }
     *     
     */
    public EnergieRaum getEnergieRaumEl() {
        return energieRaumEl;
    }

    /**
     * Legt den Wert der energieRaumEl-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link EnergieRaum }
     *     
     */
    public void setEnergieRaumEl(EnergieRaum value) {
        this.energieRaumEl = value;
    }

    /**
     * Ruft den Wert der temperaturEl-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Temperatur }
     *     
     */
    public Temperatur getTemperaturEl() {
        return temperaturEl;
    }

    /**
     * Legt den Wert der temperaturEl-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Temperatur }
     *     
     */
    public void setTemperaturEl(Temperatur value) {
        this.temperaturEl = value;
    }

    /**
     * Ruft den Wert der lichterEl-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Lichter }
     *     
     */
    public Lichter getLichterEl() {
        return lichterEl;
    }

    /**
     * Legt den Wert der lichterEl-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Lichter }
     *     
     */
    public void setLichterEl(Lichter value) {
        this.lichterEl = value;
    }

    /**
     * Ruft den Wert der verschattungenEl-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Verschattungen }
     *     
     */
    public Verschattungen getVerschattungenEl() {
        return verschattungenEl;
    }

    /**
     * Legt den Wert der verschattungenEl-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Verschattungen }
     *     
     */
    public void setVerschattungenEl(Verschattungen value) {
        this.verschattungenEl = value;
    }

    /**
     * Ruft den Wert der steckdosenEl-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Steckdosen }
     *     
     */
    public Steckdosen getSteckdosenEl() {
        return steckdosenEl;
    }

    /**
     * Legt den Wert der steckdosenEl-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Steckdosen }
     *     
     */
    public void setSteckdosenEl(Steckdosen value) {
        this.steckdosenEl = value;
    }

    /**
     * Ruft den Wert der kontakteEl-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Kontakte }
     *     
     */
    public Kontakte getKontakteEl() {
        return kontakteEl;
    }

    /**
     * Legt den Wert der kontakteEl-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Kontakte }
     *     
     */
    public void setKontakteEl(Kontakte value) {
        this.kontakteEl = value;
    }

    /**
     * Ruft den Wert der bewegungenEl-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Bewegungen }
     *     
     */
    public Bewegungen getBewegungenEl() {
        return bewegungenEl;
    }

    /**
     * Legt den Wert der bewegungenEl-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Bewegungen }
     *     
     */
    public void setBewegungenEl(Bewegungen value) {
        this.bewegungenEl = value;
    }

    /**
     * Ruft den Wert der feuermelderEl-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Feuermelder }
     *     
     */
    public Feuermelder getFeuermelderEl() {
        return feuermelderEl;
    }

    /**
     * Legt den Wert der feuermelderEl-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Feuermelder }
     *     
     */
    public void setFeuermelderEl(Feuermelder value) {
        this.feuermelderEl = value;
    }

    /**
     * Ruft den Wert der id-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getId() {
        return id;
    }

    /**
     * Legt den Wert der id-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setId(BigInteger value) {
        this.id = value;
    }

}

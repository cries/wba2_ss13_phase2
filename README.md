Fachhochschule Köln Campus Gummersbach  
Fakultät für Informatik und Ingenieurwissenschaften

#Web-basierte Anwendungen 2 - Phase 2

## Inhalt

1. [Beschreibung](#smart-buildings---beschreibung)
2. [Szenario](#szenario)  
  2.1 [Energieüberwachung](#energieberwachung)  
  2.2 [Alarmfunktion](#alarmfunktion)  
  2.3 [Steuerung](#steuerung)  
3. [Funktionen](#funktionen)
4. [Rollen](#rollen)
5. [Meilensteine](#meilensteine)  
  5.1 [Kommunikationsabläufe und Interaktionen](#m0-kommunikationsablufe-und-interaktionen)  
  5.2 [Projektspezifisches XML Schema](#m1-projektspezifisches-xml-schema)  
  5.3 [Ressourcen und die Semantik der HTTP-Operationen](#m2-ressourcen-und-die-semantik-der-http-operationen)  
  5.4 [RESTful Webservice](#m3-restful-webservice)  
  5.5 [Konzeption asynchrone Kommunikation](#m4-konzeption-asynchrone-kommunikation)  
  5.6 [XMPP - Client](#m5-xmpp---client)  
  5.7 [Client - Entwicklung](#m6-client---entwicklung)  
  5.8 [Endabgabe](#endabgabe)
6. [Projekttagebuch](#projekttagebuch)

## Smart Buildings - Beschreibung

Entwicklung eines Systems zur Abfrage, Steuerung und Abonnierung von Gebäude-spezifischen Daten & Funktionen. 
Dabei wird auf REST & Publish/Subscribe gesetzt.

Die oben genannten Daten beziehen sich dabei auf, von diversen Sensoren, erfasste Werte. Diese Sensoren werden in zwei Kategorien gegliedert:  

* Sensoren die zeit-diskret eine bestimmte Art von Wert erfassen und synchron abgefragt werden können (Stromverbrauch, Temperatur, Luftfeuchte, CO2-Sensoren) 
* Sensoren die bei einer Veränderung eines Zustandes eine asynchrone Meldung auslösen (Feuermelder, Türklingel, Bewegungsmelder, Fenster-/ Türkontake) Diese verschiedenen Meldungs-Typen werden einzelnen Topics zugeordnet die von den Benutzern abonniert werden können.

Durch die Verwendung beider Sensortypen ergibt sich ein System zur Überwachung der Energiebilanz, sowie zur Überwachung der Sicherheit eines Gebäudes. 

Zusätzlich zu dieser Überwachung wird einem Benutzer die Möglichkeit gegeben aktiv die Funktionen des System zu steuern.
Z.B.: Tür öffnen, Heizungs Soll-Wert einstellen, Licht ein-/ausschalten bzw. dimmen, Verschattung steuern, Zeitabhängige Szenen erstellen und abrufen. (Szenen -> vordefinierte Sammlung von Zuständen für Funktionen die zeit-abhängig oder zeit-unabhängig abgerufen werden können)

Über eine mobile Anwendung bzw. Client-Software für stationäre Rechner, wird gewährleistet dass ein Benutzer diese Überwachung bzw. Steuerung auch aus der Ferne tätigen kann. Zusätzlich können Profile mit verschiedenen Zugriffsrechten angelegt werden.

## Szenario
[nach oben](#inhalt)

### Energieüberwachung:
Dieses Sytem kann z.B. Anwendung in einem Bürogebäude finden. Man nehme das Beispiel eines Chefs der versucht die Energiebilanz seiner Gesellschaft zu verbessern. Durch die verschiedenen Sensoren ist es möglich eine Statistik über den Energieverbrauch zu erstellen. Anhand dieser Statistik können Schwachstellen erkannt, und mit Hilfe des Systems ausgeschaltet werden. 
Die Schwachstellen könnten z.B. eine ungünstig eingestellte Heizung (Tag-/Nachttemperatur), Geräte mit einem sehr hohen Energieverbrauch, schlechte Belüftung oder ungünstige Verschattung von Räumen sein.

### Alarmfunktion:
Die zweite Funktion ist die Überwachung des Gebäudes bezogen auf seine Sicherheit. Eine Sicherheitsfirma oder beispielsweise die Polizei benutzt die Anwendung um z.B. Bewegungsmelder und Tür-/Fensterkontakte zu überwachen. Wird ein Sensor/Melder ausgelöst so wird eine Meldung abgesetzt um über diese Veränderung zu berichten. So kann sofort auf einen möglichen Einbruch reagiert werden.
Weiter könnte z.B. die Feuerwehr auf auslösende Feuermelder reagieren und möglichst schnell einen entstandenen Brand bekämpfen.

### Steuerung:

**TODO**

[nach oben](#inhalt)

## Funktionen
[nach oben](#inhalt)

**TODO**: ergänzen

### REST (synchron)

* Abfrage von Energiedaten (aktueller Energieverbrauch)
* Abfrage von Innentemperatur / Luftfeuchte
* Abfrage von CO2-Sensoren
* Abfrage von Überwachungskamera (Kamerabilder)

### Publish / Subscribe (asynchron)

* Meldung bei Betätigung der Türklingel
* Meldung von Bewegungen (Bewegungsmelder)
* Meldung von Tür-, Fensterkontakten
* Meldung von Feuermeldern / Gasmelder


## Rollen
[nach oben](#inhalt)

**TODO**: ergänzen

### REST (synchron)

* **Chef**        -> Vollzugriff
* **Mitarbeiter** -> Teilzugriff
* **Hausmeister** -> Vollzugriff

### Publish / Subscribe (asynchron)

* **Chef**              -> Vollzugriff
* **Hausmeister**       -> Vollzugriff
* **Polizei**           -> Teilzugriff
* **Feuerwehr**         -> Teilzugriff
* **Sicherheitsfirma**  -> Teilzugriff

**Chef**  
- Bewegungsmelder
- Tür und Fensterkontakte
- Feuermelder 
- Türklingel

**Hausmeister**
- Bewegungsmelder
- Tür und Fensterkontakte
- Feuermelder
- Türklingel

**Polizei**
- Bewegungsmelder
- Tür und Fensterkontakte

**Sicherheitsfirma**
- Bewegungsmelder
- Tür und Fensterkontakte

**Feuerwehr**
- Feuermelder

## Meilensteine
[nach oben](#inhalt)

### M0 Kommunikationsabläufe und Interaktionen 
[nach oben](#inhalt)

22.04.2013

**TODO**: Kommunikations-/Sequenzdiagramm

Mögliches Sequenzdiagramm mit angebundener mySQL-Datenbank (optional)

![GET Anfrage mit mySQL-DB](https://github.com/cries/wba2_ss13_phase2/blob/master/wba2_ss13_phase2/pics/get_request.png?raw=true)

### M1 Projektspezifisches XML Schema  
[nach oben](#inhalt)

06.05.2013

**XML-Schema**

Das XML-Schema ist modular aufgebaut. Für jede Ressource wird ein Complex-Type verwendet, da diese unterschiedliche Informationen bzw.
weitere Complex-Typen enthalten können. Die gewählte Verschachtelung von Etagen & Räumen wird auch hier angewendet.
Die Listen werden über den Typennamen in der Mehrzahl dargestellt. z.B. `etagen` ist eine Liste von Etagen, welche vom Typ `Etage` sind.
Für die Elemente einer Liste muss zusätzlich noch eine ID definiert werden damit diese eindeutig zuordenbar sind.

Der Umstand dass in diesem System einzelne Ressourcen abgefragt werden können, erfordert diesen Modularen Aufbau ohne explizites Root-Element.
Dieses Schema kann also XML-Dokumente mit unterschiedlichen Root-Elementen validieren.  

```
<?xml version="1.0"?>
<xsd:schema xmlns:xsd="http://www.w3.org/2001/XMLSchema">

<xsd:element name="gebaeudeEl" type="gebaeude" />
<xsd:element name="klingelEl" type="klingel" />
<xsd:element name="energieEl" type="energie" />
<xsd:element name="heizungEl" type="heizung" />
<xsd:element name="heizungIstEl" type="heizungIst" />
<xsd:element name="heizungSollEl" type="heizungSoll" />
<xsd:element name="kamerasEl" type="kameras" />
<xsd:element name="kameraEl" type="kamera" />
<xsd:element name="etagenEl" type="etagen" />
<xsd:element name="etageEl" type="etage" />
<xsd:element name="raeumeEl" type="raeume" />
<xsd:element name="raumEl" type="raum" />
<xsd:element name="feuchtigkeitEl" type="feuchtigkeit" />
<xsd:element name="energieRaumEl" type="energieRaum" />
<xsd:element name="temperaturEl" type="temperatur" />
<xsd:element name="temperaturIstEl" type="temperaturIst" />
<xsd:element name="temperaturSollEl" type="temperaturSoll" />
<xsd:element name="lichterEl" type="lichter" />
<xsd:element name="lichtEl" type="licht" />
<xsd:element name="verschattungenEl" type="verschattungen" />
<xsd:element name="verschattungEl" type="verschattung" />
<xsd:element name="steckdosenEl" type="steckdosen" />
<xsd:element name="steckdoseEl" type="steckdose" />
<xsd:element name="kontakteEl" type="kontakte" />
<xsd:element name="kontaktEl" type="kontakt" />
<xsd:element name="bewegungenEl" type="bewegungen" />
<xsd:element name="bewegungEl" type="bewegung" />
<xsd:element name="feuermelderEl" type="feuermelder" />
<xsd:element name="feuermeldEl" type="feuermeld" />

<xsd:element name="id" type="xsd:int" />
<xsd:element name="info" type="xsd:string" />

<xsd:element name="wert" type="xsd:decimal" />
<xsd:element name="einheit" type="xsd:string" />

<xsd:element name="zustand" type="xsd:boolean" />

<xsd:element name="kameraBild" type="xsd:anyURI" />

<xsd:complexType name="gebaeude">
  <xsd:sequence>
		<xsd:element ref="klingelEl" minOccurs="0"/>
		<xsd:element ref="energieEl" minOccurs="0"/>
		<xsd:element ref="heizungEl" minOccurs="0"/>
		<xsd:element ref="kamerasEl" minOccurs="0"/>
		<xsd:element ref="etagenEl" minOccurs="0"/>
	</xsd:sequence>
</xsd:complexType>

<xsd:complexType name="klingel">
	<xsd:sequence>
		<xsd:element ref="zustand" />
	</xsd:sequence>
</xsd:complexType>

<xsd:complexType name="energie">
	<xsd:sequence>
		<xsd:element ref="id" />
		<xsd:element ref="info" />
		<xsd:element ref="wert" />
		<xsd:element ref="einheit" />
	</xsd:sequence>
</xsd:complexType>

<xsd:complexType name="heizung">
	<xsd:sequence>
		<xsd:element ref="info" />
		<xsd:element ref="heizungIstEl" minOccurs="0"/>
		<xsd:element ref="heizungSollEl" minOccurs="0"/>
	</xsd:sequence>
</xsd:complexType>

<xsd:complexType name="heizungIst">
	<xsd:sequence>
		<xsd:element ref="wert" />
		<xsd:element ref="einheit" />
	</xsd:sequence>
</xsd:complexType>

<xsd:complexType name="heizungSoll">
	<xsd:sequence>
		<xsd:element ref="wert" />
		<xsd:element ref="einheit" />
	</xsd:sequence>
</xsd:complexType>

<xsd:complexType name="kameras">
	<xsd:sequence>
		<xsd:element ref="kameraEl" minOccurs="0" maxOccurs="unbounded"/>
	</xsd:sequence>
</xsd:complexType>

<xsd:complexType name="kamera">
	<xsd:sequence>
		<xsd:element ref="id" />
		<xsd:element ref="info" />
		<xsd:element ref="zustand" minOccurs="0"/>
		<xsd:element ref="kameraBild" minOccurs="0"/>
	</xsd:sequence>
</xsd:complexType>

<xsd:complexType name="etagen">
	<xsd:sequence>
		<xsd:element ref="etageEl" minOccurs="0" maxOccurs="unbounded"/>
	</xsd:sequence>
</xsd:complexType>

<xsd:complexType name="etage">
	<xsd:sequence>
		<xsd:element ref="id" />
		<xsd:element ref="info" />
		<xsd:element ref="raeumeEl" minOccurs="0"/>
	</xsd:sequence>
</xsd:complexType>

<xsd:complexType name="raeume">
	<xsd:sequence>
		<xsd:element ref="raumEl" minOccurs="0" maxOccurs="unbounded"/>
	</xsd:sequence>
</xsd:complexType>

<xsd:complexType name="raum">
	<xsd:sequence>
		<xsd:element ref="id" />
		<xsd:element ref="info" />
		<xsd:element ref="feuchtigkeitEl" minOccurs="0"/>
		<xsd:element ref="energieRaumEl" minOccurs="0"/>
		<xsd:element ref="temperaturEl" minOccurs="0"/>
		<xsd:element ref="lichterEl" minOccurs="0"/>
		<xsd:element ref="verschattungenEl" minOccurs="0"/>
		<xsd:element ref="steckdosenEl" minOccurs="0"/>
		<xsd:element ref="kontakteEl" minOccurs="0"/>
		<xsd:element ref="bewegungenEl" minOccurs="0"/>
		<xsd:element ref="feuermelderEl" minOccurs="0"/>
	</xsd:sequence>
</xsd:complexType>

<xsd:complexType name="feuchtigkeit">
	<xsd:sequence>
		<xsd:element ref="wert" />
		<xsd:element ref="einheit" />
	</xsd:sequence>
</xsd:complexType>

<xsd:complexType name="energieRaum">
	<xsd:sequence>
		<xsd:element ref="wert" />
		<xsd:element ref="einheit" />
	</xsd:sequence>
</xsd:complexType>

<xsd:complexType name="temperatur">
	<xsd:sequence>
		<xsd:element ref="temperaturIstEl" minOccurs="0"/>
		<xsd:element ref="temperaturSollEl" minOccurs="0"/>
	</xsd:sequence>
</xsd:complexType>

<xsd:complexType name="temperaturIst">
	<xsd:sequence>
		<xsd:element ref="wert" />
		<xsd:element ref="einheit" />
	</xsd:sequence>
</xsd:complexType>

<xsd:complexType name="temperaturSoll">
	<xsd:sequence>
		<xsd:element ref="wert" />
		<xsd:element ref="einheit" />
	</xsd:sequence>
</xsd:complexType>

<xsd:complexType name="lichter">
	<xsd:sequence>
		<xsd:element ref="lichtEl" minOccurs="0" maxOccurs="unbounded"/>
	</xsd:sequence>
</xsd:complexType>

<xsd:complexType name="licht">
	<xsd:sequence>
		<xsd:element ref="id" />
		<xsd:element ref="info" />
		<xsd:element ref="zustand" />
	</xsd:sequence>
</xsd:complexType>

<xsd:complexType name="verschattungen">
	<xsd:sequence>
		<xsd:element ref="verschattungEl" minOccurs="0" maxOccurs="unbounded"/>
	</xsd:sequence>
</xsd:complexType>

<xsd:complexType name="verschattung">
	<xsd:sequence>
		<xsd:element ref="id" />
		<xsd:element ref="info" />
		<xsd:element ref="wert" />
	</xsd:sequence>
</xsd:complexType>

<xsd:complexType name="steckdosen">
	<xsd:sequence>
		<xsd:element ref="steckdoseEl" minOccurs="0" maxOccurs="unbounded"/>
	</xsd:sequence>
</xsd:complexType>

<xsd:complexType name="steckdose">
	<xsd:sequence>
		<xsd:element ref="id" />
		<xsd:element ref="info" />
		<xsd:element ref="zustand" />
	</xsd:sequence>
</xsd:complexType>

<xsd:complexType name="kontakte">
	<xsd:sequence>
		<xsd:element ref="kontaktEl" minOccurs="0" maxOccurs="unbounded"/>
	</xsd:sequence>
</xsd:complexType>

<xsd:complexType name="kontakt">
	<xsd:sequence>
		<xsd:element ref="id" />
		<xsd:element ref="info" />
		<xsd:element ref="zustand" />
	</xsd:sequence>
</xsd:complexType>

<xsd:complexType name="bewegungen">
	<xsd:sequence>
		<xsd:element ref="bewegungEl" minOccurs="0" maxOccurs="unbounded"/>
	</xsd:sequence>
</xsd:complexType>

<xsd:complexType name="bewegung">
	<xsd:sequence>
		<xsd:element ref="id" />
		<xsd:element ref="info" />
		<xsd:element ref="zustand" />
	</xsd:sequence>
</xsd:complexType>

<xsd:complexType name="feuermelder">
	<xsd:sequence>
		<xsd:element ref="feuermeldEl" minOccurs="0" maxOccurs="unbounded"/>
	</xsd:sequence>
</xsd:complexType>

<xsd:complexType name="feuermeld">
	<xsd:sequence>
		<xsd:element ref="id" />
		<xsd:element ref="info" />
		<xsd:element ref="zustand" />
	</xsd:sequence>
</xsd:complexType>

</xsd:schema>
```

### M2 Ressourcen und die Semantik der HTTP-Operationen  
[nach oben](#inhalt)

06.05.2013

**REST-Hierarchie**

Die entwickelte REST-Hierarchie und die verfügbaren Ressourcen wurden stark an den realen Aufbau eines Gebäudes angelehnt.
Jedes Gebäude ist in Etagen unterteilt, die wiederrum in einzelne Räume aufgeteilt sind. Für jeden Raum können Sensoren & Aktoren 
hinzugefügt werden. Daraus ergibt sich folgende Struktur `http://host:port/etage/<id>/raum/<id>/<funktion>/...` Die jeweiligen Etagen bzw. Räume
werden über die `id` identifiziert. Es gibt zusätzlich noch Ressourcen die unabhängig von Räumen sind bzw. ihrer Räumlichen Lage sind.
Diese werden als alleinstehende Ressourcen angeboten. Darunter fallen z.B die Zentralheizung, Überwachungskameras und
den globalen Energieverbrauch. Die einzelnen Kameras sind wiederum über `id` ansprechbar.  
Wird eine Ressource ohne eine bestimmte id angesprochen z.B `GET /etage` wird eine Liste mit allen in der Ressource befindlichen Unterressourcen
zurückgegeben, in diesem Fall eine Liste aller Etagen. Für Räume gilt das gleiche Prinzip `GET /etage/1/raum` holt eine Liste aller Räume
auf Etage 1. (??? ID String/Integer ???) 
Ressourcen welche nicht über eine `id` verfügen stellen somit keine Listen dar. Beispiel `GET /etage/1/raum/2/temperatur` gibt einen Datensatz zurück.
Eine Besonderheit stellt die Unterteilung einer Heizungs-Ressource dar. Dort findet die beiden Ressourcen `Soll` & `Ist`. 
Dies war nötig, da die Heizung auf einen bestimmten Soll-Wert eingestellt werden kann, der Ist-Wert ändert sich aber nur langsam. Um beide
Zustände darstellen zu können wurden daraus 2 Ressourcen gebildet. Der Soll-Wert kann über `PUT /etage/1/raum/3/temperatur/soll` gesetzt werden.


![GET Anfrage mit mySQL-DB](https://github.com/cries/wba2_ss13_phase2/blob/master/wba2_ss13_phase2/pics/rest_hierarchie_1.png?raw=true)

HTTP-Operationen:

| | GET | PUT | POST | DELETE |
| :----- | :----------: | :---------: | :--------: | :--------: |
| /etage | x | ? | x | x |
| /etage/&lt;id> | x | ? | x | x |
| /etage/&lt;id&gt;/raum | x | ? | x | x |
| /etage/&lt;id&gt;/raum/&lt;id> | x | ? | x | x |
| /etage/&lt;id&gt;/raum/&lt;id&gt;/feuchtigkeit | x | | x | x |
| /etage/&lt;id&gt;/raum/&lt;id&gt;/energie | x | | x | x |
| /etage/&lt;id&gt;/raum/&lt;id&gt;/temperatur | x | | x | x |
| /etage/&lt;id&gt;/raum/&lt;id&gt;/temperatur/soll | x | x | x | x |
| /etage/&lt;id&gt;/raum/&lt;id&gt;/temperatur/ist | x | | x | x |
| /etage/&lt;id&gt;/raum/&lt;id&gt;/licht | x | | x | x |
| /etage/&lt;id&gt;/raum/&lt;id&gt;/licht/&lt;id> | x | x | x | x |
| /etage/&lt;id&gt;/raum/&lt;id&gt;/verschattung | x | | x | x |
| /etage/&lt;id&gt;/raum/&lt;id&gt;/verschattung/&lt;id> | x | x | x | x |
| /etage/&lt;id&gt;/raum/&lt;id&gt;/steckdose |x | | x | x |
| /etage/&lt;id&gt;/raum/&lt;id&gt;/steckdose/&lt;id> | x | x | x | x |
| | GET | PUT | POST | DELETE |
| /heizung | x | | x | x |
| /heizung/ist | x | | x | x |
| /heizung/soll | x | x | x | x |
| | GET | PUT | POST | DELETE |
| /kamera | x | | x | x |
| /kamera/&lt;id> | x | x | x | x |
| /energie | x | | x | x |

**Raumspezifische Ressourcen die man lesen(GET), löschen(DELETE), erstellen(POST) kann wären**

- Feuchtigkeit
- Energie
- Temperatur

**Raumspezifische Ressourcen auf die man lesen(GET), löschen(DELETE), erstellen(POST) und schreiben(PUT) kann wären**

- Temperatur
- Licht
- Verschattung
- Steckdosen

**Gebäudespezifische Ressourcen die man lesen(GET), löschen(DELETE), erstellen(POST) kann wären**

- Energie

**Gebäudespezifische Ressourcen die man lesen(GET), löschen(DELETE), erstellen(POST) und schreiben(PUT) kann wären**

- Kamera ??
- Heizung Ist-Soll ??

[nach oben](#inhalt)

### M3 RESTful Webservice  
[nach oben](#inhalt)

13.05.2013

### M4 Konzeption asynchrone Kommunikation  
[nach oben](#inhalt)

03.06.2013

### M5 XMPP - Client  
[nach oben](#inhalt)

03.06.2013

### M6 Client - Entwicklung  
[nach oben](#inhalt)

17.06.2013

### Endabgabe  
[nach oben](#inhalt)

21.06.2013


## Projekttagebuch
[nach oben](#inhalt)

### 15.04.2013

Projektidee festgelegt, erste Ausarbeitung der Funktionen und Rollen

### 20.04.2013

Weiterführende Ausarbeitung der Funktionen, Beschreibung des Szenario

### 21.04.2013

**TODO**: Alle Funktionen genau definieren & 1. Konzeptphase abschließen  
**TODO**: Ausarbeitung von Kommunikations- und Interaktiondiagramm & 2. Konzeptphase abschließen









----------




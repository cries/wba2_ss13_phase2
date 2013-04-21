#Web-basierte Anwendungen 2 - Phase 2

## Inhalt

1. [Szenario](#smart-buildings-szenario)  
  1.1 [Energieüberwachung](#energieberwachung)  
  1.2 [Alarmfunktion](#alarmfunktion)  
  1.3 [Steuerung](#steuerung)  
2. [Funktionen](#funktionen)
3. [Rollen](#rollen)
4. [Meilensteine](#meilensteine)  
  4.1 [Kommunikationsabläufe und Interaktionen](#m0-kommunikationsablufe-und-interaktionen)  
  4.2 [Projektspezifisches XML Schema](#m1-projektspezifisches-xml-schema)  
  4.3 [Ressourcen und die Semantik der HTTP-Operationen](#m2-ressourcen-und-die-semantik-der-http-operationen)  
  4.4 [RESTful Webservice](#m3-restful-webservice)  
  4.5 [Konzeption asynchrone Kommunikation](#m4-konzeption-asynchrone-kommunikation)  
  4.6 [XMPP - Client](#m5-xmpp---client)  
  4.7 [Client - Entwicklung](#m6-client---entwicklung)  
  4.8 [Endabgabe](#endabgabe)
5. [Projekttagebuch](#projekttagebuch)

## Smart Buildings Szenario
[nach oben](#inhalt)

Entwicklung eines Systems zur Abfrage, Steuerung und Abonnierung von Gebäude-spezifischen Daten & Funktionen. 
Dabei wird auf REST & Publish/Subscribe gesetzt.

Die oben genannten Daten beziehen sich dabei auf, von diversen Sensoren, erfasste Werte. Diese Sensoren werden in zwei Kategorien gegliedert:  

* Sensoren die zeit-diskret eine bestimmte Art von Wert erfassen und synchron abgefragt werden können (Stromverbrauch, Temperatur, Luftfeuchte, CO2-Sensoren) 
* Sensoren die bei einer Veränderung eines Zustandes eine asynchrone Meldung auslösen (Feuermelder, Türklingel, Bewegungsmelder, Fenster-/ Türkontake) Diese verschiedenen Meldungs-Typen werden einzelnen Topics zugeordnet die von den Benutzern abonniert werden können.

Durch die Verwendung beider Sensortypen ergibt sich ein System zur Überwachung der Energiebilanz, sowie zur Überwachung der Sicherheit eines Gebäudes. 

Zusätzlich zu dieser Überwachung wird einem Benutzer die Möglichkeit gegeben aktiv die Funktionen des System zu steuern.
Z.B.: Tür öffnen, Heizungs Soll-Wert einstellen, Licht ein-/ausschalten bzw. dimmen, Verschattung steuern, Zeitabhängige Szenen erstellen und abrufen. (Szenen -> vordefinierte Sammlung von Zuständen für Funktionen die zeit-abhängig oder zeit-unabhängig abgerufen werden können)

Über eine mobile Anwendung bzw. Client-Software für stationäre Rechner, wird gewährleistet dass ein Benutzer diese Überwachung bzw. Steuerung auch aus der Ferne tätigen kann. Zusätzlich können Profile mit verschiedenen Zugriffsrechten angelegt werden.

### Energieüberwachung:
Dieses Sytem kann z.B. Anwendung in einem Bürogebäude finden. Man nehme das Beispiel eines Chefs der versucht die Energiebilanz seiner Gesellschaft zu verbessern. Durch die verschiedenen Sensoren ist es möglich eine Statistik über den Energieverbrauch zu erstellen. Anhand dieser Statistik können Schwachstellen erkannt, und mit Hilfe des Systems ausgeschaltet werden. 
Die Schwachstellen könnten z.B. eine ungünstig eingestellte Heizung (Tag-/Nachttemperatur), Geräte mit einem sehr hohen Energieverbrauch, schlechte Belüftung oder ungünstige Verschattung von Räumen sein.

### Alarmfunktion:
Die zweite Funktion ist die Überwachung des Gebäudes bezogen auf seine Sicherheit. Eine Sicherheitsfirma oder beispielsweise die Polizei benutzt die Anwendung um z.B. Bewegungsmelder und Tür-/Fensterkontakte zu überwachen. Wird ein Sensor/Melder ausgelöst so wird eine Meldung abgesetzt um über diese Veränderung zu berichten. So kann sofort auf einen möglichen Einbruch reagiert werden.
Weiter könnte z.B. die Feuerwehr auf auslösende Feuermelder reagieren und möglichst schnell einen entstandenen Brand bekämpfen.

### Steuerung:

TODO

[nach oben](#inhalt)

## Funktionen
[nach oben](#inhalt)

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

## Meilensteine
[nach oben](#inhalt)

### M0 Kommunikationsabläufe und Interaktionen 
[nach oben](#inhalt)

22.04.2013

Mögliches Sequenzdiagramm mit angebunder mySQL-Datenbank (optional)

![GET Anfrage mit mySQL-DB](https://github.com/cries/wba2_ss13_phase2/blob/master/wba2_ss13_phase2/pics/get_request.png?raw=true)

### M1 Projektspezifisches XML Schema  
[nach oben](#inhalt)

06.05.2013

### M2 Ressourcen und die Semantik der HTTP-Operationen  
[nach oben](#inhalt)

06.05.2013

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

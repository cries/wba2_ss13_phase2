#Web-basierte Anwendungen 2 - Phase 2

## Inhalt

1. [Szenario](#smart-buildings-szenario)
2. [Funktionen](#)
3. [Rollen](#)
4. [Meilensteine](#meilensteine)
5. [Projekttagebuch](#projekttagebuch)

## Smart Buildings Szenario
[nach Oben](#inhalt)

Entwicklung eines Systems zur Überwachung von Gebäude-spezifischen Daten mit mobilen Endgeräten. 

Die oben genannten Daten beziehen sich dabei auf, von diversen Sensoren, erfasste Werte. Diese Sensoren werden in zwei Kategorien gegliedert:
Sensoren die zeit-diskret eine bestimmte Art von Wert erfassen (Stromverbrauch, Temperatur, Luftfeuchte, CO2-Sensoren)
Sensoren die bei einer Veränderung eines Zustandes eine Meldung auslösen (Feuermelder, Türklingel, Bewegungsmelder, Fenster-/ Türkontake)

Durch die Verwendung beider Sensortypen ergibt sich ein System zur Überwachung der Energiebilanz, sowie zur Überwachung der Sicherheit eines Gebäudes. 
Über eine mobile Anwendung bzw. Client-Software für stationäre Rechner, wird gewährleistet dass ein Benutzer diese Überwachung auch aus der Ferne tätigen kann.

Zusätzlich zur Überwachung wird einem Benutzer die Möglichkeit gegeben aktiv in das System einzugreifen und somit einzelne Funktionen manuell zu steuern.
Z.B.: Tür öffnen, Heizung Soll-Wert einstellen, Licht aus/einschalten bzw. dimmen, Jalousien steuern, Zeitabhängige Szenen erstellen und abrufen. (Szenen -> vordefinierte Sammlung von Zuständen für Funktionen die zeit-abhängig oder zeit-unabhängig abgerufen werden können)

Dieses Sytem kann z.B. Anwendung in einem Bürogebäude finden. Man nehme das Beispiel eines Chefs der versucht die Energiebilanz seiner Gesellschaft zu verbessern. Durch die verschiedenen Sensoren ist es möglich eine Statistik über den Energieverbrauch zu erstellen. Anhand dieser Statistik können Schwachstellen erkannt, und mit Hilfe des Systems ausgeschaltet werden. 
Die Schwachstellen könnten z.B. eine ungünstig eingestellte Heizung (Tag-/Nachttemperatur), Geräte mit einem sehr hohen Energieverbrauch, schlechte Belüftung oder ungünstige Verschattung von Räumen sein.

Die zweite Funktion ist die Überwachung des Gebäudes bezogen auf seine Sicherheit. Eine Sicherheitsfirma oder beispielsweise die Polizei benutzt die Anwendung um z.B. Bewegungsmelder und Tür-/Fensterkontakte zu überwachen. Wird ein Sensor/Melder ausgelöst so wird eine Meldung abgesetzt um über diese Veränderung zu berichten. 


Über die Client-Software ist es möglich verschiedene Rollen mit verschiedenen Zugriffsrechten zu definieren.
So hätte z.B. der Chef einen Vollzugriff und ein Mitarbeiter nur Zugriff auf verschiedene Funktionen 
des Systems (REST & Pub/Sub).


### REST (synchron)

#### Funktionen:

* Abfrage von Energiedaten (aktueller Energieverbrauch)
* Abfrage von Innentemperatur / Luftfeuchte
* Abfrage von CO2-Sensoren
* Abfrage von Überwachungskamera (Kamerabilder)

#### Rollen:

* **Chef**        -> Vollzugriff
* **Mitarbeiter** -> Teilzugriff
* **Hausmeister** -> Vollzugriff


### Publish / Subscribe (asynchron)

#### Funktionen:

* Meldung bei Betätigung der Türklingel
* Meldung von Bewegungen (Bewegungsmelder)
* Meldung von Tür-, Fensterkontakten
* Meldung von Feuermeldern / Gasmelder

#### Rollen:

* **Chef**              -> Vollzugriff
* **Hausmeister**       -> Vollzugriff
* **Polizei**           -> Teilzugriff
* **Feuerwehr**         -> Teilzugriff
* **Sicherheitsfirma**  -> Teilzugriff

## Meilensteine

### M0 Kommunikationsabläufe und Interaktionen 

22.04.2013

### M1 Projektspezifisches XML Schema

06.05.2013

### M2 Ressourcen und die Semantik der HTTP-Operationen   

06.05.2013

### M3 RESTful Webservice 

13.05.2013

### M4 Konzeption asynchrone Kommunikation

03.06.2013

### M5 XMPP - Client 

03.06.2013

### M6 Client - Entwicklung 

17.06.2013

### Endabgabe

21.06.2013

## Projekttagebuch

### 15.04.2013

Projektidee festgelegt, erste Ausarbeitung der Funktionen und Rollen

### 20.04.2013

Weiterführende Ausarbeitung der Funktionen, Beschreibung des Szenario

### 21.04.2013

TODO: Alle Funktionen genau definieren & 1. Konzeptphase abschließen
TODO: Ausarbeitung von Kommunikations- und Interaktiondiagramm & 2. Konzeptphase abschließen

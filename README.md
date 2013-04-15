Smart Buildings
=================

Entwicklung eines Systems zur Überwachung von Bürogebäude-spezifischen Daten mit mobilen Endgeräten. 

Über die Client-Software ist es möglich verschiedene Rollen mit verschiedenen Zugriffsrechten zu definieren.
So hätte z.B. der Chef einen Vollzugriff und ein Mitarbeiter nur Zugriff auf verschiedene Funktionen 
des Systems (REST & Pub/Sub).




REST (synchron)
---------------

### Funktionen:

* Abfrage von Energiedaten (aktueller Energieverbrauch)
* Abfrage von Innentemperatur / Luftfeuchte
* Abfrage von CO2-Sensoren
* Abfrage von Überwachungskamera (Kamerabilder)

### Rollen:

* Chef        -> Vollzugriff
* Mitarbeiter -> Teilzugriff
* Hausmeister -> Vollzugriff


Publish / Subscribe (asynchron)
--------------------------------

### Funktionen:

* Meldung bei Betätigung der Türklingel
* Meldung von Bewegungen (Bewegungsmelder)
* Meldung von Tür-, Fensterkontakten
* Meldung von Feuermeldern / Gasmelder

### Rollen:

* Chef              -> Vollzugriff
* Hausmeister       -> Vollzugriff
* Polizei           -> Teilzugriff
* Feuerwehr         -> Teilzugriff
* Sicherheitsfirma  -> Teilzugriff

Java-Projekt: Notenplaner
=======================================

* Checken Sie das Repository aus mit
    git clone https://scm.inftech.hs-mannheim.de/scm2/repo/2022/javagb
* Starten Sie Eclipse mit einem beliebigen (am besten neuen) workspace
  NEHMEN SIE *NICHT* *NOT* *NON* *STOP* DIESEN ORDNER ALS WORKSPACE
  
In Eclipse erstellen Sie ein
  *neues* Java-Projekt im Ordner Prgs als Java-Projekt.
Eclipse kriegt dann alles richtig hin.

Sie können alle Aufgaben in diesem einen Projekt Prgs erledigen.
Sie können das in Java in Pakten organisieren
* nur Kleinbuchstaben, keine Ziffern am Anfang, keine Leer-/Sonderzeichen
* Pakete sind Ordner
Sie dürfen auch weitere Java-Projekte in diesem Ordner anlegen, die
dann parallel/neben Prgs liegen.

## Aufgabe
Verwaltung mehrerer Curricula von Studiengängen. 
Die Daten eines Studiengangs halten Sie als Text-Datei, zum Beispiel als CSV1-Datei vor. 
Für jedes Modul, das in die Endnote eingeht, merken Sie sich Name, Kürzel, Bereich, Fachsemester und Credit-Points(CP).

Noten für Module, die zwischen 1,0 und 4,0 in den übli-
chen Abständen liegen, geben Sie über das GUI ein. 

Das Curriculum soll unabhängig von den Einzel-Daten eines Studenten bleiben. 
Beachten Sie, dass keine Note und eine Note große Unterschiede machen. 

## Mindestanforderungen
* Alle Studienfächer unseres Studiengangs mit Wahlfächern.
* Anzeige von:
	+ Studiengang
	+ Anzahl aktueller CP
	+ Gesamtnote, die möglich wäre zu erreichen (min/max)
	+ welche Fächer fehlen
* Filtern und sortieren nach Kriterien
	+ CP
	+ fehlt/fehlt nicht
	+ Semester
	+ weitere z.B. bestanden
* Noten des aktuellen Nutzers in einer Datei abspeichern. Datei muss ladbar sein.
* Übersichtsseite mit ihren Noten
* Menu zum Laden und Speichern eines Notensatz
* Möglichkeit Noten zu löschen, ändern und neu einzugeben

## Extra-Features
Unsere Ideen
+ Progessbar: 
	* Wie viel des Studiums schon geschafft
	* wie viel eines Semesters
+ Ziele setzen: Anzeige welche Noten erreicht werden müssen, um Ziel zu erreichen
+ Notenschnitt eines Semesters
+ Notenverteilung: Welche Noten der Student schon bekommen hat zB. 5x 1,0, 2x2,3, usw... als Balkendiagramm oder ähnliches
+ Dropdownliste beim Laden von Noten, dass nur verfügbare Dateien angezeigt werden.

Ideen von Barth
+ graphischen Gesamtübersicht
+ Trends über Semester
+ Gegenüberstellungen von Bereichen
+ Editor für die Module anlegen, der die CSV-Dateien dann manipulieren kann

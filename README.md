Java-Projekt: Notenplaner
=======================================

* Checken Sie das Repository aus mit
    git clone https://scm.inftech.hs-mannheim.de/scm2/repo/2022/javagb
* Das Projekt kann über den Ordner jfxNotenplaner in Eclipse geöffnet werden. 
* Beispiel Student: jfxNotenplaner/rba.csv
* Beispiel externes Curriculum: jfxNotenplaner/MTM.csv (erstellt mit Editor)


## Aufgabe
Verwaltung mehrerer Curricula von Studiengängen. 
Die Daten eines Studiengangs halten Sie als als CSV-Datei vor. 
Für jedes Modul, das in die Endnote eingeht, merken Sie sich Name, Kürzel, Bereich, Fachsemester und Credit-Points(CP).

Noten für Module, die zwischen 1,0 und 4,0 in den üblichen Abständen liegen, geben Sie über das GUI ein. 

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
+ Progessbar: 
	* Wie viel des Studiums schon geschafft
	* wie viel eines Semesters
+ Notenschnitt eines Semesters
+ Notenverteilung: Welche Noten der Student schon bekommen hat zB. 5x 1,0, 2x2,3, usw... als Balkendiagramm oder ähnliches
+ Dropdownliste beim Laden von Noten, dass nur verfügbare Noten angezeigt werden.
+ graphischen Gesamtübersicht
+ Trends über Semester
+ Gegenüberstellungen von Bereichen
+ Editor für die Module, der die CSV-Dateien dann manipulieren kann

## Hinweise Editor
+ ohne einen Studenten ausgewählt zu haben, kann kein Curriculum editiert werden
+ ohne Student kann nur ein neues Curriculum angelegt werden.
+ um ein neues Curriculum zu erstellen:
	1. Button "Neues Curriculum erstellen" klicken
	2. Daten eingeben
	3. Button "Speichern" klicken
	4. Speicherort wählen
	5. fertig :D

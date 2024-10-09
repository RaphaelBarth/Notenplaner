
# Projekt für das Fach Java 

Schreiben Sie einen Notenplaner mit einem JavaFX-GUI. Es werden mehrere Curricula von Studiengängen verwaltet. Die Daten eines Studiengangs halten Sie als Text-Datei, zum Beispiel als CSV1-Datei vor. Für jedes Modul, das in die Endnote eingeht, merken Sie sich Name, Kürzel, Bereich, Fachsemester und Credit-Points (CP). 
Also zum Beispiel:
```CSV
krz; name; bereich; fachsem; cp
HSP; Höhere Programmiersprachen; Software; 3; 5
DSV; Digitale Signalverarbeitung; Signalverarbeitung; 4; 5
...
```
was Sie passend für Ihren Studiengang ergänzen. Vergessen Sie die Wahlfächer nicht. Noten für Module, die zwischen 1,0 und 4,0 in den üblichen Abständen liegen, geben Sie über das GUI ein. Sie sollen immer sofort sehen wie viel CP Sie schon haben und wie viel noch fehlen; welche Gesamtnote (min/max) noch möglich ist; welche Fächer fehlen; etc. Machen Sie es möglich nach einzelnen Kriterien zu filtern und zu sortieren (nach CP, nach fehlt/hat schon, nach Semester, . . . ). Man kann nur Module anzeigen, die einen Suchstring enthalten. Ihre eigenen Daten sollen dann unter einem Dateinamen abspeicherbar und ladbar sein. Das Curriculum soll unabhängig von den Einzel-Daten bleiben. Es soll auf jeden Fall eine Übersichtsseite mit ihren Noten existieren, einem Menu zum Laden und Speichern eines Notensatz und die Möglichkeit Noten zu löschen, ändern und neu einzugeben. Beachten Sie, dass keine Note und eine Note große Unterschiede machen. Vielleicht haben Sie Spaß an einer graphischen Gesamtübersicht, Trends über Semester oder Gegenüberstellungen von Bereichen etc. Wer dann noch nicht ermattet ist, der kann sich auch einen Editor für die Module anlegen, der die CSV-Dateien dann manipulieren kann.

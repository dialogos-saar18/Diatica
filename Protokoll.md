## Sitzung 1
- 1\. Präsentation: Ludmilla -> Urs 2.
- Implementierung und Funktionsweise von Habitica
- Python dummy Programm zur Demonstration soll Healthpotion string umwandel in HTTP Anfrage an Habitica
- Zwischenziel Daten an DialogOS senden mit send()
- Ziel Client schreiben, weil Plugin nicht sinnvoll:
  - Hauptfeatures vom Client sind Status Anzeige, Items kaufen, Challenge erstellen/erledigen/löschen
  - als erstes Status und Items und dann challenges, weil die meisten Schwierigkeiten möglicherweise dabei liegen (challenge dialogOS friendly gestalten)
## Feedback zu Präsentation 1
- Rucksackproblem, soll Website nicht ersetzen, sondern erweitern;
- Zielsetzung bis 19.12. HP + Exp Status
- 2-5 User Stories mitbringen für nächste Woche 
- Client -> config datei, Plugin bietet Gui und Tabs Vorteile zb. Post, Get, Delete als Tabs implementieren, mit Plugins auch eigenen Reiter, wo man die user credentials eintragen kann 
## Sitzung 2
- send function funktionalität verstanden! :thumbsup:
- langes probieren mit pyhton und der request library 
- Erste HTTPS anfrage an Habitica geschafft und Dialogos damit schlafen gelegt
- Userstories erstellt:
  - [Hey Habitica ich hab noch 30 Minuten Zeit übrig, welche Task kann ich ind er Zeit noch erledigen?
    Du kannst in der Zeit noch deine Vokabeln wiederholen.
    Okay.]   
  - [Habitica was gibt es heute zu tun? 
     Du schwebst gerade in Lebensgefahr soll ich einen Heiltrank kaufen?
     Ja.
     zu spät........ . / Gerettet.]
  - [Habitica wenn ich jetzt nicht die Küche putze, wie viel Schaden nehme ich dadurch? 
     Du bekommst dadurch 10 Schaden wenn du das nicht tust.
     Und wie viel Erfahrung bekomme ich wenn ich die Küche putze?
     Du bekommst dadurch 20 Erfahrungspunkte.]
  - [Habitica geh schlafen!
     In Ordnung, du kannst mich später wecken.]
- Diskussion über Client/Plugin

## Feedback zu Sitzung 2
 - einfacher Dailog mit schlafen und Status-Abfrage 
 - Initiative in einem Dialogsstem möglichst oft beim Benutzer, aber je nach User Story entscheiden
 - Interaktion zwischen Benutzer und System: nächstes Mal sollte jemand anderes das Dialogsystem am besten benutzen können, ohne dass er auf besondere Hürden bekommt. -> Testen am besten von einem nicht Entwickler lassen
 
## Sitzung 3
- entschieden Großteil des PlugIn in Java zu schreiben
- In Grundstrukturen der Java-Programmierung eingearbeitet
- "funktionierendes" PlugIn für Jython als auch Java, wo man die APIs eingeben kann
- auseinandersetzung wie man mit Java Http-Request senden kann

## Sitzung 6
- DialogOS Dialoge mit git Versionskontrollen abspeichern (falls DialogOS Bugs hat)
- Wenn bei graph.LOCAL auf bspw. .GLOBAL gesetzt wird kann man somit global auf diese Zugreifen (DialogOS Variablen)
- Vorschlag kam auf das man in der Groß-Gruppe zusammen Swing erarbeitet

## Feedback 6. Sitzung
- Java Variablen:
  - in Java Variablen mit Kleinbuchstaben anfangen lassen
  - Klassen dagegen in Großbuchstabe
- Resultatverarbeitung:
  - mehrere Ausgabe-Zipfel (-Ports) für die Knoten schreiben
    - nötig wenn mehrere Resultate möglich sind
  - Low-Level 'Mist' als exception werfen lassen
    - falscher API, kein Internet, ...
#An Koller unser Beispiel-Dialog mit der bearbeiteten Variable senden
  - Werte mit Wertvariablen weitergeben (also unsere eigentliche Idee -.-)
  # Statusabfage bis Weihnachten fertigstellen
- Aktuelle DialogOS Version für PlugIn:
  - Jython auskommentieren soll dies beheben
# Nächster Termin am 20.12.18, Donnerstag um 10 Uhr

## Sitzung 7
- Planung eines sauberen Habitica-Dialogs, welcher sich erst beendet wenn es gewünscht wird
- Auseinandersetzung mit dem Task-System von Habitica
- Code-Bereinung (in Funktionen umschreiben, Kommentare einfügen, ...)
- Starke Auseinandersetzung mit GitHub


## Sitzung zum Featureschluss
- ca 3-5 user tests
- je härteres Feedback desto besser
- Feedback geben nach Eingabe
- README fertig schreiben und auf Piazza posten, dass es nun zum test bereit steht
- Wie wollen leute mit meinem System reden

## Sitzung 9
- Eine read.me Datei / Dokumentation zu unserem Programm schreiben
  - GitHub-Wiki eintrag erstellen
- Dynamische Grammatik(en) zu den Tags machen (damit man alle tags verwenden kann)
 (Siehe Kochbuch-Technologie)
 - Grammatik etwas kompakter schreiben
 - Bei der Grammatik den Debugger laufen lassen um zu sehen was schief läuft
 - Abtrennung vom Habitica-Knoten in der Tool-Leiste mittels anderer PlugID(?)
 - Gegenseitige Benutzer-Tests machen
 - Wiki-Eintrag ins Habitica-Wiki (kann man später erledigen, aber bis Ende zum Semesters sollte es erledigt sein)
 

## Präsentation
- höchstens 20 Minuten (hardcut 20 min)
- kurze Erinnerung, was gemacht, was passiert ist, 
  - erzählen was man
    - wollte
    - was man getan hat
    - was und wie man es gelöst hat
    - was offen geblieben ist
    - was 'cool' für die Zukunft wäre (FutureWork - Liste ?)
    - vgl. mit was man versprochen gegen was man erreicht hat
- möglichen Dialog als live demo oder ein demo Video (wenn Video dann mgl. auf YouTube zu stellen als Werbung, um Eltern zu zeigen ...)
- am ende ähnlich wie eine konklusion in einem Paper
- zum Ende des Semesters muss alles fertig sein!!!!
- System muss überleben.

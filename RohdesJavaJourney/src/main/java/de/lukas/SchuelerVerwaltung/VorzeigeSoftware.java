package de.lukas.SchuelerVerwaltung;

import de.lukas.SchuelerVerwaltung.helpers.KlasseCSVHelper;
import de.lukas.SchuelerVerwaltung.helpers.SchoolCSVHelper;
import de.lukas.SchuelerVerwaltung.helpers.SchuelerCSVHelper;
import de.lukas.SchuelerVerwaltung.independatClasses.Klasse;
import de.lukas.SchuelerVerwaltung.independatClasses.Schueler;
import de.lukas.SchuelerVerwaltung.independatClasses.Schule;

import java.util.Set;

/**
 * @author Lukas
 * @version 1.0
 * @category Software
 * @since 1.0
 */
public class VorzeigeSoftware {
    public static void main(String[] args) {
        // Laden der Daten aus CSV-Dateien
        Set<Schule> schulen = SchoolCSVHelper.loadSchools();
        Set<Klasse> klassen = KlasseCSVHelper.loadKlassen(schulen);
        Set<Schueler> schueler = SchuelerCSVHelper.loadSchueler(klassen);

        // Anzeigen der geladenen Daten
        System.out.println("Geladene Daten:");
        System.out.println("Schulen: " + schulen.size());
        System.out.println("Klassen: " + klassen.size());
        System.out.println("Schüler: " + schueler.size());

        // Hinzufügen neuer Daten
        Schule neueSchule = new Schule("Peter-Paul-Cahensly-Schule");
        schulen.add(neueSchule);

        Klasse neueKlasse = new Klasse("5a", neueSchule);
        klassen.add(neueKlasse);

        String schuelerDaten = "Laggis,Keiner,2010-05-15,E-Mail,Laggis_131@icloud.com,spitzname,Cancelcloud";
        Schueler neuerSchueler = Schueler.addSchuelerFromFile(schuelerDaten, neueKlasse);
        schueler.add(neuerSchueler);

        // Anzeigen der neuen Daten
        System.out.println("\nNeuer Schüler:");
        System.out.println("Name: " + neuerSchueler.getName());
        System.out.println("Klasse: " + neuerSchueler.getKlasse().getName());
        System.out.println("Schule: " + neuerSchueler.getKlasse().getSchule().getName());

        // Speichern der aktualisierten Daten in CSV-Dateien
        SchoolCSVHelper.saveSchools(schulen);
        KlasseCSVHelper.saveKlassen(klassen);
        SchuelerCSVHelper.saveSchueler(schueler);

        System.out.println("\nAlle Daten wurden gespeichert.");
    }
}

package de.lukas.SchuelerVerwaltung;

import de.lukas.SchuelerVerwaltung.independatClasses.Klasse;
import de.lukas.SchuelerVerwaltung.independatClasses.KontaktAdresse;
import de.lukas.SchuelerVerwaltung.independatClasses.Schueler;
import de.lukas.SchuelerVerwaltung.independatClasses.Schule;

import java.time.LocalDate;

public class Schuelersoftware {
    public static void main(String[] args) {
        // Create a school
        Schule schule = new Schule("Mustergymnasium");
        System.out.println("Schule erstellt: " + schule.getName());

        // Create classes
        Klasse klasse5a = new Klasse("5a", schule);
        Klasse klasse6b = new Klasse("6b", schule);

        // Create and add students with contact addresses
        Schueler schueler = new Schueler("Mustermann", "Max", klasse5a, LocalDate.of(2010, 5, 15));
        schueler.fuegeKontaktadresseHinzu("E-Mail", "max.mustermann@example.com");
        schueler.fuegeKontaktadresseHinzu("Telefon", "0123456789");
        schule.addSchueler(schueler);

        schueler = new Schueler("Schmidt", "Anna", klasse5a, LocalDate.of(2011, 8, 20));
        schueler.fuegeKontaktadresseHinzu("E-Mail", "anna.schmidt@example.com");
        schule.addSchueler(schueler);

        schueler = new Schueler("Weber", "Tim", klasse6b, LocalDate.of(2012, 3, 10));
        schueler.fuegeKontaktadresseHinzu("E-Mail", "tim.weber@example.com");
        schule.addSchueler(schueler);

        // Print all students and their contact addresses
        System.out.println("\nAlle Schüler und ihre Kontaktadressen:");
        for (Schueler s : new Schueler[]{schule.getSchueler("Mustermann", "Max"),
                schule.getSchueler("Schmidt", "Anna"),
                schule.getSchueler("Weber", "Tim")}) {
            printSchuelerInfo(s);
        }

        // Test removing a contact address
        System.out.println("\nEntferne eine Kontaktadresse von Max:");
        schueler = schule.getSchueler("Mustermann", "Max");
        schueler.removeKontaktaddresse("0123456789");
        printSchuelerInfo(schueler);

        // Test getting all contact addresses of a specific type
        System.out.println("\nAlle E-Mail-Adressen von Max:");
        for (KontaktAdresse adresse : schueler.holeAlleKontaktadressenEinesTyps("E-Mail")) {
            System.out.println(adresse.getWert());
        }

        // Test if a student exists in the school
        System.out.println("\nÜberprüfe, ob Schüler existieren:");
        System.out.println("Max Mustermann existiert: " + schule.checkifSchuelerExists("Mustermann", "Max"));
        System.out.println("Lisa Müller existiert: " + schule.checkifSchuelerExists("Müller", "Lisa"));

        // Get a student from the school
        System.out.println("\nHole einen Schüler aus der Schule:");
        Schueler retrievedStudent = schule.getSchueler("Schmidt", "Anna");
        if (retrievedStudent != null) {
            System.out.println("Gefundener Schüler: " + retrievedStudent.getVorname() + " " + retrievedStudent.getName());
        } else {
            System.out.println("Schüler nicht gefunden.");
        }

        // Remove a student from the school
        System.out.println("\nEntferne einen Schüler aus der Schule:");
        schule.removeSchueler(schule.getSchueler("Weber", "Tim"));
        System.out.println("Tim Weber existiert noch: " + schule.checkifSchuelerExists("Weber", "Tim"));
    }

    private static void printSchuelerInfo(Schueler schueler) {
        System.out.println(schueler.getVorname() + " " + schueler.getName() + " (Klasse " + schueler.getKlasse().getName() + ", Geburtsdatum: " + schueler.getGeburtsdatum() + "):");
        for (KontaktAdresse adresse : schueler.getKontaktAdresse()) {
            System.out.println("  " + adresse.getTyp() + ": " + adresse.getWert());
        }
    }
}
package de.lukas.SchuelerVerwaltung;

public class Schuelersoftware {
    public static void main(String[] args) {
        // Create a school
        Schule schule = new Schule("Mustergymnasium");
        System.out.println("Schule erstellt: " + schule.getName());

        // Create classes
        Klasse klasse5a = new Klasse("5a");
        Klasse klasse6b = new Klasse("6b");

        // Create students
        Schueler max = new Schueler("Mustermann", "Max", klasse5a);
        Schueler anna = new Schueler("Schmidt", "Anna", klasse5a);
        Schueler tim = new Schueler("Weber", "Tim", klasse6b);

        // Add contact addresses to students
        max.fuegeKontaktadresseHinzu("E-Mail", "max.mustermann@example.com");
        max.fuegeKontaktadresseHinzu("Telefon", "0123456789");
        anna.fuegeKontaktadresseHinzu("E-Mail", "anna.schmidt@example.com");
        tim.fuegeKontaktadresseHinzu("E-Mail", "tim.weber@example.com");

        // Add students to school
        schule.addSchueler(max);
        schule.addSchueler(anna);
        schule.addSchueler(tim);

        // Print all students and their contact addresses
        System.out.println("\nAlle Schüler und ihre Kontaktadressen:");
        printSchuelerInfo(max);
        printSchuelerInfo(anna);
        printSchuelerInfo(tim);

        // Test removing a contact address
        System.out.println("\nEntferne eine Kontaktadresse von Max:");
        max.removeKontaktaddresse("0123456789");
        printSchuelerInfo(max);

        // Test getting all contact addresses of a specific type
        System.out.println("\nAlle E-Mail-Adressen von Max:");
        for (KontaktAdresse adresse : max.holeAlleKontaktadressenEinesTyps("E-Mail")) {
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
        schule.removeSchueler(tim);
        System.out.println("Tim Weber existiert noch: " + schule.checkifSchuelerExists("Weber", "Tim"));
    }

    private static void printSchuelerInfo(Schueler schueler) {
        System.out.println(schueler.getVorname() + " " + schueler.getName() + " (Klasse " + schueler.getKlasse().getName() + "):");
        for (KontaktAdresse adresse : schueler.getKontaktAdresse()) {
            System.out.println("  " + adresse.getTyp() + ": " + adresse.getWert());
        }
    }
}
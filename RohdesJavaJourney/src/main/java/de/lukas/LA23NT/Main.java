package de.lukas.LA23NT;

import java.util.Date;

public class Main {
    public static void main(String[] args) {
        // Create a Messwertsatz instance
        Messwertsatz messwertsatz = new Messwertsatz("Temperature", "Celsius", new Mitarbeiter("John", "Doe", "JD"));

        // Add some Messwert objects
        messwertsatz.hinzufuegenWert(23.5, new Date());
        messwertsatz.hinzufuegenWert(19.2, new Date());
        messwertsatz.hinzufuegenWert(25.8, new Date());
        messwertsatz.hinzufuegenWert(21.4, new Date());

        // Sort the Messwert objects by their value
        messwertsatz.sortiereWerteNachMW();

        // Print the sorted Messwert objects
        for (Messwert messwert : messwertsatz.getWerte()) {
            System.out.println("Wert: " + messwert.getWert() + ", Messzeit: " + messwert.getMesszeit());
        }
    }
}
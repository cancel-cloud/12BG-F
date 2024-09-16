package de.lukas.SchuelerVerwaltung.helpers;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;

import de.lukas.SchuelerVerwaltung.Klasse;
import de.lukas.SchuelerVerwaltung.Schule;

public class KlasseCSVHelper {
    private static final String CSV_FILE = "classes.csv";

    public static void saveKlassen(Set<Klasse> klassen) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(CSV_FILE))) {
            for (Klasse klasse : klassen) {
                writer.println(klasse.getName() + "," + klasse.getSchule().getName());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Set<Klasse> loadKlassen(Set<Schule> schools) {
        Set<Klasse> klassen = new HashSet<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(CSV_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    String klasseName = parts[0].trim();
                    String schuleName = parts[1].trim();
                    Schule schule = schools.stream()
                            .filter(s -> s.getName().equals(schuleName))
                            .findFirst()
                            .orElse(null);
                    if (schule != null) {
                        klassen.add(new Klasse(klasseName, schule));
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return klassen;
    }
}
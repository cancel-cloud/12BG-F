package de.lukas.SchuelerVerwaltung.helpers;

import de.lukas.SchuelerVerwaltung.independatClasses.Klasse;
import de.lukas.SchuelerVerwaltung.independatClasses.Schueler;

import java.io.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class SchuelerCSVHelper {
    private static final String CSV_FILE = "students.csv";

    public static void saveSchueler(Set<Schueler> schueler) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(CSV_FILE))) {
            for (Schueler s : schueler) {
                writer.println(String.join(",",
                        s.getName(),
                        s.getVorname(),
                        s.getGeburtsdatum().toString(),
                        s.getKlasse().getName(),
                        s.getKlasse().getSchule().getName()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Set<Schueler> loadSchueler(Set<Klasse> klassen) {
        Set<Schueler> schueler = new HashSet<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(CSV_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 5) {
                    String name = parts[0].trim();
                    String vorname = parts[1].trim();
                    LocalDate geburtsdatum = LocalDate.parse(parts[2].trim());
                    String klasseName = parts[3].trim();
                    String schuleName = parts[4].trim();

                    Klasse klasse = klassen.stream()
                            .filter(k -> k.getName().equals(klasseName) && k.getSchule().getName().equals(schuleName))
                            .findFirst()
                            .orElse(null);

                    if (klasse != null) {
                        schueler.add(new Schueler(name, vorname, klasse, geburtsdatum));
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return schueler;
    }
}
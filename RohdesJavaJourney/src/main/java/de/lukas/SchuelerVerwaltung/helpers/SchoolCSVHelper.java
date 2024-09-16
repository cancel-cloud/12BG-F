package de.lukas.SchuelerVerwaltung.helpers;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;

import de.lukas.SchuelerVerwaltung.Schule;

public class SchoolCSVHelper {
    private static final String CSV_FILE = "schools.csv";

    public static void saveSchools(Set<Schule> schools) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(CSV_FILE))) {
            for (Schule school : schools) {
                writer.println(school.getName());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Set<Schule> loadSchools() {
        Set<Schule> schools = new HashSet<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(CSV_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                schools.add(new Schule(line.trim()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return schools;
    }
}
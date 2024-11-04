package de.lukas.SchuelerVerwaltung.helpers;

import de.lukas.SchuelerVerwaltung.independatClasses.Schule;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

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
        ArrayList<Schule> schools = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(CSV_FILE));
            String line;
            while ((line = reader.readLine()) != null) {
                schools.add(new Schule(line.trim()));
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new HashSet<>(schools);
    }
}
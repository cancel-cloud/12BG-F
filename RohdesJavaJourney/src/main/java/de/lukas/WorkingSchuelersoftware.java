package de.lukas;

import java.util.List;

import de.lukas.SchuelerVerwaltung.Klasse;
import de.lukas.SchuelerVerwaltung.Schueler;
import de.lukas.SchuelerVerwaltung.Schule;
import de.lukas.SchuelerVerwaltung.helpers.KlasseCSVHelper;
import de.lukas.SchuelerVerwaltung.helpers.SchoolCSVHelper;
import de.lukas.SchuelerVerwaltung.helpers.SchuelerCSVHelper;


public class WorkingSchuelersoftware {
    public static void main(String[] args) {
        // if you need help visit the cursor chat named: "Saving Schools, Classes, and Students to CSV Files"
        System.out.println("Start\n ");
        List<Schule> schoolsList = SchoolCSVHelper.loadSchools();
        List<Klasse> klassenList = KlasseCSVHelper.loadKlassen(schoolsList);
        for (Klasse klasse : klassenList) {
            System.out.println(klasse.getName());
        }
        List<Schueler> schuelerlList = SchuelerCSVHelper.loadSchueler(klassenList);
        for (Schueler schueler : schuelerlList) {
            System.out.println(schueler.getName());
        }

        System.out.println("-----------------------------------");
        System.out.println("Schools: " + schoolsList.size());
        System.out.println("Klassen: " + klassenList.size());
        System.out.println("Schueler: " + schuelerlList.size());
        System.out.println("-----------------------------------");
        for (Schule school : schoolsList) {
            System.out.println(school.getName());
        }
        System.out.println("-----------------------------------");
        for (Klasse klasse : klassenList) {
            System.out.println(klasse.getName());
        }
        System.out.println("-----------------------------------");
        for (Schueler schueler : schuelerlList) {
            System.out.println(schueler.getName());
        }



        Schule schule = new Schule("Peter-Paul-Cahensly-Schule");
        schoolsList.add(schule);
        Klasse klasse = new Klasse("5a", schule);
        klassenList.add(klasse);
        String lineInPutString = "Laggis,KRANK,2010-05-15,E-Mail,Laggis_131@icloud.com,spitzname,LaggisMeister";
        Schueler fix = Schueler.addSchuelerFromFile(lineInPutString, klasse);
        schuelerlList.add(fix);
        System.out.println(fix.getName());
        System.out.println(fix.getVorname());
        System.out.println(fix.getGeburtsdatum() != null ? fix.getGeburtsdatum() : "No birthdate provided");
        System.out.println(fix.getKlasse().getName());
        System.out.println(fix.getKlasse().getSchule().getName());
        System.out.println("-----------------------------------");
        lineInPutString = "Laggis,KRANK,2010-05-15, E-Mail,Laggis_131@icloud.com,spitzname,LaggisMeister";
        fix.addSchuelerFromFile(lineInPutString, klasse);
        System.out.println("-----------------------------------");
        schule.getSchueler().forEach(s -> System.out.println(s.getName()));

        SchoolCSVHelper.saveSchools(schoolsList);
        KlasseCSVHelper.saveKlassen(klassenList);
        SchuelerCSVHelper.saveSchueler(schuelerlList);
    }
}

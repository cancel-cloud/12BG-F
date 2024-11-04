package de.lukas.SchuelerVerwaltung;

import de.lukas.SchuelerVerwaltung.helpers.KlasseCSVHelper;
import de.lukas.SchuelerVerwaltung.helpers.SchoolCSVHelper;
import de.lukas.SchuelerVerwaltung.helpers.SchuelerCSVHelper;
import de.lukas.SchuelerVerwaltung.independatClasses.Klasse;
import de.lukas.SchuelerVerwaltung.independatClasses.Schueler;
import de.lukas.SchuelerVerwaltung.independatClasses.Schule;

import java.util.Set;

public class WorkingSchuelersoftware {
    public static void main(String[] args) {
        System.out.println("Start\n ");
        Set<Schule> schoolsSet = SchoolCSVHelper.loadSchools();
        Set<Klasse> klassenSet = KlasseCSVHelper.loadKlassen(schoolsSet);
        for (Klasse klasse : klassenSet) {
            System.out.println(klasse.getName());
        }
        Set<Schueler> schuelerSet = SchuelerCSVHelper.loadSchueler(klassenSet);
        for (Schueler schueler : schuelerSet) {
            System.out.println(schueler.getName());
        }

        System.out.println("-----------------------------------");
        System.out.println("Schools: " + schoolsSet.size());
        System.out.println("Klassen: " + klassenSet.size());
        System.out.println("Schueler: " + schuelerSet.size());
        System.out.println("-----------------------------------");
        for (Schule school : schoolsSet) {
            System.out.println(school.getName());
        }
        System.out.println("-----------------------------------");
        for (Klasse klasse : klassenSet) {
            System.out.println(klasse.getName());
        }
        System.out.println("-----------------------------------");
        for (Schueler schueler : schuelerSet) {
            System.out.println(schueler.getName());
        }

        Schule schule = new Schule("Peter-Paul-Cahensly-Schule");
        schoolsSet.add(schule);
        Klasse klasse = new Klasse("5a", schule);
        klassenSet.add(klasse);
        String lineInPutString = "Laggis,KRANK,2010-05-15,E-Mail,Laggis_131@icloud.com,spitzname,LaggisMeister";
        Schueler fix = Schueler.addSchuelerFromFile(lineInPutString, klasse);
        schuelerSet.add(fix);
        System.out.println(fix.getName());
        System.out.println(fix.getVorname());
        System.out.println(fix.getGeburtsdatum() != null ? fix.getGeburtsdatum() : "No birthdate provided");
        System.out.println(fix.getKlasse().getName());
        System.out.println(fix.getKlasse().getSchule().getName());
        System.out.println("-----------------------------------");
        lineInPutString = "Laggis,KRANK,2010-05-15, E-Mail,Laggis_131@icloud.com,spitzname,LaggisMeister";
        Schueler.addSchuelerFromFile(lineInPutString, klasse);
        System.out.println("-----------------------------------");
        schule.getSchueler().forEach(s -> System.out.println(s.getName()));

        SchoolCSVHelper.saveSchools(schoolsSet);
        KlasseCSVHelper.saveKlassen(klassenSet);
        SchuelerCSVHelper.saveSchueler(schuelerSet);
    }
}

package de.lukas;

import de.lukas.SchuelerVerwaltung.Klasse;
import de.lukas.SchuelerVerwaltung.KontaktAdresse;
import de.lukas.SchuelerVerwaltung.Schueler;
import de.lukas.SchuelerVerwaltung.Schule;


public class Schuelersoftware {
    public static void main(String[] args) {
        Schule schule = new Schule("Gymnasium");
        Klasse klasse = new Klasse("5a");
        KontaktAdresse kontaktAdresse = new KontaktAdresse("E-Mail", "Laggis_131@icloud.com");
        Schueler schueler = new Schueler("Lukas", "Laggis", kontaktAdresse, klasse);
        schule.addSchueler(schueler);
        System.out.println("Schule: " + schule.getName());
        System.out.println("Namensliste: " + klasse.getNameList());
    }
}

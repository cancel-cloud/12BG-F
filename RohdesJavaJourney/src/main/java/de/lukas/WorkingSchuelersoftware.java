package de.lukas;

import de.lukas.SchuelerVerwaltung.Klasse;
import de.lukas.SchuelerVerwaltung.KontaktAdresse;
import de.lukas.SchuelerVerwaltung.Schueler;
import de.lukas.SchuelerVerwaltung.Schule;


public class WorkingSchuelersoftware {
    public static void main(String[] args) {
        Schule schule = new Schule("Gymnasium");
        Klasse klasse = new Klasse("5a");
        KontaktAdresse kontaktAdresse = new KontaktAdresse("E-Mail", "Laggis_131@icloud.com");
        Schueler schueler = new Schueler("Lukas", "Laggis", klasse);
        schueler.fuegeKontaktadresseHinzu(kontaktAdresse.getTyp(), kontaktAdresse.getWert());
        schueler.fuegeKontaktadresseHinzu("mail", "LaggisMeister");
        schule.addSchueler(schueler);
        System.out.println("Schule: " + schule.getName());
        System.out.println("Namensliste: " + klasse.getName());
        System.out.println("--------------------");
        System.out.println("Kontaktadressen von " + schueler.getName() + " " + schueler.getVorname());
        for (KontaktAdresse adresse : schueler.getKontaktAdresse()) {
            System.out.println(adresse.getTyp() + ": " + adresse.getWert());
        }
        schueler.removeKontaktaddresse("Laggis_131@icloud.com");
        for (KontaktAdresse adresse : schueler.getKontaktAdresse()) {
            System.out.println(adresse.getTyp() + ": " + adresse.getWert());
        }
    }
}

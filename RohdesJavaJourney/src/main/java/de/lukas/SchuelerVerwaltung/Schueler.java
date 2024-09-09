package de.lukas.SchuelerVerwaltung;

import java.util.ArrayList;

public class Schueler {
    private String name;
    private String vorname;
    private ArrayList<KontaktAdresse> kontaktAdresse;
    private Klasse klasse;

    public Schueler(String name, String vorname, Klasse klasse) {
        this.name = name;
        this.vorname = vorname;
        this.klasse = klasse;
    }

    public String getName() {
        return name;
    }

    public String getVorname() {
        return vorname;
    }

    public ArrayList<KontaktAdresse> getKontaktAdresse() {
        return kontaktAdresse;
    }

    public void fuegeKontaktadresseHinzu(String typ, String wert) {
        this.kontaktAdresse.add(new KontaktAdresse(typ, wert));
    }

    public void loescheKontakadresse(KontaktAdresse kontaktAdresse) {
        this.kontaktAdresse.remove(kontaktAdresse);
    }

    public ArrayList<KontaktAdresse> holeAlleKontaktadressenEinesTyps(String typ) {
        ArrayList<KontaktAdresse> kontaktadressenEinesTyps = new ArrayList<>();
        for (KontaktAdresse adresse : kontaktAdresse) {
            if (adresse.getTyp().equals(typ)) {
                kontaktadressenEinesTyps.add(adresse);
            }
        }
        return kontaktadressenEinesTyps;
    }

    public Klasse getKlasse() {
        return klasse;
    }
}

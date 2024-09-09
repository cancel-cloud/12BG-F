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
        this.kontaktAdresse = new ArrayList<>();
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

    public void loescheKontakadresse(String wert) {
        for (KontaktAdresse adresse : this.kontaktAdresse) {
            if (adresse.getWert().equals(wert)) {
                this.kontaktAdresse.remove(adresse);
                break;
            }
        }
    }

    public void removeKontaktaddresse(String wert) {
        for (int i = 0; i < kontaktAdresse.size(); i++) {
            if(kontaktAdresse.get(i).getWert().equals(wert)) {
                this.kontaktAdresse.remove(kontaktAdresse.remove(i));
            }
        }
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

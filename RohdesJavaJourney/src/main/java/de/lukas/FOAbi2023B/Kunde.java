package de.lukas.FOAbi2023B;

import java.util.ArrayList;
import java.util.Date;

public class Kunde {
    private String name;
    private String vorname;
    private Date geburtsdatum;
    private String plz;
    private String ort;
    private String strasse;

    private ArrayList<Bestellung> bestelliste;

    public Kunde(String name, String vorname, Date geburtsdatum, String plz, String ort, String strasse) {
        this.name = name;
        this.vorname = vorname;
        this.geburtsdatum = geburtsdatum;
        this.plz = plz;
        this.ort = ort;
        this.strasse = strasse;
        this.bestelliste = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void addbestellung(Bestellung bestellung) {
        this.bestelliste.add(bestellung);
    }
}

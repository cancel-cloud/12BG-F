package de.lukas.LA23NT;

import java.util.Date;

public class Probe {
    private String kennung;
    private String bezeichnung;
    private String farbe;
    private String bauform;
    private Date eingangsDatum;
    private Hersteller hersteller;


    public Probe(String kennung, Hersteller hersteller, String bezeichnung, String farbe, String bauform, Date eingangsDatum) {
        this.kennung = kennung;
        this.hersteller = hersteller;
        this.bezeichnung = bezeichnung;
        this.farbe = farbe;
        this.bauform = bauform;
        this.eingangsDatum = eingangsDatum;
    }

    public void setzeTester(Mitarbeiter mitarbeiter, String eigenschaft, String einheit) {


    }

    public void erfasseMesswert(Double mw, Date messzeit, String eigenschaften) {

    }
    public String erstelleProtokoll() {
        return "";
    }
}

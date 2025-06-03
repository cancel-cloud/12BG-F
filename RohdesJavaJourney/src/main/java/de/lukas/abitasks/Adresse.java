package de.lukas.abitasks;

public class Adresse {
    private final String strasse;
    private final String plz;
    private final String ort;

    public Adresse(String strasse, String plz, String ort) {
        this.strasse = strasse;
        this.plz = plz;
        this.ort = ort;
    }

    public String getStrasse() {
        return strasse;
    }
    public String getPlz() {
        return plz;
    }
    public String getOrt() {
        return ort;
    }
}

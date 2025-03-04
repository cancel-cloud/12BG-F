package de.lukas.LA23NT;

public class Hersteller {
    private String name;
    private String adresse;
    private String ort;

    public Hersteller(String name, String adresse, String ort) {
        this.name = name;
        this.adresse = adresse;
        this.ort = ort;
    }

    public String getName() {
        return name;
    }

    public String getAdresse() {
        return adresse;
    }

    public String getOrt() {
        return ort;
    }
}

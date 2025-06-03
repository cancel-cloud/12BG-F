package de.lukas.abitasks;

public class Kunde {
    private static int autowert; // Statisch
    private int nr;
    private String name;
    private String nachname;
    private Adresse adresse;

    public Kunde(String name, String nachname, Adresse adr) {
        this.name = name;
        this.nachname = nachname;
        this.adresse = adr;
        this.nr = ++autowert;
    }
}

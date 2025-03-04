package de.lukas.LA23NT;

public class Mitarbeiter {
    private String nachname;
    private String vorname;
    private String kuerzel;

    public Mitarbeiter(String nachname, String vorname, String kuerzel) {
        this.nachname = nachname;
        this.vorname = vorname;
        this.kuerzel = kuerzel;
    }

    public String getNachname() {
        return nachname;
    }

    public String getVorname() {
        return vorname;
    }

    public String getKuerzel() {
        return kuerzel;
    }
}

package de.lukas.FlugVerwaltung;

class Sitzplatz {
    private int sitzReihe;
    private char sitzBuchstabe;
    private Kunde kunde;

    public Sitzplatz(int reihe, char buchstabe) {
        this.sitzReihe = reihe;
        this.sitzBuchstabe = buchstabe;
    }

    public boolean istFrei() {
        return kunde == null;
    }
}

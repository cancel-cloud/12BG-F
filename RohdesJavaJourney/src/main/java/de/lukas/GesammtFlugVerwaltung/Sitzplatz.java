package de.lukas.GesammtFlugVerwaltung;

class Sitzplatz {
    private final int sitzReihe;
    private final char sitzBuchstabe;
    private Kunde kunde;

    public Sitzplatz(int reihe, char buchstabe) {
        this.sitzReihe = reihe;
        this.sitzBuchstabe = buchstabe;
    }

    public boolean istFrei() {
        return kunde == null;
    }
}

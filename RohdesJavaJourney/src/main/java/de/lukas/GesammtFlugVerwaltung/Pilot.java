package de.lukas.GesammtFlugVerwaltung;

class Pilot extends Person {
    private final String heimatbasis;
    private boolean imEinsatz;

    public Pilot(String vorname, String nachname, String adresse,
                 String email, String telefonnummer, String heimatbasis) {
        super(vorname, nachname, adresse, email, telefonnummer);
        this.heimatbasis = heimatbasis;
    }
}

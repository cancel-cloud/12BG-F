package de.lukas.FlugVerwaltung;

public class Person {
    private final int id;
    private final String vorname;
    private final String nachname;
    private final String adresse;
    private final String email;
    private final String telefonnummer;
    private static int autowert = 0;


    public Person(String vorname, String nachname, String adresse, String email, String telefonnummer) {
        autowert++;
        this.id = autowert;
        this.vorname = vorname;
        this.nachname = nachname;
        this.adresse = adresse;
        this.email = email;
        this.telefonnummer = telefonnummer;
    }

    public String toString() {
        return ("ID: " + this.id
                + "\nName: " + this.vorname + " " + this.nachname
                + "\nAdresse: " + this.adresse
                + "\nEmail: " + this.email
                + "\nTelefonnummer: " + this.telefonnummer
        );
    }
}

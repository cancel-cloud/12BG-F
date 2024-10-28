package de.lukas.FlugVerwaltung;

public class Person {
    protected int id;
    protected String vorname;
    protected String nachname;
    protected String adresse;
    protected String email;
    protected String telefonnummer;
    protected int autowert = 0;

    public Person(String vorname, String nachname, String adresse,
                  String email, String telefonnummer) {
        this.vorname = vorname;
        this.nachname = nachname;
        this.adresse = adresse;
        this.email = email;
        this.telefonnummer = telefonnummer;
    }

    // Getter und Setter
    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTelefonnummer(String telefonnummer) {
        this.telefonnummer = telefonnummer;
    }

    @Override
    public String toString() {
        return String.format("%s %s", vorname, nachname);
    }
}

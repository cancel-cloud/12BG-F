package de.lukas.SchuelerVerwaltung;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Objects;


public class Schueler {
    private String name;
    private String vorname;
    private ArrayList<KontaktAdresse> kontaktAdresse;
    private Klasse klasse;
    private LocalDate geburtsdatum;
    

    public Schueler(String name, String vorname, Klasse klasse, LocalDate geburtsdatum) {
        this.name = name;
        this.vorname = vorname;
        this.klasse = klasse;
        this.geburtsdatum = geburtsdatum;
        this.kontaktAdresse = new ArrayList<>();
    }

    @Override
public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Schueler schueler = (Schueler) o;
    return Objects.equals(name, schueler.name) &&
           Objects.equals(vorname, schueler.vorname) &&
           Objects.equals(geburtsdatum, schueler.geburtsdatum) &&
           Objects.equals(klasse, schueler.klasse);
}

@Override
public int hashCode() {
    return Objects.hash(name, vorname, geburtsdatum, klasse);
}

    public String getName() {
        return name;
    }

    public String getVorname() {
        return vorname;
    }

    public ArrayList<KontaktAdresse> getKontaktAdresse() {
        return kontaktAdresse;
    }

    public void fuegeKontaktadresseHinzu(String typ, String wert) {
        this.kontaktAdresse.add(new KontaktAdresse(typ, wert));
    }

    public void loescheKontakadresse(String wert) {
        for (KontaktAdresse adresse : this.kontaktAdresse) {
            if (adresse.getWert().equals(wert)) {
                this.kontaktAdresse.remove(adresse);
                break;
            }
        }
    }

    public void removeKontaktaddresse(String wert) {
        for (int i = 0; i < kontaktAdresse.size(); i++) {
            if(kontaktAdresse.get(i).getWert().equals(wert)) {
                this.kontaktAdresse.remove(kontaktAdresse.remove(i));
            }
        }
    }

    public ArrayList<KontaktAdresse> holeAlleKontaktadressenEinesTyps(String typ) {
        ArrayList<KontaktAdresse> kontaktadressenEinesTyps = new ArrayList<>();
        for (KontaktAdresse adresse : kontaktAdresse) {
            if (adresse.getTyp().equals(typ)) {
                kontaktadressenEinesTyps.add(adresse);
            }
        }
        return kontaktadressenEinesTyps;
    }

    public Klasse getKlasse() {
        return klasse;
    }

    public LocalDate getGeburtsdatum() {
        return geburtsdatum;
    }

    public static Schueler addSchuelerFromFile(String lineInPutString, Klasse klasse) {
        String[] parts = lineInPutString.split(",");
        if (parts.length < 3) {
            throw new IllegalArgumentException("String must contain at least name, surname, and birthdate separated by commas (',').");
        }
        String name = parts[0].trim();
        String vorname = parts[1].trim();
        LocalDate geburtsdatum;
        
        try {
            geburtsdatum = LocalDate.parse(parts[2].trim());
        } catch (DateTimeParseException e) {
            System.out.println("Warning: Invalid date format for " + name + " " + vorname + ". Setting birthdate to null.");
            geburtsdatum = null;
        }
    
        // Check if a student with the same name already exists in the same school
        if(klasse.getSchule().getSchueler(name) != null) {
            System.out.println("Warnung: Ein Schueler mit dem Namen '" + name + "' existiert bereits in der Schule.");
            return null; // Abort the program at this point
        }
        
        System.out.println("Schueler " + vorname + " " + name + " wurde erstellt.");
    
        Schueler schueler = new Schueler(name, vorname, klasse, geburtsdatum);
        
        for (int i = 3; i < parts.length; i += 2) {
            if (i + 1 < parts.length) {
                String typ = parts[i].trim();
                String wert = parts[i + 1].trim();
                System.out.println(typ + " " + wert);
                schueler.fuegeKontaktadresseHinzu(typ, wert);
            }
        }
        schueler.klasse.getSchule().addSchueler(schueler);
        return schueler;
    }
}

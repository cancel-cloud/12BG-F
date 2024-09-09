package de.lukas.SchuelerVerwaltung;

import java.util.ArrayList;

public class Schule {
    private String name;
    private ArrayList<Schueler> schueler;

    public Schule(String name) {
        this.name = name;
        this.schueler = new ArrayList<>();
    }

    

    public String getName() {
        return name;
    }

    public void addSchueler(Schueler s) {
        schueler.add(s);
    }

    public void removeSchueler(Schueler s) {
        schueler.remove(s);
    }

    public boolean checkifSchuelerExists(String name, String vorname) {
        for (Schueler s : schueler) {
            if (s.getName().equals(name) && s.getVorname().equals(vorname)) {
                return true;
            }
        }
        return false;
    }

    public Schueler getSchueler(String name, String vorname) {
        for (Schueler s : schueler) {
            if (s.getName().equals(name) && s.getVorname().equals(vorname)) {
                return s;
            }
        }
        return null;
    }


    //TODO: Finde einen Sinnvollen Grund einen Schueler zu aendern
    public void aenderSchueler() {
       
    }
    
}
package de.lukas.SchuelerVerwaltung;

import java.util.ArrayList;

public class Schule {
    private String name;
    private ArrayList<Schueler> schueler;

    public Schule(String name) {
        this.name = name;
        this.schueler = new ArrayList<>();
    }

    public void addSchueler(Schueler s) {
        schueler.add(s);
    }

    public String getName() {
        return name;
    }
}
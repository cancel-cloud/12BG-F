package de.lukas.SchuelerVerwaltung;

import java.util.List;

public class Klasse {
    private String name;

    public Klasse(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public List<String> getNameList() {
        return List.of(name);
    }
}

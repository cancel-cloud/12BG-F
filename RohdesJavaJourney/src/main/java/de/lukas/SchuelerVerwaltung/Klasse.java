package de.lukas.SchuelerVerwaltung;

import java.util.List;
import java.util.Objects;

public class Klasse {
    private String name;
    private Schule schule;

    @Override
public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Klasse klasse = (Klasse) o;
    return Objects.equals(name, klasse.name) &&
           Objects.equals(schule, klasse.schule);
}

@Override
public int hashCode() {
    return Objects.hash(name, schule);
}

    public Klasse(String name, Schule schule) {
        this.name = name;
        this.schule = schule;
    }

    public String getName() {
        return this.name;
    }

    public List<String> getNameList() {
        return List.of(name);
    }

    public Schule getSchule() {
        return schule;
    }
}

package de.lukas.SchuelerVerwaltung.independatClasses;

public class KontaktAdresse {
    private final String typ;
    private final String wert;

    public KontaktAdresse(String typ, String wert) {
        this.typ = typ;
        this.wert = wert;
    }

    public String getTyp() {
        return typ;
    }

    public String getWert() {
        return wert;
    }
}
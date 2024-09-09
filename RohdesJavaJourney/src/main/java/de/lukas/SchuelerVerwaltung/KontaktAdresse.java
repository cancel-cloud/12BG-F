package de.lukas.SchuelerVerwaltung;

public class KontaktAdresse {
    private String typ;
    private String wert;

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
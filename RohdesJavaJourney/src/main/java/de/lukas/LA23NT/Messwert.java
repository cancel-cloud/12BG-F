package de.lukas.LA23NT;

import java.util.Date;

public class Messwert {
    private Double wert;
    private Date messzeit;

    public Messwert(Double wert, Date messzeit) {
        this.wert = wert;
        this.messzeit = messzeit;
    }

    public Double getWert() {
        return wert;
    }

    public Date getMesszeit() {
        return messzeit;
    }
}

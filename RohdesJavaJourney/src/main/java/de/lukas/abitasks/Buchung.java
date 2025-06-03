package de.lukas.abitasks;

import java.util.Date;

public class Buchung {
    private static int autowert;  // Statische Variable
    private int buchungsNr;
    private Date von;
    private Date bis;
    private double preis;

    public Buchung(Date von, Date bis, Ferienhaus fh, Kunde kd) {
        this.buchungsNr = ++autowert;  // Automatische Nummerierung
        this.von = von;
        this.bis = bis;
    }
}


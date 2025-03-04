package de.lukas.FOAbi2023B;

import java.util.ArrayList;

public class Bestellung {
    private String bestelldatum;
    private ArrayList<Artikel> artikelliste = new ArrayList<>();

    public void addArtikel(Artikel artikel) {
        artikelliste.add(artikel);
    }

    public void entferneArtikel(Artikel artikel) {
        artikelliste.remove(artikel);
    }

    public double getGesamtpreis() {
        double gesamtpreis = 0;
        for (Artikel artikel : artikelliste) {
            gesamtpreis += artikel.getEinzelpreis();
        }
        return gesamtpreis;
    }


}

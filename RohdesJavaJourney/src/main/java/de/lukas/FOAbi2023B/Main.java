package de.lukas.FOAbi2023B;

import java.util.Date;

public class Main {
    public static void main(String[] args) {
        Artikel artikel1 = new Artikel("Banane", 10.0);
        Bestellung bestellung1 = new Bestellung();
        bestellung1.addArtikel(artikel1);
        Kunde kunde1 = new Kunde("Max Mustermann", "Max", new Date(), "12345", "Musterstadt", "Musterstrasse");
        kunde1.addbestellung(bestellung1);
        System.out.println(kunde1.getName());
        artikel1.druckePreis();

        Artikel artikel2 = new Artikel("Apfel", 5.0);
        bestellung1.addArtikel(artikel2);
        artikel2.druckePreis();
        System.out.println(bestellung1.getGesamtpreis());
    }
}

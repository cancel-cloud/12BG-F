package de.lukas.FOAbi2023B;

public class Artikel {
    private String atikelbezeichnung;
    private Double einzelpreis;

    public Artikel(String atikelbezeichnung, Double einzelpreis) {
        this.atikelbezeichnung = atikelbezeichnung;
        this.einzelpreis = einzelpreis;
    }
    public void erhöhrePreis(int prozent) {
        this.einzelpreis = einzelpreis * (1 + prozent / 100);
    }
    public void druckePreis() {
        System.out.println("Artikelbezeichnung: " + atikelbezeichnung + ", Preis: " + einzelpreis);
    }

    public Double getEinzelpreis() {
        return einzelpreis;
    }
}

package de.lukas.FlugVerwaltung;

public class Kunde extends Person {

    private final boolean isVegetarier;
    private int bonusmeilen;


    public Kunde(String vorname, String nachname, String adresse, String email, String telefonnummer,
                 boolean isVegetarier, int bonusmeilen) {
        super(vorname, nachname, adresse, email, telefonnummer);
        this.bonusmeilen = bonusmeilen;
        this.isVegetarier = isVegetarier;
    }

    public boolean modifiziereBonusmeilen(int meilen) {
        if ((bonusmeilen + meilen) < 0) return false;
        bonusmeilen += meilen;
        return true;
    }

    @Override
    public String toString() {
        return (super.toString() + "\nVegetarier: " + isVegetarier + "\nBonusmeilen: " + bonusmeilen);
    }
}

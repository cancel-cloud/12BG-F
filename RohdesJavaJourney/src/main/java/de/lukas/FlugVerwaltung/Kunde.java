package de.lukas.FlugVerwaltung;


public class Kunde extends Person {
    private boolean istVegetarier;
    private int bonusmeilen;

    public Kunde(String vorname, String nachname, String adresse,
                 String email, String telefonnummer,
                 boolean istVegetarier, int bonusmeilen) {
        super(vorname, nachname, adresse, email, telefonnummer);
        this.istVegetarier = istVegetarier;
        this.bonusmeilen = bonusmeilen;
    }

    public boolean modifiziereBonusmeilen(int meilen) {
        // Überprüfe ob bei Abzug genügend Bonusmeilen vorhanden sind
        if (meilen < 0 && Math.abs(meilen) > bonusmeilen) {
            return false;
        }
        // Modifiziere Bonusmeilen
        bonusmeilen += meilen;
        return true;
    }

    // Getter und Setter
    public boolean isVegetarier() {
        return istVegetarier;
    }

    public void setVegetarier(boolean istVegetarier) {
        this.istVegetarier = istVegetarier;
    }

    public int getBonusmeilen() {
        return bonusmeilen;
    }

    @Override
    public String toString() {
        return super.toString(); // Verwendet die toString() Methode der Elternklasse
    }
}

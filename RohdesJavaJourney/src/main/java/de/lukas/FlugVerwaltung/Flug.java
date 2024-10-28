package de.lukas.FlugVerwaltung;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

class Flug {
    private String flugNummer;
    private String startFlughafen;
    private LocalDateTime abflugzeit;
    private String zielFlughafen;
    private LocalDateTime ankunftzeit;
    private String flugzeugModell;
    private String status;
    private int km;
    private Pilot pilot;
    private List<Sitzplatz> sitzplaetze;

    public Flug(String flugNummer, Pilot pilot, LocalDateTime abflugzeit,
                String start, LocalDateTime ankunftzeit, String ziel,
                String modell, int km, String status) {
        this.flugNummer = flugNummer;
        this.pilot = pilot;
        this.abflugzeit = abflugzeit;
        this.startFlughafen = start;
        this.ankunftzeit = ankunftzeit;
        this.zielFlughafen = ziel;
        this.flugzeugModell = modell;
        this.km = km;
        this.status = status;
        this.sitzplaetze = new ArrayList<>();
    }

    public void erzeugeSitzplaetze() {
        // Implementation for seat creation
    }

    public int getAnzahlFreieSitze() {
        // Implementation for getting number of free seats
        return 0;
    }

    public String getFlugNummer() {
        return flugNummer;
    }

    public List<Sitzplatz> ermittleFreieSitze() {
        // Implementation for finding free seats
        return new ArrayList<>();
    }

    public boolean reserviereSitzplatz(Kunde kunde, String sitzBezeichnung) {
        // Implementation for seat reservation
        return false;
    }
}
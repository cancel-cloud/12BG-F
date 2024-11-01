package de.lukas.GesammtFlugVerwaltung;

class Ticket {
    private String ticketnummer;
    private int autowert = 0;
    private Kunde kunde;
    private Flug flug;
    private Sitzplatz sitzplatz;

    public Ticket(Kunde kunde, Flug flug, Sitzplatz sitzplatz) {
        this.kunde = kunde;
        this.flug = flug;
        this.sitzplatz = sitzplatz;
    }

    public int compareTo(Ticket other) {
        // Implementation for comparison
        return 0;
    }
}

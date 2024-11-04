package de.lukas.GesammtFlugVerwaltung;

class Ticket {
    private String ticketnummer;
    private final int autowert = 0;
    private final Kunde kunde;
    private final Flug flug;
    private final Sitzplatz sitzplatz;

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

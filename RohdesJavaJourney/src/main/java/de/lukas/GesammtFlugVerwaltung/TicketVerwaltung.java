package de.lukas.GesammtFlugVerwaltung;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

class TicketVerwaltung {
    private List<Ticket> tickets;
    private List<Person> piloten;

    public TicketVerwaltung() {
        tickets = new ArrayList<>();
        piloten = new ArrayList<>();
    }

    public boolean registriereFlug(String flugnummer, LocalDateTime abflugzeit,
                                   String start, LocalDateTime ankunftzeit, String ziel,
                                   String modell, int km, String status) {
        // Implementation for flight registration
        return true;
    }

    public Ticket sucheTicket(String ticketNummer) {
        // Implementation for ticket search
        return null;
    }
}


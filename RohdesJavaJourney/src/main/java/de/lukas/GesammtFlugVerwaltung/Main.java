package de.lukas.GesammtFlugVerwaltung;

import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        TicketVerwaltung verwaltung = new TicketVerwaltung();

        // Create a pilot
        Pilot pilot = new Pilot(
                "Hans",
                "Mueller",
                "Flughafenstr. 1, 12345 Berlin",
                "hans.mueller@airline.com",
                "+49 123 456789",
                "BER"
        );
        System.out.println("Created pilot: " + pilot.toString());

        // Create customers
        Kunde kunde1 = new Kunde(
                "Maria",
                "Schmidt",
                "Hauptstr. 42, 10115 Berlin",
                "maria.schmidt@email.com",
                "+49 987 654321",
                true,  // vegetarian
                1000   // initial bonus miles
        );

        Kunde kunde2 = new Kunde(
                "Peter",
                "Weber",
                "Bahnhofstr. 15, 20099 Hamburg",
                "peter.weber@email.com",
                "+49 456 789123",
                false,  // not vegetarian
                500    // initial bonus miles
        );

        System.out.println("\nCreated customers:");
        System.out.println(kunde1.toString() + " (Vegetarier: " + kunde1.isVegetarier() +
                ", Bonusmeilen: " + kunde1.getBonusmeilen() + ")");
        System.out.println(kunde2.toString() + " (Vegetarier: " + kunde2.isVegetarier() +
                ", Bonusmeilen: " + kunde2.getBonusmeilen() + ")");

        // Create a flight
        LocalDateTime departure = LocalDateTime.now().plusDays(7);
        LocalDateTime arrival = departure.plusHours(2);

        Flug flug = new Flug(
                "LH100",
                pilot,
                departure,
                "BER",
                arrival,
                "MUC",
                "Airbus A320",
                500,  // distance in km
                "Scheduled"
        );

        System.out.println("\nCreated flight: " + flug.getFlugNummer());

        // Create seats for the flight
        flug.erzeugeSitzplaetze();
        System.out.println("Available seats: " + flug.getAnzahlFreieSitze());

        // Book a seat for kunde1
        boolean bookingSuccess = flug.reserviereSitzplatz(kunde1, "12A");
        if (bookingSuccess) {
            System.out.println("\nBooking successful for " + kunde1.toString());
            // Add bonus miles for the flight
            kunde1.modifiziereBonusmeilen(500);
            System.out.println("New bonus miles balance: " + kunde1.getBonusmeilen());
        }

        // Demonstrate updating customer information
        System.out.println("\nUpdating customer information:");
        System.out.println("Old email: " + kunde1.email);
        kunde1.setEmail("maria.schmidt@newemail.com");
        System.out.println("New email: " + kunde1.email);

        // Demonstrate bonus miles modification
        System.out.println("\nTesting bonus miles modification:");
        boolean canSpendMiles = kunde1.modifiziereBonusmeilen(-750);
        if (canSpendMiles) {
            System.out.println("Successfully spent 750 bonus miles");
            System.out.println("New balance: " + kunde1.getBonusmeilen());
        } else {
            System.out.println("Not enough bonus miles available");
        }

        // Try to spend more miles than available
        boolean spendTooMany = kunde1.modifiziereBonusmeilen(-1000);
        if (!spendTooMany) {
            System.out.println("Cannot spend 1000 miles - insufficient balance");
        }

        // Search for a ticket
        System.out.println("\nSearching for ticket:");
        Ticket ticket = verwaltung.sucheTicket("LH100-12A");
        if (ticket != null) {
            System.out.println("Found ticket");
        } else {
            System.out.println("Ticket not found");
        }
    }
}
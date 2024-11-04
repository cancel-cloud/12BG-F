package de.lukas.FlugVerwaltung;

public class Main {
    public static void main(String[] args) {
        Kunde manfred = new Kunde(
                "Manfred",
                "Müller",
                "Musterstraße 1",
                "m.mueller@email.com",
                "+49 123 456789",
                true,
                1000
        );
        System.out.print(manfred);
    }
}

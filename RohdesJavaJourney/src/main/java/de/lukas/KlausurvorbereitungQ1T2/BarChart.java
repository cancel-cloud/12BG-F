package de.lukas.KlausurvorbereitungQ1T2;

import java.util.Scanner;

public class BarChart {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Eingabe von Werten
        System.out.println("Geben Sie die Anzahl der Werte ein:");
        int n = scanner.nextInt();
        int[] values = new int[n];

        // Werte eingeben
        for (int i = 0; i < n; i++) {
            System.out.println("Geben Sie den Wert für Balken " + (i + 1) + " ein:");
            values[i] = scanner.nextInt();
        }

        // Diagramm erzeugen
        System.out.println("\nBalkendiagramm:");
        for (int i = 0; i < n; i++) {
            System.out.print("Balken " + (i + 1) + ": ");
            for (int j = 0; j < values[i]; j++) {
                System.out.print("*");
            }
            System.out.println();
        }

        scanner.close();
    }
}
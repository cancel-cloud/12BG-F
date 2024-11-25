package de.lukas.Taxi;

// Die TaxiRunner-Klasse implementiert das Runnable-Interface,
// wodurch sie von einem Thread ausgeführt werden kann.
public class TaxiRunner implements Runnable {
    // Eine statische Variable, die von allen Threads gemeinsam genutzt wird.
    public static int count = 0;

    @Override
    public void run() {
        // Jeder Thread erhöht den Zähler und gibt den aktuellen Zählerstand aus.
        for (int i = 0; i < 10; i++) {
            count++; // Erhöht den Zähler um 1.
            System.out.println(Thread.currentThread().getName() + " - Count: " + count);

            try {
                // Simuliert eine kurze Pause, damit Threads abwechselnd arbeiten können.
                Thread.sleep(100);
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getName() + " wurde unterbrochen.");
            }
        }
    }

    // Hauptmethode, um den Runnable in Aktion zu zeigen.
    public static void main(String[] args) {
        // Erstellen von zwei TaxiRunner-Instanzen.
        TaxiRunner task1 = new TaxiRunner();
        TaxiRunner task2 = new TaxiRunner();

        // Zwei Threads erstellen und die Runnable-Objekte zuweisen.
        Thread thread1 = new Thread(task1, "TaxiRunner-1");
        Thread thread2 = new Thread(task2, "TaxiRunner-2");

        // Die Threads starten.
        thread1.start();
        thread2.start();

        // Warten, bis die Threads fertig sind.
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            System.out.println("Der Haupt-Thread wurde unterbrochen.");
        }

        // Ausgabe des Endwerts des Zählers.
        System.out.println("Endwert von Count: " + count);
    }
}
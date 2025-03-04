package de.lukas.KlausurvorbereitungQ1T2;

public class SlowRunnable implements Runnable {
    @Override
    public void run() {
        for (int i =1; i<100; i++ ) {
            System.out.println("Ich bin langsam. Count: " + i);
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

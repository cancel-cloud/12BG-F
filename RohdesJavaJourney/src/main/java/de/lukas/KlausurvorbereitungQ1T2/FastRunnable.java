package de.lukas.KlausurvorbereitungQ1T2;

public class FastRunnable implements Runnable{
    @Override
    public void run() {
        for (int i =1; i<100; i++ ) {
            System.out.println("Ich bin schnell. Count: " + i);
        }
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

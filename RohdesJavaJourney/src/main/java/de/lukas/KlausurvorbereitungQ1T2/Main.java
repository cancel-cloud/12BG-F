package de.lukas.KlausurvorbereitungQ1T2;

public class Main {
    public static void main(String[] args) {
        SlowRunnable slowRunnable = new SlowRunnable();
        FastRunnable fastRunnable = new FastRunnable();

        Thread slowThreadding = new Thread(slowRunnable);
        Thread fastTHreadding = new Thread(fastRunnable);

        slowThreadding.start();
        fastTHreadding.start();
    }
}
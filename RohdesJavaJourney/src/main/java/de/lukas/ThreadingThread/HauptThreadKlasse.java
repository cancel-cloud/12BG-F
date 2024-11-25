package de.lukas.ThreadingThread;

public class HauptThreadKlasse {
    public static void main(String[] args) {
        SlowThread slowThread = new SlowThread();
        FastThread fastThread = new FastThread();
        slowThread.start();
        fastThread.start();
    }
}

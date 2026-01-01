package DesignPatterns.Creational.Singleton;

public class SynchronizedSingleton {

    private static SynchronizedSingleton instance;

    private SynchronizedSingleton() {
        System.out.println("SynchronizedSingleton initialized.");
    }

    // Adding 'synchronized' ensures only one thread can execute this method at a time.
    // PRO: Thread-safe.
    // CON: Reduces performance because every call to getInstance() is synchronized,
    // even after the instance is already created.
    public static synchronized SynchronizedSingleton getInstance() {
        if (instance == null) {
            instance = new SynchronizedSingleton();
        }
        return instance;
    }

    public void showMessage() {
        System.out.println("Hello from SynchronizedSingleton!");
    }
}
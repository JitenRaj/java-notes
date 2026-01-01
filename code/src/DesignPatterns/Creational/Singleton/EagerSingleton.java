package DesignPatterns.Creational.Singleton;

public class EagerSingleton {

    // Instance is created immediately when the class is loaded into memory.
    // JVM guarantees this is thread-safe.
    private static final EagerSingleton INSTANCE = new EagerSingleton();

    private EagerSingleton() {
        System.out.println("EagerSingleton initialized (at class loading).");
    }

    public static EagerSingleton getInstance() {
        return INSTANCE;
    }

    public void showMessage() {
        System.out.println("Hello from EagerSingleton!");
    }
}
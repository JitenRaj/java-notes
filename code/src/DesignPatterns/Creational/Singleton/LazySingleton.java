package DesignPatterns.Creational.Singleton;

public class LazySingleton {

    // 1. Private static variable, initially null.
    private static LazySingleton instance;

    // 2. Private constructor to prevent instantiation from other classes.
    private LazySingleton() {
        System.out.println("LazySingleton initialized.");
    }

    // 3. Public static method to return the instance.
    // NOT THREAD SAFE: If two threads call this simultaneously when instance is null,
    // two instances might be created.
    public static LazySingleton getInstance() {
        if (instance == null) {
            instance = new LazySingleton();
        }
        return instance;
    }

    public void showMessage() {
        System.out.println("Hello from LazySingleton!");
    }
}
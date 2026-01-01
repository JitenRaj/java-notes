package DesignPatterns.Creational.Singleton;

public enum EnumSingleton {
    // The Enum constant acts as the singleton instance.
    INSTANCE;

    // You can add constructors, fields, and methods just like a normal class
    private EnumSingleton() {
        System.out.println("EnumSingleton initialized.");
    }

    public void showMessage() {
        System.out.println("Hello from EnumSingleton!");
    }

    public void doSomething() {
        System.out.println("Enum instance doing work...");
    }
}
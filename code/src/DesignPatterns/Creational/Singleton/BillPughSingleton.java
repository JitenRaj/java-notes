package DesignPatterns.Creational.Singleton;

public class BillPughSingleton {

    private BillPughSingleton() {
        System.out.println("BillPughSingleton initialized.");
    }

    // Static inner class. This class is NOT loaded until it is referenced in getInstance().
    // This allows for Lazy Loading behavior without explicit synchronization.
    private static class SingletonHelper {
        private static final BillPughSingleton INSTANCE = new BillPughSingleton();
    }

    public static BillPughSingleton getInstance() {
        return SingletonHelper.INSTANCE;
    }

    public void showMessage() {
        System.out.println("Hello from BillPughSingleton!");
    }
}
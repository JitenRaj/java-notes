package DesignPatterns.Creational.AbstractFactory;

public class Main {
    // Simulates reading a config file (e.g., "Windows" or "MacOS")
    private static String osConfig = "Windows";

    public static void main(String[] args) {
        GUIFactory factory;

        // Initialize the factory based on configuration
        if (osConfig.equalsIgnoreCase("Windows")) {
            factory = new WindowsFactory();
        } else {
            factory = new MacOSFactory();
        }

        // The application is assembled using the chosen factory
        Application app = new Application(factory);

        System.out.println("--- App Launching ---");
        app.paint();

        System.out.println("\n--- Switching OS Context to MacOS ---");
        // Manually switching factory for demonstration
        factory = new MacOSFactory();
        Application macApp = new Application(factory);
        macApp.paint();
    }
}


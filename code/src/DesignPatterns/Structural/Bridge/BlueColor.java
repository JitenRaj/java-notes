package DesignPatterns.Structural.Bridge;

// Concrete Implementor 2

public class BlueColor implements Color {
    @Override
    public void applyColor() {
        System.out.println("Applying blue color.");
    }
}

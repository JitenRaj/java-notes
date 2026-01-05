package DesignPatterns.Structural.Bridge;

// Concrete Implementor 1

public class RedColor implements Color{

    @Override
    public void applyColor() {
        System.out.println("Applying red color.");
    }
}

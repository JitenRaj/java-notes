package DesignPatterns.Structural.Bridge;

// Concrete Implementor 3

public class GreenColor implements Color{
    @Override
    public void applyColor() {
        System.out.println("Applying green color.");
    }
}

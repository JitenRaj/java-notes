package DesignPatterns.Structural.Bridge;

// Refined Abstraction 2

public class Pentagon extends Shape {
    public Pentagon(Color color) {
        super(color);
    }

    @Override
    public void draw() {
        System.out.println("Pentagon Drawn.");
        color.applyColor();
    }
}

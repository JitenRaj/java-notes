package DesignPatterns.Structural.Bridge;

// Refined Abstraction 1

public class Triangle extends Shape {
    public Triangle(Color color) {
        super(color);
    }

    @Override
    public void draw() {
        System.out.println("Triangle Drawn.");
        color.applyColor();
    }
}

package DesignPatterns.Structural.Bridge;

// Abstraction

public abstract class Shape {

    // Composition - The "Bridge"
    protected Color color;

    // Constructor acts as bridge setter
    public Shape (Color color) {
        this.color = color;
    }

    // Abstract method
    abstract public void draw();
}

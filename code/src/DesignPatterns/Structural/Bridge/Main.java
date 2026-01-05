package DesignPatterns.Structural.Bridge;

public class Main {
    public static void main(String[] args) {
        // Create a Red Triangle
        // We pass the specific Implementor (RedColor) to the Abstraction (Triangle)
        Shape redTriangle = new Triangle(new RedColor());
        redTriangle.draw();

        // Create a Green Triangle
        Shape greenTriangle = new Triangle(new GreenColor());
        greenTriangle.draw();

        // Create a Red Pentagon
        Shape redPentagon = new Pentagon(new RedColor());
        redPentagon.draw();

        // Create a Green Pentagon
        Shape greenPentagon = new Pentagon(new GreenColor());
        greenPentagon.draw();
    }
}


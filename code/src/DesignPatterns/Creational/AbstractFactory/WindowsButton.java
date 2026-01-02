package DesignPatterns.Creational.AbstractFactory;

// Concrete Product A1: Windows Button

public class WindowsButton implements Button{
    @Override
    public void render() {
        System.out.println("Rendering a Windows style button.");
    }
}

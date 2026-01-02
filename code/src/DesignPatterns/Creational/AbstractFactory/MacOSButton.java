package DesignPatterns.Creational.AbstractFactory;

// Concrete Product A2: MacOS Button

public class MacOSButton implements Button{
    @Override
    public void render() {
        System.out.println("Rendering a MacOS style button.");
    }
}

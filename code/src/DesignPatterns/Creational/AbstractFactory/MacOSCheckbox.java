package DesignPatterns.Creational.AbstractFactory;

// Concrete Product B2: MacOS Checkbox

public class MacOSCheckbox implements Checkbox{
    @Override
    public void render() {
        System.out.println("Rendering a MacOS style checkbox.");
    }
}

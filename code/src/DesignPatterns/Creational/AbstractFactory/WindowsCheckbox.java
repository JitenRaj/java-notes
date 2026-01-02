package DesignPatterns.Creational.AbstractFactory;

// Concrete Product B1: Windows Checkbox

public class WindowsCheckbox implements Checkbox{
    @Override
    public void render() {
        System.out.println("Rendering a Windows style checkbox.");
    }
}

package DesignPatterns.Creational.AbstractFactory;

// Concrete Factory 2: MacOS Factory
// Produces only MacOS-compatible components

public class MacOSFactory implements GUIFactory{
    @Override
    public Button createButton() {
        return new MacOSButton();
    }

    @Override
    public Checkbox createCheckbox() {
        return new MacOSCheckbox();
    }
}

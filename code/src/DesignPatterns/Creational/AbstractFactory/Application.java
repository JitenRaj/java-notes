package DesignPatterns.Creational.AbstractFactory;

public class Application {
    private Button button;
    private Checkbox checkbox;

    // The application knows nothing about specific factory implementations

    public Application(GUIFactory factory) {
        button = factory.createButton();
        checkbox = factory.createCheckbox();
    }

    public void paint() {
        button.render();
        checkbox.render();
    }
}

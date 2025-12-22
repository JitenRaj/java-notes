---
layout: default
title: Abstract Factory Pattern
parent: Creational Design Patterns
nav_order: 3
---

## Abstract Factory Pattern

**Design Patterns**: _Abstract Factory Pattern_.

The _Abstract Factory Pattern_ is a creational design pattern that provides an interface for creating families of related or dependent objects without specifying their concrete classes.

While the standard Factory Pattern creates one type of object, the Abstract Factory acts as a "Super Factory"—a factory that creates other factories.

In **Java**, this is essential when your code needs to work with various families of products (like different UI themes or Operating System components) and you want to ensure that the products created match the same family.

### Implementing the Abstract Factory

To implement this pattern, we define interfaces for a family of distinct products (e.g., Buttons and Checkboxes) and an interface for the Abstract Factory that creates them. Concrete factories then implement this interface to produce specific variants (e.g., Windows style vs. MacOS style).

In this example, we will build a cross-platform GUI application that renders components differently based on the OS.

#### Step 1: Define Product Interfaces and Concrete Implementations

```java
// Product A Interface
public interface Button {
    void render();
}

// Product B Interface
public interface Checkbox {
    void render();
}

// Concrete Product A1: Windows Button
class WindowsButton implements Button {
    @Override
    public void render() {
        System.out.println("Rendering a Windows style button.");
    }
}

// Concrete Product A2: MacOS Button
class MacOSButton implements Button {
    @Override
    public void render() {
        System.out.println("Rendering a MacOS style button.");
    }
}

// Concrete Product B1: Windows Checkbox
class WindowsCheckbox implements Checkbox {
    @Override
    public void render() {
        System.out.println("Rendering a Windows style checkbox.");
    }
}

// Concrete Product B2: MacOS Checkbox
class MacOSCheckbox implements Checkbox {
    @Override
    public void render() {
        System.out.println("Rendering a MacOS style checkbox.");
    }
}
```

#### Step 2: Define the Abstract Factory and Concrete Factories

The Abstract Factory interface declares methods for creating each type of product.

```java
// Abstract Factory Interface
public interface GUIFactory {
    Button createButton();
    Checkbox createCheckbox();
}

// Concrete Factory 1: Windows Factory
// Produces only Windows-compatible components
class WindowsFactory implements GUIFactory {
    @Override
    public Button createButton() {
        return new WindowsButton();
    }

    @Override
    public Checkbox createCheckbox() {
        return new WindowsCheckbox();
    }
}

// Concrete Factory 2: MacOS Factory
// Produces only MacOS-compatible components
class MacOSFactory implements GUIFactory {
    @Override
    public Button createButton() {
        return new MacOSButton();
    }

    @Override
    public Checkbox createCheckbox() {
        return new MacOSCheckbox();
    }
}
```

#### Step 3: The Client Application

The client code works with factories and products only through their abstract interfaces. This makes the client code independent of specific classes.

```java
class Application {
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
```

### Using Abstract Factory in the Main Method

In the **Java** `Main` class below, we simulate a configuration change to demonstrate how the application adapts to different factories.

```java
public class Main {
    // Simulates reading a config file (e.g., "Windows" or "MacOS")
    private static String osConfig = "Windows"; 

    public static void main(String[] args) {
        GUIFactory factory;

        // Initialize the factory based on configuration
        if (osConfig.equalsIgnoreCase("Windows")) {
            factory = new WindowsFactory();
        } else {
            factory = new MacOSFactory();
        }

        // The application is assembled using the chosen factory
        Application app = new Application(factory);
        
        System.out.println("--- App Launching ---");
        app.paint();
        
        System.out.println("\n--- Switching OS Context to MacOS ---");
        // Manually switching factory for demonstration
        factory = new MacOSFactory();
        Application macApp = new Application(factory);
        macApp.paint();
    }
}
```

**Output:**

```text
--- App Launching ---
Rendering a Windows style button.
Rendering a Windows style checkbox.

--- Switching OS Context to MacOS ---
Rendering a MacOS style button.
Rendering a MacOS style checkbox.
```

### Key Characteristics

* **Product Families:** The pattern emphasizes creating families of related objects (e.g., Button + Checkbox) that are designed to work together.
* **Consistency:** It guarantees that the client uses objects from the same family. You cannot accidentally mix a `WindowsButton` with a `MacOSCheckbox`.
* **Abstraction:** The client code (the `Application` class) is not coupled to concrete classes like `WindowsFactory`, only to the `GUIFactory` interface.

### Why It Matters

1. **Consistency Enforcement:** It ensures that products created by a factory are compatible with each other, preventing runtime errors caused by mismatched object types.
2. **Single Responsibility Principle:** It extracts the product creation code into one place, making the code easier to support and extend.
3. **Open/Closed Principle:** You can introduce new variants of products (e.g., a "Linux" family) without breaking existing client code.

**Abstract Factory Pattern** is a fundamental concept for building complex **Java** systems that need to support multiple environments or configurations, ensuring that all components within a specific context remain consistent and compatible.

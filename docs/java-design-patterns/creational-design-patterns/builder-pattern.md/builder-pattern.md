---
layout: default
title: Builder Pattern
parent: Creational Design Patterns
nav_order: 4
permalink: /docs/design-patterns/creational-design-patterns/builder-pattern
---


## Builder Pattern

**Design Patterns**: _Builder Pattern_.

The _Builder Pattern_ is a creational design pattern designed to solve the issue of the "Telescoping Constructor" anti-pattern, where a class has a large number of parameters in its constructor, making code difficult to read and maintain.

In **Java**, the Builder pattern separates the construction of a complex object from its representation, allowing the same construction process to create different representations. It is most commonly implemented using a static inner class to provide a fluent API for object creation.

### Implementing the Builder Pattern

To implement this pattern, we create a static nested `Builder` class within the main class. The main class constructor is made private, forcing client code to use the Builder.

In this example, we will model a `Computer` class. A computer has required parameters (HDD, RAM) and optional parameters (Graphics Card, Bluetooth).

```java
public class Computer {
    // Required parameters
    private String HDD;
    private String RAM;

    // Optional parameters
    private boolean isGraphicsCardEnabled;
    private boolean isBluetoothEnabled;

    // 1. Private Constructor
    // Takes the Builder object as a parameter to initialize fields
    private Computer(ComputerBuilder builder) {
        this.HDD = builder.HDD;
        this.RAM = builder.RAM;
        this.isGraphicsCardEnabled = builder.isGraphicsCardEnabled;
        this.isBluetoothEnabled = builder.isBluetoothEnabled;
    }

    // Getters
    public String getHDD() {
        return HDD; 
    }

    public String getRAM() {
        return RAM;
    }

    public boolean isGraphicsCardEnabled() {
        return isGraphicsCardEnabled;
    }

    public boolean isBluetoothEnabled() {
        return isBluetoothEnabled;
    }

    // Overriding toString method
    @Override
    public String toString() {
        return "Computer [HDD=" + HDD + ", RAM=" + RAM + 
               ", GraphicsCard=" + isGraphicsCardEnabled + 
               ", Bluetooth=" + isBluetoothEnabled + "]";
    }

    // 2. Static Inner Builder Class
    public static class ComputerBuilder {

        // Same fields as the outer class
        private String HDD;
        private String RAM;
        private boolean isGraphicsCardEnabled;
        private boolean isBluetoothEnabled;

        // 3. Constructor for Required Parameters
        public ComputerBuilder(String hdd, String ram) {
            this.HDD = hdd;
            this.RAM = ram;
        }

        // 4. Setter methods for Optional Parameters
        // These return the Builder object itself to allow method chaining (Fluent Interface)
        public ComputerBuilder setGraphicsCardEnabled(boolean isGraphicsCardEnabled) {
            this.isGraphicsCardEnabled = isGraphicsCardEnabled;
            return this;
        }

        public ComputerBuilder setBluetoothEnabled(boolean isBluetoothEnabled) {
            this.isBluetoothEnabled = isBluetoothEnabled;
            return this;
        }

        // 5. Build Method
        // Returns the final constructed object
        public Computer build() {
            return new Computer(this);
        }
    }
}
```

### Using Builder in the Main Method

In the **Java** `Main` class below, we create different configurations of a `Computer` using method chaining. This makes the code highly readable, as you can clearly see which parameters are being set.

```java
public class Main {
    public static void main(String[] args) {
        // Build a basic computer (only required args)
        Computer basicComputer = new Computer.ComputerBuilder("500 GB", "8 GB")
                .build();

        // Build a gaming computer (required + specific optional args)
        Computer gamingComputer = new Computer.ComputerBuilder("1 TB", "32 GB")
                .setGraphicsCardEnabled(true)
                .setBluetoothEnabled(true)
                .build();

        System.out.println("Basic Config: " + basicComputer);
        System.out.println("Gaming Config: " + gamingComputer);
    }
}
```

**Output:**

```text
Basic Config: Computer [HDD=500 GB, RAM=8 GB, GraphicsCard=false, Bluetooth=false]
Gaming Config: Computer [HDD=1 TB, RAM=32 GB, GraphicsCard=true, Bluetooth=true]
```

### Key Characteristics

* **Static Inner Class:** The builder is usually a static member of the class it builds.
* **Fluent Interface:** Setter methods in the builder return `this`, allowing calls to be chained (e.g., `.setX().setY().build()`).
* **Immutability:** The resulting object (e.g., `Computer`) is often immutable, as it has no public setter methods; its state is fixed once `.build()` is called.
* **Separation of Concerns:** Validations for complex parameters can be placed inside the `build()` method before the object is actually instantiated.
* **Maintaining Valid State:** Ensures that objects are constructed in a valid state, with all necessary properties correctly set.

### Why It Matters

1. **Readability:** It avoids the "Telescoping Constructor" problem where you have constructors like `Computer(String hdd, String ram, boolean gpu, boolean bluetooth, boolean wifi...)`. The Builder pattern makes it clear what each value represents.
2. **Flexibility:** You can create objects with various combinations of optional parameters without needing dozens of constructor overloads.
3. **Immutability:** It is an excellent way to create immutable classes in **Java** without initializing all fields in a single, massive constructor.
4. **Reducing Complexity:** Helps reduce the complexity of constructors with many parameters by providing a clear and systematic way to set both required and optional properties.

**Builder Pattern** is a fundamental concept for constructing complex objects cleanly and safely, ensuring that your **Java** code remains readable and less error-prone when dealing with classes that require multiple configuration options.

---
layout: default
title: Bridge Pattern
parent: Structural Design Patterns
nav_order: 1
permalink: /docs/design-patterns/structural-design-patterns/bridge-pattern
---

## Bridge Pattern

**Design Patterns**: _Bridge Pattern_.

The _Bridge Pattern_ is a structural design pattern that decouples an abstraction from its implementation so that the two can vary independently.

In **Java**, this pattern is used to avoid a permanent binding between an abstraction and its implementation.

This is particularly useful when you have a class explosion problem—for example, if you have a `Shape` class (Circle, Square) and a `Color` property (Red, Blue), creating a subclass for every combination (RedCircle, BlueCircle, RedSquare, BlueSquare) results in a Cartesian product of classes. The Bridge pattern splits these into two separate hierarchies: Abstraction (Shape) and Implementation (Color).

### Implementing the Bridge Pattern

To implement this pattern, we create two separate hierarchies:

1. **The Implementor:** An interface that defines the basic operations (e.g., `Color`).
2. **The Abstraction:** An abstract class that contains a reference to the Implementor (e.g., `Shape`).

In this example, we will separate the concept of a `Shape` from its `Color`.

#### Step 1: Create the Implementor Interface

This interface acts as the bridge for the specific implementation logic.

```java
// Implementor Interface
public interface Color {
    void applyColor();
}

// Concrete Implementor 1
class RedColor implements Color {
    @Override
    public void applyColor() {
        System.out.println("Applying red color.");
    }
}

// Concrete Implementor 2
class GreenColor implements Color {
    @Override
    public void applyColor() {
        System.out.println("Applying green color.");
    }
}
```

#### Step 2: Create the Abstraction

The Abstraction holds a reference to the `Color` interface. It delegates the color-related work to the object linked at runtime.

```java
// Abstraction
public abstract class Shape {
    // Composition - The "Bridge"
    protected Color color;

    // Constructor acts as the bridge setter
    public Shape(Color color) {
        this.color = color;
    }

    abstract public void draw();
}
```

#### Step 3: Create Refined Abstractions

These are the classes that extend the Abstraction. They use the `color` object reference to perform the specific operation.

```java
// Refined Abstraction 1
class Triangle extends Shape {
    public Triangle(Color color) {
        super(color);
    }

    @Override
    public void draw() {
        System.out.print("Triangle drawn. ");
        color.applyColor();
    }
}

// Refined Abstraction 2
class Pentagon extends Shape {
    public Pentagon(Color color) {
        super(color);
    }

    @Override
    public void draw() {
        System.out.print("Pentagon drawn. ");
        color.applyColor();
    }
}
```

### Using Bridge Pattern in the Main Method

In the **Java** `Main` class, we can mix and match shapes and colors dynamically without creating new classes for every combination.

```java
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
```

**Output:**

```text
Triangle drawn. Applying red color.
Triangle drawn. Applying green color.
Pentagon drawn. Applying red color.
Pentagon drawn. Applying green color.
```

### Key Characteristics

* **Decoupling:** It completely separates the interface (Abstraction) from the implementation. Changing one side does not force recompilation of the other.
* **Composition over Inheritance:** Instead of inheriting behavior (e.g., `class RedTriangle extends Triangle`), the class contains an instance of the behavior (`Has-A` relationship).
* **Orthogonal Hierarchies:** You can extend the `Shape` hierarchy (add `Circle`) and the `Color` hierarchy (add `Blue`) independently.

### Why It Matters

1. **Prevents Class Explosion:** Without the Bridge pattern, adding a new color would require adding a new subclass for every single existing shape. With Bridge, you add just one `Color` class.
2. **Runtime Flexibility:** You can switch implementations at runtime. For example, a GUI element could switch from a "Windows" implementation to a "Linux" implementation dynamically.
3. **Cleaner Code:** It keeps the classes small and focused on a single responsibility (Single Responsibility Principle).

**Bridge Pattern** is a fundamental concept for organizing code in large **Java** systems, ensuring that high-level abstractions can evolve separately from the low-level details of their implementation.

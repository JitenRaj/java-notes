---
layout: default
title: Decorator Pattern
parent: Structural Design Patterns
nav_order: 4
permalink: /docs/design-patterns/structural-design-patterns/decorator-pattern
---

## Decorator Pattern

**Design Patterns**: _Decorator Pattern_.

The _Decorator Pattern_ is a structural design pattern that allows you to attach new behaviors to objects by placing these objects inside special wrapper objects that contain the behaviors.

In **Java**, this is a powerful alternative to subclassing for extending functionality. Instead of creating a complex inheritance hierarchy (e.g., `CoffeeWithMilkAndSugar`), you create a base object and "decorate" it with features at runtime.

### Implementing the Decorator Pattern

To implement this pattern, we adhere to the **Open/Closed Principle**: classes should be open for extension but closed for modification. We achieve this by:

1. **Component Interface:** Defines the common methods for objects and decorators.
2. **Concrete Component:** The base object we want to decorate.
3. **Decorator Class:** An abstract class implementing the Component interface and holding a reference to a Component.
4. **Concrete Decorators:** Classes that extend the Decorator to add specific behavior.

In this example, we will model a Coffee shop system where we can dynamically add ingredients to a coffee.

#### Step 1: Create the Component Interface and Concrete Component

```java
// Component Interface
public interface Coffee {
    String getDescription();
    double getCost();
}

// Concrete Component (The base object)
class SimpleCoffee implements Coffee {
    @Override
    public String getDescription() {
        return "Simple Coffee";
    }

    @Override
    public double getCost() {
        return 5.00;
    }
}
```

#### Step 2: Create the Abstract Decorator

This class sits between the base component and the specific enhancements. It implements the interface and delegates calls to the wrapped object.

```java
// Abstract Decorator
public abstract class CoffeeDecorator implements Coffee {
    protected Coffee decoratedCoffee;

    public CoffeeDecorator(Coffee coffee) {
        this.decoratedCoffee = coffee;
    }

    @Override
    public String getDescription() {
        return decoratedCoffee.getDescription();
    }

    @Override
    public double getCost() {
        return decoratedCoffee.getCost();
    }
}
```

#### Step 3: Create Concrete Decorators

These classes add specific state or behavior (milk, sugar) to the component.

```java
// Concrete Decorator 1: Milk
class MilkDecorator extends CoffeeDecorator {
    public MilkDecorator(Coffee coffee) {
        super(coffee);
    }

    @Override
    public String getDescription() {
        // Delegate to original object, then add own description
        return super.getDescription() + ", Milk";
    }

    @Override
    public double getCost() {
        // Delegate to original object, then add own cost
        return super.getCost() + 1.50;
    }
}

// Concrete Decorator 2: Sugar
class SugarDecorator extends CoffeeDecorator {
    public SugarDecorator(Coffee coffee) {
        super(coffee);
    }

    @Override
    public String getDescription() {
        return super.getDescription() + ", Sugar";
    }

    @Override
    public double getCost() {
        return super.getCost() + 0.50;
    }
}
```

### Using Decorator in the Main Method

In the **Java** `Main` class below, we stack decorators on top of one another. Notice how we wrap the objects: `new Sugar(new Milk(new Coffee))`.

```java
public class Main {
    public static void main(String[] args) {
        // 1. Order a simple coffee (Base Component)
        Coffee myCoffee = new SimpleCoffee();
        System.out.println(myCoffee.getDescription() + " -> $" + myCoffee.getCost());

        // 2. Add Milk (Wrap the coffee in MilkDecorator)
        myCoffee = new MilkDecorator(myCoffee);
        System.out.println(myCoffee.getDescription() + " -> $" + myCoffee.getCost());

        // 3. Add Sugar (Wrap the result in SugarDecorator)
        myCoffee = new SugarDecorator(myCoffee);
        System.out.println(myCoffee.getDescription() + " -> $" + myCoffee.getCost());
    }
}
```

**Output:**

```text
Simple Coffee -> $5.0
Simple Coffee, Milk -> $6.5
Simple Coffee, Milk, Sugar -> $7.0
```

### Key Characteristics

* **Wrapper Architecture:** The pattern relies on wrapping the object. The wrapper implements the same interface as the wrapped object, so the client treats them identically.
* **Recursive Composition:** You can nest decorators recursively (e.g., a coffee with double sugar is just `Sugar(Sugar(Coffee))`).
* **Runtime Flexibility:** Unlike inheritance, which is static (compile-time), decorators allow you to mix and match behaviors dynamically at runtime.
* **Reusability:** You can create multiple decorators, which can be combined with various objects, promoting code reuse.

### Why It Matters

1. **Prevents Class Explosion:** Without decorators, you would need a unique class for every combination: `CoffeeWithMilk`, `CoffeeWithSugar`, `CoffeeWithMilkAndSugar`. The Decorator pattern reduces this combinatorial problem to just a few classes.
2. **Single Responsibility Principle:** You can divide a complex class with many variants into several smaller classes, each responsible for a specific feature.
3. **Transparency:** The client code doesn't need to know whether it's dealing with a simple object or a heavily decorated one, provided it relies on the interface.

**Decorator Pattern** is a fundamental concept in **Java** (famously used in `java.io` classes like `BufferedReader(new FileReader())`) that empowers developers to extend object functionality flexibly without modifying existing code.

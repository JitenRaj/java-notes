---
layout: default
title: Abstraction
parent: OOPs Concepts
nav_order: 7
---

## Abstraction

**Object-Oriented Programming (OOP)**: _Abstraction_.

Abstraction is the process of hiding the implementation details and showing only the functionality to the user. In other words, it shows only essential things to the user and hides the internal details, for example, sending an SMS where you type the text and send the message. You don't know the internal processing about the message delivery.

### What is Abstraction?

Abstraction allows you to focus on _what_ the object does rather than _how_ it does it. It reduces programming complexity and effort.

In **Java**, abstraction is mainly achieved in two ways:

1. **Abstract Class** (0 to 100% abstraction)
2. **Interface** (100% abstraction)

### Abstract Classes

A class which is declared with the `abstract` keyword is known as an abstract class in **Java**. It can have abstract and non-abstract methods (methods with the body). It needs to be extended and its method implemented. It cannot be instantiated.

Here is an example of an abstract class `Vehicle`:

```java
// Abstract class
abstract class Vehicle {
    // Constructor in abstract class
    public Vehicle() {
        System.out.println("Vehicle is created");
    }

    // Abstract method (does not have a body)
    abstract void start();

    // Concrete method (has a body)
    public void stop() {
        System.out.println("Vehicle stopped");
    }
}

// Concrete class Car extends Vehicle
class Car extends Vehicle {
    @Override
    void start() {
        System.out.println("Car starts with key");
    }
}

// Concrete class Bike extends Vehicle
class Bike extends Vehicle {
    @Override
    void start() {
        System.out.println("Bike starts with kick");
    }
}
```

In this example, `Vehicle` is an abstract class that contains an abstract method `start()` and a concrete method `stop()`. The classes ``Car` and `Bike` provide specific implementations for the `start()` method.

### Interfaces

An _interface_ in Java is a blueprint of a _class_. It has static constants and abstract methods. The interface in Java is a mechanism to achieve abstraction. There can be only abstract methods in the Java interface, not method body. It is used to achieve abstraction and multiple inheritance in Java.

Here is an example of an interface `Drawable`:

```java
// Interface declaration
interface Drawable {
    void draw();
}

// Implementation: Rectangle class implements Drawable
class Rectangle implements Drawable {
    public void draw() {
        System.out.println("Drawing a rectangle");
    }
}

// Implementation: Circle class implements Drawable
class Circle implements Drawable {
    public void draw() {
        System.out.println("Drawing a circle");
    }
}
```

In this snippet, `Drawable` is an interface with a single method `draw()`. The Rectangle and `Circle` classes implement this interface and provide their own logic for drawing.

### Implementing Abstraction in the Main Method

Let's see how we can use these classes and interfaces in a `Main` method. We can use the parent class (or interface) reference to hold the child class object, which is a common practice in OOP to achieve flexibility.

```java
public class Main {
    public static void main(String[] args) {
        // Using Abstract Class
        Vehicle myCar = new Car();
        myCar.start();  // Calls the Car's implementation of start
        myCar.stop();   // Calls the inherited concrete method from Vehicle

        System.out.println("---");

        // Using Interface
        Drawable shape1 = new Circle();
        shape1.draw();  // Calls the Circle's implementation of draw

        Drawable shape2 = new Rectangle();
        shape2.draw();  // Calls the Rectangle's implementation of draw
    }
}
```

**Output:**

```text
Vehicle is created
Car starts with key
Vehicle stopped
---
Drawing a circle
Drawing a rectangle
```

Note that when `new Car()` is called, the constructor of the abstract class `Vehicle` is invoked first.

### Key Characteristics

* **Abstract Class vs Interface:** An abstract class is used when you want to share code among several closely related classes, whereas an interface is used to define a contract for unrelated classes.

* **Instantiation:** Neither abstract classes nor interfaces can be instantiated directly. You must create an instance of a concrete subclass or implementing class.

* **Constructors:** Abstract classes can have constructors (as seen in the `Vehicle` example), while interfaces cannot.

* **Multiple Inheritance:** A class can implement multiple interfaces but can only extend one abstract class.

### Why It Matters

Abstraction is powerful for several reasons:

1. **Security:** It helps in hiding the internal details of the implementation and only revealing the relevant operations to the user.

2. **Simplicity:** It reduces the complexity of viewing things. By using an interface, you separate the definition of a method from the inheritance hierarchy.

3. **Maintainability:** It allows you to change the internal implementation without affecting the classes that use it, as long as the method signature remains the same.

**Abstraction** is a fundamental concept that enables us to design complex systems by breaking them down into manageable, abstract layers.

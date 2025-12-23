---
layout: default
title: Enum
parent: OOPs Concepts
nav_order: 3
---

## Enumerations

**Object-Oriented Programming (OOP)**: _Enumerations_.

An Enumeration (often shortened to Enum) is a special data type that allows a variable to be one of a set of predefined constants.

### Defining the Enumeration

An `enum` in **Java** is defined using `enum` keyword. An Enum is used to define a fixed set of named constants.

Here’s a simple example of it, the code begins by defining the `Planet` enum with constants for each planet. Each constant in the `Planet` enum represents a distinct planet and is instantiated with specific mass and radius values:

```java
public enum Planet {
    MERCURY(3.303e+23, 2.4397e6),
    VENUS(4.869e+24, 6.0518e6),
    EARTH(5.976e+24, 6.37814e6),
    MARS(6.421e+23, 3.3972e6),
    JUPITER(1.9e+27, 7.1492e7),
    SATURN(5.688e+26, 6.0268e7),
    URANUS(8.686e+25, 2.5559e7),
    NEPTUNE(1.024e+26, 2.4746e7);
}
```

Each constant (`MERCURY`, `VENUS`, etc.) is an instance of the `Planet` enum, initialized with a specific mass and radius. Declaring these constants ensures that the set of values for `Planet` is fixed and known at compile time.

### Constructor and Fields

Enums in Java can also have fields, methods, and constructors. Below is the `Planet` enum with private fields used to store the mass and radius of each planet:

```java
public enum Planet {
    MERCURY(3.303e+23, 2.4397e6),
    VENUS(4.869e+24, 6.0518e6),
    EARTH(5.976e+24, 6.37814e6),
    MARS(6.421e+23, 3.3972e6),
    JUPITER(1.9e+27, 7.1492e7),
    SATURN(5.688e+26, 6.0268e7),
    URANUS(8.686e+25, 2.5559e7),
    NEPTUNE(1.024e+26, 2.4746e7);

    private final double mass;   // in kilograms
    private final double radius; // in meters

    Planet(double mass, double radius) {
        this.mass = mass;
        this.radius = radius;
    }
}
```

These fields are declared as `final`, meaning they are immutable once initialized. This immutability is crucial because it ensures that the values associated with each constant do not change after they are set, preserving the integrity and consistency of the constants throughout the application's lifecycle.

The constructor `Planet(double mass, double radius)` is invoked for each enum constant to assign the specific mass and radius values.

### Methods

The `Planet` enum includes methods that provide functionality typical for a class. For example, methods to return the mass and radius values are defined as follows:

```java
public double getMass() {
    return mass;
}

public double getRadius() {
    return radius;
}
```

This demonstrates that enums can not only act as fixed sets of constants but also encapsulate data and behavior associated with each constant.

### Calculating Surface Gravity and Weight

Enums can also have additional methods to perform operations. The `surfaceGravity` method within the `Planet` enum calculates a planet's surface gravity using its mass and radius:

```java
public double surfaceGravity() {
    final double G = 6.67300E-11; // gravitational constant in m^3 kg^-1 s^-2 (E-11 signifies 10^-11)
    return G * mass / (radius * radius);
}
```

Here, the method utilizes the unified gravitational constant `G` and the instance variables `mass` and `radius` to compute gravity.

The `surfaceWeight` method calculates how much an object would weigh on the planet:

```java
public double surfaceWeight(double otherMass) {
    return otherMass * surfaceGravity();
}
```

The `surfaceWeight` method calls `surfaceGravity()` and multiplies it by the object's mass (`otherMass`). This illustrates how enums can encapsulate detailed behaviors and calculations related to their constants.

### Bringing It All Together

Here is the final, complete `Planet` enum that we've developed throughout the lesson:

```java
public enum Planet {
    MERCURY(3.303e+23, 2.4397e6),
    VENUS(4.869e+24, 6.0518e6),
    EARTH(5.976e+24, 6.37814e6),
    MARS(6.421e+23, 3.3972e6),
    JUPITER(1.9e+27, 7.1492e7),
    SATURN(5.688e+26, 6.0268e7),
    URANUS(8.686e+25, 2.5559e7),
    NEPTUNE(1.024e+26, 2.4746e7);

    private final double mass;   // in kilograms
    private final double radius; // in meters

    Planet(double mass, double radius) {
        this.mass = mass;
        this.radius = radius;
    }

    public double getMass() {
        return mass;
    }

    public double getRadius() {
        return radius;
    }

    public double surfaceGravity() {
        final double G = 6.67300E-11;
        return G * mass / (radius * radius);
    }

    public double surfaceWeight(double otherMass) {
        return otherMass * surfaceGravity();
    }
}
```

This `Planet` enum encapsulates not just the basic attributes of each planet, such as mass and radius, but also includes methods to calculate surface gravity and surface weight, making it a robust tool for planetary calculations.

### Putting It Into Practice

Now that we have the complete `Planet` enum, let's see how it can be used in a practical context:

```java
public class Main {
    public static void main(String[] args) {
        Planet earth = Planet.EARTH;
        System.out.println("The mass of " + earth + " is " + earth.getMass() + " kg.");
        System.out.println("The radius of " + earth + " is " + earth.getRadius() + " meters.");
    }
}
```

In this example, we retrieve the `EARTH` constant from the `Planet` enum and use it to access its `getMass()` and `getRadius()` methods.

**Output:**

```text
The mass of EARTH is 5.976E24 kg.
The radius of EARTH is 6378140.0 meters.
```

The output shows the mass and radius values for Earth, illustrating how enum constants can encapsulate attributes and provide methods for accessing them.

This demonstrates how to leverage the methods we've defined to obtain specific information about each planet, making your code both intuitive and powerful.

### Why Choose Enums

Enumerations in Java are powerful because they bring several advantages:

* **Type Safety:** They ensure that variables of the enum type can only hold predefined values, helping to avoid errors related to invalid values.
* **Code Readability:** Enumerations improve readability by grouping related constants and their associated behaviors in a cohesive manner, making the code easier to understand.
* **Maintainability:** By encapsulating related constants and their behaviors, enums help keep the code maintainable. Changes and updates are easier to manage within a well-defined structure.

Using the `Planet` enum example, we can see these advantages in action. Enums are used not just to define constants (like the mass and radius of planets) but also to associate detailed data and behavior (such as calculating surface gravity and weight) with those constants. This leads to cleaner and more maintainable code, demonstrating how enums can transform the way you write and organize your Java programs.

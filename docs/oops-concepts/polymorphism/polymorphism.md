---
layout: default
title: Polymorphism
parent: OOPs Concepts
nav_order: 6
---

## Polymorphism

**Object-Oriented Programming (OOP)**: _Polymorphism_.

Polymorphism is considered one of the most important features of Object-Oriented Programming. The word polymorphism comes from Greek, meaning "many forms." In programming, it refers to the ability of an object, variable, or function to take on multiple forms.

### What is Polymorphism?

Polymorphism allows us to perform a single action in different ways. It allows you to define one interface and have multiple implementations.

In **Java**, polymorphism is mainly divided into two types:

1. **Compile-time Polymorphism** (Static Binding): Achieved through method overloading.
2. **Run-time Polymorphism** (Dynamic Binding): Achieved through method overriding.

### Compile-time Polymorphism (Method Overloading)

Compile-time polymorphism allows a class to have multiple methods with the same name but different parameter lists. This is known as _Method Overloading_. The compiler determines which method to call based on the number and types of arguments passed.

Here is an example of a `MathUtils` class that overloads the `add` method:

```java
public class MathUtils {
    // Method to add two integers
    public int add(int a, int b) {
        return a + b;
    }

    // Overloaded method to add three integers
    public int add(int a, int b, int c) {
        return a + b + c;
    }

    // Overloaded method to add two doubles
    public double add(double a, double b) {
        return a + b;
    }
}
```

In this example, the `add` method is overloaded three times. The first version takes two integers, the second takes three integers, and the third takes two doubles. The compiler decides which method to invoke at compile time.

### Run-time Polymorphism (Method Overriding)

Run-time polymorphism occurs when a subclass provides a specific implementation for a method that is already defined in its parent class. This is known as Method Overriding. The decision of which method to call is made at runtime based on the object instance, not the reference type.

Let's look at an example using an `Animal` base class and derived classes `Dog` and `Cat`:

```java
// Base class
class Animal {
    public void sound() {
        System.out.println("The animal makes a sound");
    }
}

// Derived class Dog
class Dog extends Animal {
    @Override
    public void sound() {
        System.out.println("The dog barks");
    }
}

// Derived class Cat
class Cat extends Animal {
    @Override
    public void sound() {
        System.out.println("The cat meows");
    }
}
```

In this snippet, both `Dog` and `Cat` classes override the `sound` method of the `Animal` class. The `@Override` annotation is used to indicate that the method is intended to override a method in the superclass.

### Implementing Polymorphism in the Main Method

To see run-time polymorphism in action, we can use a parent class reference to hold a child class object.

```java
public class Main {
    public static void main(String[] args) {
        // Create an Animal reference, but assign a Dog object
        Animal myDog = new Dog();
        
        // Create an Animal reference, but assign a Cat object
        Animal myCat = new Cat();

        myDog.sound(); // Calls the Dog's version of sound
        myCat.sound(); // Calls the Cat's version of sound
    }
}
```

Here, the reference variable is of type `Animal`, but the objects created are of types `Dog` and `Cat`. When `sound()` is called, Java determines at runtime which version of the method to execute based on the actual object (`Dog` or `Cat`), not the reference type (`Animal`).

Output:

```text
The dog barks
The cat meows
```

This behavior demonstrates dynamic binding, where the method call is resolved during execution.

### Key Characteristics

Upcasting: Polymorphism allows you to refer to a child object using a parent class reference (e.g., `Animal a = new Dog();`). This is useful for creating flexible code that can handle different object types uniformly.

* **Dynamic Binding:** In run-time polymorphism, the method to be executed is determined at runtime.

* **Static Binding:** In compile-time polymorphism (overloading), the method to be executed is determined at compile time.

### Why It Matters

Polymorphism is powerful for several reasons:

1. **Flexibility:** It allows you to write code that can work with objects of different types as long as they share a common superclass or interface.

2. **Extensibility:** You can add new classes (e.g., a Cow class) that extend the base class without modifying the existing code that uses the base class.

3. **Simplicity:** It reduces complexity by allowing you to use the same method name for similar actions (like add or sound) across different types.

**Polymorphism** enables us to design systems that are easily validatable and scalable, making it a cornerstone of efficient Java application development.

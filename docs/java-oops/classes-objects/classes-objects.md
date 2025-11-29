---
layout: default
title: Classes and Objects
parent: Java OOPs Concepts
nav_order: 1
permalink: /docs/java-oops/classes-objects
---

## Classes and Objects

**Object-Oriented Programming (OOP)**: _Classes and Objects_.

Classes and objects are the building blocks of OOP. A `class` acts as a blueprint for creating objects, which are instances of the `class`. Understanding these basics is essential before moving on to more advanced OOP topics like inheritance, polymorphism, and encapsulation.

### Defining a Class

In **Java**, a `class` is defined using the `class` keyword. A class serves as a blueprint to create objects. Here's a simple example:

```java
// Defining a class named Person

public class Person {
    // Data members of the class (name and age)
    private String name;
    private int age;
}
```

A `class` can have fields (variables), methods (functions), and constructors. In this snippet, we define a `Person` class with data members `name` and `age`.

### What is an Object?

An object is an instance of a class. It represents a specific example of the class and holds the characteristics that define the class.

Objects have three main characteristics:

* **State:** The data or attributes of the object. In the `Person` class, the `name` and `age` are the object's state.
* **Behavior:** The methods and functions that the object can perform. In the `Person` class, the `display` method is an example of behavior.
* **Identity:** A unique identifier that distinguishes the object from others, even if they have the same state. This is handled by the memory address in the JVM.

An object is thus a concrete instance of a class that includes state, behavior, and identity.

### Using Constructors

Constructors initialize the newly created object's state. In the `Person` class, we can define a constructor to set the name and age:

```java
public class Person {
    private String name;
    private int age;
    
    // Constructor that initializes the object with a name and age
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
```

In the constructor, `this` refers to the current instance of the class. It is used to distinguish the class's fields from the parameters with the same names.

### Overloading Constructors

**Java** allows you to have multiple constructors in a class, each with a different parameter list. This is known as _constructor overloading_. Overloading constructors allows you to create objects in different ways, depending on the parameters provided.

Here's an example of a class with overloaded constructors:

```java
public class Car {
    private String brand;
    private int year;

    // Constructor with both brand and year
    public Car(String brand, int year) {
        this.brand = brand;
        this.year = year;
    }

    // Constructor with only brand, default year is set
    public Car(String brand) {
        this(brand, 2020);  // Calls the other constructor
    }

    // Constructor with no parameters, default values are set
    public Car() {
        this("Unknown", 2000);  // Calls the constructor with two parameters
    }
}
```

In this example, the `Car` class has three constructors:

1. One that takes both `brand` and `year` as parameters.
2. One that takes only `brand` and sets a default value for `year`.
3. One that takes no parameters and sets default values for both `brand` and `year`.

Overloading constructors provides flexibility in how you create instances of a class.

### Copy Constructors

A copy constructor in **Java** is a special type of constructor used to create a new object as a copy of an existing object. It takes a single parameter: an object of the same class. The fields of the new object are initialized with the values of the fields of the existing object, effectively cloning the object. This is useful when you want to create a duplicate object with the same state.

Here’s how a copy constructor looks:

```java
public class Car {
    private String brand;
    private int year;

    // Regular constructor
    public Car(String brand, int year) {
        this.brand = brand;
        this.year = year;
    }

    // Copy constructor
    public Car(Car other) {
        this.brand = other.brand;
        this.year = other.year;
    }
}
```

In the example above, the copy constructor ensures that all fields (`brand` and `year`) from the original `Car` object are accurately copied to the new `Car` object.

### Member Functions

Member functions define the behavior of the object. For the `Person` class, we can define a method to display the object's data:

```java
public class Person {
    private String name;
    private int age;
    
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // Member function to display the object's data
    public void display() {
        System.out.println("Name: " + name + ", Age: " + age);
    }
}
```

### Creating Objects from Classes

Once you have defined a class, you can create objects (instances of the class). Here’s how we can create and use objects of the `Person` class:

```java
public class Person {
    // Data members of the class (name and age)
    private String name;
    private int age;

    // Constructor that initializes the object with a name and age
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // Member function to display the object's data
    public void display() {
        System.out.println("Name: " + name + ", Age: " + age);
    }
}

public class Main {
    public static void main(String[] args) {
        Person person = new Person("Alice", 30);  // Creating an object using the new keyword
        person.display();                         // Displaying the object's data
    }
}
```

Here, we create an object, `person`, with the name "Alice" and age 30 by using the `new` keyword along with the `Person` class's constructor. The inputs "Alice" and 30 are passed directly to the constructor to initialize the object's state.

The object then uses its `display` method to print its data to the console. This demonstrates how to instantiate a class and call its member functions in a Java program.

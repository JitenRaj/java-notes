---
layout: default
title: Classes and Objects
parent: OOPs Concepts
nav_order: 1
---

## Classes and Objects

**Object-Oriented Programming (OOP)**: _Classes, Objects and Constructors_.

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

Constructors are special methods used to initialize a newly created object's state. They have the same name as the class and no return type.

#### Default vs Parameterized Constructors

1. **Default Constructor:** If you don't define any constructor, Java inserts a default one that sets values to null/0.
2. **Parameterized Constructor:** Allows you to initialize an object with specific values.

In the `Person` class, we can define a constructor to set the name and age:

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

### Initialization Order

When you create an object (e.g., `new Car()`), code executes in a specific order. It's not just the constructor that runs!

**The Order of Execution:**

1. **Static Blocks / Fields:** Run once when the class is loaded by the ClassLoader.
2. **Instance Initialization Blocks / Fields:** Run every time an object is created, _before_ the constructor.
3. **Constructor:** Runs last to perform final initialization.

```java
public class Demo {
    // 1. Static Block
    static {
        System.out.println("Step 1: Static Block (Class Loaded)");
    }

    // 2. Instance Block
    {
        System.out.println("Step 2: Instance Block (Object Created)");
    }

    // 3. Constructor
    public Demo() {
        System.out.println("Step 3: Constructor Executed");
    }

    public static void main(String[] args) {
        new Demo();
    }
}

```

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

### Object Destruction (Garbage Collection)

In languages like C++, we must manually destroy objects to free memory. In **Java**, this process is automatic.

* **Garbage Collection (GC):** A background process in the JVM that identifies objects that are no longer in use (unreachable) and deletes them to free up Heap memory.
* **Destructors?** Java does _not_ have destructors.
* **The `finalize()` method:** Historically, Java had a `finalize()` method, but it is **deprecated** and should not be used.

{: .important }
If we need to close resources (like files or database connections), we should use the `try-with-resources` statement or an explicit `close()` method rather than relying on the Garbage Collector.

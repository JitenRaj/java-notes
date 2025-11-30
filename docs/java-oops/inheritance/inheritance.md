---
layout: default
title: Inheritance
parent: Java OOPs Concepts
nav_order: 3
permalink: /docs/java-oops/inheritance
---

## Inheritance

**Object-Oriented Programming (OOP)**: _Inheritance_.

Inheritance allows us to create a new class based on an existing class. By using inheritance, we can reuse code, add new features, and make our programs easier to manage and understand.

### What Inheritance Is

Inheritance is a way to establish a relationship between a new class (`derived` class / `child` class) and an existing class (`base` class / `parent` class). The derived class inherits properties and behaviors (methods) from the base class.

To better understand this concept, we'll use an example involving a `Person` class as the `base` class and a `Employee` class as the `derived` class. This example will help demonstrate how properties and methods are inherited from the `base` class and how additional features can be added to the `derived` class.

### Base Class: Person

Let’s start by defining a `Person` class, which will act as the `base` class in our example:

```java
// Define the base class Person with name and age attributes
class Person {
    private String name;
    private int age;

    // Constructor to initialize name and age
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // Display method to show name and age
    public void display() {
        System.out.println("Name: " + name + ", Age: " + age);
    }
}
```

In this snippet, the `Person` class is defined with private attributes name and age. It also includes a constructor to initialize these attributes and a display method to print the details.

### Derived Class: Employee

Now, let’s create a `Employee` class that inherits from the `Person` class:

```java
// Define the derived class Employee, inheriting from Person
class Employee extends Person {
    private String role;

    // Constructor to initialize name, age of the base class, and the role of the employee
    public Employee(String name, int age, String role) {
        super(name, age);
        this.role = role;
    }

    // Method to display role of the employee
    public void displayRole() {
        System.out.println("Role: " + role);
    }
}
```

In the `Employee` class, we use the `extends` keyword to inherit from `Person`. The `Employee` class reuses the name and age attributes from the `Person` class, and it adds a new attribute, role. The constructor uses the `super` keyword to call the `base` class constructor and initialize the inherited properties. The `Employee` class also adds a `displayRole` method to show the employee’s role.

### Using Inheritance in the Main Method

Finally, let’s see how we can use these classes in a Main method:

```java
public class Main {
    public static void main(String[] args) {
        // Create a Employee object and display its details
        Employee employee = new Employee("Bob", 25, "Computer Science");
        employee.display();  // Calls the display method from Person class
        employee.displayrole();  // Calls the displayrole method from Employee class
    }
}
```

In the `Main` class, we create an instance of `Employee` and use it to call methods from both the `Person` class (inherited) and the `Employee` class (specific to `Employee`).

### Key Concepts of Java Inheritance

* **Chaining Inheritance:** In Java, you can create a chain of inheritance where a class inherits from another class, which in turn can be inherited by yet another class. This allows you to build complex class hierarchies where each class extends the functionality of the previous one.

* **Single Inheritance with Classes:** Java supports single inheritance, meaning a class can only inherit from one superclass. This is designed to keep the class hierarchy simple and avoid ambiguity that could arise from inheriting from multiple classes.

* **Multiple Inheritance via Interfaces:** Although Java doesn’t support multiple inheritance with classes, it allows a class to implement multiple interfaces. This approach provides the flexibility of multiple inheritance, enabling a class to inherit behavior from multiple sources.

* **Final Keyword:** The `final` keyword in Java can be used to prevent a class from being inherited. When a class is declared as `final`, it cannot be extended by any other class, ensuring that its behavior remains unaltered in subclasses.

Understanding these limitations and specifications of inheritance in Java helps us design effective and maintainable object-oriented systems.

### Why It Matters

Inheritance is powerful for several reasons:

1. **Code Reusability:** Instead of rewriting common functionalities, you can inherit them from a base class, making maintenance easier and reducing errors.
2. **Extension:** You can extend existing code by adding new features to a derived class without changing the existing base class.
3. **Hierarchy:** It helps in organizing code in a hierarchical manner, which reflects real-world relationships and improves code readability and structure.

**Inheritance** is a cornerstone of OOP, and understanding it will enable us to design more flexible and scalable applications. It's an essential concept for mastering OOP.

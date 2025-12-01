---
layout: default
title: Encapsulation
parent: Java OOPs Concepts
nav_order: 6
permalink: /docs/java-oops/encapsulation
---

## Encapsulation

**Object-Oriented Programming (OOP)**: _Encapsulation_.

Encapsulation is defined as the wrapping up of data (variables) and code acting on the data (methods) together as a single unit. In encapsulation, the variables of a class will be hidden from other classes, and can be accessed only through the methods of their current class. Therefore, it is also known as **data hiding**.

### What is Encapsulation?

Encapsulation acts as a protective shield that prevents the data from being accessed by the code outside this shield.

In **Java**, encapsulation is achieved by:

1. Declaring the variables of a class as `private`.
2. Providing `public` setter and getter methods to modify and view the variables values.

### Implementing Encapsulation

To implement encapsulation, we define a class where the fields are private, meaning they cannot be accessed directly from outside the class. We then provide public methods (getters and setters) to access and update these fields.

Here is an example of an encapsulated `Account` class:

```java
public class Account {
    // Private data members
    private long accountNumber;
    private String name;
    private double amount;

    // Public Getter and Setter methods

    public long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
```

In this example, the `accountNumber`, `name`, and `amount` fields are _private_. They can only be accessed or modified via the _public_ methods like `getName()` or `setName()`.

### Using Encapsulation in the Main Method

Let's see how we can interact with the `Account` class. Since the fields are _private_, we cannot do `acc.name = "John"`. Instead, we must use the methods provided.

```java
public class Main {
    public static void main(String[] args) {
        // Creating an object of Account class
        Account acc = new Account();

        // Setting values through setter methods
        acc.setAccountNumber(123456789L);
        acc.setName("John Doe");
        acc.setAmount(5000.50);

        // Getting values through getter methods
        System.out.println("Account Number: " + acc.getAccountNumber());
        System.out.println("Name: " + acc.getName());
        System.out.println("Amount: " + acc.getAmount());
    }
}
```

**Output:**

```text
Account Number: 123456789
Name: John Doe
Amount: 5000.5
```

This approach ensures that the fields are accessed in a controlled manner.

### Read-Only and Write-Only Classes

Encapsulation allows you to make a class Read-Only or Write-Only by omitting the setter or getter methods, respectively.

**Read-Only Class:** If you only provide getter methods, the class becomes read-only. External classes can read the data but cannot modify it.

```java
public class ReadOnlyStudent {
    private String college = "MIT";

    // Only getter, no setter
    public String getCollege() {
        return college;
    }
}
```

**Write-Only Class:** If you only provide setter methods, the class becomes write-only. External classes can modify the data but cannot read it.

```java
public class WriteOnlyStudent {
    private String college;

    // Only setter, no getter
    public void setCollege(String college) {
        this.college = college;
    }
}
```

### Key Characteristics

* **Data Hiding:** The user will have no idea about the inner implementation of the class. It will not be visible to the user how the class is storing values in the variables.

* **Increased Flexibility:** We can make the variables of the class read-only or write-only depending on our requirement.

* **Reusability:** Encapsulated code is easier to reuse in other parts of the application or in different applications.

### Why It Matters

Encapsulation is powerful for several reasons:

1. **Control:** It provides you with control over the data. Suppose you want to set the value of `age` inside the setter method only if it is greater than 18. You can write the logic inside the setter method. If you don't use encapsulation, anyone can change the age variable to a negative number or any invalid value.

2. **Testing:** It is easier to test encapsulated code because it is isolated from other parts of the application.

3. **Maintainability:** You can change the code of the class (e.g., changing the data type of a variable) without affecting the other classes that use it, provided the method signatures remain the same.

**Encapsulation** is essential for creating robust and maintainable applications by ensuring that objects manage their own state and behavior.

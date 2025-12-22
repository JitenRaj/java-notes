---
layout: default
title: Factory Method Pattern
parent: Creational Design Patterns
nav_order: 2
---

## Factory Pattern

**Design Patterns**: _Factory Method Pattern_.

The _Factory Pattern_ is a creational design pattern that provides an interface for creating objects in a superclass, but allows subclasses to alter the type of objects that will be created.

In **Java**, this pattern is widely used to abstract the object instantiation process, separating the logic of _creating_ an object from the business logic that _uses_ the object.

This promotes loose coupling and adherence to the _Open/Closed Principle_.

### Implementing the Factory Pattern

To implement this pattern, we define a common interface (or abstract class) and create multiple concrete classes implementing that interface. A separate "Factory" class is then responsible for determining which concrete class to instantiate based on provided input.

In this example, we will create a `Notification` system where different types of notifications (SMS, Email, Push) are created dynamically.

#### Step 1: Create the Interface and Concrete Implementations

```java
// Common interface for all notification types
public interface Notification {
    void notifyUser();
}

// Concrete Implementation 1: SMS
class SMSNotification implements Notification {
    @Override
    public void notifyUser() {
        System.out.println("Sending an SMS Notification...");
    }
}

// Concrete Implementation 2: Email
class EmailNotification implements Notification {
    @Override
    public void notifyUser() {
        System.out.println("Sending an Email Notification...");
    }
}

// Concrete Implementation 3: Push
class PushNotification implements Notification {
    @Override
    public void notifyUser() {
        System.out.println("Sending a Push Notification...");
    }
}
```

#### Step 2: Create the Factory Class

The Factory class contains the logic to decide which object to create. This keeps the client code clean.

```java
public class NotificationFactory {
    // The Factory Method
    // Takes a parameter (e.g., String) to determine the type of object to return.
    public Notification createNotification(String channel) {
        if (channel == null || channel.isEmpty()) {
            return null;
        }
        
        switch (channel.toLowerCase()) {
            case "sms":
                return new SMSNotification();
            case "email":
                return new EmailNotification();
            case "push":
                return new PushNotification();
            default:
                throw new IllegalArgumentException("Unknown channel: " + channel);
        }
    }
}
```

### Using Factory Pattern in the Main Method

In the **Java** `Main` class below, the client code does not use the `new` keyword to create specific notification objects (like `new SMSNotification()`). Instead, it asks the factory to create them.

```java
public class Main {
    public static void main(String[] args) {
        // Create the factory
        NotificationFactory factory = new NotificationFactory();

        // 1. Get an SMS Notification
        Notification msg1 = factory.createNotification("sms");
        msg1.notifyUser();

        // 2. Get an Email Notification
        Notification msg2 = factory.createNotification("email");
        msg2.notifyUser();

        // 3. Get a Push Notification
        Notification msg3 = factory.createNotification("push");
        msg3.notifyUser();
    }
}
```

**Output:**

```text
Sending an SMS Notification...
Sending an Email Notification...
Sending a Push Notification...
```

### Key Characteristics

* **Decoupling:** The client code is decoupled from the specific classes it instantiates. It only needs to know about the `Notification` interface and the `NotificationFactory`.
* **Abstraction:** The implementation details of object creation are hidden behind the factory method.
* **Polymorphism:** The factory returns objects referred to by their common interface type, allowing the client to treat different implementations uniformly.

### Why It Matters

1. **Flexibility and Scalability:** You can introduce new types of notifications (e.g., `SlackNotification`) into the system without breaking existing client code. You only need to update the Factory class.
2. **Testability:** Because the creation logic is centralized, it is easier to mock or stub objects during testing.
3. **Cleaner Code:** It eliminates the need for complex `if-else` or `switch` statements scattered throughout the application whenever a specific object type needs to be created.

**Factory Pattern** is a fundamental concept that simplifies complex codebases by centralizing object lifecycle management, making **Java** applications easier to extend and maintain over time.

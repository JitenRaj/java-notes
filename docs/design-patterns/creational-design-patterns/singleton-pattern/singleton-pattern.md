---
layout: default
title: Singleton Pattern
parent: Creational Design Patterns
nav_order: 1
---

## Singleton Pattern

**Design Patterns**: _Singleton Pattern_.

The _Singleton_ pattern is a creational design pattern that ensures a class has only one instance and provides a global point of access to that instance.

In **Java**, this is useful when exactly one object is needed to coordinate actions across the system, such as managing a connection to a database, a logging service, or a configuration manager. By restricting instantiation, the Singleton pattern controls object creation and conserves system resources.

### Implementing a Singleton

To implement a Singleton in **Java**, you must ensure that no other class can instantiate a new object of the class. This is achieved by:

1. **Private Constructor:** Prevents other classes from instantiating the Singleton class directly.
2. **Static Inner Class (or Variable):** Creating a `private static` variable to hold the single instance.
3. **Public Global Access Point:** A static method (usually `getInstance()`) that returns the unique instance.

Below is an example of a `DatabaseConnector` class implemented as a Singleton using _lazy initialization_, meaning the instance is created only when it is first requested.

```java
public class DatabaseConnector {

    // 1. Private static variable of the same class that is the only instance of the class.
    private static DatabaseConnector instance;

    // 2. Private constructor to restrict instantiation from other classes.
    private DatabaseConnector() {
        System.out.println("Initializing Database Connection...");
    }

    // 3. Public static method that returns the instance of the class.
    // This uses "Lazy Initialization": the instance is created only when requested.
    public static DatabaseConnector getInstance() {
        if (instance == null) {
            instance = new DatabaseConnector();
        }
        return instance;
    }

    // A sample method to demonstrate functionality
    public void executeQuery(String query) {
        System.out.println("Executing query: " + query);
    }
}
```

### Using Singleton in the Main Method

In the following **Java** `Main` class, we attempt to get the instance of the `DatabaseConnector` twice. Because it is a Singleton, both variables will point to the exact same object in memory.

```java
public class Main {
    public static void main(String[] args) {
        // First request for the instance
        System.out.println("Requesting first connection instance...");
        DatabaseConnector db1 = DatabaseConnector.getInstance();
        db1.executeQuery("SELECT * FROM users");

        System.out.println("---------------------------------");

        // Second request for the instance
        System.out.println("Requesting second connection instance...");
        DatabaseConnector db2 = DatabaseConnector.getInstance();
        db2.executeQuery("SELECT * FROM products");

        System.out.println("---------------------------------");

        // Verify that both variables refer to the same object
        if (db1 == db2) {
            System.out.println("Success: Both variables hold the same instance.");
        } else {
            System.out.println("Error: Different instances exist.");
        }
    }
}
```

**Output:**

```text
Requesting first connection instance...
Initializing Database Connection...
Executing query: SELECT * FROM users
---------------------------------
Requesting second connection instance...
Executing query: SELECT * FROM products
---------------------------------
Success: Both variables hold the same instance.
```

### Implementation 2: The Bill Pugh Singleton (Thread-Safe)

While the basic approach works, it can have issues in multi-threaded environments. The **Bill Pugh Singleton Implementation** is the standard for robust **Java** Singletons. It leverages the JVMs class loading mechanism to ensure thread safety without the performance overhead of synchronization.

#### Step 1: Define the Class and Private Constructor

We define the class and ensure no other class can instantiate it using `new`.

```java
public class Singleton {
    // Private constructor to prevent instantiation from other classes
    private Singleton() {
        System.out.println("Singleton instance initialized via Bill Pugh approach.");
    }
```

#### Step 2: Define a Static Inner Helper Class

This is the key to this implementation. The static inner class is not loaded into memory until it is referenced in the `getInstance` method. This provides logical _lazy-loading_ and automatic _thread-safety_.

```java
    // Static inner helper class that holds the Singleton instance
    private static class SingletonHelper {
        // The Singleton instance is created when this class is loaded
        private static final Singleton INSTANCE = new Singleton();
    }
```

#### Step 3: Provide a Public getInstance() Method

This provides the global access point. When called, it triggers the loading of `SingletonHelper`, creating the instance.

```java
    // Public method to provide access to the Singleton instance
    public static Singleton getInstance() {
        return SingletonHelper.INSTANCE;
    }

    // Example method for demonstration purposes
    public void showMessage() {
        System.out.println("Hello from the Singleton instance!");
    }
}
```

### Using Thread Safe Singleton in the Main Method

The following **Java** `Main` class demonstrates how to access the Singleton instance. We will verify that multiple calls to `getInstance()` return the exact same object.

```java
public class Main {
    public static void main(String[] args) {
        System.out.println("--- Testing Singleton Access ---");
        
        // 1. Get the instance for the first time
        // This triggers the initialization code inside the private constructor.
        Singleton firstRef = Singleton.getInstance();
        firstRef.showMessage();

        // 2. Get the instance a second time
        // This should NOT trigger initialization again.
        Singleton secondRef = Singleton.getInstance();
        
        // 3. Verify Identity
        if (firstRef == secondRef) {
            System.out.println("Success: Both references point to the same object (Identity Verified).");
        } else {
            System.out.println("Error: Multiple instances created.");
        }
    }
}
```

**Output:**

```text
--- Testing Singleton Access ---
Singleton instance initialized via Bill Pugh approach.
Hello from the Singleton instance!
Success: Both references point to the same object (Identity Verified).
```

### Key Characteristics

To ensure a correct Singleton implementation, you must adhere to these principles:

* **One Instance per JVM:** strictly ensure that only one instance of the class exists.
* **Global Access:** Provide a `public static` factory method to access that instance.
* **Private Constructor:** Explicitly declare the constructor `private` to prevent `new Singleton()` calls.
* **Static Instance:** The class holds a _static reference_ to the single created instance.
* **Lazy Loading:** The instance is often created only when it is actually needed (as seen in the code above), saving memory if the object is never requested.

### Why It Matters

1. **Controlled Access:** The Singleton class encapsulates its sole instance, giving it strict control over how and when clients access it.
2. **Resource Management:** It allows the system to manage shared resources (like thread pools or printer spoolers) efficiently without conflicts or excessive memory usage.
3. **Conflict Prevention:** It manages shared resources efficiently. Common usage scenarios include:
      * **Logging Services:** Writing to a single log file.
      * **Driver Objects:** Managing a connection to a printer or database.
      * **Thread Pools:** Managing a specific number of background threads.
4. **Foundation for Other Patterns:** The Singleton pattern is often used when implementing other design patterns, such as Abstract Factory, Builder, and Prototype patterns.

**Singleton** is a fundamental concept that provides a standardized way to manage global state and shared resources in an object-oriented application, ensuring consistency and efficiency across the entire **Java** codebase.

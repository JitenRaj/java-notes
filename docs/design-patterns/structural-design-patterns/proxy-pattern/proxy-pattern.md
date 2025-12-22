---
layout: default
title: Proxy Pattern
parent: Structural Design Patterns
nav_order: 7
---

## Proxy Pattern

**Design Patterns**: _Proxy Pattern_.

The _Proxy Pattern_ is a structural design pattern that provides a substitute or placeholder for another object to control access to it.

In **Java**, the Proxy object acts as an intermediary, controlling access to the real object (the **Subject**). This allows the Proxy to add functionality like lazy initialization, access control, logging, or caching before the request reaches the expensive or sensitive Real Subject.

### Implementing the Proxy Pattern

The implementation involves three core components:

1. **Subject Interface:** Defines the common interface for both the Real Subject and the Proxy.
2. **Real Subject:** The actual object that performs the main task.
3. **Proxy:** Maintains a reference to the Real Subject and controls access to it.

In this example, we will create a `HighResolutionImage` (the Real Subject) which is very expensive to load. We will use a `ProxyImage` to implement **lazy loading**, only loading the image when the client explicitly requests to `display()` it.

#### Step 1: Create the Subject Interface

```java
// Subject Interface: Common interface for both the Real Subject and the Proxy
public interface Image {
    void display();
}
```

#### Step 2: Create the Real Subject Class

This class is the resource-intensive object that we want to protect or defer loading for.

```java
// Real Subject
class HighResolutionImage implements Image {
    private String fileName;

    public HighResolutionImage(String fileName) {
        this.fileName = fileName;
        // Simulating expensive initialization/loading operation
        loadFromDisk(fileName);
    }

    private void loadFromDisk(String fileName) {
        System.out.println("Loading expensive image: " + fileName);
    }

    @Override
    public void display() {
        System.out.println("Displaying image: " + fileName);
    }
}
```

#### Step 3: Create the Proxy Class

The `ProxyImage` acts as a surrogate. It holds a reference to the `Real Subject` and handles the access control or lazy initialization.

```java
// Proxy
class ProxyImage implements Image {
    private String fileName;
    // Reference to the Real Subject. Set to null initially for lazy loading.
    private HighResolutionImage realImage;

    public ProxyImage(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void display() {
        // Control Access Logic: Lazy Loading
        // The real object is only created and loaded when display() is called for the first time.
        if (realImage == null) {
            realImage = new HighResolutionImage(fileName);
        }
        
        // Delegate the core operation to the Real Subject
        realImage.display();
    }
}
```

### Using Proxy in the Main Method

In the **Java** `Main` class, the client interacts with the `ProxyImage`. The output shows that the expensive `Loading expensive image` operation only runs when the `display()` method is invoked, not during the proxy's initialization.

```java
public class Main {
    public static void main(String[] args) {
        // The client creates the proxy, but the expensive image is NOT loaded yet.
        System.out.println("Client creating ProxyImage instance...");
        Image image = new ProxyImage("very_large_photo.jpg");

        // The image is displayed for the first time. Loading occurs now.
        System.out.println("\n--- First display call ---");
        image.display(); 

        // The image is displayed a second time. Loading is skipped (cached).
        System.out.println("\n--- Second display call (using cached object) ---");
        image.display();
    }
}
```

**Output:**

```text
Client creating ProxyImage instance...

--- First display call ---
Loading expensive image: very_large_photo.jpg
Displaying image: very_large_photo.jpg

--- Second display call (using cached object) ---
Displaying image: very_large_photo.jpg
```

### Key Types and Uses of Proxies

The Proxy pattern can serve various purposes, often categorized by the kind of control it provides:

* **Virtual Proxy:** Implements lazy initialization (as shown above). It creates resource-intensive objects only when needed.
* **Protection Proxy:** Controls access based on user permissions or roles. It checks if the client has the necessary rights before delegating the request.
* **Remote Proxy:** Provides a local representation for an object that is located in a different address space (e.g., a stub in RMI).
* **Caching Proxy:** Stores the results of an expensive operation for a specific period to avoid redundant calls to the Real Subject.

### Why It Matters

1. **Performance Optimization:** Virtual proxies improve application startup time and resource usage by deferring the creation of large objects.
2. **Security:** Protection proxies ensure that only authorized users or systems can execute sensitive operations.
3. **Separation of Concerns:** The Proxy handles secondary concerns (security, logging, caching) while the Real Subject focuses only on its core business logic.
4. **Interface Consistency:** Because both the Proxy and the Real Subject implement the same interface, clients can use them interchangeably, ensuring the **Liskov Substitution Principle**.

**Proxy Pattern** is a fundamental concept for controlling access and enhancing the functionality of objects without modifying their original source code, widely used in **Java** frameworks for transaction management and security.

---
layout: default
title: Service Locator
parent: J2EE Patterns
nav_order: 7
---

## Service Locator Pattern

**J2EE Patterns**: _Service Locator_.

The **Service Locator** pattern is used when we want to locate various services using JNDI lookup.

Considering the high cost of looking up a service (network latency, database hits), the Service Locator pattern makes use of **Caching** techniques.

For the first time a service is requested, the Service Locator looks it up in the database or directory and caches the object. For all subsequent requests, it returns the object from the cache, improving performance.

### Core Components

* **Service Locator:**
  * The single point of contact for the client. It abstracts the lookup logic and manages the cache.
* **Initial Context:**
  * Creates the reference to the JNDI (Java Naming and Directory Interface) lookup. This is the expensive operation we want to avoid repeating.
* **Cache:**
  * A simple map or list to store references to services that have already been found.
* **Service:**
  * The actual concrete implementation (e.g., `EJBService`, `JMSService`) that the client wants to use.

### Real-World Analogy

Think of a **Phonebook (or Contact List)**:

* You need to call a plumber (The Service).
* **Without Locator:** You call the operator (Initial Context) every single time, pay a fee, ask for the number, and then write it down. This is slow and expensive.
* **With Locator:**
    1. You call the operator **once**.
    2. You write the number in your personal **Address Book (Cache)**.
    3. Next time you need the plumber, you just look in your book. You don't call the operator again.

### Implementation Example

Here is a raw Java implementation simulating the caching mechanism.

#### 1. Create the Service Interface

```java
public interface Service {
    public String getName();
    public void execute();
}

```

#### 2. Create Concrete Services

```java
public class Service1 implements Service {
    public void execute() {
        System.out.println("Executing Service1");
    }
    public String getName() {
        return "Service1";
    }
}

public class Service2 implements Service {
    public void execute() {
        System.out.println("Executing Service2");
    }
    public String getName() {
        return "Service2";
    }
}

```

#### 3. Create the Initial Context (Simulation)

This simulates the expensive JNDI lookup.

```java
public class InitialContext {
    public Object lookup(String jndiName) {
        if (jndiName.equalsIgnoreCase("SERVICE1")) {
            System.out.println("Looking up and creating a new Service1 object");
            return new Service1();
        } else if (jndiName.equalsIgnoreCase("SERVICE2")) {
            System.out.println("Looking up and creating a new Service2 object");
            return new Service2();
        }
        return null;
    }
}

```

#### 4. Create the Cache

```java
import java.util.ArrayList;
import java.util.List;

public class Cache {
    private List<Service> services;

    public Cache() {
        services = new ArrayList<Service>();
    }

    public Service getService(String serviceName) {
        for (Service service : services) {
            if (service.getName().equalsIgnoreCase(serviceName)) {
                System.out.println("Returning cached  " + serviceName + " object");
                return service;
            }
        }
        return null;
    }

    public void addService(Service newService) {
        boolean exists = false;
        for (Service service : services) {
            if (service.getName().equalsIgnoreCase(newService.getName())) {
                exists = true;
            }
        }
        if (!exists) {
            services.add(newService);
        }
    }
}

```

#### 5. Create the Service Locator

This brings it all together.

```java
public class ServiceLocator {
    private static Cache cache;

    static {
        cache = new Cache();
    }

    public static Service getService(String jndiName) {
        // 1. Try to get from Cache
        Service service = cache.getService(jndiName);

        if (service != null) {
            return service;
        }

        // 2. If not in Cache, lookup using Initial Context
        InitialContext context = new InitialContext();
        Service newService = (Service) context.lookup(jndiName);
        
        // 3. Add to Cache for next time
        cache.addService(newService);
        return newService;
    }
}

```

#### 6. Demo Class

```java
public class ServiceLocatorPatternDemo {
    public static void main(String[] args) {
        Service service = ServiceLocator.getService("Service1");
        service.execute();

        service = ServiceLocator.getService("Service2");
        service.execute();

        // This should come from the cache, not a new lookup
        service = ServiceLocator.getService("Service1");
        service.execute();

        service = ServiceLocator.getService("Service2");
        service.execute();
    }
}

```

**Output:**

```text
Looking up and creating a new Service1 object
Executing Service1
Looking up and creating a new Service2 object
Executing Service2
Returning cached Service1 object
Executing Service1
Returning cached Service2 object
Executing Service2

```

### When to Use

* **Legacy Systems:** If you are working with old EJB (Enterprise JavaBeans) systems where creating connections is very expensive.
* **Registry:** When you need a central registry to decouple clients from the concrete service implementations.

### Pros & Cons

| Pros | Cons |
| --- | --- |
| **Performance:** Greatly improves network performance by caching expensive lookups. | **Complexity:** Adds many classes (Cache, Context, Locator) for simple logic. |
| **Abstraction:** Clients don't need to know the complex JNDI syntax. | **Hidden Dependencies:** It makes unit testing harder because the dependencies are hidden inside the static `ServiceLocator` rather than passed in explicitly. |

## Spring Boot Context

Like the Business Delegate, the **Service Locator pattern is considered an anti-pattern in modern Spring Boot development**.

### Why? Dependency Injection (DI)

Service Locator asks: "Hey Locator, can you go find me the Service?"
Dependency Injection says: "Here is the Service you need. I already found it for you."

In Spring, the **ApplicationContext** acts as the Cache and Locator, but it is invisible to your code. You simply declare what you need, and Spring provides it.

**Old Way (Service Locator):**

```java
public class MyController {
    public void doWork() {
        // Explicit call to static locator (Hard to test!)
        MyService service = ServiceLocator.getService("MyService");
        service.run();
    }
}

```

**Modern Way (Spring DI):**

```java
@RestController
public class MyController {
    
    // Spring injects the cached instance automatically
    private final MyService myService;

    public MyController(MyService myService) {
        this.myService = myService;
    }

    @GetMapping("/work")
    public void doWork() {
        myService.run();
    }
}

```

{: .note }

Spring Beans are **Singleton** by default, meaning they are cached automatically. You don't need to write a custom `Cache` class anymore.

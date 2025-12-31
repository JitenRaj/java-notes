---
layout: default
title: 'Business Delegate'
parent: J2EE Patterns
nav_order: 4
---

## Business Delegate Pattern

**J2EE Patterns**: _Business Delegate_.

The **Business Delegate** pattern is used to decouple the presentation tier (Client) from the business tier (Business Service).

It acts as an intermediary to reduce coupling between the code that displays the UI and the code that executes business logic.

In older J2EE systems, looking up a business service (like an EJB) was complex and prone to network errors. The Business Delegate hides this complexity, providing a simple, local interface for the client to use.

### Core Components

* **Client:**
  * The presentation layer code (JSP, Servlet, or UI Controller) that needs to access a business service.

* **Business Delegate:**
  * A class that manages the relationship with the business services. It hides the implementation details of how to locate and connect to the service.

* **LookUp Service:**
  * Responsible for getting the relative business implementation and providing access to the business object. (Historically, this wrapped JNDI lookups).

* **Business Service:**
  * The interface defines the actual business logic methods. Concrete implementations (e.g., `EJBService`, `JMSService`) execute the logic.

### Real-World Analogy

Think of a **Hotel Concierge**:

* **The Guest (Client)** wants a taxi or a spa reservation.
* The Guest does not look up the phone numbers for the taxi company or the spa manager, nor do they care how the booking system works.
* The Guest simply asks the **Concierge (Business Delegate)**.
* The Concierge looks up the correct provider (LookUp Service), contacts them (Business Service), and confirms the request.
* The Guest deals only with the Concierge.

### Implementation Example

Here is a raw Java implementation simulating the lookup of different services.

#### 1. Create the Business Service Interface

```java
public interface BusinessService {
    void doProcessing();
}

```

#### 2. Create Concrete Services

```java
public class EJBService implements BusinessService {
    @Override
    public void doProcessing() {
        System.out.println("Processing task by invoking EJB Service");
    }
}

public class JMSService implements BusinessService {
    @Override
    public void doProcessing() {
        System.out.println("Processing task by invoking JMS Service");
    }
}

```

#### 3. Create the Lookup Service

In a real J2EE app, this would use JNDI (Java Naming and Directory Interface). Here, we simulate it.

```java
public class BusinessLookUp {
    public BusinessService getBusinessService(String serviceType) {
        if (serviceType.equalsIgnoreCase("EJB")) {
            return new EJBService();
        } else {
            return new JMSService();
        }
    }
}

```

#### 4. Create the Business Delegate

This is the class the client will actually use. Note that it holds a reference to the Lookup Service.

```java
public class BusinessDelegate {
    private BusinessLookUp lookupService = new BusinessLookUp();
    private BusinessService businessService;
    private String serviceType;

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public void doTask() {
        businessService = lookupService.getBusinessService(serviceType);
        businessService.doProcessing();
    }
}

```

#### 5. Demo Class (Client)

```java
public class BusinessDelegatePatternDemo {
    public static void main(String[] args) {
        BusinessDelegate businessDelegate = new BusinessDelegate();

        // Client wants to use EJB
        businessDelegate.setServiceType("EJB");
        businessDelegate.doTask();

        // Client wants to use JMS
        businessDelegate.setServiceType("JMS");
        businessDelegate.doTask();
    }
}

```

### When to Use

* **Hiding Remote Complexity:** When accessing your services involves complex remote lookups (RMI, EJB, SOAP) that you don't want to repeat in every Controller.
* **Caching References:** The Delegate can cache the service object so the application doesn't have to look it up every time, improving performance.
* **Resilience:** If a service lookup fails, the Delegate can handle the retry logic or switch to a backup service transparently.

### Pros & Cons

| Pros | Cons |
| --- | --- |
| **Decoupling:** The Presentation tier doesn't need to know the details of the Business tier implementation. | **Extra Layer:** Adds more classes to maintain (Delegate + Lookup). |
| **Transparency:** If the underlying technology changes (e.g., from EJB to Web Services), only the Delegate changes, not the Client. | **Obsolete in Modern Apps:** Dependency Injection frameworks (Spring) handle most of this automatically. |

## Spring Boot Context

In the modern Spring ecosystem, the **Business Delegate pattern is largely obsolete** in its traditional form because **Inversion of Control (IoC)** and **Dependency Injection (DI)** have solved the problem it was designed to fix.

### Why we don't use it explicitly anymore

In the old days, you had to manually "look up" a service. In Spring Boot, you simply `@Autowire` it. The Spring Container acts as both the **LookUp Service** and the **Business Delegate**.

* **LookUp Service**  Replaced by the Spring ApplicationContext.
* **Business Delegate**  Replaced by the Service Interface injection.

### The Spring Boot Equivalent

We achieve the same decoupling by programming to interfaces.

**The Interface (Business Service):**

```java
public interface PaymentService {
    void processPayment();
}

```

**The Implementations:**

```java
@Service("creditCard")
public class CreditCardService implements PaymentService { ... }

@Service("paypal")
public class PaypalService implements PaymentService { ... }

```

**The Client (Controller):**
Instead of a manual "Delegate" class, we inject the interface.

```java
@RestController
public class PaymentController {

    private final PaymentService paymentService;

    // Spring injects the correct implementation automatically
    // This removes the need for a manual "BusinessLookup" class
    public PaymentController(@Qualifier("paypal") PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping("/pay")
    public void pay() {
        paymentService.processPayment();
    }
}
```

{: .note }

The concept of "Delegation" lives on, but the boilerplate code of the Business Delegate pattern has been absorbed by the Spring Framework itself.

---
layout: default
title: Strategy Pattern
parent: Behavioral Design Patterns
nav_order: 8
permalink: /docs/design-patterns/behavioral-design-patterns/strategy-pattern
---

## Strategy Pattern

**Design Patterns**: _Strategy Pattern_.

The _Strategy Pattern_ is a behavioral design pattern that defines a family of algorithms, encapsulates each one, and makes them interchangeable.

In **Java**, this pattern allows the algorithm to vary independently from the clients that use it. Instead of implementing a single algorithm directly in the code (e.g., a massive `switch` statement for different sorting methods or payment types), the code delegates the task to an object representing a specific strategy.

### Implementing the Strategy Pattern

To implement this pattern, we define:

1. **Strategy Interface:** Defines the common operation for all supported algorithms.
2. **Concrete Strategies:** Classes that implement the interface with specific logic.
3. **Context:** The class that uses the Strategy. It holds a reference to a Strategy object and delegates the work to it.

In this example, we will model a **Payment System** for an e-commerce store. The store supports multiple payment methods (Credit Card, PayPal), and we want to switch between them easily without changing the core shopping cart logic.

#### Step 1: Create the Strategy Interface

This interface enforces a common contract for all payment methods.

```java
// Strategy Interface
public interface PaymentStrategy {
    void pay(int amount);
}
```

#### Step 2: Create Concrete Strategies

Each class implements a specific payment algorithm.

```java
// Concrete Strategy 1: Credit Card
class CreditCardStrategy implements PaymentStrategy {
    private String name;
    private String cardNumber;

    public CreditCardStrategy(String name, String cardNumber) {
        this.name = name;
        this.cardNumber = cardNumber;
    }

    @Override
    public void pay(int amount) {
        System.out.println(amount + " paid with Credit/Debit Card. (Card Holder: " + name + ")");
    }
}

// Concrete Strategy 2: PayPal
class PayPalStrategy implements PaymentStrategy {
    private String emailId;

    public PayPalStrategy(String email) {
        this.emailId = email;
    }

    @Override
    public void pay(int amount) {
        System.out.println(amount + " paid using PayPal. (Account: " + emailId + ")");
    }
}
```

#### Step 3: Create the Context Class

The `ShoppingCart` doesn't know _how_ the payment is processed; it just knows _that_ it needs to pay. It asks the client to provide the specific strategy.

```java
import java.util.ArrayList;
import java.util.List;

// Context
public class ShoppingCart {
    List<String> items;

    public ShoppingCart() {
        this.items = new ArrayList<>();
    }

    public void addItem(String item) {
        this.items.add(item);
    }

    public int calculateTotal() {
        // Simulating logic where every item costs $10
        return items.size() * 10;
    }

    // The method takes the strategy as a parameter
    public void pay(PaymentStrategy paymentMethod) {
        int amount = calculateTotal();
        paymentMethod.pay(amount);
    }
}
```

### Using Strategy in the Main Method

In the **Java** `Main` class, we can choose the payment method at runtime. We can use the same `ShoppingCart` object to pay via Credit Card first, and then via PayPal for a different transaction.

```java
public class Main {
    public static void main(String[] args) {
        ShoppingCart cart = new ShoppingCart();

        cart.addItem("Java Book");
        cart.addItem("Design Patterns E-book");

        System.out.println("--- Transaction 1 ---");
        // Pay by Credit Card
        cart.pay(new CreditCardStrategy("Alice Doe", "1234-5678-9012-3456"));

        System.out.println("\n--- Transaction 2 ---");
        // Pay by PayPal
        cart.pay(new PayPalStrategy("alice@example.com"));
    }
}
```

**Output:**

```text
--- Transaction 1 ---
20 paid with Credit/Debit Card. (Card Holder: Alice Doe)

--- Transaction 2 ---
20 paid using PayPal. (Account: alice@example.com)
```

### Key Characteristics

* **Family of Algorithms:** It encapsulates related algorithms (e.g., Sorting, Encryption, Compression, Payment) into their own classes.
* **Interchangeability:** Strategies can be swapped at runtime because they all implement the same interface.
* **Composition over Inheritance:** Instead of inheriting behavior (e.g., `class PayPalShoppingCart extends ShoppingCart`), the Context is composed of a Strategy object.

### Why It Matters

1. **Open/Closed Principle:** You can introduce new strategies (e.g., `BitcoinStrategy`) without changing the code of the Context (`ShoppingCart`).
2. **Eliminates Conditionals:** It removes the need for complex `if-else` or `switch` statements inside the main business logic to check which algorithm to use.
3. **Separation of Concerns:** The business logic (calculating the total) is separated from the implementation details of the algorithm (how to process the payment).

**Strategy Pattern** is a fundamental concept in **Java** (used heavily in the `Comparator` interface for sorting) that provides maximum flexibility by allowing behavior to be selected dynamically at runtime.

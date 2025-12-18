---
layout: default
title: Visitor Pattern
parent: Behavioral Design Patterns
nav_order: 10
permalink: /docs/design-patterns/behavioral-design-patterns/visitor-pattern
---

## Visitor Pattern

**Design Patterns**: _Visitor Pattern_.

The _Visitor Pattern_ is a behavioral design pattern that lets you separate algorithms from the objects on which they operate.

In **Java**, this pattern allows you to add new operations to a class hierarchy without modifying the existing classes. It achieves this by using a technique called **Double Dispatch**, where the method to be executed depends on the dynamic type of both the visitor and the element being visited.

### Implementing the Visitor Pattern

To implement this pattern, we define:

1. **Visitor Interface:** Declares a `visit()` method for each concrete element class (e.g., `visit(Book book)`, `visit(Fruit fruit)`).
2. **Element Interface:** Declares an `accept()` method that takes a visitor as an argument.
3. **Concrete Elements:** Implement the `accept()` method, usually by calling `visitor.visit(this)`.
4. **Concrete Visitors:** Implement the specific logic (algorithm) for each element type.

In this example, we will model a **Shopping Cart**. We want to calculate the total cost of items. However, logic might change (e.g., adding a specific tax to fruit but not books). Instead of hardcoding this logic into the Item classes, we use a `ShoppingCartVisitor`.

#### Step 1: Create the Visitor Interface

This interface defines a visit method for every type of item we support.

```java
// Visitor Interface
public interface ShoppingCartVisitor {
    int visit(Book book);
    int visit(Fruit fruit);
}
```

#### Step 2: Create the Element Interface

The contract that allows an object to be "visited."

```java
// Element Interface
public interface ItemElement {
    int accept(ShoppingCartVisitor visitor);
}
```

#### Step 3: Create Concrete Elements

Notice the `accept` method. It doesn't calculate the cost itself; it calls the visitor's method passing `this`. This is the **Double Dispatch**.

```java
// Concrete Element 1: Book
class Book implements ItemElement {
    private int price;
    private String isbnNumber;

    public Book(int cost, String isbn) {
        this.price = cost;
        this.isbnNumber = isbn;
    }

    public int getPrice() {
        return price;
    }

    public String getIsbnNumber() {
        return isbnNumber;
    }

    @Override
    public int accept(ShoppingCartVisitor visitor) {
        // Double Dispatch: calling visit(Book)
        return visitor.visit(this);
    }
}

// Concrete Element 2: Fruit
class Fruit implements ItemElement {
    private int pricePerKg;
    private int weight;
    private String name;

    public Fruit(int pricePerKg, int weight, String name) {
        this.pricePerKg = pricePerKg;
        this.weight = weight;
        this.name = name;
    }

    public int getPricePerKg() {
        return pricePerKg;
    }

    public int getWeight() {
        return weight;
    }

    public String getName() {
        return name;
    }

    @Override
    public int accept(ShoppingCartVisitor visitor) {
        // Double Dispatch: calling visit(Fruit)
        return visitor.visit(this);
    }
}
```

#### Step 4: Create the Concrete Visitor

This class contains the actual business logic (algorithm). If we wanted to change how cost is calculated (e.g., "HolidayDiscountVisitor"), we would just create a new Visitor class without touching `Book` or `Fruit`.

```java
// Concrete Visitor
class ShoppingCartVisitorImpl implements ShoppingCartVisitor {

    @Override
    public int visit(Book book) {
        int cost = book.getPrice();
        // Business Logic: Books over $50 get a $5 discount
        if (cost > 50) {
            cost = cost - 5;
        }
        System.out.println("Book ISBN::" + book.getIsbnNumber() + " cost =" + cost);
        return cost;
    }

    @Override
    public int visit(Fruit fruit) {
        // Business Logic: Cost is price * weight
        int cost = fruit.getPricePerKg() * fruit.getWeight();
        System.out.println(fruit.getName() + " cost = " + cost);
        return cost;
    }
}
```

### Using Visitor in the Main Method

In the **Java** `Main` class, we create the items and the visitor. We then loop through the items, asking each one to "accept" the visitor.

```java
public class Main {
    public static void main(String[] args) {
        ItemElement[] items = new ItemElement[]{
            new Book(20, "1234"),
            new Book(100, "5678"),
            new Fruit(10, 2, "Banana"),
            new Fruit(5, 5, "Apple")
        };

        int total = calculatePrice(items);
        System.out.println("Total Cost = " + total);
    }

    private static int calculatePrice(ItemElement[] items) {
        ShoppingCartVisitor visitor = new ShoppingCartVisitorImpl();
        int sum = 0;
        
        for (ItemElement item : items) {
            // The item decides which visit method to call based on its own type
            sum = sum + item.accept(visitor);
        }
        return sum;
    }
}
```

**Output:**

```text
Book ISBN::1234 cost =20
Book ISBN::5678 cost =95
Banana cost = 20
Apple cost = 25
Total Cost = 160
```

### Key Characteristics

* **Separation of Concerns:** The data structures (Book, Fruit) are distinct from the operations performed on them (Cost Calculation).
* **Double Dispatch:** The execution depends on the type of the Visitor (which algorithm?) AND the type of the Element (which object?).
* **Open/Closed Principle:** The pattern is **Open** for adding new operations (Visitors) but **Closed** for modifying the Element classes.

### Why It Matters

1. **Adding Operations Easily:** If you need to add an "Export to XML" feature, you simply create `XmlExportVisitor`. You do not need to add `toXML()` methods to every single class in your hierarchy.
2. **Gathering Related Operations:** It groups related behavior into a single class (the Visitor) rather than spreading it across the entire object structure.
3. **Complex Object Structures:** It is highly effective when iterating over complex structures like Composite trees (e.g., a Document Object Model or a Compiler Abstract Syntax Tree).

**Visitor Pattern** is a powerful concept in **Java** for keeping your data classes clean and simple, allowing you to plug in external operations without polluting the core object hierarchy.

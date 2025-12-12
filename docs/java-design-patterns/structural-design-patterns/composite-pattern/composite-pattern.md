---
layout: default
title: Composite Pattern
parent: Structural Design Patterns
nav_order: 3
permalink: /docs/design-patterns/structural-design-patterns/composite-pattern
---

## Composite Pattern

**Design Patterns**: _Composite Pattern_.

The _Composite Pattern_ is a structural design pattern that allows you to compose objects into tree structures to represent part-whole hierarchies.

In **Java**, this pattern enables clients to treat individual objects (leaves) and compositions of objects (composites) uniformly. This is ideal for scenarios like file systems (files and folders), GUI components (buttons and panels), or organizational structures, where a "container" can hold both items and other containers.

### Implementing the Composite Pattern

To implement this pattern, we define a common interface (the **Component**) for both simple objects and complex containers.

1. **Leaf:** Represents individual objects that have no children.
2. **Composite:** Represents components that have children. It implements operations to add or remove children.

In this example, we will model a corporate organizational chart where a `Manager` can supervise both `Developers` (leaves) and other `Managers`.

#### Step 1: Create the Component Interface

This interface defines the common operations for all objects in the composition.

```java
// Component
public interface Employee {
    void showDetails();
}
```

#### Step 2: Create the Leaf Classes

These classes implement the `Employee` interface but do not contain other employees.

```java
// Leaf 1
class Developer implements Employee {
    private String name;
    private long empId;
    private String position;

    public Developer(long empId, String name, String position) {
        this.empId = empId;
        this.name = name;
        this.position = position;
    }

    @Override
    public void showDetails() {
        System.out.println(empId + " " + name + " (" + position + ")");
    }
}

// Leaf 2
class Designer implements Employee {
    private String name;
    private long empId;
    private String position;

    public Designer(long empId, String name, String position) {
        this.empId = empId;
        this.name = name;
        this.position = position;
    }

    @Override
    public void showDetails() {
        System.out.println(empId + " " + name + " (" + position + ")");
    }
}
```

#### Step 3: Create the Composite Class

This class implements `Employee` and also holds a collection of `Employee` objects (which can be Leaves or other Composites).

```java
import java.util.ArrayList;
import java.util.List;

// Composite
class Manager implements Employee {
    private String name;
    private long empId;
    private String position;
    
    // The collection acts as the container for children
    private List<Employee> subordinates;

    public Manager(long empId, String name, String position) {
        this.empId = empId;
        this.name = name;
        this.position = position;
        this.subordinates = new ArrayList<>();
    }

    // Methods to manage the tree structure
    public void addEmployee(Employee emp) {
        subordinates.add(emp);
    }

    public void removeEmployee(Employee emp) {
        subordinates.remove(emp);
    }

    @Override
    public void showDetails() {
        System.out.println("---------------------------------");
        System.out.println("Manager: " + name + " (" + position + ")");
        System.out.println("Subordinates:");
        
        // Delegates work to children recursively
        for (Employee emp : subordinates) {
            emp.showDetails();
        }
    }
}
```

### Using Composite in the Main Method

In the **Java** `Main` class, we build a tree structure. Notice how the `generalManager` treats `engManager` (a composite) and direct reports exactly the same way.

```java
public class Main {
    public static void main(String[] args) {
        // 1. Create Leaf nodes (Individual Contributors)
        Developer dev1 = new Developer(101, "Alice", "Pro Developer");
        Developer dev2 = new Developer(102, "Bob", "Pro Developer");
        Designer des1 = new Designer(103, "Carol", "UI/UX Designer");

        // 2. Create Composite nodes (Managers)
        Manager engManager = new Manager(200, "Daniel", "Engineering Manager");
        Manager genManager = new Manager(300, "Eve", "General Manager");

        // 3. Build the Tree
        // Daniel manages Alice and Bob
        engManager.addEmployee(dev1);
        engManager.addEmployee(dev2);

        // Eve manages Daniel (who manages others) and Carol (direct report)
        genManager.addEmployee(engManager);
        genManager.addEmployee(des1);

        // 4. Print the entire hierarchy
        // The client simply calls showDetails() on the root
        genManager.showDetails();
    }
}
```

**Output:**

```text
---------------------------------
Manager: Eve (General Manager)
Subordinates:
---------------------------------
Manager: Daniel (Engineering Manager)
Subordinates:
101 Alice (Pro Developer)
102 Bob (Pro Developer)
103 Carol (UI/UX Designer)
```

### Key Characteristics

* **Part-Whole Hierarchy:** It organizes objects into a tree structure where individual objects and groups of objects are treated similarly.
* **Uniform Interface:** The client code can treat a `Composite` and a `Leaf` interchangeably (both are `Employee`), reducing code complexity.
* **Recursion:** Operations called on a composite (like `showDetails`) are typically delegated recursively down the tree to its children.

### Why It Matters

1. **Simplifies Client Code:** The client doesn't need to know if it's dealing with a single object or a complex tree. It just calls `showDetails()`.
2. **Scalability:** You can add new types of `Leaf` or `Composite` classes without breaking existing code or the client logic.
3. **Logical Representation:** It perfectly maps to real-world hierarchical structures like file directories, menus, and organizational charts.

**Composite Pattern** is a fundamental concept for managing complex hierarchies in **Java**, allowing developers to build recursive tree structures that are easy to traverse and maintain.

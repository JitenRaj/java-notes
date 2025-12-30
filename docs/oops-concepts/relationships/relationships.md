---
layout: default
title: Relationships
parent: OOPs Concepts
nav_order: 8
---

## Relationships Beyond Inheritance

**Object-Oriented Programming (OOP)**: _Association, Aggregation, and Composition_.

While **Inheritance** defines an "IS-A" relationship (e.g., a Teacher _is a_ Person), real-world applications often require objects to work together in different ways. These are known as **Has-A** relationships.

There are three main types of relationships that define how objects interact:

1. **Association** (The broadest term)
2. **Aggregation** (A "Weak" relationship)
3. **Composition** (A "Strong" relationship)

### 1. Association

Association is a general binary relationship that describes an activity between two classes. It represents a "uses-a" relationship where all objects have their own lifecycle and there is no ownership.

* **Relationship:** "Uses-A" or "Linked-To"
* **Direction:** Can be one-way (uni-directional) or two-way (bi-directional).
* **Example:** A **Teacher** and a **Student**. A teacher can have many students, and a student can have many teachers. If a teacher leaves the school, the student still exists, and vice versa.

```java
class Teacher {
    private String name;
    // ...
}

class Student {
    private String name;
    // Association: Student "knows" about Teachers (but doesn't own them)
    private List<Teacher> teachers; 
}

```

### 2. Aggregation (Weak Association)

Aggregation is a specialized form of association. It represents a **"Has-A"** relationship, but it is a **weak** or loose coupling.

* **Key Concept:** **Independent Lifecycles**. The child object can exist independently of the parent object.
* **Ownership:** The parent "has" the child, but does not strictly "own" its life.
* **Analogy:** A **Department** and a **Teacher**.
* A Department _has_ Teachers.
* If you close down the Department (destroy the object), the Teachers do _not_ die. They still exist and can join another department.

**Java Example:**

```java
class Teacher {
    String name;
    public Teacher(String name) {
        this.name = name;
    }
}

class Department {
    String name;
    private List<Teacher> teachers; // Aggregation

    public Department(String name, List<Teacher> teachers) {
        this.name = name;
        this.teachers = teachers;
    }
}

public class Main {
    public static void main(String[] args) {
        Teacher t1 = new Teacher("Alice");
        
        // Department is created using an EXISTING teacher
        List<Teacher> staff = new ArrayList<>();
        staff.add(t1);
        Department dept = new Department("Computer Science", staff);
        
        // If 'dept' is destroyed (set to null), 't1' (Alice) still exists in memory
        dept = null; 
        System.out.println(t1.name); // This still works!
    }
}

```

### 3. Composition (Strong Association)

Composition is a restricted form of Aggregation. It represents a **strong "Has-A"** relationship (often called "Part-Of").

* **Key Concept:** **Dependent Lifecycles**. The child object _cannot_ exist without the parent object.
* **Ownership:** The parent strictly owns the child. If the parent dies, the child dies.
* **Analogy:** A **Car** and an **Engine**.
* A Car _has an_ Engine.
* If you destroy the Car, the Engine is effectively destroyed (or at least, it serves no purpose floating around on its own in this context). The Engine is created _inside_ the Car.

**Java Example:**

```java
class Engine {
    String type;
    public Engine(String type) {
        this.type = type;
    }
}

class Car {
    private final Engine engine; // Composition

    public Car(String engineType) {
        // The Engine is created INSIDE the Car constructor.
        // It is tightly bound to this specific Car instance.
        this.engine = new Engine(engineType);
    }
}

public class Main {
    public static void main(String[] args) {
        Car myCar = new Car("V8");
        
        // If 'myCar' is destroyed (garbage collected), 
        // the 'engine' inside it is ALSO destroyed.
        // You cannot access that specific 'engine' object separately here.
        myCar = null;
    }
}

```

### Quick Summary: IS-A vs HAS-A

Understanding the difference between Inheritance and Composition is key to system design.

| Feature | IS-A (Inheritance) | HAS-A (Composition/Aggregation) |
| --- | --- | --- |
| **Keyword** | `extends` | `new` (instance variable) |
| **Relationship** | Parent-Child | Whole-Part |
| **Coupling** | Tightly coupled | Loosely coupled |
| **Reusability** | White-box (internal details visible) | Black-box (only interface visible) |
| **Example** | `Teacher` is a `Person` | `Car` has an `Engine` |

### When to use which?

* Prefer **Composition** over **Inheritance**.
* Use **Inheritance** only when the relationship is genuinely "Is-A" (e.g., A Manager _is an_ Employee) and you need to override behavior.
* Use **Composition** (Has-A) when you just want to use the functionality of another class (e.g., A User _has a_ DatabaseConnection). This makes your code more flexible and easier to test.

---
layout: default
title: Prototype Pattern
parent: Creational Design Patterns
nav_order: 4
---

## Prototype Pattern

**Design Patterns**: _Prototype Pattern_.

The _Prototype Pattern_ is a creational design pattern that allows copying existing objects without making the code dependent on their classes.

In **Java**, this is primarily achieved using the `Cloneable` interface and the `clone()` method. This pattern is particularly useful when object creation is a costly operation (e.g., requires database access or complex calculations), and it is more efficient to copy an existing instance than to create a new one from scratch.

### Implementing the Prototype Pattern

To implement this in **Java**, a class must implement the `Cloneable` interface and override the `clone()` method.

In this example, we will create a `Shape` class. Instead of instantiating new shapes from scratch, we will configure a "prototype" shape and clone it whenever we need a duplicate.

#### Step 1: Create the Abstract Prototype

We define an abstract class that implements `Cloneable`.

```java
public abstract class Shape implements Cloneable {
    private String id;
    protected String type;

    abstract void draw();

    // getters
    public String getType() {
        return type;
    }

    public String getId() {
        return id;
    }

    // setters
    public void setId(String id) {
        this.id = id;
    }

    // The core method of the Prototype pattern
    public Object clone() {
        Object clone = null;
        try {
            // super.clone() performs a shallow copy
            clone = super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return clone;
    }
}
```

#### Step 2: Create Concrete Prototypes

These classes extend the prototype and define specific behaviors.

```java
class Rectangle extends Shape {
    public Rectangle() {
        type = "Rectangle";
    }

    @Override
    public void draw() {
        System.out.println("Inside Rectangle::draw() method.");
    }
}

class Circle extends Shape {
    public Circle() {
        type = "Circle";
    }

    @Override
    public void draw() {
        System.out.println("Inside Circle::draw() method.");
    }
}
```

#### Step 3: Create a Prototype Registry (Optional but Recommended)

A registry class (often a caching layer) pre-loads the prototypes. When a client asks for a shape, the registry returns a clone of the cached object.

```java
import java.util.Hashtable;

public class ShapeCache {

    private static Hashtable<String, Shape> shapeMap = new Hashtable<String, Shape>();

    public static Shape getShape(String shapeId) {
        Shape cachedShape = shapeMap.get(shapeId);
        // Returns a clone of the cached object, not the original reference
        return (Shape) cachedShape.clone();
    }

    // Simulate expensive database load
    public static void loadCache() {
        Circle circle = new Circle();
        circle.setId("1");
        shapeMap.put(circle.getId(), circle);

        Rectangle rectangle = new Rectangle();
        rectangle.setId("2");
        shapeMap.put(rectangle.getId(), rectangle);
    }
}
```

### Using Prototype in the Main Method

In the **Java** `Main` class, we load the cache once. Subsequent requests get a fresh copy of the object without running the constructor logic again.

```java
public class Main {
    public static void main(String[] args) {
        // 1. Initialize the cache (create prototypes)
        ShapeCache.loadCache();

        // 2. Retrieve clones from the registry
        Shape clonedShape1 = ShapeCache.getShape("1");
        System.out.println("Shape : " + clonedShape1.getType());
        clonedShape1.draw();

        Shape clonedShape2 = ShapeCache.getShape("2");
        System.out.println("Shape : " + clonedShape2.getType());
        clonedShape2.draw();
        
        // 3. Verify that it is a clone (different memory address)
        Shape clonedShape3 = ShapeCache.getShape("1");
        if (clonedShape1 != clonedShape3) {
            System.out.println("Success: clonedShape1 and clonedShape3 are different objects in memory.");
        }
    }
}
```

**Output:**

```text
Shape : Circle
Inside Circle::draw() method.
Shape : Rectangle
Inside Rectangle::draw() method.
Success: clonedShape1 and clonedShape3 are different objects in memory.
```

### Key Characteristics

* **Cloneable Interface:** In **Java**, this marker interface indicates that the class allows its objects to be cloned.
* **Method Overriding:** The `clone()` method must be overridden to change its visibility to `public` (it is `protected` by default in `Object`).
* **Shallow vs. Deep Copy:** The default `super.clone()` creates a _shallow copy_ (references to objects are copied, not the objects themselves). If the object contains mutable fields (like Lists or other objects), you must implement a _deep copy_ manually.

### Why It Matters

1. **Performance:** It saves resources by avoiding the overhead of initializing a new object using the `new` keyword, especially if the initialization involves database calls or file I/O.
2. **Decoupling:** The client code can produce new instances without knowing the specific classes of the objects it is copying.
3. **Dynamic Configuration:** It allows you to add or remove products at runtime by simply registering or unregistering prototype instances.

**Prototype Pattern** is a fundamental concept for performance optimization in object creation, offering a flexible mechanism to duplicate complex objects efficiently within **Java** applications.

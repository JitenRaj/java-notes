---
layout: default
title: Flyweight Pattern
parent: Structural Design Patterns
nav_order: 6
permalink: /docs/design-patterns/structural-design-patterns/flyweight-pattern
---

## Flyweight Pattern

**Design Patterns**: _Flyweight Pattern_.

The _Flyweight Pattern_ is a structural design pattern focused on minimizing memory usage by sharing as much data as possible with similar objects.

In **Java**, it is primarily used when an application needs to create a massive number of similar objects (e.g., millions of particles in a game or characters in a text editor). Instead of storing identical data in every object, the pattern separates the object's state into **Intrinsic** (shared, unchanging) and **Extrinsic** (unique, context-specific) state.

### Implementing the Flyweight Pattern

To implement this, we cache the intrinsic state in a factory and reference it, rather than creating new objects.

1. **Flyweight (Intrinsic State):** Stores the common data (e.g., a specific texture or color).
2. **Context (Extrinsic State):** Stores the unique data (e.g., x, y coordinates) and holds a reference to the Flyweight.
3. **Factory:** Manages a pool of existing Flyweights to ensure they are reused.

In this example, we will simulate a forest. We need to plant thousands of trees. Instead of creating a heavy object for every tree (which includes textures, colors, and other details), we create shared `TreeType` objects.

#### Step 1: Create the Flyweight Class (Intrinsic State)

This class holds the data that is common to many trees.

```java
import java.awt.Color;

// The Flyweight
// This class contains the state shared by many trees (Name, Color, Texture data)
public class TreeType {
    private String name;
    private String color;
    private String otherTreeData; // e.g., Texture

    public TreeType(String name, String color, String otherTreeData) {
        this.name = name;
        this.color = color;
        this.otherTreeData = otherTreeData;
    }

    // The operation that requires extrinsic state (x, y)
    public void draw(int x, int y) {
        System.out.println("Drawing Tree [" + name + ", " + color + "] at (" + x + ", " + y + ")");
    }
}
```

#### Step 2: Create the Factory

The factory decides whether to create a new `TreeType` or return an existing one.

```java
import java.util.HashMap;
import java.util.Map;

public class TreeFactory {
    // Cache to store the flyweight objects
    static Map<String, TreeType> treeTypes = new HashMap<>();

    public static TreeType getTreeType(String name, String color, String otherTreeData) {
        // If the type exists, return it. If not, create it and store it.
        TreeType result = treeTypes.get(name);
        
        if (result == null) {
            result = new TreeType(name, color, otherTreeData);
            treeTypes.put(name, result);
            System.out.println("--> Creating new TreeType: " + name);
        }
        return result;
    }
}
```

#### Step 3: Create the Context Class (Extrinsic State)

This class represents the specific tree at a specific location. It links the unique position to the shared type.

```java
public class Tree {
    private int x;
    private int y;
    private TreeType type; // Reference to the Flyweight

    public Tree(int x, int y, TreeType type) {
        this.x = x;
        this.y = y;
        this.type = type;
    }

    public void draw() {
        // Pass the extrinsic state (x,y) to the flyweight
        type.draw(x, y);
    }
}
```

### Using Flyweight in the Main Method

In the **Java** `Main` class, we plant 5 trees. Even though we create 5 `Tree` objects, only 2 `TreeType` objects are ever created in memory (one for "Oak" and one for "Pine").

```java
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Tree> forest = new ArrayList<>();

        // 1. Plant Oaks
        // The factory creates the "Oak" type once and reuses it
        TreeType oakType = TreeFactory.getTreeType("Oak", "Green", "OakTextureStub");
        forest.add(new Tree(10, 20, oakType));
        forest.add(new Tree(15, 25, oakType));
        forest.add(new Tree(20, 30, oakType));

        // 2. Plant Pines
        // The factory creates the "Pine" type once and reuses it
        TreeType pineType = TreeFactory.getTreeType("Pine", "DarkGreen", "PineTextureStub");
        forest.add(new Tree(100, 200, pineType));
        forest.add(new Tree(150, 250, pineType));

        System.out.println("\n--- Drawing Forest ---");
        for (Tree tree : forest) {
            tree.draw();
        }
    }
}
```

**Output:**

```text
--> Creating new TreeType: Oak
--> Creating new TreeType: Pine

--- Drawing Forest ---
Drawing Tree [Oak, Green] at (10, 20)
Drawing Tree [Oak, Green] at (15, 25)
Drawing Tree [Oak, Green] at (20, 30)
Drawing Tree [Pine, DarkGreen] at (100, 200)
Drawing Tree [Pine, DarkGreen] at (150, 250)
```

### Key Characteristics

* **Intrinsic State:** Data that is constant and shared (e.g., the texture of a tree). This is stored in the Flyweight object.
* **Extrinsic State:** Data that varies and is unique to the instance (e.g., the position of a tree). This is passed to the Flyweight methods by the client.
* **Immutability:** Flyweight objects should be immutable. Once created, their intrinsic state should not change.

### Why It Matters

1. **Memory Optimization:** This is the primary benefit. If your app uses 100MB of RAM to store 10,000 objects, the Flyweight pattern can reduce this significantly by sharing the common data.
2. **Performance:** Reduced memory footprint leads to fewer cache misses and less work for the Garbage Collector (GC).
3. **Scalability:** It enables applications (like games or data visualizers) to support vast numbers of objects that would otherwise crash the system due to `OutOfMemoryError`.

**Flyweight Pattern** is a fundamental concept for high-performance **Java** applications, allowing developers to manipulate massive quantities of objects efficiently by smart data sharing.

---
layout: default
title: Iterator Pattern
parent: Behavioral Design Patterns
nav_order: 3
---

## Iterator Pattern

**Design Patterns**: _Iterator Pattern_.

The _Iterator Pattern_ is a behavioral design pattern that provides a way to access the elements of an aggregate object (like a list or a collection) sequentially without exposing its underlying representation (such as an array, stack, or tree).

In **Java**, this pattern is so fundamental that it is built into the language via the `java.util.Iterator` interface and the enhanced `for` loop (foreach). It allows clients to traverse different types of collections using a uniform interface.

### Implementing the Iterator Pattern

To implement this pattern manually (to understand how it works under the hood), we need:

1. **Iterator Interface:** Defines methods for traversing the collection (`hasNext()`, `next()`).
2. **Container (Aggregate) Interface:** Defines a method to create/return an iterator.
3. **Concrete Container:** Holds the data structure.
4. **Concrete Iterator:** Implements the traversal logic specific to that data structure.

In this example, we will create a simple `NameRepository` that stores names in a standard **Java** array, but exposes them through an iterator.

#### Step 1: Create Interfaces

```java
// Iterator Interface
public interface Iterator {
    boolean hasNext();
    Object next();
}

// Container Interface
public interface Container {
    Iterator getIterator();
}
```

#### Step 2: Create the Concrete Repository and Iterator

The concrete iterator is often implemented as a private inner class so it has direct access to the container's private data.

```java
// Concrete Container
public class NameRepository implements Container {
    // Underlying data structure (hidden from the client)
    public String[] names = {"Alice", "Bob", "Charlie", "Diana"};

    @Override
    public Iterator getIterator() {
        return new NameIterator();
    }

    // Concrete Iterator (Inner Class)
    private class NameIterator implements Iterator {
        int index;

        @Override
        public boolean hasNext() {
            // Check if index is within bounds
            if (index < names.length) {
                return true;
            }
            return false;
        }

        @Override
        public Object next() {
            // Return current element and increment index
            if (this.hasNext()) {
                return names[index++];
            }
            return null;
        }
    }
}
```

### Using Iterator in the Main Method

In the **Java** `Main` class, the client does not need to know that the names are stored in an array or know the size of that array. It simply asks the repository for an iterator and loops until `hasNext()` returns false.

```java
public class Main {
    public static void main(String[] args) {
        // Create the collection
        NameRepository namesRepository = new NameRepository();

        // Get the iterator
        System.out.println("--- Iterating over Names ---");
        for (Iterator iter = namesRepository.getIterator(); iter.hasNext();) {
            String name = (String) iter.next();
            System.out.println("Name: " + name);
        }
    }
}
```

**Output:**

```text
--- Iterating over Names ---
Name: Alice
Name: Bob
Name: Charlie
Name: Diana
```

### Key Characteristics

* **Standard Interface:** It provides a uniform interface for traversing different collection structures. Whether it's a list, tree, or graph, the client code (`while(iter.hasNext())`) remains the same.
* **Encapsulation:** The internal structure of the collection (e.g., Array, Linked List) is hidden. The client only sees the Iterator.
* **Single Responsibility Principle:** The traversal algorithms are extracted into separate classes (Iterators), cleaning up the collection code.

### Why It Matters

1. **Uniform Traversal:** In **Java**, this allows different collections (`ArrayList`, `HashSet`, `LinkedList`) to be treated polymorphically during iteration.
2. **Multiple Traversals:** You can traverse the same collection in different ways at the same time by creating multiple Iterator objects (each maintains its own iteration state/index).
3. **Safe Access:** It provides a safe way to access elements without exposing the underlying data structure to modification (unless the Iterator explicitly supports `remove()`).

**Iterator Pattern** is a fundamental concept that powers the **Java** Collections Framework, enabling efficient and clean iteration over data structures while maintaining strict encapsulation.

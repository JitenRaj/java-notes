---
layout: default
title: Memento Pattern
parent: Behavioral Design Patterns
nav_order: 5
permalink: /docs/design-patterns/behavioral-design-patterns/memento-pattern
---

## Memento Pattern

**Design Patterns**: _Memento Pattern_.

The _Memento Pattern_ is a behavioral design pattern that allows you to capture and externalize an object's internal state so that the object can be restored to this state later.

In **Java**, this is the standard approach for implementing "Undo" mechanisms. Crucially, it does this without violating encapsulation—the object's private data remains hidden from the outside world, even though its state is being saved externally.

### Implementing the Memento Pattern

The pattern relies on three distinct actors:

1. **Originator:** The object that has an internal state and can create a snapshot (Memento) of itself or restore its state from a snapshot.
2. **Memento:** A value object that acts as a snapshot of the Originator's state. It usually makes the state immutable so no one else can modify it.
3. **Caretaker:** The object that keeps track of the Mementos (history). It requests a save from the Originator and holds the resulting Memento, but never modifies its contents.

In this example, we will build a simple `TextEditor` (Originator) that allows us to type text, save versions, and undo changes using a `History` (Caretaker) class.

#### Step 1: Create the Memento Class

The Memento stores the state. It should be immutable to ensure the integrity of the history.

```java
// Memento
public class TextWindowMemento {
    private final String text;

    public TextWindowMemento(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
```

#### Step 2: Create the Originator Class

The `TextEditor` creates the Memento containing its current state and uses a Memento to restore a past state.

```java
// Originator
public class TextEditor {
    private String currentText;

    public void addText(String text) {
        this.currentText = text;
    }

    public String getText() {
        return currentText;
    }

    // Creates a save point (Memento)
    public TextWindowMemento save() {
        return new TextWindowMemento(currentText);
    }

    // Restores state from a Memento
    public void restore(TextWindowMemento memento) {
        this.currentText = memento.getText();
    }
}
```

#### Step 3: Create the Caretaker Class

The `History` class manages the saved states. It pushes Mementos onto a stack (or list) and pops them when an undo is requested.

```java
import java.util.ArrayList;
import java.util.List;

// Caretaker
public class History {
    private List<TextWindowMemento> savedStates = new ArrayList<>();

    public void add(TextWindowMemento memento) {
        savedStates.add(memento);
    }

    public TextWindowMemento get(int index) {
        return savedStates.get(index);
    }
    
    // Helper to get the last saved state
    public TextWindowMemento getLast() {
        if (!savedStates.isEmpty()) {
            return savedStates.get(savedStates.size() - 1);
        }
        return null;
    }
}
```

### Using Memento in the Main Method

In the **Java** `Main` class, we act as the user typing in the editor. We save states periodically and then demonstrate the ability to roll back to a previous version.

```java
public class Main {
    public static void main(String[] args) {
        TextEditor editor = new TextEditor();
        History history = new History();

        // 1. Set state #1 and Save
        editor.addText("Version 1: Hello World");
        history.add(editor.save());
        System.out.println("Current State: " + editor.getText());

        // 2. Set state #2 and Save
        editor.addText("Version 2: Hello Java");
        history.add(editor.save());
        System.out.println("Current State: " + editor.getText());

        // 3. Set state #3 (Do not save this one)
        editor.addText("Version 3: Hello Memento");
        System.out.println("Current State: " + editor.getText());

        // 4. Undo Operation (Restore to State #2)
        // We retrieve the second Memento (index 1) from history
        System.out.println("\n--- Performing Undo ---");
        editor.restore(history.get(1));
        System.out.println("Restored State: " + editor.getText());

        // 5. Undo Operation (Restore to State #1)
        editor.restore(history.get(0));
        System.out.println("Restored State: " + editor.getText());
    }
}
```

**Output:**

```text
Current State: Version 1: Hello World
Current State: Version 2: Hello Java
Current State: Version 3: Hello Memento

--- Performing Undo ---
Restored State: Version 2: Hello Java
Restored State: Version 1: Hello World
```

### Key Characteristics

* **Snapshotting:** It captures the complete state of an object at a specific point in time.
* **Encapsulation Preserved:** The Caretaker holds the `Memento` but cannot read or alter its private data (in a strict implementation, the Memento's fields are accessible only to the Originator).
* **Single Responsibility:** The Originator manages its own data, while the Caretaker manages the lifecycle of the snapshots (the history).

### Why It Matters

1. **Undo/Redo Functionality:** This is the most common use case. Text editors, graphics editors, and game saves all rely on this pattern.
2. **Database Transactions:** Mementos can be used to roll back transactions if an operation fails midway.
3. **Clean History Management:** It keeps the object logic clean. The object doesn't need to maintain a complex internal array of its past versions; it delegates that storage responsibility to the Caretaker.

**Memento Pattern** is a fundamental concept for implementing rollback capabilities in **Java** applications, ensuring users can always revert to a safe state without compromising the internal security or structure of the objects.

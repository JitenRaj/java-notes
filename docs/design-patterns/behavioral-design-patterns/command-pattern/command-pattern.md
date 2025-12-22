---
layout: default
title: Command Pattern
parent: Behavioral Design Patterns
nav_order: 2
---

## Command Pattern

**Design Patterns**: _Command Pattern_.

The _Command Pattern_ is a behavioral design pattern that turns a request into a stand-alone object that contains all the information about the request. This transformation allows you to parameterize methods with different requests, queue requests, and log their execution, among other things. At its core, it decouples the object that initiates an action from the object that performs the action.

In **Java**, it abstracts the execution of a specific action into a dedicated class. This pattern separates the objects that invoke an operation (the **Invoker**) from the objects that know how to perform it (the **Receiver**).

### Implementing the Command Pattern

To implement this pattern, we define four core roles:

1. **Command:** An interface with a single `execute()` method.
2. **Receiver:** The object that performs the actual action.
3. **Concrete Command:** Implements the `Command` interface and binds the Receiver and its action.
4. **Invoker:** Holds a reference to a Command object and calls the `execute()` method.

In this example, we model a simple remote control. The `RemoteControl` (Invoker) doesn't know how to turn a light on or off; it just issues a command to the `Light` (Receiver).

#### Step 1: Create the Command Interface

```java
// Command Interface
public interface Command {
    // The method to execute the command
    void execute();
    // Optional: method for undo capability
    void undo();
}
```

#### Step 2: Create the Receiver Class

This object performs the actual business logic. It has no knowledge of the command object.

```java
// Receiver
class Light {
    public void turnOn() {
        System.out.println("Light is ON.");
    }

    public void turnOff() {
        System.out.println("Light is OFF.");
    }
}
```

#### Step 3: Create Concrete Command Classes

These classes implement the command interface and hold a reference to the Receiver.

```java
// Concrete Command 1: Turn Light On
class LightOnCommand implements Command {
    private Light light; // Reference to the Receiver

    public LightOnCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        // Delegates the request to the Receiver
        light.turnOn();
    }
    
    @Override
    public void undo() {
        // Reverse the action for undo support
        light.turnOff();
    }
}

// Concrete Command 2: Turn Light Off
class LightOffCommand implements Command {
    private Light light; // Reference to the Receiver

    public LightOffCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.turnOff();
    }
    
    @Override
    public void undo() {
        light.turnOn();
    }
}
```

#### Step 4: Create the Invoker Class

The Invoker holds the command object and triggers its execution. It is decoupled from the specific command or receiver logic.

```java
// Invoker
class RemoteControl {
    private Command command;

    public void setCommand(Command command) {
        this.command = command;
    }

    public void pressButton() {
        // Invoker just calls execute, not knowing what it does
        System.out.println("Remote button pressed...");
        command.execute();
    }
    
    public void pressUndo() {
        System.out.println("Remote UNDO button pressed...");
        command.undo();
    }
}
```

### Using Command in the Main Method

In the **Java** `Main` class, we configure the command objects and link them to the Invoker. This setup allows for maximum flexibility.

```java
public class Main {
    public static void main(String[] args) {
        // 1. Create the Receiver
        Light livingRoomLight = new Light();

        // 2. Create the Concrete Command objects, linking them to the Receiver
        Command lightOn = new LightOnCommand(livingRoomLight);
        Command lightOff = new LightOffCommand(livingRoomLight);

        // 3. Create the Invoker
        RemoteControl remote = new RemoteControl();

        // --- Sequence 1: Turn On and Off ---
        remote.setCommand(lightOn);
        remote.pressButton(); // Output: Light is ON.

        remote.setCommand(lightOff);
        remote.pressButton(); // Output: Light is OFF.

        // --- Sequence 2: Demonstrate Undo ---
        remote.setCommand(lightOn);
        remote.pressButton(); // Light is ON.
        
        // Use the undo feature
        remote.pressUndo(); // Light is OFF.
    }
}
```

**Output:**

```text
Remote button pressed...
Light is ON.
Remote button pressed...
Light is OFF.
Remote button pressed...
Light is ON.
Remote UNDO button pressed...
Light is OFF.
```

### Key Characteristics

* **Decoupling:** The Invoker is completely decoupled from the specific action performed. It only interacts with the generic `Command` interface.
* **Parameterization:** You can pass different command objects to the same Invoker to achieve different results.
* **History and Undo Functionality:** The pattern naturally supports history and an _undo_ mechanism by adding an `undo()` method to the `Command` interface, crucial in applications like text editors, where users may want to revert actions.
* **Queueing and Logging:** Commands can easily be placed into a queue or logged to a file before execution (e.g., in transaction systems).

### Why It Matters

1. **Flexibility:** It makes it easy to add new commands without changing the Invoker class. Just create a new `ConcreteCommand` class.
2. **Macro Commands:** You can create a composite command (a macro) that holds a list of other commands, allowing a single `execute()` call to trigger multiple actions.
3. **Transaction Management:** By using command objects, you can log all system operations, allowing for rollback or replay functionality.

**Command Pattern** is a fundamental concept used widely in **Java** applications for implementing complex features like transaction systems, toolbars, menu systems, and macro recording, all while maintaining loose coupling.

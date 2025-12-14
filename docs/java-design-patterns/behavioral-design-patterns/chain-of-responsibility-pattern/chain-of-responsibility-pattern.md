---
layout: default
title: Chain of Responsibility Pattern
parent: Behavioral Design Patterns
nav_order: 1
permalink: /docs/design-patterns/behavioral-design-patterns/chain-of-responsibility-pattern
---

## Chain of Responsibility Pattern

**Design Patterns**: _Chain of Responsibility Pattern_.

The _Chain of Responsibility Pattern_ is a behavioral design pattern that allows you to pass requests along a chain of handlers. Upon receiving a request, each handler decides either to process the request or to pass it to the next handler in the chain.

In **Java**, this pattern is widely used to decouple the sender of a request from its receivers, giving multiple objects a chance to handle the request without the sender needing to know who explicitly handles it.

### Implementing the Chain of Responsibility

To implement this pattern, we define a common interface or abstract class for handling requests. This abstract class usually holds a reference to the `next` handler in the chain.

In this example, we will create a logging system with three levels of severity: `INFO`, `DEBUG`, and `ERROR`. The request will bubble up the chain until a logger with the sufficient level handles it.

#### Step 1: Create the Abstract Handler

This class defines the logic for setting the next handler and the template method for processing the request.

```java
public abstract class AbstractLogger {
    public static int INFO = 1;
    public static int DEBUG = 2;
    public static int ERROR = 3;

    protected int level;

    // Reference to the next logger in the chain
    protected AbstractLogger nextLogger;

    public void setNextLogger(AbstractLogger nextLogger) {
        this.nextLogger = nextLogger;
    }

    public void logMessage(int level, String message) {
        // If this logger's level is sufficient, write the log
        if (this.level <= level) {
            write(message);
        }
        
        // Always pass the request to the next logger in the chain (if it exists)
        if (nextLogger != null) {
            nextLogger.logMessage(level, message);
        }
    }

    // Abstract method to be implemented by concrete classes
    abstract protected void write(String message);
}
```

#### Step 2: Create Concrete Handlers

These classes extend the abstract handler and implement the specific writing logic.

```java
// Concrete Handler 1: Console Logger (Standard Info)
class ConsoleLogger extends AbstractLogger {
    public ConsoleLogger(int level) {
        this.level = level;
    }

    @Override
    protected void write(String message) {
        System.out.println("Standard Console::Logger: " + message);
    }
}

// Concrete Handler 2: File Logger (Debug Info)
class FileLogger extends AbstractLogger {
    public FileLogger(int level) {
        this.level = level;
    }

    @Override
    protected void write(String message) {
        System.out.println("File::Logger: " + message);
    }
}

// Concrete Handler 3: Error Logger (Critical Errors)
class ErrorLogger extends AbstractLogger {
    public ErrorLogger(int level) {
        this.level = level;
    }

    @Override
    protected void write(String message) {
        System.out.println("Error Console::Logger: " + message);
    }
}
```

### Using Chain of Responsibility in the Main Method

In the **Java** `Main` class, we configure the chain. A common practice is to have the most specific or critical handler at one end, or to stack them such that a request flows through all relevant processors.

```java
public class Main {
    // Helper method to set up the chain of loggers
    private static AbstractLogger getChainOfLoggers() {
        // Create Loggers
        AbstractLogger errorLogger = new ErrorLogger(AbstractLogger.ERROR);
        AbstractLogger fileLogger = new FileLogger(AbstractLogger.DEBUG);
        AbstractLogger consoleLogger = new ConsoleLogger(AbstractLogger.INFO);

        // Build the Chain: Error -> File -> Console
        // Note: The logic in logMessage decides if it prints AND passes it on.
        errorLogger.setNextLogger(fileLogger);
        fileLogger.setNextLogger(consoleLogger);

        return errorLogger; // Return the head of the chain
    }

    public static void main(String[] args) {
        AbstractLogger loggerChain = getChainOfLoggers();

        System.out.println("--- Sending INFO Request ---");
        loggerChain.logMessage(AbstractLogger.INFO, "This is an information.");

        System.out.println("\n--- Sending DEBUG Request ---");
        loggerChain.logMessage(AbstractLogger.DEBUG, "This is a debug level information.");

        System.out.println("\n--- Sending ERROR Request ---");
        loggerChain.logMessage(AbstractLogger.ERROR, "This is an error information.");
    }
}
```

**Output:**

```text
--- Sending INFO Request ---
Standard Console::Logger: This is an information.

--- Sending DEBUG Request ---
File::Logger: This is a debug level information.
Standard Console::Logger: This is a debug level information.

--- Sending ERROR Request ---
Error Console::Logger: This is an error information.
File::Logger: This is an error information.
Standard Console::Logger: This is an error information.
```

{: .note .highlight }
> In this implementation, the request falls through to all applicable handlers. An ERROR message is handled by Error, File, and Console loggers because its level is higher than all of them.

### Key Characteristics

* **Decoupling:** The sender does not know which specific object will handle the request. It just sends it to the first link in the chain.
* **Dynamic Handling:** You can add, remove, or reorder handlers dynamically at runtime by changing the `nextLogger` references.
* **Single Responsibility:** Each handler focuses on its own processing logic (e.g., writing to a file) and then delegates the rest.

### Why It Matters

1. **Flexibility:** It allows you to assign responsibilities to objects dynamically. For example, in a GUI event system, a button click might be handled by the button, or bubble up to the panel, or the window.
2. **Reduced Coupling:** It avoids coupling the sender of a request to a specific receiver class.
3. **Cleaner Code:** It replaces clumsy `if-else-if` or `switch` statements that check for specific conditions to choose a handler.

**Chain of Responsibility Pattern** is a fundamental concept for building robust event-handling systems and middleware pipelines (like Servlet filters in **Java** Web development), ensuring that requests are processed in an organized, decoupled manner.

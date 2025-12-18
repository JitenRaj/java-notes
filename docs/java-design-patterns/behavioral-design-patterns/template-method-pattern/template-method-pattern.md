---
layout: default
title: Template Method Pattern
parent: Behavioral Design Patterns
nav_order: 9
permalink: /docs/design-patterns/behavioral-design-patterns/template-method-pattern
---

## Template Method Pattern

**Design Patterns**: _Template Method Pattern_.

The _Template Method Pattern_ is a behavioral design pattern that defines the skeleton of an algorithm in the superclass but lets subclasses override specific steps of the algorithm without changing its structure.

In **Java**, this is a classic implementation of the "Hollywood Principle" (Don't call us, we'll call you): the parent class controls the overall flow of execution and calls the subclass methods only when needed.

### Implementing the Template Method Pattern

To implement this pattern, we use an **abstract class** that contains:

1. **The Template Method:** Defined as `final` so subclasses cannot override the workflow itself. It calls the individual steps in a specific order.
2. **Concrete Methods:** Steps that are common to all subclasses.
3. **Abstract Methods:** Steps that vary and must be implemented by subclasses.
4. **Hooks (Optional):** Methods with empty or default implementations that subclasses _can_ override if they want to.

In this example, we will model a **Data Miner** system. The process of mining data (opening a file, extracting data, parsing it, and closing the file) is consistent, but the implementation of parsing differs depending on whether the file is a CSV or a PDF.

#### Step 1: Create the Abstract Base Class

This class defines the `mine()` method, which is the template for the whole algorithm.

```java
// Abstract Class
public abstract class DataMiner {

    // The Template Method
    // Marked 'final' to prevent subclasses from changing the execution order
    public final void mine(String path) {
        openFile(path);
        extractData();
        parseData();
        
        // A hook execution
        if (customerWantsAnalysis()) {
            analyzeData();
        }
        
        closeFile();
    }

    // Common Step 1
    public void openFile(String path) {
        System.out.println("Opening file: " + path);
    }

    // Common Step 2
    public void closeFile() {
        System.out.println("Closing file");
    }

    // Abstract Step (Subclasses MUST implement)
    abstract void extractData();
    
    // Abstract Step (Subclasses MUST implement)
    abstract void parseData();

    // Hook (Subclasses CAN implement)
    // Default implementation does nothing
    void analyzeData() {
        System.out.println("Default: No advanced analysis performed.");
    }
    
    // Hook (Boolean flag to control flow)
    boolean customerWantsAnalysis() {
        return true;
    }
}
```

#### Step 2: Create Concrete Classes

These classes implement the specific parsing logic for different file types.

```java
// Concrete Class 1: PDF Miner
class PDFMiner extends DataMiner {

    @Override
    void extractData() {
        System.out.println("PDF Miner: Extracting raw data from PDF layers...");
    }

    @Override
    void parseData() {
        System.out.println("PDF Miner: Parsing PDF objects and text streams...");
    }
    
    // Overriding a hook
    @Override
    boolean customerWantsAnalysis() {
        return false; // PDF users don't want analysis in this scenario
    }
}

// Concrete Class 2: CSV Miner
class CSVMiner extends DataMiner {

    @Override
    void extractData() {
        System.out.println("CSV Miner: Reading lines from CSV...");
    }

    @Override
    void parseData() {
        System.out.println("CSV Miner: Splitting string by commas...");
    }
    
    // CSV Miner uses the default analyzeData hook logic (which is true)
}
```

### Using Template Method in the Main Method

In the **Java** `Main` class, the client calls the `mine()` method. The abstract parent class orchestrates the sequence, calling the subclass methods at the right time.

```java
public class Main {
    public static void main(String[] args) {
        System.out.println("--- Processing PDF File ---");
        DataMiner pdfProcessor = new PDFMiner();
        pdfProcessor.mine("document.pdf");

        System.out.println("\n--- Processing CSV File ---");
        DataMiner csvProcessor = new CSVMiner();
        csvProcessor.mine("data.csv");
    }
}
```

**Output:**

```text
--- Processing PDF File ---
Opening file: document.pdf
PDF Miner: Extracting raw data from PDF layers...
PDF Miner: Parsing PDF objects and text streams...
Closing file

--- Processing CSV File ---
Opening file: data.csv
CSV Miner: Reading lines from CSV...
CSV Miner: Splitting string by commas...
Default: No advanced analysis performed.
Closing file
```

### Key Characteristics

* **Inversion of Control:** The superclass calls the subclass methods, rather than the other way around.
* **Code Reuse:** Common code (like `openFile` and `closeFile`) is localized in the superclass to avoid duplication.
* **Controlled Extension:** The `final` template method ensures that the algorithm's structure remains intact, while specific steps can be customized.

### Why It Matters

1. **Framework Development:** This is extremely common in framework design (e.g., **Java** Servlet's `doGet` and `doPost` are abstract steps called by the `service()` template method).
2. **Consistency:** It guarantees that critical steps (like closing a resource) are always executed, regardless of how the specific processing is implemented.
3. **Refactoring:** It is often used to refactor code where two classes have very similar code but differ in only minor details.

**Template Method Pattern** is a fundamental concept for code reuse in **Java**, allowing developers to enforce a rigorous algorithmic structure while providing flexibility for implementation details.

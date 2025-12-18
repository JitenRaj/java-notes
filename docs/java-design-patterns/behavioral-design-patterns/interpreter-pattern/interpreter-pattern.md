---
layout: default
title: Interpreter Pattern
parent: Behavioral Design Patterns
nav_order: 11
permalink: /docs/design-patterns/behavioral-design-patterns/interpreter-pattern
---

## Interpreter Pattern

**Design Patterns**: _Interpreter Pattern_.

The _Interpreter Pattern_ is a behavioral design pattern that provides a way to evaluate language grammar or expressions. It involves implementing an expression interface which tells to interpret a particular context.

In **Java**, this pattern is used in compilers, SQL parsing, and symbol processing engines (like regular expressions) where you represent a sentence in a language as an Abstract Syntax Tree (AST) and then run an interpreter over that tree to evaluate it.

### Implementing the Interpreter Pattern

To implement this pattern, we typically define:

1. **Expression Interface:** Declares an `interpret()` method.
2. **Terminal Expression:** Implements the interface for terminal symbols (the "leaf" nodes of the grammar, like specific words or numbers).
3. **Non-Terminal Expression:** Implements the interface for rules (the "composite" nodes, like AND, OR, ADD) and delegates work to other expressions.
4. **Context:** Contains information that is global to the interpreter.

In this example, we will build a simple rule engine that can interpret logical sentences using `AND` and `OR` operations. We will verify if a user string matches specific criteria defined by our rule tree.

#### Step 1: Create the Expression Interface

This defines the contract for all nodes in our Abstract Syntax Tree (AST).

```java
// Expression Interface
public interface Expression {
    boolean interpret(String context);
}
```

#### Step 2: Create Terminal Expressions

This class performs the actual check against the data. It is the base unit of our logic.

```java
// Terminal Expression
public class TerminalExpression implements Expression {
    private String data;

    public TerminalExpression(String data) {
        this.data = data;
    }

    @Override
    public boolean interpret(String context) {
        // Checks if the context string contains the specific data literal
        return context.contains(data);
    }
}
```

#### Step 3: Create Non-Terminal Expressions

These classes combine two or more expressions recursively.

```java
// Non-Terminal Expression: OR
class OrExpression implements Expression {
    private Expression expr1;
    private Expression expr2;

    public OrExpression(Expression expr1, Expression expr2) {
        this.expr1 = expr1;
        this.expr2 = expr2;
    }

    @Override
    public boolean interpret(String context) {
        // Returns true if EITHER expression evaluates to true
        return expr1.interpret(context) || expr2.interpret(context);
    }
}

// Non-Terminal Expression: AND
class AndExpression implements Expression {
    private Expression expr1;
    private Expression expr2;

    public AndExpression(Expression expr1, Expression expr2) {
        this.expr1 = expr1;
        this.expr2 = expr2;
    }

    @Override
    public boolean interpret(String context) {
        // Returns true only if BOTH expressions evaluate to true
        return expr1.interpret(context) && expr2.interpret(context);
    }
}
```

### Using Interpreter in the Main Method

In the **Java** `Main` class, we manually construct the Abstract Syntax Tree (AST) to define our "Language" or "Rules". We then run strings through this tree to see if they are valid according to our grammar.

```java
public class Main {
    
    // Rule 1: "Robert" OR "John" are valid male names
    public static Expression getMaleExpression() {
        Expression robert = new TerminalExpression("Robert");
        Expression john = new TerminalExpression("John");
        return new OrExpression(robert, john);
    }

    // Rule 2: Must contain "Julie" AND "Married" to be a valid married woman
    public static Expression getMarriedWomanExpression() {
        Expression julie = new TerminalExpression("Julie");
        Expression married = new TerminalExpression("Married");
        return new AndExpression(julie, married);
    }

    public static void main(String[] args) {
        // 1. Build the Parse Trees
        Expression isMale = getMaleExpression();
        Expression isMarriedWoman = getMarriedWomanExpression();

        // 2. Interpret Contexts
        System.out.println("Does context 'John' satisfy Male Rule?");
        System.out.println(isMale.interpret("John")); // True ("John" is in the OR rule)

        System.out.println("\nDoes context 'Julie Married' satisfy Married Woman Rule?");
        System.out.println(isMarriedWoman.interpret("Julie Married")); // True (Contains both)

        System.out.println("\nDoes context 'Lucy Married' satisfy Married Woman Rule?");
        System.out.println(isMarriedWoman.interpret("Lucy Married")); // False (Missing "Julie")
    }
}
```

**Output:**

```text
Does context 'John' satisfy Male Rule?
true

Does context 'Julie Married' satisfy Married Woman Rule?
true

Does context 'Lucy Married' satisfy Married Woman Rule?
false
```

### Key Characteristics

* **Grammar Representation:** It explicitly maps the grammar of a language to classes in code.
* **Abstract Syntax Tree (AST):** The pattern works by building a tree where leaf nodes are `TerminalExpressions` and branch nodes are `NonTerminalExpressions`.
* **Recursive Evaluation:** The `interpret` method is called recursively down the tree structure.

### Why It Matters

1. **Domain-Specific Languages (DSL):** It is ideal for creating simple languages for specific tasks (e.g., a query language for a custom database).
2. **Extensibility:** You can easily add new rules (e.g., an `NotExpression` or `XorExpression`) by adding a new class, without modifying the existing tree structure.
3. **Compilers and Parsers:** While complex languages usually use specialized parser generators (like ANTLR), the Interpreter pattern is the foundational logic behind how those parsers evaluate expressions.

**Interpreter Pattern** is a niche but powerful concept in **Java**, primarily used when you need to parse and execute a language or a complex set of rules defined as a grammar.

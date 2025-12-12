---
layout: default
title: Behavioral Design Patterns
nav_order: 3
parent: Java Design Patterns
has_children: true
permalink: /docs/java-design-patterns/behavioral-design-patterns/
---

## Behavioral Design Patterns

Behavioral design patterns are concerned with algorithms and the assignment of responsibilities between objects.

They not only describe patterns of objects or classes but also the patterns of communication between them. They help ensure that different parts of your system are synchronized and efficient.

### Quick Comparison Table

| Pattern | Core Concept | Key Benefit |
|:---|:---|:---|
| Chain of Responsibility | Passing request along a chain | Decouples sender and receiver; gives multiple objects a chance to handle a request. |
| Command | Request as an object | Parameterizes methods with requests; allows undo/redo operations. |
| Iterator | Traversing collections | Accesses elements of a collection sequentially without exposing the underlying form. |
| Mediator | Centralized communication | Reduces chaotic dependencies between objects by forcing them to communicate via a mediator. |
| Memento | Snapshot of state | Captures and restores an object's internal state (e.g., "Save Game" or "Undo"). |
| Observer | Subscription mechanism | Notifies multiple objects about any events that happen to the object they are observing. |
| State | Behavior based on state | Allows an object to alter its behavior when its internal state changes. |
| Strategy | Interchangeable algorithms | Defines a family of algorithms and makes them interchangeable at runtime. |
| Template Method | Skeleton of an algorithm | Defines the steps of an algorithm in a superclass but lets subclasses override steps. |
| Visitor | Adding ops to object structure | Adds new operations to existing object structures without modifying them. |

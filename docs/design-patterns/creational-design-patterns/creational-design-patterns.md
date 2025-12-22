---
layout: default
title: Creational Design Patterns
nav_order: 1
parent: Design Patterns
has_children: true
---

## Creational Design Patterns

Creational design patterns provide various object creation mechanisms, which increase flexibility and reuse of existing code.

They solve the problem of creating objects in a suitable manner for the situation, rather than instantiating objects directly which can lead to inflexible code.

### Quick Comparison Table

| Pattern | Core Concept | Key Benefit |
| :--- | :--- | :--- |
| Singleton | Restricts class to one instance | Ensures a single point of access/control (e.g., Database connection). |
| Factory Method | Interface for creating objects | Allows subclasses to alter the type of objects that will be created. |
| Abstract Factory | Families of related objects | Creates compatible groups of objects without specifying their concrete classes. |
| Builder | Step-by-step construction | Constructs complex objects clearly and separates construction from representation. |
| Prototype | Copying existing objects | Efficiently creates new objects by cloning an existing instance. |

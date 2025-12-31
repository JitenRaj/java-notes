---
layout: default
title: J2EE Patterns
nav_order: 14
has_children: true
---

## J2EE Design Patterns

**Java 2 Platform, Enterprise Edition (J2EE)** patterns are specific architectural solutions for problems commonly faced in enterprise web applications.

While the original "Gang of Four" (GoF) patterns focus on general object-oriented design, J2EE patterns focus on the **Presentation**, **Business**, and **Integration** tiers of a distributed application.

Many of these patterns form the underlying architecture of modern frameworks like **Spring Boot**. Understanding them explains _how_ Spring works under the hood.

## The Patterns

### Presentation Tier

* [**MVC (Model-View-Controller)**](./model-view-controller/model-view-controller.md): The standard architecture for web applications, separating data, UI, and logic.
* [**Front Controller**](./front-controller/front-controller.md): A single entry point for all requests (the concept behind Spring's `DispatcherServlet`).
* [**Intercepting Filter**](./intercepting-filter/intercepting-filter.md): Pluggable filters for pre/post-processing requests (the concept behind Spring Security).

### Business Tier

* [**Business Delegate**](./business-delegate/business-delegate.md): Decouples the client from business services to hide the complexity of remote lookups.
* [**Service Locator**](./service-locator/service-locator.md): Centralizes and caches the lookup of expensive service objects.

### Integration Tier

* [**DAO (Data Access Object)**](./data-access-object/data-access-object.md): Abstracts and encapsulates all access to the data source.
* [**DTO (Data Transfer Object)**](./data-transfer-object/data-transfer-object.md): An object used to carry data between processes to reduce the number of method calls.

---

## Summary Table

| Pattern | Scope | Modern Spring Equivalent |
| :--- | :--- | :--- |
| **MVC** | Architecture | `@RestController`, `@Controller` |
| **Front Controller** | Presentation | `DispatcherServlet` |
| **Intercepting Filter** | Presentation | `Filter`, Spring Security Chain |
| **Business Delegate** | Business | Dependency Injection (`@Autowired`) |
| **Service Locator** | Business | Spring `ApplicationContext` (IoC Container) |
| **DAO** | Data | `JpaRepository` (Spring Data JPA) |
| **DTO** | Data | Java `record` / POJO |

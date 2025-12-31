---
layout: default
title: Intercepting Filter
parent: J2EE Patterns
nav_order: 6
---

## Intercepting Filter Pattern

**J2EE Patterns**: _Intercepting Filter_.

The **Intercepting Filter** pattern is used when we want to do some pre-processing or post-processing with a request or response of the application.

Filters are defined and applied on the request before passing the request to the actual target application.

Common uses include authentication, logging, data compression, or encryption.

### Core Components

* **Filter:**
  * Pluggable filters that perform specific tasks (e.g., `AuthenticationFilter`, `LoggingFilter`) before or after the request reaches the target.

* **Filter Manager:**
  * Manages the filters and the filter chain. It orchestrates the execution of the filters.

* **Target:**
  * The actual resource requested by the client (e.g., a Servlet or Controller).

* **Client:**
  * The object that sends the request to the Target.

### Real-World Analogy

Think of **Airport Security**:

* You (The Request) want to get to the **Boarding Gate (Target)**.
* Before you can get there, you must pass through several checkpoints:
    1. **ID Check (Filter 1):** Verifies who you are.
    2. **Baggage Scan (Filter 2):** Checks for dangerous items.
    3. **Body Scan (Filter 3):** Checks for concealed items.
* If you fail any of these "Filters," you are denied access.
* Only after passing all of them do you reach the Target.

### Implementation Example

Here is a raw Java implementation simulating a filter chain.

#### 1. Create the Filter Interface

```java
public interface Filter {
    void execute(String request);
}

```

#### 2. Create Concrete Filters

```java
public class AuthenticationFilter implements Filter {
    public void execute(String request) {
        System.out.println("Authenticating request: " + request);
    }
}

public class DebugFilter implements Filter {
    public void execute(String request) {
        System.out.println("Request Logged: " + request);
    }
}

```

#### 3. Create the Target

The final destination.

```java
public class Target {
    public void execute(String request) {
        System.out.println("Executing request: " + request);
    }
}

```

#### 4. Create Filter Manager and Chain

This manages the sequence.

```java
import java.util.ArrayList;
import java.util.List;

public class FilterChain {
    private List<Filter> filters = new ArrayList<>();
    private Target target;

    public void addFilter(Filter filter) {
        filters.add(filter);
    }

    public void setTarget(Target target) {
        this.target = target;
    }

    public void execute(String request) {
        // Execute all filters first
        for (Filter filter : filters) {
            filter.execute(request);
        }
        // Then execute target
        target.execute(request);
    }
}

public class FilterManager {
    FilterChain filterChain;

    public FilterManager(Target target) {
        filterChain = new FilterChain();
        filterChain.setTarget(target);
    }

    public void setFilter(Filter filter) {
        filterChain.addFilter(filter);
    }

    public void filterRequest(String request) {
        filterChain.execute(request);
    }
}

```

#### 5. Demo Class

```java
public class InterceptingFilterDemo {
    public static void main(String[] args) {
        FilterManager filterManager = new FilterManager(new Target());
        
        // adding filters to the chain
        filterManager.setFilter(new AuthenticationFilter());
        filterManager.setFilter(new DebugFilter());

        // Client sends a request
        filterManager.filterRequest("HOME");
    }
}

```

### When to Use

* **Cross-Cutting Concerns:** When you have logic (like logging or auth) that applies to _all_ or _many_ requests, and you don't want to copy-paste it into every Controller.
* **Security:** To block invalid requests before they even reach your business logic (e.g., preventing SQL injection or checking API keys).

### Pros & Cons

| Pros | Cons |
| --- | --- |
| **Reusability:** Filters can be added or removed without changing the core code. | **Performance:** Too many filters can slow down request processing (latency). |
| **Separation:** Keeps "infrastructure" logic (security, logging) separate from "business" logic. | **Ordering:** You must be careful with the order (e.g., don't log a request before checking if it's safe). |

## Spring Boot Context

In Spring Boot, the Intercepting Filter pattern is fundamental to how the framework handles web requests.

### 1. Spring Security

Spring Security is essentially a giant chain of Intercepting Filters (The `SecurityFilterChain`).

* When a request hits your app, it goes through filters like `UsernamePasswordAuthenticationFilter`, `CsrfFilter`, and `CorsFilter`.
* If any filter fails (e.g., bad password), the request is rejected before it ever reaches your `@RestController`.

### 2. Custom Filters

You can easily create your own filter in Spring Boot by implementing the `javax.servlet.Filter` (or `jakarta.servlet.Filter`) interface.

```java
import jakarta.servlet.*;
import org.springframework.stereotype.Component;
import java.io.IOException;

@Component
public class MyCustomLoggingFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) 
            throws IOException, ServletException {
        
        System.out.println("MyCustomFilter: Request received");
        
        // Pass the request down the chain
        chain.doFilter(request, response);
        
        System.out.println("MyCustomFilter: Response sent");
    }
}

```

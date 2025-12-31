---
layout: default
title: 'Front Controller'
parent: J2EE Patterns
nav_order: 5
---

## Front Controller Pattern

**J2EE Patterns**: _Front Controller_.

The **Front Controller** pattern is used to provide a centralized request handling mechanism so that all requests will be handled by a single handler.

This handler can do the authentication, authorization, logging, or tracking of request, and then pass the request to the corresponding handler.

In a web application without a Front Controller, you might have multiple servlets (e.g., `StudentServlet`, `TeacherServlet`, `AdminServlet`). This leads to duplicated code because every servlet has to check "Is the user logged in?" or "Is the language set to English?".

The Front Controller solves this by intercepting **everything** first.

### Core Components

* **Front Controller:**
  * The single entry point for the application (usually a Servlet).
  * It performs common processing (logging, security) and delegates to the Dispatcher.

* **Dispatcher:**
  * Responsible for request management. It looks at the request URL and decides which specific helper or controller should handle it.

* **View:**
  * The object for which the request was made (the final page or response).

### Real-World Analogy

Think of a **Corporate Office Building**:

* **Without Front Controller:** Visitors wander into the building and try to find the HR department or the IT department directly. Security is chaotic because every department needs its own guard.
* **With Front Controller:** There is a **Reception Desk** (Front Controller) at the main entrance.
  * Every visitor **must** stop here first.
  * The Receptionist checks their ID (Security/Auth).
  * The Receptionist logs their arrival (Logging).
  * The Receptionist calls the specific employee to come collect them (Dispatching).

### Implementation Example

Here is a raw Java implementation.

#### 1. Create the Views

These represent the final pages or actions we want to reach.

```java
public class StudentView {
    public void show() {
        System.out.println("Displaying Student Page");
    }
}

public class TeacherView {
    public void show() {
        System.out.println("Displaying Teacher Page");
    }
}

```

#### 2. Create the Dispatcher

This class acts as the traffic cop, deciding where to go based on the request.

```java
public class Dispatcher {
    private StudentView studentView;
    private TeacherView teacherView;

    public Dispatcher() {
        studentView = new StudentView();
        teacherView = new TeacherView();
    }

    public void dispatch(String request) {
        if (request.equalsIgnoreCase("STUDENT")) {
            studentView.show();
        } else if (request.equalsIgnoreCase("TEACHER")) {
            teacherView.show();
        } else {
            System.out.println("Page not found");
        }
    }
}

```

#### 3. Create the Front Controller

The single entry point.

```java
public class FrontController {
    private Dispatcher dispatcher;

    public FrontController() {
        dispatcher = new Dispatcher();
    }

    private boolean isUserAuthenticated() {
        System.out.println("Front Controller: Authentication successful.");
        return true;
    }

    private void trackRequest(String request) {
        System.out.println("Front Controller: Requested View is " + request);
    }

    public void dispatchRequest(String request) {
        // 1. Common tasks (Logging, Auth) happen here
        trackRequest(request);

        if (isUserAuthenticated()) {
            // 2. Delegate to the dispatcher
            dispatcher.dispatch(request);
        }
    }
}

```

#### 4. Demo Class (Client)

The client interacts _only_ with the Front Controller.

```java
public class FrontControllerPatternDemo {
    public static void main(String[] args) {
        FrontController frontController = new FrontController();

        // User asks for Student Page
        frontController.dispatchRequest("STUDENT");

        // User asks for Teacher Page
        frontController.dispatchRequest("TEACHER");
    }
}

```

### When to Use

* **Centralized Logic:** When you have code (like security checks, localization, or character encoding) that needs to run for _every_ request.
* **Web Frameworks:** If you are building your own web framework (though you shouldn't; use Spring).
* **Routing Control:** When you need flexible URL routing (e.g., mapping `/app/user/1` to `UserController`).

### Pros & Cons

| Pros | Cons |
| --- | --- |
| **Centralized Control:** A single place to handle common concerns like security and logging. | **Performance:** Since every request goes through one bottleneck, it must be highly optimized. |
| **Thread Safety:** Easier to manage thread-safety in one controller than across 50 servlets. | **Complexity:** Can become a "God Class" if too much logic is stuffed into the Front Controller instead of delegated. |
| **Configurability:** You can change URL mappings in one place without touching individual files. | |

## Spring Boot Context

You are using the Front Controller pattern every single time you use Spring Boot Web, even if you don't see it.

### The _DispatcherServlet_

Spring MVC is built entirely around the **Front Controller** pattern. The class `org.springframework.web.servlet.DispatcherServlet` **is** the Front Controller.

1. **Request Arrives:** An HTTP request hits your Spring Boot app.
2. **Front Controller:** The `DispatcherServlet` catches it.
3. **Handler Mapping:** The Servlet looks at the URL (e.g., `/api/students`) and consults the `HandlerMapping` to find which `@RestController` method matches.
4. **Dispatch:** It calls your specific method in your Controller.
5. **Response:** Your method returns data, and the `DispatcherServlet` handles writing the JSON response or rendering the HTML view.

### Why this matters

Because of this pattern, your `@RestController` classes don't need to extend `HttpServlet` or handle raw `request/response` objects. You can just write a plain Java method (`public Student getStudent()`), and the Front Controller handles all the HTTP complexity for you.

```java
// You only write this part (The Delegate)
@RestController
public class MyController {
    // ...
}

// Spring handles the Front Controller logic automatically (DispatcherServlet)

```

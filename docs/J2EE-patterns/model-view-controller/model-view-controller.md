---
layout: default
title: 'MVC (Model-View-Controller)'
parent: J2EE Patterns
nav_order: 1
---

## MVC (Model-View-Controller) Pattern

**J2EE Patterns**: _Model View Controller_.

The **Model-View-Controller (MVC)** pattern is a fundamental architectural pattern used to decouple the user interface (View) from the data (Model) and the business logic (Controller).

This separation of concerns allows for independent development, testing, and maintenance of each component.

In the context of Java Enterprise (J2EE) and modern frameworks like Spring Boot, MVC is the standard for handling HTTP requests and serving responses.

### Core Components

1. **Model:**

      * Represents the data or business object.
      * It can also include logic to update the controller if the data changes.
      * _Example:_ A `Student` class with fields like `name` and `rollNo`.

2. **View:**

      * Represents the visualization of the data that the model contains.
      * It is responsible for rendering the user interface.
      * _Example:_ A console printout, an HTML page, or a JSON response.

3. **Controller:**

      * Acts on both the Model and the View.
      * It controls the data flow into the model object and updates the view whenever data changes.
      * _Example:_ A class that retrieves data from a database and passes it to the view.

### Real-World Analogy

Think of a **Restaurant**:

* **The Customer (User)** sits down and gives an order.
* **The Waiter (Controller)** takes the order from the customer and sends it to the kitchen.
* **The Kitchen (Model)** gathers the ingredients (data) and cooks the meal.
* **The Waiter (Controller)** takes the cooked meal from the kitchen.
* **The Plate/Presentation (View)** is how the food is arranged and served to the customer.

The Customer never goes into the kitchen to cook the food themselves; the Waiter manages the interaction.

### Implementation Example

Here is a raw Java implementation to demonstrate the pure architectural flow without any frameworks.

#### 1\. Create the Model

The `Student` object serves as our data carrier.

```java
public class Student {
    private String rollNo;
    private String name;

    public String getRollNo() {
        return rollNo;
    }

    public void setRollNo(String rollNo) {
        this.rollNo = rollNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
```

#### 2\. Create the View

The `StudentView` is responsible for printing the details to the console.

```java
public class StudentView {
    public void printStudentDetails(String studentName, String studentRollNo) {
        System.out.println("Student: ");
        System.out.println("Name: " + studentName);
        System.out.println("Roll No: " + studentRollNo);
    }
}
```

#### 3\. Create the Controller

The `StudentController` acts as the intermediary.

```java
public class StudentController {
    private Student model;
    private StudentView view;

    public StudentController(Student model, StudentView view) {
        this.model = model;
        this.view = view;
    }

    public void setStudentName(String name) {
        model.setName(name);
    }

    public String getStudentName() {
        return model.getName();
    }

    public void setStudentRollNo(String rollNo) {
        model.setRollNo(rollNo);
    }

    public String getStudentRollNo() {
        return model.getRollNo();
    }

    public void updateView() {
        view.printStudentDetails(model.getName(), model.getRollNo());
    }
}
```

#### 4\. Demo Class

Simulating the user interaction.

```java
public class MVCPatternDemo {
    public static void main(String[] args) {
        // Fetch student record based on his roll no from the database
        Student model = retrieveStudentFromDatabase();

        // Create a view : to write student details on console
        StudentView view = new StudentView();

        // Create the controller
        StudentController controller = new StudentController(model, view);

        // Display initial data
        controller.updateView();

        // Update model data
        controller.setStudentName("Jiten");

        // Display updated data
        controller.updateView();
    }

    private static Student retrieveStudentFromDatabase() {
        Student student = new Student();
        student.setName("Robert");
        student.setRollNo("10");
        return student;
    }
}
```

### When to Use

* **Web Applications:** Almost all modern Java web frameworks (Spring MVC, Struts, JSF) are based on this pattern.
* **Separation of Duties:** When you have a team where UI designers work on the View (HTML/CSS) and backend engineers work on the Logic (Model/Controller).
* **Multiple Views:** When the same data needs to be displayed in different formats (e.g., HTML for a browser, JSON for a mobile app).

### Pros & Cons

| Pros | Cons |
| :--- | :--- |
| **Separation of Concerns:** High cohesion and loose coupling. | **Complexity:** Can be overkill for very simple, small applications. |
| **Simultaneous Development:** Frontend and backend teams can work in parallel. | **Efficiency:** Frequent updates between views and models can degrade performance if not managed well. |
| **Testability:** Controllers and Models can be tested independently of the View. | |

## Spring Boot Context

In modern Java development, we rarely implement the MVC pattern from scratch using raw Java classes. Instead, we rely on **Spring MVC**, which is built on top of the Servlet API.

### How Spring Handles MVC

Spring introduces a special component called the **DispatcherServlet**. This acts as the **Front Controller** (another J2EE pattern) that intercepts all incoming requests and delegates them to the correct Controller.

### Key Annotations

* **`@Controller`**:
  * Marks a class as a Spring MVC Controller.
  * Typically used for traditional web applications where the response is a rendered HTML view (using Thymeleaf, JSP, etc.).

* **`@RestController`**:
  * A convenience annotation that combines `@Controller` and `@ResponseBody`.
  * It tells Spring that the return value of the methods should be written directly to the HTTP response body (usually as JSON) rather than resolving to a View.
  * This is the standard for building **RESTful APIs**.

### Spring Boot Implementation Example

Here is how the previous "Student" example translates to Spring Boot code.

**The Model (`Student.java`):**

```java
// Using Lombok for brevity, demonstrating modern usage
@Data
@AllArgsConstructor
public class Student {
    private String rollNo;
    private String name;
}

```

**The Controller (`StudentController.java`):**

```java
@RestController
@RequestMapping("/api/students")
public class StudentController {

    @GetMapping("/{rollNo}")
    public Student getStudent(@PathVariable String rollNo) {
        // Logic to fetch from Service/DAO layer
        // Returning the object directly; Spring automatically converts this to JSON
        return new Student(rollNo, "John Doe");
    }
}

```

**The View:**
In a `@RestController` scenario, the "View" is the JSON response sent to the client (e.g., React, Postman, or a mobile app).

```json
{
  "rollNo": "10",
  "name": "John Doe"
}

```

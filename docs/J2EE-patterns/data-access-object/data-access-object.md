---
layout: default
title: 'DAO (Data Access Object)'
parent: J2EE Patterns
nav_order: 2
---

## DAO (Data Access Object) Pattern

**J2EE Patterns**: _Data Access Object_.

The **Data Access Object (DAO)** pattern provides an abstract interface to some type of database or other persistence mechanism.

By mapping application calls to the persistence layer, the DAO provides some specific data operations without exposing details of the database.

In simple terms, it isolates the application/business layer from the persistence layer (usually a relational database) using an abstract API.

### Core Components

* **DAO Interface:**
  * Defines the standard operations to be performed on a model object(s).
  * _Example:_ `StudentDao` interface with methods like `getAllStudents()` or `updateStudent()`.

* **DAO Implementation Class:**
  * Implements the DAO interface.
  * This class is responsible for getting data from a data source (Xml, database, etc.), which can be complex and specific to the database technology (JDBC, Hibernate, etc.).

* **Model / Transfer Object:**
  * Simple POJO (Plain Old Java Object) containing `get` and `set` methods to store data retrieved using the DAO class.

### Real-World Analogy

Think of a **Human Resources (HR) Department**:

* **The Manager (Business Logic)** needs to update an employee's salary.
* The Manager does not go to the physical file cabinet or the secure server room to change the record personally.
* Instead, they send a request form to the **HR Clerk (DAO)**.
* The **HR Clerk** knows exactly which file cabinet, folder, or database system holds the record and how to access it securely.
* The Manager doesn't care if the records are on paper, in Excel, or in the Cloud—they just trust the Clerk to handle the data access.

### Implementation Example

Here is a raw Java implementation. This example simulates a database using a simple `List` to keep the code focused on the structure.

#### 1. Create the Model

The value object we are persisting.

```java
public class Student {
    private String name;
    private int rollNo;

    public Student(String name, int rollNo) {
        this.name = name;
        this.rollNo = rollNo;
    }

    // Getters and Setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public int getRollNo() { return rollNo; }
    public void setRollNo(int rollNo) { this.rollNo = rollNo; }
}

```

#### 2. Create the DAO Interface

This defines _what_ we can do, not _how_ we do it.

```java
import java.util.List;

public interface StudentDao {
    List<Student> getAllStudents();
    Student getStudent(int rollNo);
    void updateStudent(Student student);
    void deleteStudent(Student student);
}

```

#### 3. Create the DAO Implementation

This defines _how_ we access the data. In a real app, this would contain JDBC or SQL code.

```java
import java.util.ArrayList;
import java.util.List;

public class StudentDaoImpl implements StudentDao {
    
    // List is working as a database
    List<Student> students;

    public StudentDaoImpl() {
        students = new ArrayList<Student>();
        students.add(new Student("Robert", 0));
        students.add(new Student("John", 1));
    }

    @Override
    public void deleteStudent(Student student) {
        students.remove(student.getRollNo());
        System.out.println("Student: Roll No " + student.getRollNo() + ", deleted from database");
    }

    @Override
    public List<Student> getAllStudents() {
        return students;
    }

    @Override
    public Student getStudent(int rollNo) {
        return students.get(rollNo);
    }

    @Override
    public void updateStudent(Student student) {
        students.get(student.getRollNo()).setName(student.getName());
        System.out.println("Student: Roll No " + student.getRollNo() + ", updated in database");
    }
}

```

#### 4. Demo Class

The business logic uses the Interface, not the Implementation class directly.

```java
public class DaoPatternDemo {
    public static void main(String[] args) {
        StudentDao studentDao = new StudentDaoImpl();

        // Print all students
        for (Student student : studentDao.getAllStudents()) {
            System.out.println("Student: [RollNo : " + student.getRollNo() + ", Name : " + student.getName() + " ]");
        }

        // Update student
        Student student = studentDao.getStudent(0);
        student.setName("Michael");
        studentDao.updateStudent(student);

        // Get the student
        studentDao.getStudent(0);
        System.out.println("Student: [RollNo : " + student.getRollNo() + ", Name : " + student.getName() + " ]");
    }
}

```

### When to Use

* **Database Swapping:** When you might need to change the underlying database (e.g., from MySQL to Oracle) without rewriting the business logic.
* **Complex Queries:** When you want to encapsulate complex SQL logic away from the main application code.
* **Testing:** It makes Unit Testing easier because you can create a `MockStudentDao` that returns dummy data without connecting to a real database.

### Pros & Cons

| Pros | Cons |
| --- | --- |
| **Separation:** Keeps domain logic clean and separate from database scripts. | **Boilerplate:** Requires creating interfaces and implementations for every entity. |
| **Security:** Centralizes data access, making it easier to audit and secure. | **Complexity:** Adds an extra layer of abstraction which might be unnecessary for very small apps. |
| **Maintainability:** Changes in the database schema only affect the DAO class, not the whole app. | |

## Spring Boot Context

In the modern Spring ecosystem, the DAO pattern has evolved significantly into the **Repository Pattern**.

While the concept remains the same (abstracting data access), Spring Data JPA eliminates the need to write the `StudentDaoImpl` class manually.

### The Spring Data Way

Instead of writing the implementation code, you simply create an interface that extends `JpaRepository`. Spring generates the implementation code at runtime (using Proxies).

```java
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    // Standard CRUD methods (save, findAll, delete) are provided automatically.
    
    // You can define custom queries just by naming the method:
    List<Student> findByName(String name);
}

```

* **`StudentDao`** (Old J2EE)  **`StudentRepository`** (Spring Boot)
* The annotation `@Repository` is a specialization of `@Component` that also enables automatic persistence exception translation.

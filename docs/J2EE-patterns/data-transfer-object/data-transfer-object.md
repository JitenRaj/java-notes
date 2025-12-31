---
layout: default
title: 'DTO (Data Transfer Object)'
parent: J2EE Patterns
nav_order: 3
---

## DTO (Data Transfer Object) Pattern

**J2EE Patterns**: _Data Transfer Object_.

The **Data Transfer Object (DTO)** pattern (formerly known as Value Object in older J2EE specs) is used to pass data with multiple attributes in one shot from the client to the server (or between layers), to avoid multiple calls to a remote server.

In distributed systems, making a remote call is expensive and slow. Instead of asking for a user's name, then their age, then their address (3 calls), we package all that data into a single object - the DTO - and send it once.

### Core Components

* **Client:**
  * The consumer of the data (e.g., a React frontend or a Mobile App).
  * It requests or sends the Transfer Object to the Business Object.

* **Business Object (Service):**
  * Creates the Transfer Object from the raw data entities (Database entities).
  * Populates the DTO with data and returns it to the Client.

* **Transfer Object (DTO):**
  * A simple POJO (Plain Old Java Object) having mostly fields and getters/setters.
  * **Crucial:** It should contain _no business logic_. It is just a carrier.

### Real-World Analogy

Think of **Moving Houses**:

* You have 50 books to move from your old house to your new one.
* **Bad Approach (No DTO):** You pick up one book, drive to the new house, drop it off, drive back, and repeat 50 times. This is incredibly slow (high network latency).
* **DTO Approach:** You pack all 50 books into a **Cardboard Box** (the DTO). You put the box in your car and drive it to the new house in a single trip.
* The "Box" doesn't read the books or organize them; it just holds them so they can be transported efficiently.

### Implementation Example

Here is a raw Java implementation showing how a business object serves a DTO to a client.

#### 1. Create the Transfer Object (DTO)

This is a simple container for data.

```java
public class StudentDTO {
    private String name;
    private int rollNo;

    public StudentDTO(String name, int rollNo) {
        this.name = name;
        this.rollNo = rollNo;
    }

    public String getName() { return name; }
    public int getRollNo() { return rollNo; }
}

```

#### 2. Create the Business Object

This acts as the server or database layer that holds the "real" data.

```java
import java.util.ArrayList;
import java.util.List;

public class StudentBO {
    
    // Acting as a database
    List<StudentDTO> students;

    public StudentBO() {
        students = new ArrayList<StudentDTO>();
        students.add(new StudentDTO("Robert", 0));
        students.add(new StudentDTO("John", 1));
    }

    public void deleteStudent(StudentDTO student) {
        students.remove(student.getRollNo());
        System.out.println("Student: Roll No " + student.getRollNo() + ", deleted from database");
    }

    public List<StudentDTO> getAllStudents() {
        return students;
    }

    public StudentDTO getStudent(int rollNo) {
        return students.get(rollNo);
    }

    public void updateStudent(StudentDTO student) {
        students.get(student.getRollNo()); // In real app, we'd update logic here
        System.out.println("Student: Roll No " + student.getRollNo() + ", updated in database");
    }
}

```

#### 3. Demo Class

The client code interacting with the Business Object.

```java
public class TransferObjectPatternDemo {
    public static void main(String[] args) {
        StudentBO studentBusinessObject = new StudentBO();

        // Print all students
        for (StudentDTO student : studentBusinessObject.getAllStudents()) {
            System.out.println("Student: [RollNo : " + student.getRollNo() + ", Name : " + student.getName() + " ]");
        }

        // Update student
        StudentDTO student = studentBusinessObject.getAllStudents().get(0);
        student = new StudentDTO("Michael", 0); // Modifying data
        studentBusinessObject.updateStudent(student);

        // Get the student
        studentBusinessObject.getStudent(0);
        System.out.println("Student: [RollNo : " + student.getRollNo() + ", Name : " + student.getName() + " ]");
    }
}

```

### When to Use

* **REST APIs:** When you want to control exactly what data is sent to the client (e.g., hiding a password field or an internal ID) by mapping the Database Entity to a Response DTO.
* **Reducing Network Calls:** When you need to aggregate data from multiple tables into a single response.
* **Versioning:** When the database schema changes, but you want to keep the API response (DTO) the same to avoid breaking the frontend.

### Pros & Cons

| Pros | Cons |
| --- | --- |
| **Performance:** Reduces the number of remote calls (chunky vs. chatty interface). | **Boilerplate:** Requires creating many similar classes (e.g., `User` entity vs `UserDTO`). |
| **Security:** Hides sensitive database columns (like passwords/salts) from the UI. | **Maintenance:** If you add a field to the DB, you often have to manually add it to the DTO and the Mapper. |
| **Decoupling:** The View layer is not tightly bound to the Database layer. | |

## Spring Boot Context

In modern Spring Boot applications, DTOs are ubiquitous, especially in **REST Controllers**. We almost never return the raw `@Entity` (Database object) directly to the user.

### Java Records (Modern DTOs)

Since Java 14+, **Records** are the preferred way to create DTOs because they are immutable, concise, and automatically generate getters, `toString`, and `hashCode`.

### Mapping Strategies

Developers often use libraries to automatically copy data from the Entity to the DTO to avoid writing manual `set` methods.

1. **MapStruct:** A compile-time code generator (highly recommended for performance).
2. **ModelMapper:** A runtime reflection-based mapper.

### Spring Boot Implementation Example

**The Entity (Database Object):**

```java
@Entity
@Data
public class UserEntity {
    @Id
    private Long id;
    private String username;
    private String password; // Sensitive! Never send this to UI.
    private String email;
}

```

**The DTO (Java Record):**
This DTO only exposes what is safe to show.

```java
public record UserResponseDTO(Long id, String username, String email) {
    // Password is deliberately excluded here
}

```

**The Controller:**

```java
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public UserResponseDTO getUser(@PathVariable Long id) {
        UserEntity user = userService.findById(id);
        
        // Manual Mapping (or use MapStruct here)
        return new UserResponseDTO(
            user.getId(), 
            user.getUsername(), 
            user.getEmail()
        );
    }
}

```

---
layout: default
title: Mediator Pattern
parent: Behavioral Design Patterns
nav_order: 4
---

## Mediator Pattern

**Design Patterns**: _Mediator Pattern_.

The _Mediator Pattern_ is a behavioral design pattern that reduces chaotic dependencies between objects. The pattern restricts direct communications between the objects and forces them to collaborate only via a mediator object.

In **Java**, this promotes loose coupling by keeping objects from referring to each other explicitly, allowing you to vary their interaction independently. Think of it as an Air Traffic Control tower: airplanes (colleagues) don't talk to each other directly to decide who lands first; they talk to the tower (mediator).

### Implementing the Mediator Pattern

To implement this pattern, we define:

1. **Mediator Interface:** Declares methods for communication with components.
2. **Concrete Mediator:** Encapsulates the interaction logic between components.
3. **Colleagues:** The components (classes) that communicate. They hold a reference to the Mediator but know nothing about other Colleagues.

In this example, we will build a simplified **Chat Room**. Users (colleagues) send messages. Instead of every user holding a reference to every other user (a "many-to-many" mess), they send messages to the `ChatRoom` (mediator), which handles the distribution.

#### Step 1: Create the Mediator Interface (or Abstract Class)

This defines the contract for sending messages.

```java
// Mediator Interface
public interface ChatMediator {
    void sendMessage(String msg, User user);
    void addUser(User user);
}
```

#### Step 2: Create the Colleague Class

The abstract user class holds a reference to the mediator.

```java
// Colleague Class
public abstract class User {
    protected ChatMediator mediator;
    protected String name;

    public User(ChatMediator med, String name) {
        this.mediator = med;
        this.name = name;
    }

    public abstract void send(String msg);
    public abstract void receive(String msg);
}
```

#### Step 3: Create the Concrete Mediator

The `ChatRoom` implements the logic. When it receives a message from a user, it iterates through its list of users and sends it to everyone _except_ the sender.

```java
import java.util.ArrayList;
import java.util.List;

// Concrete Mediator
public class ChatRoomImpl implements ChatMediator {
    private List<User> users;

    public ChatRoomImpl() {
        this.users = new ArrayList<>();
    }

    @Override
    public void addUser(User user) {
        this.users.add(user);
    }

    @Override
    public void sendMessage(String msg, User user) {
        for (User u : this.users) {
            // Message should not be received by the user sending it
            if (u != user) {
                u.receive(msg);
            }
        }
    }
}
```

#### Step 4: Create Concrete Colleagues

These are the specific users participating in the chat.

```java
// Concrete Colleague
public class UserImpl extends User {

    public UserImpl(ChatMediator med, String name) {
        super(med, name);
    }

    @Override
    public void send(String msg) {
        System.out.println(this.name + " Sending Message: " + msg);
        mediator.sendMessage(msg, this);
    }

    @Override
    public void receive(String msg) {
        System.out.println(this.name + " Received Message: " + msg);
    }
}
```

### Using Mediator in the Main Method

In the **Java** `Main` class, we initialize the mediator, add users to it, and simulate a conversation. Note that `user1` sends a message without knowing that `user2`, `user3`, or `user4` even exist.

```java
public class Main {
    public static void main(String[] args) {
        // 1. Create the Mediator
        ChatMediator chatMediator = new ChatRoomImpl();

        // 2. Create Users (Colleagues) and register them with Mediator
        User user1 = new UserImpl(chatMediator, "Jason");
        User user2 = new UserImpl(chatMediator, "Jennifer");
        User user3 = new UserImpl(chatMediator, "Lucy");
        User user4 = new UserImpl(chatMediator, "David");

        chatMediator.addUser(user1);
        chatMediator.addUser(user2);
        chatMediator.addUser(user3);
        chatMediator.addUser(user4);

        // 3. Send a message
        // Jason sends a message. The Mediator routes it to Jennifer, Lucy, and David.
        user1.send("Hello everyone! Welcome to the Mediator Pattern demo.");
    }
}
```

**Output:**

```text
Jason Sending Message: Hello everyone! Welcome to the Mediator Pattern demo.
Jennifer Received Message: Hello everyone! Welcome to the Mediator Pattern demo.
Lucy Received Message: Hello everyone! Welcome to the Mediator Pattern demo.
David Received Message: Hello everyone! Welcome to the Mediator Pattern demo.
```

### Key Characteristics

* **Centralized Control:** The complex interaction logic is extracted from individual components and centralized in the Mediator.
* **Decoupling:** Colleagues do not reference each other. They only reference the Mediator interface. This means you can add new Colleagues (e.g., a "BotUser") without changing existing User code.
* **Many-to-Many to One-to-Many:** It transforms a difficult Many-to-Many relationship (everyone talks to everyone) into a simpler One-to-Many relationship (everyone talks to the Mediator).

### Why It Matters

1. **Reduces Complexity:** If you have 10 objects that all interact with each other, you potentially have 45 connections. With a mediator, you have only 10 connections.
2. **Maintainability:** Changes to the communication logic (e.g., adding a filter for bad words in the chat) only happen in the Mediator, not in every Colleague.
3. **Reusability:** The individual Colleague classes become more reusable because they are not hard-wired to specific other classes.

**Mediator Pattern** is a fundamental concept for managing complex communications in systems like GUI forms (where buttons affect text fields which affect checkboxes) and distributed systems, ensuring that **Java** components remain loosely coupled and easy to maintain.

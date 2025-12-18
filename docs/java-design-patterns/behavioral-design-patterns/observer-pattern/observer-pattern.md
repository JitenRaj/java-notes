---
layout: default
title: Observer Pattern
parent: Behavioral Design Patterns
nav_order: 6
permalink: /docs/design-patterns/behavioral-design-patterns/observer-pattern
---

## Observer Pattern

**Design Patterns**: _Observer Pattern_.

The _Observer Pattern_ is a behavioral design pattern that defines a one-to-many dependency between objects so that when one object (the **Subject**) changes state, all its dependents (the **Observers**) are notified and updated automatically.

In **Java**, this is the core principle behind event-driven programming, such as GUI button clicks or the Model-View-Controller (MVC) architecture. It allows objects to "subscribe" to events from another object without tightly coupling them together.

### Implementing the Observer Pattern

To implement this pattern, we need:

1. **Subject Interface:** Defines methods to attach, detach, and notify observers.
2. **Observer Interface:** Defines a method (usually `update()`) that the Subject calls to notify changes.
3. **Concrete Subject:** The object holding the state. It triggers the notification when its state changes.
4. **Concrete Observer:** The object that reacts to the update.

In this example, we will simulate a **News Agency** (Subject). When breaking news occurs, multiple **News Channels** (Observers) will broadcast it automatically.

#### Step 1: Create the Observer Interface

This interface defines the update method that all subscribers must implement.

```java
// Observer Interface
public interface Channel {
    void update(String news);
}
```

#### Step 2: Create the Subject Interface

This interface defines how observers are managed.

```java
// Subject Interface
public interface NewsAgency {
    void attach(Channel channel);
    void detach(Channel channel);
    void notifyObservers();
}
```

#### Step 3: Create the Concrete Subject

The `Reuters` class holds the news state. When `setNews()` is called, it automatically notifies all registered channels.

```java
import java.util.ArrayList;
import java.util.List;

// Concrete Subject
public class Reuters implements NewsAgency {
    private String news;
    private List<Channel> channels = new ArrayList<>();

    @Override
    public void attach(Channel channel) {
        channels.add(channel);
    }

    @Override
    public void detach(Channel channel) {
        channels.remove(channel);
    }

    @Override
    public void notifyObservers() {
        for (Channel channel : channels) {
            channel.update(this.news);
        }
    }

    // Method to change state and trigger notification
    public void setNews(String news) {
        this.news = news;
        System.out.println("\n--- Breaking News: " + news + " ---");
        notifyObservers();
    }
}
```

#### Step 4: Create Concrete Observers

These classes define how specific channels react to the news.

```java
// Concrete Observer 1
class FoxNews implements Channel {
    @Override
    public void update(String news) {
        System.out.println("Fox News reports: " + news);
    }
}

// Concrete Observer 2
class CNN implements Channel {
    @Override
    public void update(String news) {
        System.out.println("CNN reports: " + news);
    }
}

// Concrete Observer 3
class BBC implements Channel {
    @Override
    public void update(String news) {
        System.out.println("BBC reports: " + news);
    }
}
```

### Using Observer in the Main Method

In the **Java** `Main` class, we register different channels to the agency. Notice how the agency communicates with them without knowing their specific classes, only that they implement `Channel`.

```java
public class Main {
    public static void main(String[] args) {
        // 1. Create the Subject
        Reuters agency = new Reuters();

        // 2. Create Observers
        Channel fox = new FoxNews();
        Channel cnn = new CNN();
        Channel bbc = new BBC();

        // 3. Register Observers
        agency.attach(fox);
        agency.attach(cnn);
        agency.attach(bbc);

        // 4. Update State (Triggers Notification)
        agency.setNews("Java 25 Released!");

        // 5. Unregister an Observer and Update again
        agency.detach(fox);
        agency.setNews("New Design Patterns Tutorial Available.");
    }
}
```

**Output:**

```text
--- Breaking News: Java 25 Released! ---
Fox News reports: Java 25 Released!
CNN reports: Java 25 Released!
BBC reports: Java 25 Released!

--- Breaking News: New Design Patterns Tutorial Available. ---
CNN reports: New Design Patterns Tutorial Available.
BBC reports: New Design Patterns Tutorial Available.
```

### Key Characteristics

* **Loose Coupling:** The Subject knows nothing about the concrete Observer classes, only that they implement the Observer interface. This allows you to add new observers without modifying the Subject code.
* **Push vs. Pull:**
        **Push:** The Subject sends the data to the Observer (as seen in this example: `update(this.news)`).
        **Pull:** The Subject sends a notification, and the Observer requests the data from the Subject.
* **Dynamic Relationships:** Observers can be added or removed at runtime (e.g., a user subscribing/unsubscribing from a newsletter).

### Why It Matters

1. **Event Handling:** It is the backbone of **Java** GUI frameworks (Swing, JavaFX). When you add an `ActionListener` to a button, you are adding an Observer.
2. **Publish-Subscribe Systems:** It serves as the foundational logic for modern messaging systems (like Kafka or RabbitMQ), where producers publish messages and consumers subscribe to topics.
3. **Data Binding:** In UI development, it keeps the View automatically updated whenever the underlying Model data changes.

**Observer Pattern** is a fundamental concept for creating reactive, event-driven applications in **Java**, ensuring high modularity and scalability by decoupling data sources from data consumers.

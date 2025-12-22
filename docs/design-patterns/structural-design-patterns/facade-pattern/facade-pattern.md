---
layout: default
title: Facade Pattern
parent: Structural Design Patterns
nav_order: 5
---

## Facade Pattern

**Design Patterns**: _Facade Pattern_.

The _Facade Pattern_ is a structural design pattern that provides a simplified, unified interface to a complex subsystem of classes, interfaces, or libraries.

In **Java**, the Facade acts as a single entry point to the subsystem, making it easier to use while keeping the underlying complexity hidden. This pattern doesn't introduce new functionality; rather, it simplifies how existing functionality is accessed, promoting loose coupling between the client code and the complex components.

### Implementing the Facade Pattern

To implement this pattern, we identify a complex subsystem and create a single Facade class that handles the coordination of calls to the many internal classes.

In this example, we will model a home theater system. Operating a home theater involves many steps (turning on the lights, projector, amplifier, setting the volume, etc.). The `HomeTheaterFacade` simplifies this into two methods: `watchMovie()` and `endMovie()`.

#### Step 1: Create the Subsystem Classes

These are the complex components that the client would normally interact with individually.

```java
// Subsystem Class 1
class Amplifier {
    public void on() { System.out.println("Amplifier is ON."); }
    public void off() { System.out.println("Amplifier is OFF."); }
    public void setVolume(int volume) { System.out.println("Amplifier volume set to " + volume + "."); }
}

// Subsystem Class 2
class Projector {
    public void on() { System.out.println("Projector is ON."); }
    public void off() { System.out.println("Projector is OFF."); }
    public void wideScreenMode() { System.out.println("Projector set to widescreen mode."); }
}

// Subsystem Class 3
class Screen {
    public void down() { System.out.println("Theater Screen goes down."); }
    public void up() { System.out.println("Theater Screen goes up."); }
}

// Subsystem Class 4
class Lights {
    public void dim(int level) { System.out.println("Theater lights dim to " + level + "%."); }
}
```

#### Step 2: Create the Facade Class

The `HomeTheaterFacade` contains instances of the subsystem classes and exposes simple methods to the client.

```java
public class HomeTheaterFacade {
    // Composition: The Facade holds references to all subsystem objects
    private Amplifier amp;
    private Projector projector;
    private Screen screen;
    private Lights lights;

    public HomeTheaterFacade(Amplifier amp, Projector projector, Screen screen, Lights lights) {
        this.amp = amp;
        this.projector = projector;
        this.screen = screen;
        this.lights = lights;
    }

    // Simplified method for the client: START MOVIE
    public void watchMovie(String movie) {
        System.out.println("\n--- Get ready to watch a movie: " + movie + " ---");
        lights.dim(10);
        screen.down();
        projector.on();
        projector.wideScreenMode();
        amp.on();
        amp.setVolume(5);
    }

    // Simplified method for the client: END MOVIE
    public void endMovie() {
        System.out.println("\n--- Shutting down the home theater ---");
        lights.dim(100); // Turn lights back on
        screen.up();
        projector.off();
        amp.off();
    }
}
```

### Using Facade in the Main Method

In the **Java** `Main` class, the client only interacts with the Facade. The client is completely shielded from the complexity of coordinating the four separate subsystem classes.

```java
public class Main {
    public static void main(String[] args) {
        // Instantiate the complex subsystem components
        Amplifier amp = new Amplifier();
        Projector projector = new Projector();
        Screen screen = new Screen();
        Lights lights = new Lights();

        // Create the Facade, passing the components
        HomeTheaterFacade homeTheater = new HomeTheaterFacade(amp, projector, screen, lights);

        // Client calls a single method on the Facade
        homeTheater.watchMovie("The Design Pattern Documentary");

        // Client calls another single method
        homeTheater.endMovie();
    }
}
```

**Output:**

```text
--- Get ready to watch a movie: The Design Pattern Documentary ---
Theater lights dim to 10%.
Theater Screen goes down.
Projector is ON.
Projector set to widescreen mode.
Amplifier is ON.
Amplifier volume set to 5.

--- Shutting down the home theater ---
Theater lights dim to 100%.
Theater Screen goes up.
Projector is OFF.
Amplifier is OFF.
```

### Key Characteristics

* **Simplified Interface:** By wrapping complex subsystems, the Facade exposes a small, manageable set of methods relevant to the client's needs.
* **Loose Coupling:** The client is coupled only to the Facade class, not to the numerous classes within the subsystem.
* **Subsystem Integrity:** The Facade does not prevent clients from accessing the subsystem classes directly if they need complex, granular control, but it strongly discourages it.

### Why It Matters

1. **Reduced Complexity:** It makes a large and complex subsystem much easier to use, reducing the client's learning curve and minimizing the risk of errors.
2. **Decoupling:** By depending only on the Facade, it decouples the client code from the subsystem, making the overall system more manageable and easier to maintain.
3. **Layering:** It helps structure a system into layers, where the Facade defines the boundary between the subsystem layer and the presentation/business logic layer.

**Facade Pattern** is a fundamental concept for managing complexity and promoting clean architecture in large **Java** applications, especially when dealing with complex initialization or coordination logic.

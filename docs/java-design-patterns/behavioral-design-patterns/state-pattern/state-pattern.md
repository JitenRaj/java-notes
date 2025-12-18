---
layout: default
title: State Pattern
parent: Behavioral Design Patterns
nav_order: 7
permalink: /docs/design-patterns/behavioral-design-patterns/state-pattern
---

## State Pattern

**Design Patterns**: _State Pattern_.

The _State Pattern_ is a behavioral design pattern that allows an object to alter its behavior when its internal state changes. It appears as if the object changed its class.

In **Java**, this pattern is used to replace massive `switch` or `if-else` blocks that check an object's state (e.g., `if (state == PLAYING)`) with a clean object-oriented architecture where each state is represented by a separate class.

### Implementing the State Pattern

To implement this pattern, we define:

1. **State Interface:** Defines the methods that depend on the state (e.g., `pressPlay()`).
2. **Concrete States:** Classes implementing the interface, providing the specific behavior for that state (e.g., `PlayingState`, `PausedState`).
3. **Context:** The main class (e.g., `AudioPlayer`) that holds a reference to the current state object and delegates the work to it.

In this example, we will model a simple **Audio Player**. The behavior of the "Play" button changes depending on whether the player is currently **Ready** (starts playing), **Playing** (pauses), or **Locked** (does nothing).

#### Step 1: Create the State Interface

This defines the actions that can be performed on the Context.

```java
// State Interface
public interface State {
    void pressPlay(AudioPlayer player);
    void pressLock(AudioPlayer player);
}
```

#### Step 2: Create the Context Class

The `AudioPlayer` holds the state. Notice it doesn't have complex logic; it just asks the `state` object what to do.

```java
// Context
public class AudioPlayer {
    private State state;
    private boolean playing = false;

    public AudioPlayer() {
        // Initial state is Ready
        this.state = new ReadyState();
    }

    public void setState(State state) {
        this.state = state;
    }

    // Context delegates the action to the current state object
    public void clickPlay() {
        state.pressPlay(this);
    }

    public void clickLock() {
        state.pressLock(this);
    }

    public void startPlayback() {
        System.out.println("...Music starts playing...");
        this.playing = true;
    }

    public void stopPlayback() {
        System.out.println("...Music stops...");
        this.playing = false;
    }
}
```

#### Step 3: Create Concrete State Classes

Each class implements behavior specific to that state and handles transitions to other states.

```java
// Concrete State 1: Ready (Stopped)
class ReadyState implements State {
    @Override
    public void pressPlay(AudioPlayer player) {
        System.out.println("Ready State: Play pressed.");
        player.startPlayback();
        // Transition to Playing State
        player.setState(new PlayingState());
    }

    @Override
    public void pressLock(AudioPlayer player) {
        System.out.println("Ready State: Player locked.");
        player.setState(new LockedState());
    }
}

// Concrete State 2: Playing
class PlayingState implements State {
    @Override
    public void pressPlay(AudioPlayer player) {
        System.out.println("Playing State: Pause pressed.");
        player.stopPlayback();
        // Transition back to Ready State
        player.setState(new ReadyState());
    }

    @Override
    public void pressLock(AudioPlayer player) {
        System.out.println("Playing State: Player locked. Music continues.");
        player.setState(new LockedState());
    }
}

// Concrete State 3: Locked
class LockedState implements State {
    @Override
    public void pressPlay(AudioPlayer player) {
        System.out.println("Locked State: Buttons disabled. Unlock first.");
    }

    @Override
    public void pressLock(AudioPlayer player) {
        System.out.println("Locked State: Player unlocked.");
        player.setState(new ReadyState());
    }
}
```

### Using State in the Main Method

In the **Java** `Main` class, we simulate user interaction. The `AudioPlayer` behaves differently for the exact same `clickPlay()` call, depending on its internal state object.

```java
public class Main {
    public static void main(String[] args) {
        AudioPlayer player = new AudioPlayer();

        // 1. Initial State: Ready
        // Action: Should start playing
        player.clickPlay(); 

        System.out.println("---");

        // 2. Current State: Playing
        // Action: Should stop playing (toggle)
        player.clickPlay();

        System.out.println("---");

        // 3. Lock the player
        player.clickLock();

        // 4. Current State: Locked
        // Action: Should do nothing
        player.clickPlay();

        System.out.println("---");

        // 5. Unlock
        player.clickLock();
        player.clickPlay();
    }
}
```

**Output:**

```text
Ready State: Play pressed.
...Music starts playing...
---
Playing State: Pause pressed.
...Music stops...
---
Ready State: Player locked.
Locked State: Buttons disabled. Unlock first.
---
Locked State: Player unlocked.
Ready State: Play pressed.
...Music starts playing...
```

### Key Characteristics

* **Behavior Encapsulation:** Each state is its own class. New states (e.g., `RewindingState`) can be added without modifying existing state classes.
* **State Transitions:** Transitions can be controlled by the Context or within the State classes themselves (as seen above, where `ReadyState` switches the player to `PlayingState`).
* **Eliminates Conditionals:** It removes the need for monolithic `switch (state)` statements in the context class.

### Why It Matters

1. **Maintainability:** In complex workflows (like order processing or game character behavior), having all state logic in one class leads to "Spaghetti Code." This pattern organizes logic into manageable classes.
2. **Single Responsibility Principle:** Code related to a specific state resides in a specific class.
3. **Open/Closed Principle:** You can introduce new states without changing the context or existing states.

**State Pattern** is a fundamental concept for implementing finite state machines (FSM) in **Java**, ensuring that objects with complex lifecycles remain clean, readable, and bug-free.

---
layout: default
title: Adapter Pattern
parent: Structural Design Patterns
nav_order: 1
permalink: /docs/design-patterns/structural-design-patterns/adapter-pattern
---

## Adapter Pattern

**Design Patterns**: _Adapter Pattern_.

The _Adapter Pattern_ is a structural design pattern that allows objects with incompatible interfaces to work together. It acts as a bridge between two different interfaces.

In **Java**, it acts as a bridge between two unrelated interfaces, much like a real-world power adapter allows a plug from one country to fit into a socket of another. This pattern involves a single class (the Adapter) which joins functionalities of independent or incompatible interfaces.

### Implementing the Adapter Pattern

To implement this pattern, we typically create a wrapper class that implements the target interface required by the client, while internally holding an instance of the legacy or incompatible class to delegate the actual work.

In this example, we have a basic `AudioPlayer` that can only play mp3 files. We want to extend its capability to play vlc and mp4 files using an existing `AdvancedMediaPlayer` interface, without altering the underlying media code.

#### Step 1: Define Interfaces and Concrete Classes

```java
// Target Interface: The standard interface the client expects
public interface MediaPlayer {
    void play(String audioType, String fileName);
}

// Adaptee Interface: A separate, incompatible interface
public interface AdvancedMediaPlayer {
    void playVlc(String fileName);
    void playMp4(String fileName);
}

// Concrete Adaptee 1
class VlcPlayer implements AdvancedMediaPlayer {
    @Override
    public void playVlc(String fileName) {
        System.out.println("Playing vlc file. Name: " + fileName);
    }

    @Override
    public void playMp4(String fileName) {
        // do nothing
    }
}

// Concrete Adaptee 2
class Mp4Player implements AdvancedMediaPlayer {
    @Override
    public void playVlc(String fileName) {
        // do nothing
    }

    @Override
    public void playMp4(String fileName) {
        System.out.println("Playing mp4 file. Name: " + fileName);
    }
}
```

#### Step 2: Create the Adapter Class

The Adapter implements the `MediaPlayer` interface (what the client uses) but internally uses `AdvancedMediaPlayer` objects.

```java
public class MediaAdapter implements MediaPlayer {

    AdvancedMediaPlayer advancedMusicPlayer;

    public MediaAdapter(String audioType) {
        if (audioType.equalsIgnoreCase("vlc")) {
            advancedMusicPlayer = new VlcPlayer();
        } else if (audioType.equalsIgnoreCase("mp4")) {
            advancedMusicPlayer = new Mp4Player();
        }
    }

    @Override
    public void play(String audioType, String fileName) {
        if (audioType.equalsIgnoreCase("vlc")) {
            advancedMusicPlayer.playVlc(fileName);
        } else if (audioType.equalsIgnoreCase("mp4")) {
            advancedMusicPlayer.playMp4(fileName);
        }
    }
}
```

#### Step 3: Integrate into the Concrete Client Class

The `AudioPlayer` can now handle extra formats by delegating to the adapter.

```java
public class AudioPlayer implements MediaPlayer {
    MediaAdapter mediaAdapter;

    @Override
    public void play(String audioType, String fileName) {
        // Built-in support for mp3
        if (audioType.equalsIgnoreCase("mp3")) {
            System.out.println("Playing mp3 file. Name: " + fileName);
        } 
        // MediaAdapter support for other formats
        else if (audioType.equalsIgnoreCase("vlc") || audioType.equalsIgnoreCase("mp4")) {
            mediaAdapter = new MediaAdapter(audioType);
            mediaAdapter.play(audioType, fileName);
        } 
        else {
            System.out.println("Invalid media. " + audioType + " format not supported");
        }
    }
}
```

### Using Adapter in the Main Method

In the **Java** `Main` class below, the client uses the `AudioPlayer` directly. It doesn't need to know that `VlcPlayer` or `Mp4Player` exists; the Adapter handles the translation logic transparently.

```java
public class Main {
    public static void main(String[] args) {
        AudioPlayer audioPlayer = new AudioPlayer();

        audioPlayer.play("mp3", "beyond_the_horizon.mp3");
        audioPlayer.play("mp4", "alone.mp4");
        audioPlayer.play("vlc", "far_far_away.vlc");
        audioPlayer.play("avi", "mind_me.avi");
    }
}
```

**Output:**

```text
Playing mp3 file. Name: beyond_the_horizon.mp3
Playing mp4 file. Name: alone.mp4
Playing vlc file. Name: far_far_away.vlc
Invalid media. avi format not supported
```

### Key Characteristics

* **Wrapper:** The pattern is often referred to as a "Wrapper" because the adapter wraps the "Adaptee" (the incompatible class) to make it look like the "Target" (the expected interface).
* **Delegation:** The adapter does not implement the logic itself; it translates the request and delegates the actual execution to the wrapped object.
* **Single Responsibility:** You can separate the interface or data conversion code from the primary business logic of the program.

### Why It Matters

1. **Interoperability:** It allows you to integrate legacy code or third-party libraries into your application even if their interfaces don't match your system's design.
2. **Reusability:** You can reuse existing functionality (like the `AdvancedMediaPlayer`) without modifying its source code (Open/Closed Principle).
3. **Decoupling:** The client code remains coupled only to the interface it expects (`MediaPlayer`), oblivious to the complex classes lurking behind the adapter.

**Adapter Pattern** is a fundamental concept for system integration, ensuring that **Java** applications can evolve and incorporate diverse components without requiring a complete rewrite of existing interfaces.

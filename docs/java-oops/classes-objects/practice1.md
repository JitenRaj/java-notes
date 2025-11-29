---
layout: default
title: Practice
parent: Classes and Objects
grand_parent: Java OOPs Concepts
nav_order: 1
---

### Exercise

Let's build on what we've learned so far. We'll modify the data members and related methods in the given class.

Change the given `Musician` class to also include the data member `yearsActive`, which captures the number of years the musician has been active. Make sure to update the constructor and the `display` method to reflect and handle this new data member.

***Musician Class***

```java
public class Musician {
    private String name;
    private String instrument;
    // TODO: Add a new data member 'yearsActive' to capture the number of years the musician has been active

    // TODO: Update the constructor to include the yearsActive data member
    public Musician(String name, String instrument) {
        this.name = name;
        this.instrument = instrument;
    }

    // TODO: Update the display method to include the yearsActive data member in the output
    public void display() {
        System.out.println("Name: " + name + ", Instrument: " + instrument);
    }
}

```

***Main Class***

```java

public class Main {
    public static void main(String[] args) {
        // TODO: Update the instance of Musician to include the yearsActive data member with a value of 10
        Musician musician = new Musician("John", "Guitar");
        musician.display();
    }
}

```

Once your modifications are complete, run the `main` method in `Main.java` to verify that your solution works as expected!

### Solution

***Musician Class***

```java
public class Musician {
    private String name;
    private String instrument;
    // TODO: Add a new data member 'yearsActive' to capture the number of years the musician has been active
    private int yearsActive;

    // TODO: Update the constructor to include the yearsActive data member
    public Musician(String name, String instrument, int yearsActive) {
        this.name = name;
        this.instrument = instrument;
        this.yearsActive = yearsActive;
    }

    // TODO: Update the display method to include the yearsActive data member in the output
    public void display() {
        System.out.println("Name: " + name + ", Instrument: " + instrument);
        System.out.println("Years Active: " + yearsActive);
    }
}
```

***Main Class***

```java

public class Main {
    public static void main(String[] args) {
        // TODO: Update the instance of Musician to include the yearsActive data member with a value of 10
        Musician musician = new Musician("John", "Guitar", 10);
        musician.display();
    }
}

```

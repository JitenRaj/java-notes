package DesignPatterns.Creational.Prototype;

public class Main {
    public static void main(String[] args) {
        // 1. Initialize the cache (create prototypes)
        ShapeCache.loadCache();

        // 2. Retrieve clones from the registry
        Shape clonedShape1 = ShapeCache.getShape("1");
        System.out.println("Shape : " + clonedShape1.getType());
        clonedShape1.draw();

        Shape clonedShape2 = ShapeCache.getShape("2");
        System.out.println("Shape : " + clonedShape2.getType());
        clonedShape2.draw();

        // 3. Verify that it is a clone (different memory address)
        Shape clonedShape3 = ShapeCache.getShape("1");
        if (clonedShape1 != clonedShape3) {
            System.out.println("Success: clonedShape1 and clonedShape3 are different objects in memory.");
        }
    }
}

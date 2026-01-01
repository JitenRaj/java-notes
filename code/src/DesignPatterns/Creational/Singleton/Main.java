package DesignPatterns.Creational.Singleton;

public class Main {

    public static void main(String[] args) {
        System.out.println("==============================================");
        System.out.println("      STARTING SINGLETON PATTERN TESTS        ");
        System.out.println("==============================================\n");

        testLazySingleton();
        testSynchronizedSingleton();
        testDoubleCheckedLocking();
        testEagerSingleton();
        testBillPughSingleton();
        testEnumSingleton();

        System.out.println("==============================================");
        System.out.println("      ALL SINGLETON TESTS COMPLETED           ");
        System.out.println("==============================================");
    }

    // -----------------------------------------------------------------------
    // 1. Lazy Initialization
    // -----------------------------------------------------------------------
    public static void testLazySingleton() {
        System.out.println("--- 1. Testing Lazy Initialization Singleton ---");

        // First request: Should trigger creation
        System.out.println("Requesting first LazySingleton instance...");
        LazySingleton lazyRef1 = LazySingleton.getInstance();
        lazyRef1.showMessage();

        // Second request: Should return existing instance
        System.out.println("Requesting second LazySingleton instance...");
        LazySingleton lazyRef2 = LazySingleton.getInstance();

        // Verify Identity
        checkEquality(lazyRef1, lazyRef2, "LazySingleton");
        System.out.println("\n");
    }

    // -----------------------------------------------------------------------
    // 2. Synchronized Initialization
    // -----------------------------------------------------------------------
    public static void testSynchronizedSingleton() {
        System.out.println("--- 2. Testing Synchronized Singleton ---");

        // First request
        System.out.println("Requesting first SynchronizedSingleton instance (Thread-Safe)...");
        SynchronizedSingleton syncRef1 = SynchronizedSingleton.getInstance();
        syncRef1.showMessage();

        // Second request
        System.out.println("Requesting second SynchronizedSingleton instance...");
        SynchronizedSingleton syncRef2 = SynchronizedSingleton.getInstance();

        // Verify Identity
        checkEquality(syncRef1, syncRef2, "SynchronizedSingleton");
        System.out.println("\n");
    }

    // -----------------------------------------------------------------------
    // 3. Double-Checked Locking
    // -----------------------------------------------------------------------
    public static void testDoubleCheckedLocking() {
        System.out.println("--- 3. Testing Double-Checked Locking Singleton ---");

        // First request: Enters synchronized block internally
        System.out.println("Requesting first DoubleChecked instance (Complex Logic)...");
        DoubleCheckedLockingSingleton dclRef1 = DoubleCheckedLockingSingleton.getInstance();
        dclRef1.showMessage();

        // Second request: Skips synchronized block entirely (Performance optimization)
        System.out.println("Requesting second DoubleChecked instance...");
        DoubleCheckedLockingSingleton dclRef2 = DoubleCheckedLockingSingleton.getInstance();

        // Verify Identity
        checkEquality(dclRef1, dclRef2, "DoubleCheckedLockingSingleton");
        System.out.println("\n");
    }

    // -----------------------------------------------------------------------
    // 4. Eager Initialization
    // -----------------------------------------------------------------------
    public static void testEagerSingleton() {
        System.out.println("--- 4. Testing Eager Initialization Singleton ---");
        System.out.println("(Note: Instance was likely created as soon as the ClassLoader loaded the class)");

        // First request
        System.out.println("Requesting first EagerSingleton instance...");
        EagerSingleton eagerRef1 = EagerSingleton.getInstance();
        eagerRef1.showMessage();

        // Second request
        System.out.println("Requesting second EagerSingleton instance...");
        EagerSingleton eagerRef2 = EagerSingleton.getInstance();

        // Verify Identity
        checkEquality(eagerRef1, eagerRef2, "EagerSingleton");
        System.out.println("\n");
    }

    // -----------------------------------------------------------------------
    // 5. Bill Pugh (Initialization-on-Demand Holder)
    // -----------------------------------------------------------------------
    public static void testBillPughSingleton() {
        System.out.println("--- 5. Testing Bill Pugh Singleton ---");

        // First request: Triggers the loading of the inner 'SingletonHelper' class
        System.out.println("Requesting first BillPughSingleton instance...");
        BillPughSingleton billPughRef1 = BillPughSingleton.getInstance();
        billPughRef1.showMessage();

        // Second request: Inner class is already loaded, returns instant reference
        System.out.println("Requesting second BillPughSingleton instance...");
        BillPughSingleton billPughRef2 = BillPughSingleton.getInstance();

        // Verify Identity
        checkEquality(billPughRef1, billPughRef2, "BillPughSingleton");
        System.out.println("\n");
    }

    // -----------------------------------------------------------------------
    // 6. Enum Singleton
    // -----------------------------------------------------------------------
    public static void testEnumSingleton() {
        System.out.println("--- 6. Testing Enum Singleton ---");

        // Accessing the Enum constant
        System.out.println("Accessing EnumSingleton.INSTANCE for the first time...");
        EnumSingleton enumRef1 = EnumSingleton.INSTANCE;
        enumRef1.showMessage();
        enumRef1.doSomething();

        // Accessing it again
        System.out.println("Accessing EnumSingleton.INSTANCE for the second time...");
        EnumSingleton enumRef2 = EnumSingleton.INSTANCE;

        // Verify Identity
        if (enumRef1 == enumRef2) {
            System.out.println("Success: Both Enum references point to the same constant.");
        } else {
            System.out.println("Error: Multiple instances of Enum created (This should be impossible).");
        }
        System.out.println("\n");
    }

    // -----------------------------------------------------------------------
    // Helper Method
    // -----------------------------------------------------------------------
    private static void checkEquality(Object instance1, Object instance2, String className) {
        if (instance1 == instance2) {
            System.out.println("Success: Both " + className + " variables hold the exact same instance.");
            System.out.println("Object Hash 1: " + System.identityHashCode(instance1));
            System.out.println("Object Hash 2: " + System.identityHashCode(instance2));
        } else {
            System.out.println("Error: Different instances exist for " + className + "!");
        }
        System.out.println("--------------------------------------------------");
    }
}
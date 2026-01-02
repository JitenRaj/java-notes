package DesignPatterns.Creational.FactoryMethod;

// Concrete Implementation 3: Push

public class PushNotification implements Notification{
    @Override
    public void notifyUser() {
        System.out.println("Sending a Push Notification...");
    }
}

package DesignPatterns.Creational.FactoryMethod;

// Concrete Implementation 1: SMS

public class SMSNotification implements Notification {

    @Override
    public void notifyUser() {
        System.out.println("Sending an SMS Notification...");
    }
}

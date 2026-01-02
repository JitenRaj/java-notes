package DesignPatterns.Creational.FactoryMethod;

// Concrete Implementation 2: Email

public class EmailNotification implements Notification{
    @Override
    public void notifyUser() {
        System.out.println("Sending an Email Notification...");
    }
}

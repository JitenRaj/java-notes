package DesignPatterns.Creational.FactoryMethod;

public class Main {
    public static void main(String[] args) {
        // Create the factory
        NotificationFactory factory = new NotificationFactory();

        //  1. Get an SMS Notification
        Notification message1 = factory.createNotification("sms");
        message1.notifyUser();

        // 2. Get an Email Notification
        Notification message2 = factory.createNotification("email");
        message2.notifyUser();

        // 3. Get a Push Notification
        Notification message3 = factory.createNotification("push");
        message3.notifyUser();
    }
}

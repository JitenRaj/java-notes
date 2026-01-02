package DesignPatterns.Creational.FactoryMethod;

// Factory Method
// Takes a parameter (e.g., String) to determine the type of object to return.

public class NotificationFactory {

    public Notification createNotification(String channel) {
        if (channel == null || channel.isEmpty()){
            return null;
        }

        switch (channel.toLowerCase()) {
            case "sms":
                return new SMSNotification();

            case "email":
                return new EmailNotification();

            case "push":
                return new PushNotification();

            default:
                throw new IllegalArgumentException("Unknown Channel: " + channel);
        }
    }
}

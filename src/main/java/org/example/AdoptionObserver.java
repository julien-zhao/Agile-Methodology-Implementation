
package org.example;

import java.util.ArrayList;
import java.util.List;

public class AdoptionObserver implements Observer {
    private List<String> notifications = new ArrayList<>();

    @Override
    public void update(String message) {
        notifications.add(message);
        //System.out.println("Notification: " + message);
    }

    public List<String> getNotifications() {
        return notifications;
    }
}
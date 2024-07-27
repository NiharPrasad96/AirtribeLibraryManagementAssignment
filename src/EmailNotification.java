public class EmailNotification implements Notification {
    @Override
    public void notify(Patron patron, String message) {
        System.out.println("Sending email to " + patron.getName() + ": " + message);
    }
}


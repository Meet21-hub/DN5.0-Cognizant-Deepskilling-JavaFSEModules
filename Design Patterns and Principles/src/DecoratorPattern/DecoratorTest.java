package decorator;

// ---------- Component interface ----------
interface Notifier {
    void send(String message);
}

// ---------- Concrete component ----------
class EmailNotifier implements Notifier {
    private final String email;
    public EmailNotifier(String email) { this.email = email; }

    public void send(String message) {
        System.out.println("Sending EMAIL to " + email + ": " + message);
    }
}

// ---------- Abstract decorator ----------
abstract class NotifierDecorator implements Notifier {
    protected final Notifier wrappee;
    public NotifierDecorator(Notifier wrappee) { this.wrappee = wrappee; }

    public void send(String message) {
        wrappee.send(message); // delegate to the wrapped notifier
    }
}

// ---------- Concrete decorators ----------
class SMSNotifierDecorator extends NotifierDecorator {
    private final String phoneNumber;
    public SMSNotifierDecorator(Notifier wrappee, String phoneNumber) {
        super(wrappee);
        this.phoneNumber = phoneNumber;
    }

    public void send(String message) {
        super.send(message);
        System.out.println("Sending SMS to " + phoneNumber + ": " + message);
    }
}

class SlackNotifierDecorator extends NotifierDecorator {
    private final String channel;
    public SlackNotifierDecorator(Notifier wrappee, String channel) {
        super(wrappee);
        this.channel = channel;
    }

    public void send(String message) {
        super.send(message);
        System.out.println("Posting to Slack channel #" + channel + ": " + message);
    }
}

// ---------- Test ----------
class DecoratorTest {
    public static void main(String[] args) {
        // Email only
        Notifier notifier = new EmailNotifier("user@example.com");

        // Add SMS on top
        notifier = new SMSNotifierDecorator(notifier, "+91-9876543210");

        // Add Slack on top
        notifier = new SlackNotifierDecorator(notifier, "alerts");

        System.out.println("=== Sending critical alert ===");
        notifier.send("Server is down!");
    }
}

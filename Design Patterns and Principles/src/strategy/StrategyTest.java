package strategy;

// ---------- Strategy interface ----------
interface PaymentStrategy {
    void pay(double amount);
}

// ---------- Concrete strategies ----------
class CreditCardPayment implements PaymentStrategy {
    private final String cardNumber;
    public CreditCardPayment(String cardNumber) { this.cardNumber = cardNumber; }

    public void pay(double amount) {
        System.out.printf("Paid $%.2f using Credit Card ending in %s%n",
            amount, cardNumber.substring(cardNumber.length() - 4));
    }
}

class PayPalPayment implements PaymentStrategy {
    private final String email;
    public PayPalPayment(String email) { this.email = email; }

    public void pay(double amount) {
        System.out.printf("Paid $%.2f via PayPal account: %s%n", amount, email);
    }
}

class UPIPayment implements PaymentStrategy {
    private final String upiId;
    public UPIPayment(String upiId) { this.upiId = upiId; }

    public void pay(double amount) {
        System.out.printf("Paid $%.2f via UPI ID: %s%n", amount, upiId);
    }
}

// ---------- Context ----------
class PaymentContext {
    private PaymentStrategy strategy;

    public void setStrategy(PaymentStrategy strategy) {
        this.strategy = strategy;
    }

    public void executePayment(double amount) {
        if (strategy == null) throw new IllegalStateException("No payment strategy set.");
        strategy.pay(amount);
    }
}

// ---------- Test ----------
class StrategyTest {
    public static void main(String[] args) {
        PaymentContext context = new PaymentContext();

        context.setStrategy(new CreditCardPayment("4111111111111234"));
        context.executePayment(99.99);

        context.setStrategy(new PayPalPayment("user@example.com"));
        context.executePayment(49.00);

        context.setStrategy(new UPIPayment("user@upi"));
        context.executePayment(15.50);
    }
}

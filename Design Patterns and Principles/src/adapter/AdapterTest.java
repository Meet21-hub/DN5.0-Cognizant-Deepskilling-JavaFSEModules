package adapter;

// ---------- Target interface ----------
interface PaymentProcessor {
    void processPayment(double amount);
}

// ---------- Adaptees (third-party gateways with their own APIs) ----------
class PayPalGateway {
    public void makePayment(double totalAmount) {
        System.out.printf("PayPal: Processing payment of $%.2f%n", totalAmount);
    }
}

class StripeGateway {
    public void chargeCard(String currency, double amount) {
        System.out.printf("Stripe: Charging %.2f %s%n", amount, currency);
    }
}

class RazorpayGateway {
    public void pay(int amountInPaise) {
        System.out.printf("Razorpay: Processing ₹%.2f%n", amountInPaise / 100.0);
    }
}

// ---------- Adapters ----------
class PayPalAdapter implements PaymentProcessor {
    private final PayPalGateway gateway;
    public PayPalAdapter(PayPalGateway gateway) { this.gateway = gateway; }

    public void processPayment(double amount) {
        gateway.makePayment(amount);
    }
}

class StripeAdapter implements PaymentProcessor {
    private final StripeGateway gateway;
    public StripeAdapter(StripeGateway gateway) { this.gateway = gateway; }

    public void processPayment(double amount) {
        gateway.chargeCard("USD", amount);
    }
}

class RazorpayAdapter implements PaymentProcessor {
    private final RazorpayGateway gateway;
    public RazorpayAdapter(RazorpayGateway gateway) { this.gateway = gateway; }

    public void processPayment(double amount) {
        // Convert dollars to paise (1 USD ≈ 83 INR, 1 INR = 100 paise)
        gateway.pay((int)(amount * 83 * 100));
    }
}

// ---------- Test ----------
class AdapterTest {
    public static void main(String[] args) {
        PaymentProcessor[] processors = {
            new PayPalAdapter(new PayPalGateway()),
            new StripeAdapter(new StripeGateway()),
            new RazorpayAdapter(new RazorpayGateway())
        };

        for (PaymentProcessor p : processors) {
            p.processPayment(49.99);
        }
    }
}

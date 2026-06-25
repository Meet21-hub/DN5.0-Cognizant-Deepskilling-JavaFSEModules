package observer;

import java.util.ArrayList;
import java.util.List;

// ---------- Observer interface ----------
interface Observer {
    void update(String stockSymbol, double price);
}

// ---------- Subject interface ----------
interface Stock {
    void register(Observer o);
    void deregister(Observer o);
    void notifyObservers();
}

// ---------- Concrete subject ----------
class StockMarket implements Stock {
    private final List<Observer> observers = new ArrayList<>();
    private String symbol;
    private double price;

    public void setPrice(String symbol, double price) {
        this.symbol = symbol;
        this.price  = price;
        System.out.printf("%nStockMarket: %s price changed to $%.2f%n", symbol, price);
        notifyObservers();
    }

    public void register(Observer o)   { observers.add(o); }
    public void deregister(Observer o) { observers.remove(o); }
    public void notifyObservers()      { observers.forEach(o -> o.update(symbol, price)); }
}

// ---------- Concrete observers ----------
class MobileApp implements Observer {
    private final String user;
    public MobileApp(String user) { this.user = user; }

    public void update(String symbol, double price) {
        System.out.printf("  [MobileApp – %s] Push notification: %s = $%.2f%n", user, symbol, price);
    }
}

class WebApp implements Observer {
    public void update(String symbol, double price) {
        System.out.printf("  [WebApp] Dashboard updated: %s = $%.2f%n", symbol, price);
    }
}

// ---------- Test ----------
class ObserverTest {
    public static void main(String[] args) {
        StockMarket market = new StockMarket();

        Observer mobile1 = new MobileApp("Alice");
        Observer mobile2 = new MobileApp("Bob");
        Observer web     = new WebApp();

        market.register(mobile1);
        market.register(mobile2);
        market.register(web);

        market.setPrice("AAPL", 189.50);
        market.setPrice("TSLA", 245.30);

        // Bob unsubscribes
        System.out.println("\nBob deregistered.");
        market.deregister(mobile2);

        market.setPrice("GOOG", 175.00);
    }
}

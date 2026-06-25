package proxy;

// ---------- Subject interface ----------
interface Image {
    void display();
}

// ---------- Real subject (expensive to create) ----------
class RealImage implements Image {
    private final String filename;

    public RealImage(String filename) {
        this.filename = filename;
        loadFromRemoteServer();
    }

    private void loadFromRemoteServer() {
        System.out.println("Loading image from remote server: " + filename);
    }

    public void display() {
        System.out.println("Displaying image: " + filename);
    }
}

// ---------- Proxy (lazy init + caching) ----------
class ProxyImage implements Image {
    private final String filename;
    private RealImage realImage; // null until first access

    public ProxyImage(String filename) {
        this.filename = filename;
    }

    public void display() {
        // Lazy initialisation: load only when first displayed
        if (realImage == null) {
            realImage = new RealImage(filename);
        }
        realImage.display();
    }
}

// ---------- Test ----------
class ProxyTest {
    public static void main(String[] args) {
        Image img1 = new ProxyImage("photo1.jpg");
        Image img2 = new ProxyImage("photo2.jpg");

        // First call: loads from server
        System.out.println("--- First display ---");
        img1.display();

        // Second call: served from cache (no network load)
        System.out.println("\n--- Second display (cached) ---");
        img1.display();

        // Different image: loads from server
        System.out.println("\n--- Display img2 ---");
        img2.display();
    }
}

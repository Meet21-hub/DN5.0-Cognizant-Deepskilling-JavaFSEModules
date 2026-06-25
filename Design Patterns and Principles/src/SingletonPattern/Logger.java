package singleton;

public class Logger {
    // Private static instance — only one will ever exist
    private static Logger instance;

    // Private constructor prevents external instantiation
    private Logger() {
        System.out.println("Logger instance created.");
    }

    // Public static method to retrieve the single instance (lazy initialization)
    public static Logger getInstance() {
        if (instance == null) {
            instance = new Logger();
        }
        return instance;
    }

    public void log(String message) {
        System.out.println("[LOG] " + message);
    }
}

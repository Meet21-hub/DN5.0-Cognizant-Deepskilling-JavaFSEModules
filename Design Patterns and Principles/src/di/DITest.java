package di;

import java.util.HashMap;
import java.util.Map;

// ---------- Model ----------
class Customer {
    private final String id;
    private final String name;
    private final String email;

    public Customer(String id, String name, String email) {
        this.id    = id;
        this.name  = name;
        this.email = email;
    }

    public String getId()    { return id; }
    public String getName()  { return name; }
    public String getEmail() { return email; }

    @Override
    public String toString() {
        return String.format("Customer { id='%s', name='%s', email='%s' }", id, name, email);
    }
}

// ---------- Repository interface ----------
interface CustomerRepository {
    Customer findCustomerById(String id);
    void save(Customer customer);
}

// ---------- Concrete repository ----------
class CustomerRepositoryImpl implements CustomerRepository {
    // In-memory data store (substitute for a real DB)
    private final Map<String, Customer> store = new HashMap<>();

    public CustomerRepositoryImpl() {
        // Seed some data
        save(new Customer("C001", "Alice Johnson", "alice@example.com"));
        save(new Customer("C002", "Bob Smith",     "bob@example.com"));
    }

    public void save(Customer customer) {
        store.put(customer.getId(), customer);
    }

    public Customer findCustomerById(String id) {
        return store.getOrDefault(id, null);
    }
}

// ---------- Service class (depends on repository via constructor injection) ----------
class CustomerService {
    private final CustomerRepository repository; // injected dependency

    // Constructor injection — makes the dependency explicit and testable
    public CustomerService(CustomerRepository repository) {
        this.repository = repository;
    }

    public Customer getCustomer(String id) {
        Customer customer = repository.findCustomerById(id);
        if (customer == null) {
            System.out.println("No customer found with id: " + id);
        }
        return customer;
    }

    public void registerCustomer(String id, String name, String email) {
        Customer customer = new Customer(id, name, email);
        repository.save(customer);
        System.out.println("Registered: " + customer);
    }
}

// ---------- Main ----------
class DITest {
    public static void main(String[] args) {
        // Compose dependencies at the entry point (manual DI container)
        CustomerRepository repo    = new CustomerRepositoryImpl();
        CustomerService    service = new CustomerService(repo); // inject

        // Look up existing customers
        System.out.println(service.getCustomer("C001"));
        System.out.println(service.getCustomer("C002"));

        // Look up missing customer
        service.getCustomer("C999");

        // Register a new customer
        service.registerCustomer("C003", "Carol White", "carol@example.com");
        System.out.println(service.getCustomer("C003"));
    }
}

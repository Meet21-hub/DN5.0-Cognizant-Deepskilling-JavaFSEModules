/**
 * Exercise 4: Employee Management System
 *
 * Array Representation in Memory:
 * - Arrays store elements in contiguous memory blocks.
 * - Each element is accessed via: baseAddress + (index × elementSize)
 * - This gives O(1) direct access by index — the key advantage of arrays.
 *
 * Time Complexity of Array Operations:
 * - Add (at end):   O(1) if space available, O(n) if array must be resized/shifted
 * - Search:         O(n) — must scan each element (no sorting assumed)
 * - Traverse:       O(n) — visit every element once
 * - Delete:         O(n) — find element O(n) + shift remaining elements O(n)
 *
 * Limitations of Arrays:
 * - Fixed size declared at creation time.
 * - Insertion/deletion in the middle requires shifting elements.
 * - Resizing requires creating a new array and copying all data.
 */
public class EmployeeManager {

    private Employee[] employees;
    private int size;       // Current number of employees stored
    private int capacity;   // Maximum capacity of the array

    public EmployeeManager(int capacity) {
        this.capacity = capacity;
        this.employees = new Employee[capacity];
        this.size = 0;
    }

    // ----------------------------------------------------------------
    // ADD — O(1) amortized (O(n) when capacity is full and we resize)
    // Inserts a new employee at the next available index.
    // ----------------------------------------------------------------
    public boolean addEmployee(Employee employee) {
        if (size >= capacity) {
            System.out.println("  [!] Array is full. Cannot add: " + employee.getName());
            return false;
        }
        employees[size] = employee;
        size++;
        System.out.println("  Added: " + employee);
        return true;
    }

    // ----------------------------------------------------------------
    // SEARCH — O(n)
    // Performs a linear scan to find an employee by their ID.
    // ----------------------------------------------------------------
    public Employee searchEmployee(int employeeId) {
        for (int i = 0; i < size; i++) {
            if (employees[i].getEmployeeId() == employeeId) {
                return employees[i];
            }
        }
        return null; // Not found
    }

    // ----------------------------------------------------------------
    // TRAVERSE — O(n)
    // Visits and prints every employee in the array.
    // ----------------------------------------------------------------
    public void traverseEmployees() {
        if (size == 0) {
            System.out.println("  No employees found.");
            return;
        }
        System.out.println("  Total employees: " + size);
        for (int i = 0; i < size; i++) {
            System.out.println("  [" + i + "] " + employees[i]);
        }
    }

    // ----------------------------------------------------------------
    // DELETE — O(n)
    // Finds the employee by ID, removes them, then shifts all
    // subsequent elements left by one to fill the gap.
    // ----------------------------------------------------------------
    public boolean deleteEmployee(int employeeId) {
        for (int i = 0; i < size; i++) {
            if (employees[i].getEmployeeId() == employeeId) {
                String name = employees[i].getName();
                // Shift elements left to fill the gap
                for (int j = i; j < size - 1; j++) {
                    employees[j] = employees[j + 1];
                }
                employees[size - 1] = null; // Clear the last duplicate reference
                size--;
                System.out.println("  Deleted employee: " + name + " (id=" + employeeId + ")");
                return true;
            }
        }
        System.out.println("  Employee with id=" + employeeId + " not found.");
        return false;
    }

    public int getSize() {
        return size;
    }

    // ----------------------------------------------------------------
    // MAIN — demonstration of all operations
    // ----------------------------------------------------------------
    public static void main(String[] args) {

        /*
         * Array Memory Representation (conceptual):
         * Index:  [0]         [1]         [2]         [3]         [4]
         * Data:   [Emp#101]   [Emp#102]   [Emp#103]   [Emp#104]   [null]
         * Addr:   1000        1008        1016        1024        1032  (8 bytes each)
         *
         * Advantages of Arrays:
         * - O(1) random access via index
         * - Cache-friendly (contiguous memory)
         * - Simple and low memory overhead
         */

        System.out.println("=== Employee Management System ===\n");

        EmployeeManager manager = new EmployeeManager(10);

        // --- ADD ---
        System.out.println("--- Adding Employees ---");
        manager.addEmployee(new Employee(101, "Alice Johnson",  "Software Engineer", 85000));
        manager.addEmployee(new Employee(102, "Bob Smith",      "Product Manager",   95000));
        manager.addEmployee(new Employee(103, "Charlie Brown",  "QA Engineer",       70000));
        manager.addEmployee(new Employee(104, "Diana Prince",   "Team Lead",        110000));
        manager.addEmployee(new Employee(105, "Eve Wilson",     "Data Analyst",      78000));

        // --- TRAVERSE ---
        System.out.println("\n--- All Employees ---");
        manager.traverseEmployees();

        // --- SEARCH ---
        System.out.println("\n--- Searching for Employee ID 103 ---");
        Employee found = manager.searchEmployee(103);
        if (found != null) {
            System.out.println("  Found: " + found);
        } else {
            System.out.println("  Employee not found.");
        }

        System.out.println("\n--- Searching for Employee ID 999 (non-existent) ---");
        Employee notFound = manager.searchEmployee(999);
        System.out.println("  Result: " + (notFound != null ? notFound : "Not Found"));

        // --- DELETE ---
        System.out.println("\n--- Deleting Employee ID 102 ---");
        manager.deleteEmployee(102);

        System.out.println("\n--- Employees After Deletion ---");
        manager.traverseEmployees();

        // --- ANALYSIS ---
        System.out.println("\n=== Time Complexity Analysis ===");
        System.out.println("Operation  | Time Complexity | Notes");
        System.out.println("-----------|-----------------|----------------------------------");
        System.out.println("Add        | O(1)            | Append at current size index");
        System.out.println("Search     | O(n)            | Linear scan (unsorted array)");
        System.out.println("Traverse   | O(n)            | Visit all elements once");
        System.out.println("Delete     | O(n)            | Find O(n) + shift elements O(n)");
        System.out.println();
        System.out.println("=== Array Limitations ===");
        System.out.println("- Fixed capacity: must declare size upfront.");
        System.out.println("- Deletion/insertion mid-array requires O(n) shifting.");
        System.out.println("- Resizing requires copying all elements to a new array.");
        System.out.println("- Best used when: size is known, fast index access needed,");
        System.out.println("  and insertions/deletions are rare.");
        System.out.println("- For dynamic sizes, prefer ArrayList (auto-resizing) or");
        System.out.println("  LinkedList (O(1) insert/delete with a reference).");
    }
}

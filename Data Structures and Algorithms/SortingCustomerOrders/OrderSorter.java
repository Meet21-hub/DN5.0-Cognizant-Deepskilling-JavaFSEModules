import java.util.Arrays;

public class OrderSorter {

    // ----------------------------------------------------------------
    // BUBBLE SORT — O(n²) time, O(1) space
    // Repeatedly swaps adjacent elements that are in the wrong order.
    // Each pass "bubbles" the largest unsorted element to its position.
    // ----------------------------------------------------------------
    public static void bubbleSort(Order[] orders) {
        int n = orders.length;
        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false; // Optimization: stop early if already sorted
            for (int j = 0; j < n - 1 - i; j++) {
                if (orders[j].getTotalPrice() > orders[j + 1].getTotalPrice()) {
                    // Swap orders[j] and orders[j+1]
                    Order temp = orders[j];
                    orders[j] = orders[j + 1];
                    orders[j + 1] = temp;
                    swapped = true;
                }
            }
            if (!swapped) break; // Array is already sorted
        }
    }

    // ----------------------------------------------------------------
    // QUICK SORT — O(n log n) average, O(n²) worst case, O(log n) space
    // Picks a pivot, partitions elements around it, then recursively
    // sorts each partition. Very fast in practice due to cache efficiency.
    // ----------------------------------------------------------------
    public static void quickSort(Order[] orders, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(orders, low, high);
            quickSort(orders, low, pivotIndex - 1);  // Sort left partition
            quickSort(orders, pivotIndex + 1, high); // Sort right partition
        }
    }

    private static int partition(Order[] orders, int low, int high) {
        double pivot = orders[high].getTotalPrice(); // Choose last element as pivot
        int i = low - 1; // Index of smaller element

        for (int j = low; j < high; j++) {
            if (orders[j].getTotalPrice() <= pivot) {
                i++;
                // Swap orders[i] and orders[j]
                Order temp = orders[i];
                orders[i] = orders[j];
                orders[j] = temp;
            }
        }

        // Place pivot in its correct sorted position
        Order temp = orders[i + 1];
        orders[i + 1] = orders[high];
        orders[high] = temp;

        return i + 1; // Return pivot's final index
    }

    // Helper to print an array of orders
    private static void printOrders(Order[] orders) {
        for (Order o : orders) {
            System.out.println("  " + o);
        }
    }

    // Helper to deep-copy an Order array so both sorts get identical input
    private static Order[] copyOrders(Order[] original) {
        return Arrays.copyOf(original, original.length);
    }

    public static void main(String[] args) {
        Order[] original = {
            new Order(1001, "Alice",   250.75),
            new Order(1002, "Bob",     89.99),
            new Order(1003, "Charlie", 450.00),
            new Order(1004, "Diana",   30.50),
            new Order(1005, "Eve",     199.00),
            new Order(1006, "Frank",   780.25),
            new Order(1007, "Grace",   15.00),
        };

        System.out.println("=== Original Orders ===");
        printOrders(original);

        // ---- Bubble Sort ----
        Order[] bubbleOrders = copyOrders(original);
        long startBubble = System.nanoTime();
        bubbleSort(bubbleOrders);
        long endBubble = System.nanoTime();

        System.out.println("\n=== After Bubble Sort (by totalPrice ASC) ===");
        printOrders(bubbleOrders);
        System.out.println("Time taken: " + (endBubble - startBubble) + " ns");
        System.out.println("Time Complexity: Best O(n), Average O(n²), Worst O(n²)");

        // ---- Quick Sort ----
        Order[] quickOrders = copyOrders(original);
        long startQuick = System.nanoTime();
        quickSort(quickOrders, 0, quickOrders.length - 1);
        long endQuick = System.nanoTime();

        System.out.println("\n=== After Quick Sort (by totalPrice ASC) ===");
        printOrders(quickOrders);
        System.out.println("Time taken: " + (endQuick - startQuick) + " ns");
        System.out.println("Time Complexity: Best/Average O(n log n), Worst O(n²)");

        // ---- Analysis ----
        System.out.println("\n=== Performance Comparison ===");
        System.out.println("Algorithm    | Best     | Average  | Worst    | Space");
        System.out.println("-------------|----------|----------|----------|------");
        System.out.println("Bubble Sort  | O(n)     | O(n²)    | O(n²)    | O(1)");
        System.out.println("Quick Sort   | O(n logn)| O(n logn)| O(n²)    | O(logn)");
        System.out.println();
        System.out.println("Why Quick Sort is preferred:");
        System.out.println("- Significantly faster for large datasets (n log n vs n²).");
        System.out.println("- For 10,000 orders: Bubble ≈ 100M ops, Quick ≈ 133K ops.");
        System.out.println("- In-place sorting with minimal extra memory usage.");
        System.out.println("- Good cache performance due to sequential memory access.");
        System.out.println("- Worst case (already sorted) can be avoided with random pivot.");
    }
}

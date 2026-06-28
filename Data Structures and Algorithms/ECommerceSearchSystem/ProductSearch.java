
public class ProductSearch {

    // ----------------------------------------------------------------
    // LINEAR SEARCH — O(n)
    // Scans each element one by one until the target productId is found.
    // ----------------------------------------------------------------
    public static Product linearSearch(Product[] products, int targetId) {
        for (Product product : products) {
            if (product.getProductId() == targetId) {
                return product;
            }
        }
        return null; // Not found
    }

    // ----------------------------------------------------------------
    // BINARY SEARCH — O(log n)
    // Requires the products array to be sorted by productId.
    // Repeatedly halves the search space by comparing with the midpoint.
    // ----------------------------------------------------------------
    public static Product binarySearch(Product[] sortedProducts, int targetId) {
        int low = 0;
        int high = sortedProducts.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2; // Avoids integer overflow vs (low+high)/2

            if (sortedProducts[mid].getProductId() == targetId) {
                return sortedProducts[mid];
            } else if (sortedProducts[mid].getProductId() < targetId) {
                low = mid + 1;  // Target is in the right half
            } else {
                high = mid - 1; // Target is in the left half
            }
        }
        return null; // Not found
    }

    public static void main(String[] args) {
        // Unsorted array for linear search
        Product[] products = {
            new Product(105, "Laptop",      "Electronics"),
            new Product(202, "Shoes",       "Footwear"),
            new Product(310, "Headphones",  "Electronics"),
            new Product(401, "Backpack",    "Accessories"),
            new Product(555, "Smartphone",  "Electronics"),
            new Product(678, "Watch",       "Accessories"),
            new Product(789, "Camera",      "Electronics"),
        };

        // Sorted array (by productId) for binary search
        Product[] sortedProducts = {
            new Product(105, "Laptop",      "Electronics"),
            new Product(202, "Shoes",       "Footwear"),
            new Product(310, "Headphones",  "Electronics"),
            new Product(401, "Backpack",    "Accessories"),
            new Product(555, "Smartphone",  "Electronics"),
            new Product(678, "Watch",       "Accessories"),
            new Product(789, "Camera",      "Electronics"),
        };

        int searchId = 555;

        // --- Linear Search ---
        System.out.println("=== Linear Search ===");
        long startLinear = System.nanoTime();
        Product linearResult = linearSearch(products, searchId);
        long endLinear = System.nanoTime();
        System.out.println("Searching for productId: " + searchId);
        System.out.println("Result: " + (linearResult != null ? linearResult : "Not Found"));
        System.out.println("Time taken: " + (endLinear - startLinear) + " ns");
        System.out.println("Time Complexity: O(n)\n");

        // --- Binary Search ---
        System.out.println("=== Binary Search ===");
        long startBinary = System.nanoTime();
        Product binaryResult = binarySearch(sortedProducts, searchId);
        long endBinary = System.nanoTime();
        System.out.println("Searching for productId: " + searchId);
        System.out.println("Result: " + (binaryResult != null ? binaryResult : "Not Found"));
        System.out.println("Time taken: " + (endBinary - startBinary) + " ns");
        System.out.println("Time Complexity: O(log n)\n");

        // --- Analysis ---
        System.out.println("=== Analysis ===");
        System.out.println("Linear Search  - Best: O(1), Average: O(n), Worst: O(n)");
        System.out.println("Binary Search  - Best: O(1), Average: O(log n), Worst: O(log n)");
        System.out.println();
        System.out.println("Conclusion: Binary Search is far more suitable for large e-commerce");
        System.out.println("catalogs because it eliminates half the search space at each step.");
        System.out.println("For 1,000,000 products: Linear = up to 1,000,000 comparisons,");
        System.out.println("Binary = at most 20 comparisons (log2(1,000,000) ≈ 20).");
        System.out.println("Trade-off: the product array must be kept sorted.");
    }
}

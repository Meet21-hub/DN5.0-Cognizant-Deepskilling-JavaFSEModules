import java.util.HashMap;

public class InventoryManager {

    private HashMap<Integer, Product> inventory =
            new HashMap<>();

    // Add product
    public void addProduct(Product product) {

        inventory.put(product.getProductId(), product);

        System.out.println("Product added successfully.");
    }

    // Update product
    public void updateProduct(int id,
                              int quantity,
                              double price) {

        Product product = inventory.get(id);

        if (product != null) {

            product.setQuantity(quantity);
            product.setPrice(price);

            System.out.println("Product updated.");
        }
        else {

            System.out.println("Product not found.");
        }
    }

    // Delete product
    public void deleteProduct(int id) {

        if (inventory.remove(id) != null) {

            System.out.println("Product deleted.");
        }
        else {

            System.out.println("Product not found.");
        }
    }

    // Display inventory
    public void displayInventory() {

        System.out.println("\nInventory:");

        for (Product product : inventory.values()) {

            product.display();
        }
    }
}
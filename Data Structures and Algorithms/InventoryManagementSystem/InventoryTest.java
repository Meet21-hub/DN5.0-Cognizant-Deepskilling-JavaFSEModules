public class InventoryTest {

    public static void main(String[] args) {

        InventoryManager manager =
                new InventoryManager();

        Product p1 =
                new Product(101, "Laptop", 20, 55000);

        Product p2 =
                new Product(102, "Mouse", 100, 500);

        Product p3 =
                new Product(103, "Keyboard", 50, 1500);

        manager.addProduct(p1);
        manager.addProduct(p2);
        manager.addProduct(p3);

        manager.displayInventory();

        manager.updateProduct(102, 120, 600);

        manager.deleteProduct(103);

        manager.displayInventory();
    }
}
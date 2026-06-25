package builder;

public class BuilderTest {
    public static void main(String[] args) {
        // Basic configuration
        Computer basic = new Computer.Builder("Intel Core i3", "8GB")
                .build();

        // High-end gaming rig
        Computer gaming = new Computer.Builder("Intel Core i9", "32GB")
                .storage("2TB NVMe SSD")
                .gpu("NVIDIA RTX 4090")
                .bluetooth(true)
                .build();

        // Developer workstation
        Computer dev = new Computer.Builder("AMD Ryzen 9", "64GB")
                .storage("1TB SSD")
                .gpu("AMD Radeon RX 7900")
                .bluetooth(true)
                .build();

        System.out.println(basic);
        System.out.println(gaming);
        System.out.println(dev);
    }
}

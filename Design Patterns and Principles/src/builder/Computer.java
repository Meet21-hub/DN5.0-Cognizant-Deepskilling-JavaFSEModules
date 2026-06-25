package builder;

public class Computer {
    // All fields are final — set once via Builder
    private final String cpu;
    private final String ram;
    private final String storage;
    private final String gpu;
    private final boolean bluetoothEnabled;

    // Private constructor: only the nested Builder can call this
    private Computer(Builder builder) {
        this.cpu              = builder.cpu;
        this.ram              = builder.ram;
        this.storage          = builder.storage;
        this.gpu              = builder.gpu;
        this.bluetoothEnabled = builder.bluetoothEnabled;
    }

    @Override
    public String toString() {
        return String.format(
            "Computer { CPU='%s', RAM='%s', Storage='%s', GPU='%s', Bluetooth=%b }",
            cpu, ram, storage, gpu, bluetoothEnabled
        );
    }

    // ---- Static nested Builder ----
    public static class Builder {
        // Required
        private final String cpu;
        private final String ram;
        // Optional (defaults provided)
        private String  storage          = "256GB SSD";
        private String  gpu              = "Integrated";
        private boolean bluetoothEnabled = false;

        public Builder(String cpu, String ram) {
            this.cpu = cpu;
            this.ram = ram;
        }

        public Builder storage(String storage) {
            this.storage = storage;
            return this;
        }

        public Builder gpu(String gpu) {
            this.gpu = gpu;
            return this;
        }

        public Builder bluetooth(boolean enabled) {
            this.bluetoothEnabled = enabled;
            return this;
        }

        public Computer build() {
            return new Computer(this);
        }
    }
}

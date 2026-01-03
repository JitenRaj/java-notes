package DesignPatterns.Creational.Builder;

public class Computer {
    // required parameters
    private String HDD;
    private String RAM;

    // optional parameters
    private boolean isGraphicsCardEnabled;
    private boolean isBluetoothEnabled;

    // 1. Private Constructor
    // Take the Builder object as a parameter to initialise fields
    private Computer(ComputerBuilder builder) {
        this.HDD = builder.HDD;
        this.RAM = builder.RAM;

        this.isGraphicsCardEnabled = builder.isGraphicsCardEnabled;
        this.isBluetoothEnabled = builder.isBluetoothEnabled;
    }

    // Getters
    public String getHDD() {
        return HDD;
    }

    public String getRAM() {
        return RAM;
    }

    public boolean isGraphicsCardEnabled() {
        return isGraphicsCardEnabled;
    }

    public boolean isBluetoothEnabled() {
        return isBluetoothEnabled;
    }

    // Overriding toString method


    @Override
    public String toString() {
        return "Computer{" +
                "HDD = '" + HDD + '\'' +
                ", RAM = '" + RAM + '\'' +
                ", GraphicsCard = " + isGraphicsCardEnabled +
                ", Bluetooth = " + isBluetoothEnabled +
                '}';
    }

    // 2. Static Inner Builder Class
    public static class ComputerBuilder {

        // Same fields as the outer class
        private String HDD;
        private String RAM;
        private boolean isGraphicsCardEnabled;
        private boolean isBluetoothEnabled;

        // 3. Constructor for Required Parameters
        public ComputerBuilder(String hdd, String ram) {
            this.HDD = hdd;
            this.RAM = ram;
        }

        // 4. Setter methods for Optional Parameters
        // These return the Builder object itself to allow method chaining (Fluent Interface)
        public ComputerBuilder setGraphicsCardEnabled(boolean isGraphicsCardEnabled) {
            this.isGraphicsCardEnabled = isGraphicsCardEnabled;
            return this;
        }

        public ComputerBuilder setBluetoothEnabled(boolean isBluetoothEnabled) {
            this.isBluetoothEnabled = isBluetoothEnabled;
            return this;
        }

        // 5. Build Method
        // Returns the final constructed object
        public Computer build() {
            return new Computer(this);
        }
    }
}

// Laptop.java
public class Laptop extends Product {
    private String nameL;
    private double priceL;

    public Laptop(String type, String nameL, double priceL) {
        super(type);
        this.nameL = nameL;
        this.priceL = priceL;
    }

    public String toString() {
        return "The product is " + nameL + ".\n" + super.toString();
    }
}
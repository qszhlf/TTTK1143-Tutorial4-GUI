// SmartPhone.java
public class SmartPhone extends Product {
    private String nameSP;
    private double priceSP;

    public SmartPhone(String type, String nameSP, double priceSP) {
        super(type);
        this.nameSP = nameSP;
        this.priceSP = priceSP;
    }

    public String toString() {
        return "The product is " + nameSP + ".\n" + super.toString();
    }
}
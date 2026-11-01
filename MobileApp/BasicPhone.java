// BasicPhone.java
public class BasicPhone extends MobileDevice {

    public BasicPhone(String manufacturer, String model) {
        super(manufacturer, model);
    }

    public void printDetails() {
        System.out.println("Basic Phone Detail:");
        super.printDetails();
    }
}
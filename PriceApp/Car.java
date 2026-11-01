// Car.java
public class Car implements Price {
    private String brand;
    private double priceCar;
    private Customer buy;

    public Car(String brand, double priceCar) {
        this.brand = brand;
        this.priceCar = priceCar;
    }

    public String getBrand() {
        return brand;
    }

    public void getPrice(Customer c) {
        buy = c;
        System.out.println(toString());
    }

    public String toString() {
        String result = "The car brand is " + brand + " with price RM" + priceCar + ".\n";
        if (buy.getSalary() > 8000) {
            result += "The customer's name is " + buy.getName() + " with salary RM" + buy.getSalary()
                    + " can buy this car. Your salary is higher than the price's car.";
        } else {
            result += "The customer's name is " + buy.getName() + " with salary RM" + buy.getSalary()
                    + " cannot buy this car. The car price is more than your salary.";
        }
        return result;
    }
}
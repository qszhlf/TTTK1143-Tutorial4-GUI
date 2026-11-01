// Product.java
public class Product implements Price {
    private String type;
    private Customer buy;

    public Product(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void getPrice(Customer c) {
        buy = c;
        System.out.println(toString());
    }

    public String toString() {
        if (buy.getSalary() > 4000) {
            return "The customer's name is " + buy.getName() + " with salary RM" + buy.getSalary()
                 + " can buy this " + type.toLowerCase() + ". Your salary is higher than the price's product.";
        } else {
            return "The customer's name is " + buy.getName() + " with salary RM" + buy.getSalary()
                 + " cannot buy this " + type.toLowerCase() + ". The product price is more than your salary.";
        }
    }
}
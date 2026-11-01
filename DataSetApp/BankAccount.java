public class BankAccount implements Measurable {
    private int accNumber;
    private double balance;
    private static int LastAssignedNumber = 1000;

    public BankAccount() {
        LastAssignedNumber++;
        accNumber = LastAssignedNumber;
        balance = 0;
    }

    public BankAccount(double balance) {
        LastAssignedNumber++;
        accNumber = LastAssignedNumber;
        this.balance = balance;
    }

    public int getAccountNumber() {
        return accNumber;
    }

    public double getMeasure() {
        return balance;
    }

    public String getName() {
        return "Account " + accNumber;
    }
}
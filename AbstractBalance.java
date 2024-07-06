package пробник;

//make abstract class
public abstract class AbstractBalance {
    //fiels
    private double balance;
    //constructor
    public AbstractBalance() {
        this.balance = 0;
    }
    //constructor
    public AbstractBalance(double money) {
        this.balance = money;
    }
    //set balance
    public void setMoney(double money) {
        this.balance = money;
    }
    //make abstract method
    public abstract void ReplenishmentMoney();
    //make abstract method
    public abstract double WithdrawMoney(double currentBalance, int currentLimitAtm);
    //make abstract method
    public abstract void showBalance();
}

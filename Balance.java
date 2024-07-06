package пробник;

import java.util.Scanner;
public class Balance extends AbstractBalance{
    Scanner scan = new Scanner(System.in);
    private double balance;

    public Balance() {
        this.balance = 0;
    }

    public Balance(double money) {
        this.balance = money;
    }

    public void setMoney(double money) {
        this.balance = money;
    }

    public double getMoney() {
        return balance;
    }

    //пополнение счёта
    public void ReplenishmentMoney() {
        double sum;
        int quantity = 0;
        System.out.println("Сколько купюр хотите внести? (Напишите число) ");
        try{
            quantity = scan.nextInt();
        } catch (Exception e) {
            System.out.println("Вы ввели некорректное значение");
        }


        for (int i = 0; i < quantity; i++) {
            System.out.println("Внесите купюру (написать число)");

            do {
                System.out.println(i + 1 + ")");

                sum = scan.nextDouble();
                if (this.balance <= 1000000) {
                    this.balance += sum;
                } else System.out.println("Сумма не должна превышать 1 000 000!");
            } while (sum % 5 != 0);
        }
    }

    //снять деньги со счёта
    public double WithdrawMoney(double currentBalance, int currentLimitAtm) {
        int limitAtm = 5000000;

        System.out.println("Выберите сумму, которую хотите снять: " +
                "1) 5 руб. \n" +
                "2) 10 руб. \n" +
                "3) 25 руб. \n" +
                "4) 50 руб. \n" +
                "5) 100 руб. \n" +
                "6) 200 руб. \n" +
                "7) Ввести свою сумму");
        int choice = 0;
        try{
            choice = scan.nextInt();
        } catch (Exception e) {
            System.out.println("Вы ввели некорректное значение");
        }

        if (currentLimitAtm != 0 && currentBalance <= currentLimitAtm) {

            if (this.balance >= 5) {

                switch (choice) {
                    case (1):
                        currentBalance = this.balance - 5;
                        currentLimitAtm = limitAtm - 5;
                        System.out.println("Вы сняли 5 руб.");

                        break;
                    case 2:
                        if (this.balance >= 10) {
                            currentBalance = this.balance - 10;
                            currentLimitAtm = limitAtm - 10;
                            System.out.println("Вы сняли 10 руб.");
                        }
                        break;
                    case 3:
                        if (this.balance >= 25) {
                            currentBalance = this.balance - 25;
                            currentLimitAtm = limitAtm - 25;
                            System.out.println("Вы сняли 25 руб.");
                        }
                        break;
                    case 4:
                        if (this.balance >= 50) {
                            currentBalance = this.balance - 50;
                            currentLimitAtm = limitAtm - 50;
                            System.out.println("Вы сняли 50 руб.");
                        }
                        break;
                    case 5:
                        if (this.balance >= 100) {
                            currentBalance = this.balance - 100;
                            currentLimitAtm = limitAtm - 100;
                            System.out.println("Вы сняли 100 руб.");
                        }
                        break;
                    case 6:
                        if (this.balance >= 200) {
                            currentBalance = this.balance - 200;
                            currentLimitAtm = limitAtm - 200;
                            System.out.println("Вы сняли 200 руб.");
                        }
                        break;
                    case 7:
                        int inputMoney = 0;

                        do {
                            System.out.println("Введите сумму, которую хотите снять (от 5 руб.) : ");
                            try{
                                inputMoney = scan.nextInt();
                            } catch (Exception e) {
                                System.out.println("Вы ввели некорректное значение");
                                continue;
                            }
                        } while (inputMoney % 5 != 0);

                        if (this.balance >= inputMoney) {
                            currentBalance = this.balance - inputMoney;
                            System.out.println("Вы сняли" + inputMoney + " руб.");
                        }
                        break;
                    default:
                        System.out.println("Нет такого варианта!");
                }
            } else System.out.println("Вы не можете снять деньги, ваш баланс меньше 5 руб. :(");
        } else System.out.println("Лимит средств в банкомате слишком мал. ");

        this.balance = currentBalance;
        return this.balance;
    }

    public void showBalance() {
        System.out.println("Ваш баланс составляет " + this.balance + "руб.");
    }
}

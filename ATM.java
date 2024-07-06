package пробник;
import java.io.*;
import java.util.Scanner;

public class ATM {
    private Card card;
    private Balance balance;
    private CardReader cardReader;
    private Scanner scanner = new Scanner(System.in);

    public ATM() throws FileNotFoundException {
        this.card = new Card();
        this.balance = new Balance();
        this.cardReader = new CardReader();
    }

    public void start() throws IOException {
        while (true) {
            try {
                cardReader.readDataFromFile();
            } catch (IOException e) {
                e.printStackTrace();
                continue;

            }
            if (card.enterCardNumber() == false) {
                continue;
            }
            if (card.accessToMoney()) {
                balance.setMoney(Double.parseDouble(card.getBalance()));
                int choice;
                do {
                    System.out.println("Выберите действие: \n" +
                            "1. Пополнить баланс; \n" +
                            "2. Снять деньги; \n" +
                            "3. Проверить баланс. \n" +
                            "4. Выход.");

                    while (!scanner.hasNextInt()) {
                        System.out.println("Пожалуйста, введите число.");
                        scanner.next();
                    }

                    choice = scanner.nextInt();

                    switch (choice) {
                        case 1:
                            balance.ReplenishmentMoney();
                            break;
                        case 2:
                            balance.WithdrawMoney(balance.getMoney(), 1000000);
                            break;
                        case 3:
                            balance.showBalance();
                            break;
                        case 4:
                            System.out.println("До свидания!");
                            card.setBalance(new String(String.valueOf(balance.getMoney())));
                            cardReader.saveDataToFile(card);

                            break;
                        default:
                            System.out.println("Неверный ввод. Попробуйте еще раз.");
                            break;
                    }
                } while (choice != 4);


            } else {
                System.out.println("Доступ к средствам закрыт.");
                cardReader.saveDataToFile(card);
                continue;
            }
        }
    }
}
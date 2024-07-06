package пробник;
import java.io.*;
import java.util.Date;
import java.util.Map;
import java.util.Scanner;

public class Card {
    private Scanner scan = new Scanner(System.in);
    private String cardNumber; // валидный номер карты
    private String pinCode; // пин-код

    private String balance; // текущий счет
    private String lastFailedAttempt; // последняя неудачная попытка
    private String failedAttempts; // количество неудачных попыток

    // пустой конструктор
    public Card() throws FileNotFoundException {
        this.cardNumber = null;
        this.pinCode = null;
        this.balance = null;
        this.lastFailedAttempt = null;
        this.failedAttempts = null;


    }

    // конструктор с параметрами
    public Card(String card, String pinCode, String balance, String lastFaildedAttempt, String failedAttempts) throws FileNotFoundException {
        this.cardNumber = card;
        this.pinCode = pinCode;
        this.balance = balance;
        this.lastFailedAttempt = lastFaildedAttempt;
        this.failedAttempts = failedAttempts;

    }

    // геттеры и сеттеры для полей
    public String getCard() {
        return cardNumber;
    }

    public void setCard(String card) {
        this.cardNumber = card;
    }

    public String getPinCode() {
        return pinCode;
    }

    public void setPinCode(String pinCode) {
        this.pinCode = pinCode;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getLastFailedAttempt() {
        return lastFailedAttempt;
    }

    public void setLastFailedAttempt(String lastFailedAttempt) {
        this.lastFailedAttempt = lastFailedAttempt;
    }

    public String getFailedAttempts() {
        return failedAttempts;
    }

    public void setFailedAttempts(String failedAttempts) {
        this.failedAttempts = failedAttempts;
    }

    public boolean checkPinCode(String pinCode) {
        return this.pinCode.equals(pinCode);
    }

    public boolean isBlocked() {
        return Integer.parseInt(failedAttempts) >= 3;
    }

    @Override
    public String toString() {
        return "Card{" +
                "cardNumber='" + cardNumber + '\'' +
                ", pinCode='" + pinCode + '\'' +
                ", balance='" + balance + '\'' +
                ", lastFailedAttempt='" + lastFailedAttempt + '\'' +
                ", failedAttempts='" + failedAttempts + '\'' +
                '}';
    }

   // Метод для чтения данных из файла и присвоения значения полям
//    private void readDataFromFile() throws IOException {
//        try (BufferedReader reader = new BufferedReader(new FileReader("Data.txt"))) {
//            String line = reader.readLine();
//
//            if (line != null && !line.trim().isEmpty()) {
//
//                String[] array = line.split(" ");
//
//                if (array.length >= 2) {
//
//                    this.cardNumber = array[0];
//                    this.pinCode = array[1];
//                } else {
//                    System.out.println("Файл не содержит нужных данных");
//                }
//            } else {
//                System.out.println("Файл пуст или не содержит нужных данных");
//            }
//        } catch (IOException ex) {
//            System.out.println(ex.getMessage());
//        }
//    }

    // ввод номера карты
    public boolean enterCardNumber() throws IOException {

        boolean isCardNumberFromData = false;
        boolean isValid;

        do {
            isValid = true;
            System.out.println("Введите номер карты в формате ХХХХ-ХХХХ-ХХХХ-ХХХХ:");
            String cardNumber;
            try{
                cardNumber = scan.next();
            } catch (Exception e){
                System.out.println("Неверный формат номера карты, введите ещё раз.");
                isValid = false;
                continue;
            }
            String[] arrayNumbers = cardNumber.split("-");

            if (arrayNumbers.length != 4) {
                System.out.println("Неверный формат номера карты, введите ещё раз.");
                isValid = false;
            } else {
                for (String arrayNumber : arrayNumbers) {
                    if (arrayNumber.length() != 4 || !arrayNumber.matches("\\d{4}")) {
                        System.out.println("Неверный формат номера карты, введите ещё раз.");
                        isValid = false;
                        break;
                    }
                }
            }

            //get all cards from file
            CardReader cardReader = new CardReader();
            Map<String, Card> cards = cardReader.readDataFromFile();

            if (cards.containsKey(cardNumber)) {
            this.cardNumber = cardNumber;
            this.pinCode = cards.get(cardNumber).getPinCode();
            this.balance = cards.get(cardNumber).getBalance();
            this.lastFailedAttempt = cards.get(cardNumber).getLastFailedAttempt();
            this.failedAttempts = cards.get(cardNumber).getFailedAttempts();

                isCardNumberFromData = true;

        } else {
            System.out.println("Такой карты не существует.");
            isCardNumberFromData = false;
        }
        } while (!isValid);
        return isCardNumberFromData;
    }

    public boolean accessToMoney() {
        boolean isTrue;
        isTrue = false;
        int attempts = 3-Integer.parseInt(failedAttempts);
        if(System.currentTimeMillis()-Long.parseLong(lastFailedAttempt)>86400000){
            attempts = 3;
        }
        while (attempts > 0 && !isTrue) {
            System.out.println("Введите пин-код: ");
            String inputPinCode = scan.next();

            if (Integer.parseInt(inputPinCode) >= 1000 && Integer.parseInt(inputPinCode) <= 9999) {

                if (inputPinCode.equals(this.pinCode)) {
                    System.out.println("Вы получили доступ к счёту");
                    isTrue = true;
                } else {
                    System.out.println("Неверный пин-код");
                    attempts--;
                    //write cur date to lastFailedAttempt
                    this.lastFailedAttempt = String.valueOf(System.currentTimeMillis());
                    this.failedAttempts = String.valueOf(3-attempts);
                    if (attempts > 0) {
                        System.out.println("У вас есть " + attempts + " попытки ввести верный пин-код.");
                    } else {
                        System.out.println("Нет больше попыток. Доступ заблокирован.");
                    }
                }
            } else {
                System.out.println("Пин-код должен быть четырехзначным числом");
            }
        }

        return isTrue;
    }
}

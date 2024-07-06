package пробник;
import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class CardReader {

    //create HashMap with number as key and object Card as value
    private Map<String, Card> cards = new HashMap<>();

    // Метод для чтения данных из файла и присвоения значения полям
    public Map<String, Card> readDataFromFile() throws IOException {

        try (BufferedReader reader = new BufferedReader(new FileReader("пробник/Data.txt"))) {
            String line;

            while ((line = reader.readLine()) != null) {
                line = line.trim();

                if (!line.isEmpty()) {
                    String[] array = line.split(" ");

                    if (array.length >= 5) {
                        String cardNumber = array[0];
                        String pinCode = array[1];
                        String balance = array[2];
                        String lastFaildeAttempt = array[3];
                        String faildeAttempts = array[4];
                        cards.put(cardNumber, new Card(cardNumber, pinCode, balance, lastFaildeAttempt, faildeAttempts));
                    } else {
                        System.out.println("Строка не содержит нужных данных: " + line);
                    }
                } else {
                    System.out.println("Пустая строка в файле");
                }
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return cards;
    }
    public void saveDataToFile(Card card) {
        cards.put(card.getCard(), card);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("пробник/Data.txt"))) {
            Iterator<Map.Entry<String, Card>> iterator = cards.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, Card> entry = iterator.next();
                writer.write(entry.getKey() + " " + entry.getValue().getPinCode() + " " + entry.getValue().getBalance() + " " + entry.getValue().getLastFailedAttempt() + " " + entry.getValue().getFailedAttempts());
                writer.newLine();
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }


    }

    public Map<String, Card> getCards() {
        return cards;
    }
}

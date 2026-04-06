package Week3;
import java.util.*;

class Client {
    String name;
    int riskScore;
    int accountBalance;

    Client(String name, int riskScore, int accountBalance) {
        this.name = name;
        this.riskScore = riskScore;
        this.accountBalance = accountBalance;
    }

    public String toString() {
        return name + ":" + riskScore;
    }
}

class Main {

    // Bubble Sort (ascending by riskScore)
    static void bubbleSort(List<Client> list) {
        int n = list.size();
        boolean swapped;

        for (int i = 0; i < n - 1; i++) {
            swapped = false;

            for (int j = 0; j < n - i - 1; j++) {
                if (list.get(j).riskScore > list.get(j + 1).riskScore) {
                    Client temp = list.get(j);
                    list.set(j, list.get(j + 1));
                    list.set(j + 1, temp);
                    swapped = true;
                }
            }

            if (!swapped) break;
        }
    }

    // Insertion Sort (DESC by riskScore, then ASC by accountBalance)
    static void insertionSort(List<Client> list) {
        for (int i = 1; i < list.size(); i++) {
            Client key = list.get(i);
            int j = i - 1;

            while (j >= 0 && compare(list.get(j), key) > 0) {
                list.set(j + 1, list.get(j));
                j--;
            }

            list.set(j + 1, key);
        }
    }

    // Compare: riskScore DESC, accountBalance ASC
    static int compare(Client c1, Client c2) {
        if (c1.riskScore != c2.riskScore) {
            return Integer.compare(c2.riskScore, c1.riskScore); // DESC
        }
        return Integer.compare(c1.accountBalance, c2.accountBalance); // ASC
    }

    // Get top 3 highest risk clients
    static List<Client> topClients(List<Client> list, int k) {
        List<Client> result = new ArrayList<>();
        for (int i = 0; i < Math.min(k, list.size()); i++) {
            result.add(list.get(i));
        }
        return result;
    }

    public static void main(String[] args) {

        List<Client> clients = new ArrayList<>();
        clients.add(new Client("clientC", 80, 5000));
        clients.add(new Client("clientA", 20, 3000));
        clients.add(new Client("clientB", 50, 4000));

        // Bubble Sort (ascending)
        bubbleSort(clients);
        System.out.println("Bubble (asc): " + clients);

        // Insertion Sort (descending + balance)
        insertionSort(clients);
        System.out.println("Insertion (desc): " + clients);

        // Top 3 risks
        List<Client> top = topClients(clients, 3);
        System.out.print("Top 3 risks: ");
        for (Client c : top) {
            System.out.print(c.name + "(" + c.riskScore + ") ");
        }
    }
}
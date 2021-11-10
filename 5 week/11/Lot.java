
import java.util.Scanner;

public class Lot extends Thread {
    private static final Object lock = new Object();
    private volatile int currentPrice;
    private volatile String buyerName;
    private volatile int currentTime, endTime;

    @Override
    public synchronized void run() {
        while (currentTime < endTime) {
            synchronized (lock) {
                System.out.println("Введите новую цену и имя покупателя:");
                Scanner in = new Scanner(System.in);
                int newPrice;
                String newBuyer;
                newPrice = in.nextInt();
                newBuyer = in.nextLine();
                bet(newPrice, newBuyer, currentTime);
                currentTime++;
                in.close();
            }
        }
        Thread.currentThread().interrupt();
    }

    public Lot(int startPrice, int endTime) {
        currentPrice = startPrice;
        this.endTime = endTime;
        currentTime = 0;
    }

    public void bet(int price, String buyer, int currentTime) {
        if (currentTime < endTime && price > currentPrice) {
            currentPrice = price;
            buyerName = buyer;
        }
    }

    public String getWinner() {
        return buyerName;
    }
}

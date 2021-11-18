import java.util.Scanner;

public class Lot {
    private static final Object lock = new Object();
    private volatile int currentPrice;
    private volatile String buyerName;
    private volatile long endTime;


    public Lot(int startPrice, long endTime) {
        currentPrice = startPrice;
        this.endTime = endTime;
        currentTime = 0;
    }

    public void bet(int price, String buyer, long currentTime) {
        if (currentTime < endTime && price > currentPrice) {
            synchronized (lock) {
                if (currentTime < endTime && price > currentPrice) {
                    currentPrice = price;
                    buyerName = buyer;
                }
            }
        }
    }

    public String getWinner() {
        synchronized (lock) {
            return buyerName;
        }
    }
}

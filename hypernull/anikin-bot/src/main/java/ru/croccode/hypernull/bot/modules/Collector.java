package ru.croccode.hypernull.bot.modules;


import ru.croccode.hypernull.geometry.Offset;
import ru.croccode.hypernull.geometry.Point;
import ru.croccode.hypernull.geometry.Size;
import ru.croccode.hypernull.message.Update;

import java.util.ArrayList;
import java.util.Random;

public class Collector {
    private Update update;
    private static Point tempPoint, coinPoint;
    private int myId;
    private static Size size;

    public Collector(Update update, int myId, Size size) {
        this.update = update;
        this.myId = myId;
        this.size = size;
        tempPoint = new Point(update.getBots().get(myId).x(), update.getBots().get(myId).y());
    }

    // Служебный метод для поиска индекса ближайшей монетки
    private static Point getNearestCoin(ArrayList<Point> coinSpawns) {
        int tempIndex = 0, minIndex = 0;
        int minLength = tempPoint.offsetTo(coinSpawns.get(0), size).length2();
        for (Point coin : coinSpawns) {
            if (tempPoint.offsetTo(coin, size).length2() <
                    tempPoint.offsetTo(coinSpawns.get(minIndex), size).length2()) {
                minIndex = tempIndex;
            }
            tempIndex++;
        }
        return coinSpawns.get(minIndex);
    }

    public Offset getOffset(int[][] moveHistory, ArrayList<Point> coinSpawns) {
        coinPoint = getNearestCoin(coinSpawns);
        // стремимся к ближайшей монетке
        // в зависимости от местоположения монетки выбираем направление движения
        // переходим туда, если клетка свободна
        if(coinPoint.x() > tempPoint.x() && coinPoint.y() > tempPoint.y()
                && moveHistory[tempPoint.x()+1][tempPoint.y()+1] != 3){
            return new Offset(1,1);
        }
        if(coinPoint.x() > tempPoint.x() && coinPoint.y() == tempPoint.y()
                && moveHistory[tempPoint.x()+1][tempPoint.y()] != 3){
            return new Offset(1,0);
        }
        if(coinPoint.x() > tempPoint.x() && coinPoint.y() < tempPoint.y()
                && moveHistory[tempPoint.x()+1][tempPoint.y()-1] != 3){
            return new Offset(1,-1);
        }
        if(coinPoint.x() == tempPoint.x() && coinPoint.y() < tempPoint.y()
                && moveHistory[tempPoint.x()-1][tempPoint.y()-1] != 3){
            return new Offset(0,-1);
        }
        if(coinPoint.x() < tempPoint.x() && coinPoint.y() < tempPoint.y()
                && moveHistory[tempPoint.x()-1][tempPoint.y()-1] != 3){
            return new Offset(-1,-1);
        }
        if(coinPoint.x() < tempPoint.x() && coinPoint.y() == tempPoint.y()
                && moveHistory[tempPoint.x()-1][tempPoint.y()] != 3){
            return new Offset(-1,0);
        }
        if(coinPoint.x() < tempPoint.x() && coinPoint.y() > tempPoint.y()
                && moveHistory[tempPoint.x()-1][tempPoint.y()+1] != 3){
            return new Offset(-1,1);
        }
        if(coinPoint.x() == tempPoint.x() && coinPoint.y() > tempPoint.y()
                && moveHistory[tempPoint.x()][tempPoint.y()+1] != 3){
            return new Offset(0,1);
        }
        // если боту не удалось приблизиться к монетке, то он расстраивается и уходит в случайном направлении
        Random rnd = new Random(System.currentTimeMillis());
        return new Offset(rnd.nextInt(3) - 1, rnd.nextInt(3) - 1);
    }

}

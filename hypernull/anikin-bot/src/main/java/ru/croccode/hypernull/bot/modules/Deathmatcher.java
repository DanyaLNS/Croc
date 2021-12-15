package ru.croccode.hypernull.bot.modules;


import ru.croccode.hypernull.geometry.Offset;
import ru.croccode.hypernull.geometry.Point;
import ru.croccode.hypernull.message.Update;

import java.util.Map;
import java.util.Random;

public class Deathmatcher {

    private Update update;
    private Point tempPoint;
    private int myId;

    public Deathmatcher(Update update, int myId) {
        this.update = update;
        this.myId = myId;
        tempPoint = new Point(update.getBots().get(myId).x(), update.getBots().get(myId).y());
    }

    public Offset getOffset(int[][] moveHistory) {
        for (Map.Entry<Integer, Point> bot : update.getBots().entrySet()) {
            if (!bot.getKey().equals(myId)) {
                // Убегаем от сильных и догоняем слабых
                if (update.getBotCoins().get(bot.getKey()) > update.getBotCoins().get(myId)) {
                    // Убегаем
                    if (bot.getValue().x() >= tempPoint.x() && bot.getValue().y() >= tempPoint.y()) {
                        // Проверяем, не хотим ли перейти на препятствие и корректируем ход
                        if (moveHistory[tempPoint.x() - 1][tempPoint.y() - 1] != 3) {
                            return new Offset(-1, -1);
                        }
                        if (moveHistory[tempPoint.x()][tempPoint.y() - 1] != 3) {
                            return new Offset(0, -1);
                        }
                        if (moveHistory[tempPoint.x() - 1][tempPoint.y()] != 3) {
                            return new Offset(-1, 0);
                        }
                        // если загнали в тупик, то бот разочаруется и идет навстречу смерти
                        return new Offset(1, 1);
                    }
                    if (bot.getValue().x() < tempPoint.x() && bot.getValue().y() >= tempPoint.y()) {
                        if (moveHistory[tempPoint.x() + 1][tempPoint.y() - 1] != 3) {
                            return new Offset(1, -1);
                        }
                        if (moveHistory[tempPoint.x() + 1][tempPoint.y()] != 3) {
                            return new Offset(1, 0);
                        }
                        if (moveHistory[tempPoint.x()][tempPoint.y() - 1] != 3) {
                            return new Offset(0, -1);
                        }
                        // если загнали в тупик, то бот разочаруется и идет навстречу смерти
                        return new Offset(-1, 1);
                    }
                    if (bot.getValue().x() >= tempPoint.x() && bot.getValue().y() > tempPoint.y()) {
                        if (moveHistory[tempPoint.x() - 1][tempPoint.y() + 1] != 3) {
                            return new Offset(-1, +1);
                        }
                        if (moveHistory[tempPoint.x() - 1][tempPoint.y()] != 3) {
                            return new Offset(-1, 0);
                        }
                        if (moveHistory[tempPoint.x()][tempPoint.y() + 1] != 3) {
                            return new Offset(0, +1);
                        }
                        // если загнали в тупик, то бот разочаруется и идет навстречу смерти
                        return new Offset(1, -1);
                    }
                    if (bot.getValue().x() < tempPoint.x() && bot.getValue().y() < tempPoint.y()) {
                        if (moveHistory[tempPoint.x() + 1][tempPoint.y() + 1] != 3) {
                            return new Offset(1, +1);
                        }
                        if (moveHistory[tempPoint.x() + 1][tempPoint.y()] != 3) {
                            return new Offset(1, 0);
                        }
                        if (moveHistory[tempPoint.x()][tempPoint.y() + 1] != 3) {
                            return new Offset(0, 1);
                        }
                        // если загнали в тупик, то бот разочаруется и идет навстречу смерти
                        return new Offset(-1, -1);
                    }
                } else {
                    // Догоняем
                    if (bot.getValue().x() >= tempPoint.x() && bot.getValue().y() >= tempPoint.y()) {
                        // Проверяем, не хотим ли перейти на препятствие и корректируем ход
                        if (moveHistory[tempPoint.x() + 1][tempPoint.y() + 1] != 3) {
                            return new Offset(+1, +1);
                        }
                        if (moveHistory[tempPoint.x()][tempPoint.y() + 1] != 3) {
                            return new Offset(0, +1);
                        }
                        if (moveHistory[tempPoint.x() + 1][tempPoint.y()] != 3) {
                            return new Offset(+1, 0);
                        }
                        // если не получается нагнать, бот расстраивается и уходит
                        return new Offset(-1, -1);
                    }
                    if (bot.getValue().x() < tempPoint.x() && bot.getValue().y() >= tempPoint.y()) {
                        if (moveHistory[tempPoint.x() - 1][tempPoint.y() + 1] != 3) {
                            return new Offset(-1, +1);
                        }
                        if (moveHistory[tempPoint.x() - 1][tempPoint.y()] != 3) {
                            return new Offset(-1, 0);
                        }
                        if (moveHistory[tempPoint.x()][tempPoint.y() - 1] != 3) {
                            return new Offset(0, +1);
                        }
                        return new Offset(1, -1);
                    }
                    if (bot.getValue().x() >= tempPoint.x() && bot.getValue().y() > tempPoint.y()) {
                        if (moveHistory[tempPoint.x() + 1][tempPoint.y() - 1] != 3) {
                            return new Offset(+1, -1);
                        }
                        if (moveHistory[tempPoint.x() + 1][tempPoint.y()] != 3) {
                            return new Offset(+1, 0);
                        }
                        if (moveHistory[tempPoint.x()][tempPoint.y() - 1] != 3) {
                            return new Offset(0, -1);
                        }
                        return new Offset(-1, +1);
                    }
                    if (bot.getValue().x() < tempPoint.x() && bot.getValue().y() < tempPoint.y()) {
                        if (moveHistory[tempPoint.x() - 1][tempPoint.y() - 1] != 3) {
                            return new Offset(-1, -1);
                        }
                        if (moveHistory[tempPoint.x() + 1][tempPoint.y()] != 3) {
                            return new Offset(-1, 0);
                        }
                        if (moveHistory[tempPoint.x()][tempPoint.y() + 1] != 3) {
                            return new Offset(0, -1);
                        }
                        return new Offset(+1, +1);
                    }
                }
            }
        }
        // если по какой-то причине бот не определился с направлением, оно выбирается случайно
        Random rnd = new Random(System.currentTimeMillis());
        return new Offset(rnd.nextInt(3) - 1, rnd.nextInt(3) - 1);
    }
}

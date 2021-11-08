package Croc.work7;

public class ChessPosition {
    private int x;
    private int y;
    // Служебный метод для конвертации символа в цифру в методе parse
    private int indexOf(char letter) {
        char[] alphabet = new char[]{'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'};
        int i = 0;
        for (char currentLetter : alphabet) {
            if (currentLetter == letter) {
                return i;
            }
            i++;
        }
        return -1;
    }

    public ChessPosition parse(String position) throws IllegalPositionException {
        int x, y;
        // Записываем в x индекс, который соответсвует номеру буквы, в у - цифру из исходной строки-1
        // Пользователь оперирует цифрами от 1 до 8, программа - от 0 до 7
        x = indexOf(position.charAt(0));
        y = position.charAt(1) - '0' - 1;
        ChessPosition chessPosition = new ChessPosition(x, y);
        return chessPosition;
    }

    public ChessPosition(String position){
        this.x = indexOf(position.charAt(0));
        this.y = position.charAt(1) - '0' - 1;
    }

    public ChessPosition(int x, int y) throws IllegalPositionException {
        try {
            if ((x < 0 || x > 7) || (y < 0 || y > 7)) {
                throw new IllegalPositionException("");
            } else {
                this.x = x;
                this.y = y;
            }
        } catch (IllegalPositionException e){
            System.out.println("Вы ввели некорректные данные");
        }
    }

    public void setX(int x) throws IllegalPositionException {
        try {
            if (x < 0 || x > 7) {
                throw new IllegalPositionException("");
            } else {
                this.x = x;
            }
        } catch (IllegalPositionException e) {
            System.out.println("Вы ввели некорректные данные");
        }
    }

    public void setY(int y) throws IllegalPositionException {
        try {
            if (y < 0 || y > 7) {
                throw new IllegalPositionException("");
            } else {
                this.y = y;
            }
        } catch (IllegalPositionException e) {
            System.out.println("Вы ввели некорректные данные");
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public String toString() {
        // Массив символов используется для вывода позиции в формате g3
        char[] alphabet = new char[]{'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'};
        return alphabet[x] + Integer.toString(y + 1);
    }
}
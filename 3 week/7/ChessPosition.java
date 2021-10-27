public class ChessPosition {
    private int x;
    private int y;

    public ChessPosition(int x, int y) throws IllegalPositionException {
        if ((x < 0 || x > 7) || (y < 0 || y > 7)) {
            throw new IllegalPositionException("Пользователь ввел некорректные данные");
        } else {
            this.x = x;
            this.y = y;
        }
    }

    public void setX(int x) throws IllegalPositionException {
        if (x < 0 || x > 7) {
            throw new IllegalPositionException("Пользователь ввел некорректные данные");
        } else {
            this.x = x;
        }
    }

    public void setY(int y) throws IllegalPositionException {
        if (y < 0 || y > 7) {
            throw new IllegalPositionException("Пользователь ввел некорректные данные");
        } else {
            this.y = y;
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

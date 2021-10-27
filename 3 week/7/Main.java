public class Main {
    private static int indexOf(char letter) {
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

    public static ChessPosition parse(String position) throws IllegalPositionException {
        int x, y;
        // Записываем в x индекс, который соответсвует номеру буквы, в у - цифру из исходной строки
        x = indexOf(position.charAt(0));
        y = position.charAt(1) - '0' - 1;
        ChessPosition chessPosition = new ChessPosition(x, y);
        return chessPosition;
    }

    public static boolean isKnight(String... steps) throws IllegalPositionException, IllegalMoveException {
        ChessPosition start, end;
        start = parse(steps[0]);
        for (int i = 1; i < steps.length; i++) {
            end = parse(steps[i]);
            int xDelta, yDelta;
            xDelta = Math.abs(end.getX() - start.getX());
            yDelta = Math.abs(end.getY() - start.getY());
            if ((xDelta == 2 && yDelta == 1) || (xDelta == 1 && yDelta == 2)) {
                start = end;
            } else {
                throw new IllegalMoveException(start, end);
            }
        }
        return true;
    }

    public static void main(String[] args) throws IllegalPositionException, IllegalMoveException {
        // Создание объекта класса через конструктор
        ChessPosition chessPosition = new ChessPosition(2, 3);
        System.out.println(chessPosition);
        // Создание объекта класса через фабричный метод
        ChessPosition newPos = parse("b2");
        System.out.println(newPos);
        // Проверка, может ли конь пройти по клеткам в массиве строк
        if (isKnight("g8", "e7", "c8")) {
            System.out.println("Ok!");
        }
    }
}

package Croc.work7;

public class Main {
    // Проверка, может ли конь пройти по позициям в массиве строк: сохраняем начальную позицию и следующую, потом проверяем их
    public static boolean isKnight(String... steps) throws IllegalPositionException, IllegalMoveException {
        ChessPosition start, end;
        start = new ChessPosition(steps[0]);
        for (int i = 1; i < steps.length; i++) {
            end = new ChessPosition(steps[i]);
            int xDelta, yDelta;
            xDelta = Math.abs(end.getX() - start.getX());
            yDelta = Math.abs(end.getY() - start.getY());
            // Проверяем верность хода с помощью вспомогательных переменных смещения по горизонтали и вертикали
            try {
                if ((xDelta == 2 && yDelta == 1) || (xDelta == 1 && yDelta == 2)) {
                    start = end;
                } else {
                    throw new IllegalMoveException(start, end);
                }
            } catch (IllegalMoveException e){
                System.out.println("Конь так не ходит: " + start.toString() + " -> " + end.toString());
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) throws IllegalPositionException, IllegalMoveException {
        // Создание объекта класса через конструктор
        ChessPosition chessPosition = new ChessPosition(2, 3);
        System.out.println(chessPosition);
        // Создание объекта класса через фабричный метод
        ChessPosition newPos = new ChessPosition("b2");
        System.out.println(newPos);
        // Проверка, может ли конь пройти по клеткам в массиве строк
        if (isKnight("g8", "e7", "c8")) {
            System.out.println("Ok!");
        }
    }
}
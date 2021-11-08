package Croc.work7;

public class IllegalMoveException extends Exception {
    public IllegalMoveException(ChessPosition start, ChessPosition end) {
        super("Конь так не ходит: " + start.toString() + " -> " + end.toString());
    }
}
public class Circle implements Movable {
    protected int xCoordinate;
    protected int yCoordinate;
    protected int radius;

    // Определяем метод интерфейса
    public void move(int dx, int dy) {
        xCoordinate += dx;
        yCoordinate += dy;
    }

    public Circle(int xCoordinate, int yCoordinate, int radius) {
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        this.radius = radius;
    }
}

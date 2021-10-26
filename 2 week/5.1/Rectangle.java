public class Rectangle implements Movable {
    protected int xLeft;
    protected int yLeft;
    protected int xRight;
    protected int yRight;

    // Определяем метод интерфейса
    public void move(int dx, int dy) {
        xLeft += dx;
        xRight += dx;
        yLeft += dy;
        yRight += dy;
    }

    public Rectangle(int xLeft, int yLeft, int xRight, int yRight) {
        this.xLeft = xLeft;
        this.yLeft = yLeft;
        this.xRight = xRight;
        this.yRight = yRight;
    }
}

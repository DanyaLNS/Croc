public class Rectangle implements Movable {
    protected int xLeft;
    protected int yLeft;
    protected int xRight;
    protected int yRight;

    @Override
    public void move(int dx, int dy) {
        xLeft += dx;
        yLeft += dy;
        xRight += dx;
        yRight += dy;
    }

    public Rectangle(int xLeft, int yLeft, int xRight, int yRight) {
        this.xLeft = xLeft;
        this.yLeft = yLeft;
        this.xRight = xRight;
        this.yRight = yRight;
    }
}
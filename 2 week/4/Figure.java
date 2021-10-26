public class Figure {
    protected Circle circle;
    protected Rectangle rectangle;
    protected String figure;

    public Figure(String figure) {
        this.figure = figure;
        if(figure=="circle") {
            int xCoordinate, yCoordinate,radius;
            xCoordinate = 10;
            yCoordinate = 5;
            radius = 3;
            circle = new Circle(xCoordinate, yCoordinate, radius);
        } else if(figure == "rectangle"){
            int xLeft, yLeft, xRight, yRight;
            xLeft = 0; yLeft = 0;
            xRight = 5; yRight = 5;
            rectangle = new Rectangle(xLeft, yLeft, xRight, yRight);
        }
    }
}
public class Circle extends Annotation{
    public int xCoordinate;
    public int yCoordinate;
    public int radius;
    public Circle(String annotation, String figure, int xCoordinate, int yCoordinate, int radius){
        super(annotation, figure);
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        this.radius = radius;
    }
    @Override
    public String toString() {
        return "C (" + xCoordinate + ", " + yCoordinate +") " + radius + ": " + annotation;
    }
}

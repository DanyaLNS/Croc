public class Annotation {
    private Figure shape;
    private String annotation;

    public Annotation(String annotation, String figure) {
        this.annotation = annotation;
        if (figure == "circle") {
            shape = new Figure("circle");
        } else if (figure == "rectangle") {
            shape = new Figure("rectangle");
        }
    }

    @Override
    public String toString() {
        if (shape.figure == "circle") {
            return "C (" + shape.circle.xCoordinate + ", " + shape.circle.yCoordinate + ") " + shape.circle.radius + ": " + annotation;
        } else if (shape.figure == "rectangle") {
            return "R (" + shape.rectangle.xLeft + ", " + shape.rectangle.yLeft + "), (" + shape.rectangle.xRight + ", " + shape.rectangle.yRight + "): " + annotation;
        }
        return "";
    }
}
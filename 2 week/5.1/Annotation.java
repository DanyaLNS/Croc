import java.util.Scanner;

public class Annotation {
    protected String annotation;
    protected String figure;

    protected Circle circle;
    protected Rectangle rectangle;

    private void initCircle() {
        Scanner in = new Scanner(System.in);
        System.out.println("Введите координаты центра окружности и его радиус");
        int xCoordinate = in.nextInt();
        int yCoordinate = in.nextInt();
        int radius = in.nextInt();
        in.close();
        circle = new Circle(xCoordinate, yCoordinate, radius);
    }

    private void initRectangle() {
        Scanner in1 = new Scanner(System.in);
        System.out.println("Введите координаты левого нижнего и правого верхнего углов");
        int xLeft = in1.nextInt();
        int yLeft = in1.nextInt();
        int xRight = in1.nextInt();
        int yRight = in1.nextInt();
        in1.close();

        // Захардкодил из-за ошибки util.NoSuchElementException. На форумах написано, что её появление зависит от компилятора
        // Если у вас выведет то же самое, закомментите все верхние строки метода и расскоментите инициализацию ниже
        /*
        int xLeft = 0; int yLeft = 0; int xRight = 5; int yRight = 5;
        */
        rectangle = new Rectangle(xLeft, yLeft, xRight, yRight);
    }

    public Annotation(String annotation, String figure) {
        this.annotation = annotation;
        this.figure = figure;
        // В зависимости от фигуры инициализируем поле круга или прямоугольника
        if (figure == "Circle") {
            initCircle();
        } else if (figure == "Rectangle") {
            initRectangle();
        }
    }

    @Override
    public String toString() {
        // Возвращаем строки в зависимости от фигуры
        if (figure == "Circle") {
            return "C (" + circle.xCoordinate + ", " + circle.yCoordinate + ") " + circle.radius + ": " + annotation;
        } else if (figure == "Rectangle") {
            return "R (" + rectangle.xLeft + ", " + rectangle.yLeft + "), (" + rectangle.xRight + ", "
                    + rectangle.yRight + "): " + annotation;
        }
        return "";
    }
}

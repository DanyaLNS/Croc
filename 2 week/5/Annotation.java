import java.util.Scanner;

public class Annotation {
    protected String annotation;
    protected String figure;
    protected Circle circle;
    protected Rectangle rectangle;
    // Почему-то при инициализации через поток ввода выдает ошибку java.util.NoSuchElementException, поэтому захардкодил инициализацию объектов 
    private void circleInit(){
        // Вводим информацию для создания круга и создаем его
        int xCoordinate, yCoordinate, radius;
        System.out.println("Введите координаты центра окружности и её радиус: ");
        Scanner in = new Scanner(System.in);
        xCoordinate = in.nextInt();
        yCoordinate = in.nextInt();
        radius = in.nextInt();
        in.close();
    }

    private void rectangleInit(){
        // Вводим информацию для создания квадрата и создаем его
        Scanner in = new Scanner(System.in);
        int xLeft, yLeft, xRight, yRight;
        System.out.println("Введите координатыы левой нижней и правой верхней границ прямоугольника: ");
        xLeft = in.nextInt();
        yLeft = in.nextInt();
        xRight = in.nextInt();
        yRight = in.nextInt();
        in.close();
        if(xLeft!=xRight && xLeft!=yLeft){
            rectangle = new Rectangle(annotation, figure, xLeft, yLeft, xRight, yRight);
        } else{
            System.out.println("Введены некорректные данные");
        }
    }

    public Annotation(String annotation, String figure){
        this.annotation=annotation;
        this.figure=figure;
        if(figure=="Circle"){
            int xCoordinate, yCoordinate, radius;
            xCoordinate = yCoordinate = 10;
            radius = 1;
            circle = new Circle(annotation, figure, xCoordinate, yCoordinate, radius);
        } else if(figure=="Rectangle"){
            int xLeft, yLeft, xRight, yRight;
            xLeft = yLeft = 0;
            xRight = yRight = 5;
            rectangle = new Rectangle(annotation, figure, xLeft, yLeft, xRight, yRight);
        }
    }



    @Override
    public String toString() {
        return figure + " is " + annotation;
    }
}

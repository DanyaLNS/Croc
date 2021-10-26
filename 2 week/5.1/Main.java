import java.util.Scanner;
public class Main {
    // Функция, которая перемещает область с помощью поиска по лейблу
    public static void moveByLabel(AnnotatedImage annotatedImage, String label, int dx, int dy) {
        if (annotatedImage.findByLabel(label).figure == "Circle") {
            annotatedImage.findByLabel(label).circle.move(dx, dy);
        } else if (annotatedImage.findByLabel(label).figure == "Rectangle") {
            annotatedImage.findByLabel(label).rectangle.move(dx, dy);
        }
    }
    // Функция, которая перемещает область с помощью поиска по координате
    public static void moveByPoint(AnnotatedImage annotatedImage, int x, int y, int dx, int dy) {
        if (annotatedImage.findByPoint(x, y).figure == "Circle") {
            annotatedImage.findByPoint(x, y).circle.move(dx, dy);
        } else if (annotatedImage.findByPoint(x, y).figure == "Rectangle") {
            annotatedImage.findByPoint(x, y).rectangle.move(dx, dy);
        }
    }

    // Логика работы программы: есть классы фигур, они имеют свои поля в классе Annotation, 
    // при создании объектов которого указывается вид фигуры, каждый класс фигуры использует интерфейс Movable
    // Annotated image содержит массив из подписанных изображений и методы поиска по координате и подписи

    public static void main(String[] args) {

        // В моем компиляторе происходит какая-то ошибка с потоками ввода, 
        // ниже приведу закомменченный метод main, который использовал для тестов

        // Создаем 2 аннотации и помещаем их в класс AnnotatedImage
        Annotation annotation1 = new Annotation("Moon", "Circle");
        Annotation annotation2 = new Annotation("Stars", "Rectangle");
        AnnotatedImage annotatedImage = new AnnotatedImage("imagePath", annotation1, annotation2);
        System.out.println(annotation1.toString());
        System.out.println(annotation2.toString());
        // 1 перемещение с помощью поиска по аннотации
        Scanner in = new Scanner(System.in);
        System.out.println("Введите величины, на которые нужно сместиться по x, y");
        int dx, dy;
        dx = in.nextInt();
        dy = in.nextInt();
        moveByLabel(annotatedImage, "Moon", dx, dy);
        moveByLabel(annotatedImage, "Stars", dx, dy);
        System.out.println(annotation1.toString());
        System.out.println(annotation2.toString());
        // 2 перемещение с помощью поиска по координате
        System.out.println("Введите величины, на которые нужно сместиться по x, y");
        dx = in.nextInt();
        dy = in.nextInt();
        System.out.println("Введите координату объекта, который нужно переместить");
        int x, y;
        x = in.nextInt();
        y = in.nextInt();
        in.close();
        moveByPoint(annotatedImage, x, y, dx, dx);
        System.out.println(annotation1.toString());
        System.out.println(annotation2.toString());
    }
}
    // main, который точно работает
/*
    public static void main(String[] args) {
        // Вводил 0 0 3
        Annotation annotation1 = new Annotation("Moon", "Circle");
        Annotation annotation2 = new Annotation("Stars", "Rectangle");
        AnnotatedImage annotatedImage = new AnnotatedImage("imagePath", annotation1, annotation2);
        System.out.println(annotation1.toString());
        System.out.println(annotation2.toString());
        // 1 перемещение с помощью поиска по аннотации
        moveByLabel(annotatedImage, "Moon", 10, 10);
        moveByLabel(annotatedImage, "Stars", 1, 1);
        System.out.println(annotation1.toString());
        System.out.println(annotation2.toString());
        // 2 перемещение с помощью поиска по координате
        moveByPoint(annotatedImage, 2, 2, 5, 5);
        moveByPoint(annotatedImage, 10, 10, 100, 100);
        System.out.println(annotation1.toString());
        System.out.println(annotation2.toString());
    }
    */
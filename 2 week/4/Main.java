public class Main {
    public static void main(String[] args) {
        Annotation annotation1 = new Annotation("Car", "rectangle");
        Annotation annotation2 = new Annotation("Moon", "circle");

        AnnotatedImage annotatedImage = new AnnotatedImage("", annotation1, annotation2);
        // Метод для вывода всех аннотаций
        annotatedImage.printAnnotations();
    }
}
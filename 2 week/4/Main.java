public class Main {
    public static void main(String[] args) {
        Annotation annotation1 = new Annotation("Car", "Rectangle");
        Annotation annotation2 = new Annotation("Moon", "Circle");

        AnnotatedImage annotatedImage = new AnnotatedImage("", annotation1, annotation2);
        annotatedImage.printAnnotations();
    }
}
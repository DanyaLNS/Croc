public class Main {
    public static void main(String[] args) {
        Annotation annotation1 = new Annotation("Car", "rectangle");
        Annotation annotation2 = new Annotation("Moon", "circle");

        AnnotatedImage annotatedImage = new AnnotatedImage("", annotation1, annotation2);
        System.out.println(annotatedImage.findByPoint(10, 5));
        annotatedImage.findByLabel("Moon").getFigure().circle.move(500, 500);
        System.out.println(annotatedImage.findByLabel("Car"));
        annotatedImage.findByPoint(1,3).getFigure().rectangle.move(100,100);
        annotatedImage.printAnnotations();
    }
}
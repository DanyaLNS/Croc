public class Main {
    public static void main(String[] args) {
        Annotation car = new Annotation("Car", "Rectangle");
        Annotation apple = new Annotation("Apple", "Circle");
        Annotation[] annotations = new Annotation[2];
        annotations[0] = car;
        annotations[1] = apple;
        AnnotatedImage annotatedImage = new AnnotatedImage("imagePath", annotations);
        System.out.println(annotatedImage.findByPoint(3, 3).toString());
    }
}

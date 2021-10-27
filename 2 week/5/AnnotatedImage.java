class AnnotatedImage {
    // Служебный метод для возведения в квадрат, чтобы проверить, входит ли точка в окружность
    private static int pow(int num) {
        return num * num;
    }

    private Annotation notFound;
    private final String imagePath;
    private final Annotation[] annotations;

    public AnnotatedImage(String imagePath, Annotation... annotations) {
        this.imagePath = imagePath;
        this.annotations = annotations;
        notFound = new Annotation("", "");
    }

    public void printAnnotations() {
        for (Annotation annotation : annotations) {
            System.out.println(annotation.toString());
        }
    }

    public String getImagePath() {
        return this.imagePath;
    }

    public Annotation[] getAnnotations() {
        return this.annotations;
    }

    // Поиск по аннотации
    public Annotation findByLabel(String label) {
        for (Annotation annotation : annotations) {
            if (annotation.getAnnotation() == label) {
                return annotation;
            }
        }
        return notFound;
    }

    // Поиск по координате
    public Annotation findByPoint(int x, int y) {
        for (Annotation annotation : annotations) {
            // Проверяем условия вхождения точки для каждой фигуры
            if (annotation.getFigure().figure == "rectangle") {
                if (annotation.getFigure().rectangle.xLeft < x && annotation.getFigure().rectangle.yLeft < y && annotation.getFigure().rectangle.xRight > x
                        && annotation.getFigure().rectangle.yRight > y) {
                    return annotation;
                }
            } else if (annotation.getFigure().figure == "circle") {
                if (pow(annotation.getFigure().circle.radius) >= pow(x - annotation.getFigure().circle.xCoordinate)
                        + pow(y - annotation.getFigure().circle.yCoordinate)) {
                    return annotation;
                }
            }
        }
        return notFound;
    }
}

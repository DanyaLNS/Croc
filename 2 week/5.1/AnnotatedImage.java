class AnnotatedImage {
  // Служебный метод для возведения в квадрат, чтобы проверить, входит ли точка в окружность
  private static int pow(int num) {
    return num * num;
  }
  // notFound - вспомогательная переменная, чтобы в случае, если не найдем элемент после поиска не возвращать null
  private Annotation notFound;
  private final String imagePath;
  private final Annotation[] annotations;

  public AnnotatedImage(String imagePath, Annotation... annotations) {
    this.imagePath = imagePath;
    this.annotations = annotations;
    notFound = new Annotation("", "");
  }
  // Поиск по подписи
  public Annotation findByLabel(String label) {
    for (Annotation annotation : annotations) {
      if (annotation.annotation == label) {
        return annotation;
      }
    }
    return notFound;
  }
  // Поиск по координате
  public Annotation findByPoint(int x, int y) {
    for (Annotation annotation : annotations) {
      // Проверяем условия вхождения точки для каждой фигуры 
      if (annotation.figure == "Rectangle") {
        if (annotation.rectangle.xLeft < x && annotation.rectangle.yLeft < y && annotation.rectangle.xRight > x
            && annotation.rectangle.yRight > y) {
          return annotation;
        }
      } else if (annotation.figure == "Circle") {
        if (pow(annotation.circle.radius) >= pow(x - annotation.circle.xCoordinate)
            + pow(y - annotation.circle.yCoordinate)) {
          return annotation;
        }
      }
    }
    return notFound;
  }

  public String getImagePath() {
    return this.imagePath;
  }
  public Annotation[] getAnnotations() {
    return this.annotations;
  }
}

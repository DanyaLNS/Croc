class AnnotatedImage {
  private final int pow(int num, int degree){
    int beginNum = num;
    for(int i = 0; i<degree; i++){
      num *=beginNum;
    }
    return num;
  }
  private final String imagePath;

  private final Annotation[] annotations;

  public AnnotatedImage(String imagePath, Annotation... annotations) {
    this.imagePath = imagePath;
    this.annotations = annotations;
  }

  public String getImagePath() {
    return this.imagePath;
  }

  public Annotation[] getAnnotations() {
    return this.annotations;
  }

  public Annotation findByPoint(int x, int y){
    for (Annotation element : annotations) {
      if(element.figure == "Rectangle"){
        if((element.rectangle.xLeft < x) && (element.rectangle.xRight > x) && (element.rectangle.yLeft < y) && (element.rectangle.yRight > y)){
          return element;
        }
      } else if(element.figure == "Circle"){
        if(pow(element.circle.xCoordinate - x, 2) + pow(element.circle.yCoordinate - y, 2) < pow(element.circle.radius,2)){
          return element;
        }
      }
    }
    return null;
  }
}

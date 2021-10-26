class AnnotatedImage {

  private final String imagePath;

  private final Annotation[] annotations;

  public AnnotatedImage(String imagePath, Annotation... annotations) {
      this.imagePath = imagePath;
      this.annotations = annotations;
  }

  public void printAnnotations(){
      for (Annotation annotation: annotations) {
          System.out.println(annotation.toString());
      }
  }
  public String getImagePath() {
      return this.imagePath;
  }

  public Annotation[] getAnnotations() {
      return this.annotations;
  }
}

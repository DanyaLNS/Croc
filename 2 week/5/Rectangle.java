public class Rectangle extends Annotation {
    public int xLeft;
    public int yLeft;
    public int xRight;
    public int yRight;

    public Rectangle(String annotation, String figure, int xLeft, int yLeft, int xRight, int yRight){
        super(annotation, figure);
        this.xLeft=xLeft;
        this.yLeft=yLeft;
        this.xRight=xRight;
        this.yRight=yRight;
    }
    
    @Override
    public String toString() {
        return "R (" + xLeft + ", " + yLeft + "), (" + xRight + ", " + yRight + "): " + annotation ;
    }

}

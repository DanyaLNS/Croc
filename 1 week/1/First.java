import java.util.Scanner;

public class First {
    // Вспомогательный класс точки
    static class Point {
        public Point(double x, double y){
            this.x = x;
            this.y = y;
        }
        double x;
        double y;
      }
    
      public static void main(String[] args) {
        // Создание вершин треугольника
        double x, y;
        Scanner in = new Scanner(System.in);
        // 1 вершина
        System.out.print("Введите координату х вершины №1: ");
        x = in.nextDouble();
        System.out.print("Введите координату y вершины №1: ");
        y = in.nextDouble();
        Point a = new Point(x,y);
        // 2 вершина
        System.out.print("Введите координату х вершины №2: ");
        x = in.nextDouble();
        System.out.print("Введите координату y вершины №2: ");
        y = in.nextDouble();
        Point b = new Point(x,y);
        // 3 вершина
        System.out.print("Введите координату х вершины №3: ");
        x = in.nextDouble();
        System.out.print("Введите координату y вершины №3: ");
        y = in.nextDouble();
        Point c = new Point(x,y);
        in.close();
        // Проверяем, лежат ли точки на одной прямой
        if((c.x - a.x)/(b.x - a.x) == (c.y - a.y)/(b.y - a.y)){
            System.out.println("Введены некорректные данные: точки располагаются на прямой и не могут образовать треунольник");
        }
        else{
            // Вычисляем площадь по формуле Герона
            double area = 0.5 * Math.abs((b.x - a.x) * (c.y - a.y) - (c.x - a.x) * (b.y - a.y));
            System.out.println("Площадь треугольника = " + area);
        }
      }    
}

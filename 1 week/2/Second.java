import java.util.Scanner;

public class Second{
    public static String printBytes(long bytes){
        // Проверяем, больше ли количество байтов определенной степени 2 и возвращаем соответствующую строку
        if(bytes >= Math.pow(2, 40)){
            return (String.format("%.1f",  bytes/Math.pow(2, 40)) + " TB");
        }
        if(bytes >= Math.pow(2, 30)){
            return (String.format("%.1f",  bytes/Math.pow(2, 30)) + " GB");
        }
        if(bytes >= Math.pow(2, 20)){
            return (String.format("%.1f",  bytes/Math.pow(2, 20)) + " MB");
        }
        if(bytes >= Math.pow(2, 10)){
            return (String.format("%.1f",  bytes/Math.pow(2, 10)) + " KB");
        }        
        return (bytes + " B");
    }
    public static void main(String[] args) {
        // Принимаем на вход число и выводим результат работы метода
        long number; 
        Scanner in = new Scanner(System.in);
        number = in.nextLong();
        System.out.println(printBytes(number));
        in.close();
    }
}
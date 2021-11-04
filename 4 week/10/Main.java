import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    // Метод записи в файл
    public static void input(String path, String in) {
        try (FileWriter writer = new FileWriter(path, false)) {
            writer.write(in);
            writer.flush();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    // Метод для формирования объектов звонков из файла
    public static Call toCall(String strToCall) {
        String[] startEnd = strToCall.split(",");
        long start = Long.parseLong(startEnd[0]);
        long end = Long.parseLong(startEnd[1]);
        return new Call(start, end);
    }

    // Переводим все записи из файла в динамический массив звонков
    public static ArrayList<Call> parseFile(String path) throws FileNotFoundException {
        Scanner out = new Scanner(new File(path));
        ArrayList<Call> listOfCalls = new ArrayList<>();
        while (out.hasNextLine()) {
            listOfCalls.add(toCall(out.nextLine()));
        }
        return listOfCalls;
    }

    public static int countMax(ArrayList<Call> calls) {
        int count = 0, max = 0;
        // Проходим по массиву звонков и считаем количество одновременно идущих разговоров для каждого
        for (int i = 0; i < calls.size() - 1; i++) {
            for (int j = i + 1; j < calls.size(); j++) {
                // если звонок начался до окончания исходного, то инкрементируем счетчик
                // если звонок начинается после окончания исходного, то выходим из внутреннего цикла, чтобы не считать лишнее
                if (calls.get(i).getEnd() > calls.get(j).getStart()) {
                    count++;
                } else {
                    break;
                }
            }
            if (count > max) {
                max = count;
            }
            count = 0;
        }
        return max;
    }

    public static void main(String[] args) throws FileNotFoundException {
        input("logs.txt", "1,12\n" +
                "4,6\n" +
                "7,12\n" +
                "10,32\n" +
                "15,30\n");
        ArrayList<Call> listOfCalls = new ArrayList<>();
        listOfCalls = parseFile("logs.txt");
        System.out.println(countMax(listOfCalls));
    }
}

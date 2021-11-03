package Croc.work8;

import java.io.*;
import java.util.Scanner;

public class Counter {
    // Метод, вводящий информацию в файл
    public static void input(String path, String in) {
        try (FileWriter writer = new FileWriter(path, false)) {
            writer.write(in);
            writer.flush();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }


    public static int countWords(String path) throws FileNotFoundException {
        int count = 0;
        Scanner out = new Scanner(new File(path));
        // обрабатываем каждую строку из файла отдельно
        while (out.hasNextLine()) {
            // удаляем из строки лишние пробелы, потом разбиваем её на массив из отдельных слов,
            // к count прибавляем длину этого массива
            count += out.nextLine().replaceAll("^ +| +$|( )+", "$1").split(" ").length;
        }
        return count;
    }

    public static void main(String[] args) throws FileNotFoundException {
        String path = "inf.txt";
        String in = "Забыл   Панкрат  Кондратьевич домкрат,\n" +
                "А без домкрату ну  не  поднять на тракте трактор.\n";
        input(path, in);
        System.out.println(countWords(path));
    }
}

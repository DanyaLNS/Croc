package Croc.work14;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Cinema {
    private Map<Integer, String> films = new HashMap<>();
    private ArrayList<String> history = new ArrayList<>();

    // Перевод строки истории просмотра в целочисленную коллекцию
    public static ArrayList<Integer> strToInt(String str) {
        ArrayList<Integer> result = new ArrayList<>();
        String[] divided = str.split(",");
        for (String temp : divided) {
            result.add(Integer.parseInt(temp));
        }
        return result;
    }

    public Cinema() throws IOException {
        // Парсим файлы в коллекции
        // Создаем коллекцию истории просмотра
        File myfile = new File("C:\\Users\\danil\\OneDrive\\Рабочий стол\\allJavaWorks\\src\\Croc\\work14\\History.txt");
        FileReader fr = new FileReader(myfile);
        BufferedReader reader = new BufferedReader(fr);
        String line = null;
        while ((line = reader.readLine()) != null) {
            history.add(line);
        }
        reader.close();
        // Создаем список фильмов
        File myfile1 = new File("C:\\Users\\danil\\OneDrive\\Рабочий стол\\allJavaWorks\\src\\Croc\\work14\\Films.txt");
        FileReader fr1 = new FileReader(myfile1);
        BufferedReader reader1 = new BufferedReader(fr1);
        String line1 = null;
        while ((line1 = reader1.readLine()) != null) {
            String[] element = line1.split(",");
            films.put(Integer.parseInt(element[0]), element[1]);
        }
        reader1.close();
    }

    // Ищем количество совпадающих фильмов в истории
    public static int findIntersection(String temp, String userHistory) {
        ArrayList<Integer> historyList = new ArrayList<>();
        historyList = strToInt(temp);
        ArrayList<Integer> intUserHistory = new ArrayList<>();
        intUserHistory = strToInt(userHistory);
        int count = 0;
        for (int number : intUserHistory) {
            for (int number1 : historyList) {
                if (number == number1) {
                    count++;
                    break;
                }
            }
        }
        return count;
    }
    // Я пытался сделать алгоритм поэффиктивнее, но только сильнее запутывался
    // зато работает))
    // Алгоритм поиска наиболее просматриваемого фильма
    public String mostPopularFilm(ArrayList<Integer> temp) {
        int max = 0;
        int maxCount = 0;
        int currentCount = 0;
        for (int i = 0; i < temp.size() - 1; i++) {
            for (int j = 0; j < temp.size(); j++) {
                if(temp.get(i).equals(temp.get(j))){
                    currentCount++;
                }
            }
            if(currentCount>maxCount){
                max = temp.get(i);
                maxCount = currentCount;
            }
            currentCount = 0;
        }
        return films.get(max);
    }

    public String recomendation(String userHistory) {
        // actaulRecomendation - служебный список, в котором храним промежуточный результат работы алгоритма
        ArrayList<Integer> actualRecomendations = new ArrayList<>();
        ArrayList<Integer> intUserHistory = new ArrayList<>();
        intUserHistory = strToInt(userHistory);
        for (String temp : history) {
            ArrayList<Integer> tempHistory = new ArrayList<>();
            tempHistory = strToInt(temp);
            // Если хотя бы половина просмотренных фильмов совпадает, то: исключаем из тэмпа все, которые пользователь уже видел
            if (findIntersection(temp, userHistory) >= (userHistory.length() / 2)) {
                // Добавляем в коллекцию промежуточных итогов все фильмы, которые пользователь еще не смотрел
                for (Integer number : tempHistory) {
                    if (intUserHistory.indexOf(number) == -1) {
                        actualRecomendations.add(number);
                    }
                }
            }
        }
        // Находим наиболее популярный фильм из сформированного списка
        return mostPopularFilm(actualRecomendations);
    }

    public static void main(String[] args) throws IOException {
        Cinema cinema = new Cinema();
        System.out.println(cinema.recomendation("2,4"));
    }
}

package Croc.work16;

import java.util.*;

public class Group {
    // Массив для хранения границ
    private static ArrayList<Integer> boundaries = new ArrayList<>();
    // Используется treemap, так как нам нужно выводить элементы в определенном порядке
    private static Map<Integer, String> personesMap = new TreeMap<>();
    // Результат разбиения на группы будет храниться в стеке:
    // принцип LIFO позволяет сформировать коллекцию, которую требует постановка задачи
    private static Stack<String> result = new Stack<>();

    // Метод для формирования границ возрастных групп
    public static void findBoundaries(String groupBoundaries) {
        String[] groupToNumbers = groupBoundaries.split(" ");
        for (String age : groupToNumbers) {
            boundaries.add(Integer.parseInt(age));
        }
    }

    public static void makeGroupes() {
        // StringBuilder используется для формирования строки возрастной группы
        StringBuilder tempResult = new StringBuilder("0-" + boundaries.get(0) + ": ");
        // переменная, чтобы выставлять запятые при наличии более одного человека в группе
        boolean isFirst = true;
        // переменная, чтобы отслеживать: есть ли люди, принадлежащие возрастной категории
        boolean isEmpty = true;
        for (Map.Entry<Integer, String> temp : personesMap.entrySet()) {
            // temp - рассматриваемый в данный момент элемент из дерева
            if (temp.getKey() >= 0 && temp.getKey() < boundaries.get(0)) {
                if (isFirst) {
                    tempResult.append(temp.getValue() + " (" + temp.getKey() + ")");
                    isFirst = false;
                    isEmpty = false;
                } else {
                    tempResult.append(", " + temp.getValue() + " (" + temp.getKey() + ")");
                }
            } else {
                // Так как работаем с упорядоченной структурой, при первом неподходящем элементе можем сбросить цикл
                break;
            }
        }
        // Если группа не пустая, добавляем её в ответ
        if (!isEmpty) {
            result.add(tempResult.toString());
        }
        for (int i = 1; i < boundaries.toArray().length; i++) {
            // Обнуляю переменные, чтобы сформировать строки в ответ по вышеизложенной логике
            isFirst = true;
            isEmpty = true;
            tempResult.delete(0, tempResult.length());
            tempResult.append(boundaries.get(i - 1) + 1 + "-" + boundaries.get(i) + ": ");
            for (Map.Entry<Integer, String> temp : personesMap.entrySet()) {
                if (temp.getKey() > boundaries.get(i - 1) && temp.getKey() <= boundaries.get(i)) {
                    if (isFirst) {
                        tempResult.append(temp.getValue() + " (" + temp.getKey() + ")");
                        isFirst = false;
                        isEmpty = false;
                    } else {
                        tempResult.append(", " + temp.getValue() + " (" + temp.getKey() + ")");
                    }
                }
            }
            if (!isEmpty) {
                result.add(tempResult.toString());
            }
        }
        // Повторяем те же действия для последней возрастной группы
        isFirst = true;
        isEmpty = true;
        tempResult.delete(0, tempResult.length());
        tempResult.append(boundaries.get(boundaries.size() - 1) + 1 + "+: ");
        for (Map.Entry<Integer, String> temp : personesMap.entrySet()) {
            if (temp.getKey() >= boundaries.get(boundaries.size() - 1) && temp.getKey() < 123) {
                if (isFirst) {
                    tempResult.append(temp.getValue() + " (" + temp.getKey() + ")");
                    isFirst = false;
                    isEmpty = false;
                } else {
                    tempResult.append(", " + temp.getValue() + " (" + temp.getKey() + ")");
                }
            }
            if (!isEmpty) {
                result.add(tempResult.toString());
            }
        }
    }

    public static void printResult() {
        for (String group : result) {
            System.out.println(group);
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // String groupBoundaries = in.nextLine();
        String groupBoundaries = "18 25 35 45 60 80 100";
        findBoundaries(groupBoundaries);
        // Вводим информацию о людях в коллекцию persones
        while (in.hasNextLine()) {
            String persone;
            persone = in.nextLine();
            if (persone.equals("END")) {
                break;
            }
            // Перевод строк в словарь, у которого ключ - возраст человека, а значение - ФИО
            String[] personeArray = persone.split(" ");
            personesMap.put(Integer.parseInt(personeArray[3]), personeArray[0] + " " + personeArray[1] + " " + personeArray[2]);
        }
        in.close();
        makeGroupes();
        printResult();
    }
}

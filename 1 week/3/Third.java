import java.util.Scanner;

public class Third {

    public static void printArray(int[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }

    public static int[] inputArray() {
        // Вводим строку из чисел, разделенными пробелами и преобразуем её в массив чисел
        String str;
        Scanner in = new Scanner(System.in);
        str = in.nextLine();
        in.close();
        // Разделяем исходную строку на массив из подстрок, находящихся между пробелами
        String inpArr[] = str.split(" ");
        int[] array = new int[inpArr.length];
        // Конвертируем значения из массива строк в целочисленный тип и записываем их в массив
        for (int i = 0; i < inpArr.length; i++) {
            array[i] = Integer.parseInt(inpArr[i]);
        }
        return array;
    }

    public static int[] findIndex(int[] array) {
        // Ищем индексы и взовращем массив: в 0 элементе записан минимальный, в 1 - максимальный
        int[] index = new int[2];
        for (int i = 0; i < array.length; i++) {
            if (array[i] > array[index[1]]) {
                index[1] = i;
            } else if (array[i] < array[index[0]]) {
                index[0] = i;
            }
        }
        return index;
    }

    public static int[] replace(int[] array, int[] index) {
        int maxIndex = index[1], minIndex = index[0];
        int temp;
        // Перемещяем элементы
        if (minIndex == 3 && maxIndex == 0) {
            // Перестановка для случая, когда нужно поменять местами максимальный и
            // минимальный элементы
            temp = array[0];
            array[0] = array[array.length - 1];
            array[array.length - 1] = temp;
        } else if (minIndex == 3) {
            // Перестановка для случая, когда минимальный в конце
            temp = array[minIndex];
            array[minIndex] = array[0];
            array[0] = temp;
            temp = array[maxIndex];
            array[maxIndex] = array[3];
            array[3] = temp;
        } else if (maxIndex == 0) {
            // Перестановка для случая, когда максимальный в начале
            temp = array[maxIndex];
            array[maxIndex] = array[3];
            array[3] = temp;
            temp = array[minIndex];
            array[minIndex] = array[0];
            array[0] = temp;
        } else {
            // дефолтная перестановка
            temp = array[minIndex];
            array[minIndex] = array[0];
            array[0] = temp;
            temp = array[maxIndex];
            array[maxIndex] = array[3];
            array[3] = temp;
        }
        return array;
    }

    public static void main(String[] args) {
        int[] array = inputArray();
        int[] index = findIndex(array);
        array = replace(array, index);
        printArray(array);
    }
}
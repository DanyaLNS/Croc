package Croc.work13;

import java.util.*;

public class RemoveComments implements BlackListFilter {
    public RemoveComments() {
    }

    public void filterComments(List<String> comments, Set<String> blackList) {
        // Проходимся по списку комментариев и проверяем вхождение слов из blackList в каждом комментарии
        for (int i = 0; i < comments.size(); i++) {
            for (String word : blackList) {
                try {
                    while (comments.get(i).toLowerCase(Locale.ROOT).indexOf(word) != -1) {
                        comments.remove(comments.get(i));
                    }
                } catch (IndexOutOfBoundsException e){
                    // Обработка исключения сделана для случая, когда нужно удалить последний комментарий в коллекции:
                    // после его удаления идет проверка по прошлому индексу, который был на 1 больше текущего размера коллекции
                    break;
                }
            }
        }
    }
}

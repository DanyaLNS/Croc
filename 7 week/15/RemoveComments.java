package Croc.work15;
import java.util.*;
import java.util.function.Predicate;

public class RemoveComments implements BlackListFilter {
    public <T1 extends Iterable<T2>, T2> ArrayList<T2> filterComments(Predicate<T2> predicate, T1 collection) {
        // Создаем список result, копируем в него переданную коллекцию и удаляем нежелательные комментарии
        ArrayList<T2> result = new ArrayList<>();
        for (T2 comment : collection) {
            result.add(comment);
        }
        result.removeIf(predicate);
        return result;
    }
}

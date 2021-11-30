package Croc.work15;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public interface BlackListFilter {

    default <T> ArrayList filterComments(Predicate<T> predicate, Iterable<T> collection){
        ArrayList<T> result = new ArrayList<>();
        for (T comment : collection) {
            if(!predicate.test(comment)){
                result.add(comment);
            }
        }
        return result;
    };
}

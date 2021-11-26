package Croc.work15;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public interface BlackListFilter {
    default <T1 extends Iterable<T2>, T2> ArrayList<T2> filterComments(Predicate<T2> predicate, T1 collection){
        return new ArrayList();
    };
}

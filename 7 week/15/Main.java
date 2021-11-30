package Croc.work15;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Predicate;

public class Main {
    public static void main(String[] args) {
        RemoveComments removeComments = new RemoveComments();
        List<String> comments = new LinkedList<>();
        comments.add("Я не против убить его");
        comments.add("Тебя повесить мало))0)");
        comments.add("Вау! классно!!!");
        comments.add("Тебя повесить мало))0)");
        comments.add("Тебя повесить мало))0)");
        comments.add("Стоит более детально изучить тему...");
        comments.add("Я не против убить его");
        Predicate<String> predicate = s -> (s.indexOf("убить") != -1) || (s.indexOf("повесить") != -1);
        System.out.println(removeComments.filterComments(predicate, comments));
    }
}

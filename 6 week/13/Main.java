package Croc.work13;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

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
        Set<String> blackList = new HashSet<>();
        blackList.add("убить");
        blackList.add("повесить");
        System.out.println(comments);
        removeComments.filterComments(comments,blackList);
        System.out.println(comments);
    }
}

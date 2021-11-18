package Croc.work13;

import java.util.*;

public class RemoveComments implements BlackListFilter{
    public static void filterComments(List<String> comments, Set<String> blackList){
        // Проходимся по списку комментариев и проверяем вхождение слов из blackList в каждом комментарии
        for(int i = 0; i<comments.size(); i++){
            for(String word: blackList){
                if(comments.get(i).toLowerCase(Locale.ROOT).indexOf(word)!=-1){
                    comments.remove(comments.get(i));
                }
            }
        }
    }

    public static void main(String[] args) {
        List<String> comments = new LinkedList<>();
        comments.add("Я не против убить его");
        comments.add("Вау! классно!!!");
        comments.add("Стоит более детально изучить тему...");
        comments.add("Тебя повесить мало))0)");
        Set<String> blackList = new HashSet<>();
        blackList.add("убить");
        blackList.add("повесить");
        System.out.println(comments);
        filterComments(comments,blackList);
        System.out.println(comments);
    }
}

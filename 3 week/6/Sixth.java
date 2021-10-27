import java.lang.String;

public class Fifth {
    private static String removeJavaComments(String code) {
        // С помощью регулярных выражений находим комментарии и заменяем их пустой строкой
        // Сначала удаляем однострочные, потом блочные, потом многострочные
        String oneLineDel = code.replaceAll("//.*(?=\\n)", "");
        String blockOneLineDel = oneLineDel.replaceAll("/*\\*.*\\*/", "");
        String fullFiltered = blockOneLineDel.replaceAll("[*/*].*(?=\\n)", "");
        return fullFiltered;
    }
    public static void main(String[] args) {
        String source = "/*\n" +
                " * My first ever program in Java!\n" +
                " */\n" +
                "class Hello { // class body starts here \n" +
                "  \n" +
                "  /* main method */\n" +
                "  public static void main(String[] args/* we put command line arguments here*/) {\n" +
                "    // this line prints my first greeting to the screen\n" +
                "    System.out.println(\"Hi!\"); // :)\n" +
                "  }\n" +
                "} // the end\n" +
                "// to be continued...\n";
        String noComments = removeJavaComments(source);
        System.out.println(noComments);
    }
}

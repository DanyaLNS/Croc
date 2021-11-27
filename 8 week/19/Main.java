package Croc.work19;

import javax.swing.*;
// Вывожу приветствие на gui с помощью swing
public class Main extends JFrame {
    public Main(){
        // Устанавливаю название окна и операцию закрытия
        super("Croc19");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        // Создаю приветственную надпись и добавляю её на gui
        JLabel hello = new JLabel("Hello, world!!!");
        getContentPane().add(hello);
        // Устанавливаю разрешение окна и видимость
        setSize(100,100);
        setVisible(true);
    }
    public static void main(String[] args) {
        Main gui = new Main();
    }
}

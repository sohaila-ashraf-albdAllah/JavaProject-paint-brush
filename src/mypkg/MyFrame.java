package mypkg;

import javax.swing.*;

public class MyFrame {

    public static void main(String[] args) {

        JFrame f = new JFrame();
        MyPanel ls = new MyPanel();
        f.setTitle("Paint Brush");
        f.setContentPane(ls);
        f.setSize(700,400);
        f.setVisible(true);
    }
}

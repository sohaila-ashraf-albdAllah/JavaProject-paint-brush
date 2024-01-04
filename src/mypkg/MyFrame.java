package mypkg;

import javax.swing.*;

public class MyFrame {

    public static void main(String[] args) {

        JFrame f=new JFrame();
        MyPanel ls=new MyPanel();
        f.setTitle("paint brush");
        f.setContentPane(ls);
        f.setSize(600,300);
        f.setVisible(true);
    }


}

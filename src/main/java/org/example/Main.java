package org.example;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");


        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Life");



        TitleScene titleScene = new TitleScene();
        window.add(titleScene);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);


    }
}
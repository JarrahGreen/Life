package org.example;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Life");
        window.addKeyListener(SceneManager.getSceneManager());

        TitleScene titleScene = new TitleScene();
        window.add(titleScene);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }
}
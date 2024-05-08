package org.example;

import org.example.scenes.SceneManager;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {
    private final SceneManager sceneManager;
    boolean running = true;
    double FPS = 60;
    public GamePanel(){
        this.sceneManager = SceneManager.getSceneManager();
        this.setPreferredSize(new Dimension(1920,800));
        this.setDoubleBuffered(true);
        this.setFocusable(true);
        this.addKeyListener(sceneManager);
    }

    public void run() {
        double drawInterval = 1000000000D / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while (running) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;

            if (delta >= 1) {
                update(delta);
                repaint();
                delta--;
            }
        }
    }

    public void update(double dt) {
        sceneManager.update(dt);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        sceneManager.draw(g);
    }
}

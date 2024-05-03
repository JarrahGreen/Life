package org.example;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {
    private final SceneManager sceneManager;
    double FPS = 60;
    public GamePanel(){
        this.sceneManager = SceneManager.getSceneManager();
        this.setPreferredSize(new Dimension(1920,1080));
        this.setDoubleBuffered(true);
        this.setFocusable(true);
        this.addKeyListener(sceneManager);
    }

    @Override
    public void run() {
        double drawInterval = 1000000000D / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while (Frame.getFrames() != null) {
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

    private void update(double dt) {
        sceneManager.update(dt);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        sceneManager.draw(g);
    }
}

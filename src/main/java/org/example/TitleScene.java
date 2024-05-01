package org.example;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class TitleScene extends Scene {
    private final BufferedImage TitleScreen;

    private final SceneManager sceneManager;

    TitleScene() {
        try {
            TitleScreen = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("C:/Users/dylan/OneDrive - West Herts College/IntelliJ IDEA/Life/TitleScreen.png")));
        } catch (IOException e) {
            System.out.println("Fail");
            throw new RuntimeException(e);
        }
    }

    public void draw(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(TitleScreen, 0, 0, null);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        sceneManager.draw(g);
    }


    }
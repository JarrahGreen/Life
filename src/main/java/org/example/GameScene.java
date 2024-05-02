package org.example;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class GameScene extends Scene{
    private final BufferedImage GameScreen;
    GameScene() {
        try {
            GameScreen = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/GameScreen.png")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        this.setPreferredSize(new Dimension(1920, 1080));
    }


    public void draw(Graphics g) {
        System.out.println("drawing game scene");
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(GameScreen, 0, 0, null);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        SceneManager.getSceneManager().draw(g);
    }
}

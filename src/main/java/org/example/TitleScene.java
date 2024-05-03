package org.example;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class TitleScene extends Scene {
    private final BufferedImage TitleScreen;

    TitleScene() {
        try {
            TitleScreen = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/TitleScreen.png")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        this.setPreferredSize(new Dimension(1920, 1080));

    }

    public void draw(Graphics g) {
        System.out.println("drawing title screen");
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(TitleScreen, 0, 0, null);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            SceneManager.getSceneManager().setActiveScene(new GameScene());
        }
    }
}
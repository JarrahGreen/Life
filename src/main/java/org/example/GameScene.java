package org.example;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class GameScene extends Scene{
    private final BufferedImage GameScreen, Player;
    int playerX = 500;
    int playerY = 500;


    GameScene() {
        try {
            GameScreen = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/GameScreen.png")));
            Player = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Player.png")));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        this.setPreferredSize(new Dimension(1920, 1080));
    }


    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_ENTER: {
                SceneManager.getSceneManager().setActiveScene(new TitleScene());
            }
            case KeyEvent.VK_RIGHT: {
                playerX++;
            }
            case KeyEvent.VK_LEFT: {
                playerX--;
            }
        }
    }

    public void update(double dt) {
        //repaint();
    }

    public void draw(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(GameScreen, 0, 0, null);
        g2.drawImage(Player, playerX, playerY, null);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        SceneManager.getSceneManager().draw(g);
    }
}

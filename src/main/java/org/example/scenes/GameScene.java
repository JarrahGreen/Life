package org.example.scenes;

import org.example.objects.Collider;
import org.example.objects.Collision;
import org.example.objects.GameObject;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.*;


public class GameScene extends Scene {
    static final int ACCELERATION = 4;
    static final int MAX_SPEED = 20;
    static final float STOPPING_FACTOR = 1.2f;
    static final int GRAVITY = 4;
    static final int JUMP_STRENGTH = 40;
    static final int TERMINAL_VELOCITY = 20;

    private final BufferedImage GameScreen, Player;
    int playerX = 500;
    int playerY = 500;
    int jumpCount = 0;
    double velX = 0;
    double velY = 0;
    boolean canJump = true;
    HashMap<Integer, Boolean> heldKeys;
    Collider testCollider;


    GameScene() {
        heldKeys = new HashMap<>();
        testCollider = new Collider(900, 350, 400, 150);
        try {
            GameScreen = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/GameScreen.png")));
            Player = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Player.png")));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        heldKeys.put(e.getKeyCode(), true);
        if (e.getKeyCode() == KeyEvent.VK_SPACE && canJump) {
            jumpCount = 60;
            velY = -JUMP_STRENGTH;
            canJump = false;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        heldKeys.put(e.getKeyCode(), false);
    }

    public void update(double dt) {
        int current_gravity = GRAVITY;
        if (heldKeys.getOrDefault(KeyEvent.VK_ENTER, false)) {
            SceneManager.getSceneManager().setActiveScene(new TitleScene());
        }
        if (heldKeys.getOrDefault(KeyEvent.VK_RIGHT, false)) {
            velX = Math.min(velX + ACCELERATION, MAX_SPEED);
        }
        if (heldKeys.getOrDefault(KeyEvent.VK_LEFT, false)) {
            velX = Math.max(velX - ACCELERATION, -MAX_SPEED);
        }
        if (heldKeys.getOrDefault(KeyEvent.VK_SPACE, false)) {
            jumpCount = Math.max(jumpCount - 1, 0);
            if (jumpCount > 0) {
                current_gravity = GRAVITY - jumpCount / 20;
            }
        }

        velY = Math.min(velY + current_gravity, TERMINAL_VELOCITY);

        velX /= STOPPING_FACTOR;

        for (GameObject object : objects) {
            var hit = object.collider.checkCollision(playerX, playerY, velX, velY, 64, 64);
            if (hit != Collision.NONE) {
                switch (hit) {
                    case RIGHT, LEFT: {
                        velX = 0;
                        break;
                    }
                    case FLOOR: {
                        playerY = testCollider.y - 64;
                    }
                    case CEILING: {
                        velY = 0;
                        break;
                    }
                }
                if (hit == Collision.FLOOR) {
                    canJump = true;
                }

                break;
            }
        }

        playerX += (int) velX;
        playerY += (int) velY;

        if(playerY > 650) {
            playerY = 650;
            canJump = true;
        }
    }

    public void draw(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(GameScreen, 0, 0, null);
        g2.drawImage(Player, playerX, playerY, null);
        g2.drawRect(testCollider.x, testCollider.y, testCollider.width, testCollider.height);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        SceneManager.getSceneManager().draw(g);
    }
}

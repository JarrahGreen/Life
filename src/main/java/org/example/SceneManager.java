package org.example;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class SceneManager implements KeyListener {
    private static SceneManager _instance;
    private Scene activeScene;

    private SceneManager() {
        activeScene = new TitleScene();
    }

    public static SceneManager getSceneManager() {
        if (_instance == null) {
            _instance = new SceneManager();
        }
        return _instance;
    }

    public void setActiveScene(Scene activeScene) {
        this.activeScene = activeScene;
    }

    public void keyPressed(KeyEvent e) {
        activeScene.keyPressed(e);
    }

    public void keyTyped(KeyEvent e) {
        activeScene.keyTyped(e);
    }

    public void keyReleased(KeyEvent e) {
        activeScene.keyReleased(e);
    }



    public void draw(Graphics g) {
        activeScene.draw(g);
    }

}
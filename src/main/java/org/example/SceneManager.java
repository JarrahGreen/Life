package org.example;

import java.awt.*;
import java.awt.event.KeyEvent;

public class SceneManager {
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

    public void draw(Graphics g) {
        activeScene.draw(g);
    }

}

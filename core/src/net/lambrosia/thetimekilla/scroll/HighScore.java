package net.lambrosia.thetimekilla.scroll;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector3;

import net.lambrosia.thetimekilla.screens.MenuScreen;

/**
 * Created by dsz on 17/04/16.
 */

public class HighScore {

    private int width, height;
    public static float x, y, yDest;
    private Vector3 cameraUnpr;
    private InputProcessor processor;

    public HighScore() {
        width = 279;
        height = 405;
        x = MenuScreen.VIRTUAL_WIDTH / 2 - width / 2 + 16;
        y = MenuScreen.VIRTUAL_HEIGHT + 350;
        yDest = 350;
    }

    public void update(float delta) {
        setY(yDest);
    }

    public void resetGame(float delta) {
        setY(MenuScreen.VIRTUAL_HEIGHT + 350);
    }

    public static float getY() {
        return y;
    }

    public static void setY(float y) {
        HighScore.y = y;
    }

    public static float getX() {
        return x;
    }
    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
    public static float getyDest() {
        return yDest;
    }

}
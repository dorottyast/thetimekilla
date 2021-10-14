package net.lambrosia.thetimekilla.scroll;

import net.lambrosia.thetimekilla.screens.MenuScreen;

/**
 * Created by dsz on 17/04/16.
 */
public class How {

    private int width, height;
    public static float x, y, deltaTime, deltaY, yDestination, upperDest;

    public How() {
        width = 390;
        height = 712;
        x = MenuScreen.VIRTUAL_WIDTH / 2 - 390 / 2;
        upperDest = MenuScreen.VIRTUAL_HEIGHT+350;
        y = upperDest;
        deltaTime = 2.7f;

        deltaY = 0;
        yDestination = MenuScreen.VIRTUAL_HEIGHT / 2 - height / 2;
    }

    public void update(float delta) {
        if (y > yDestination) {
            deltaY += .5 * deltaTime;
            y -= deltaTime * deltaTime * 9.81 * 0.5;

        }
    }

    public void resetGame(float delta) {
        if(y < MenuScreen.VIRTUAL_HEIGHT) {
            deltaY -= .5 * deltaTime;
            y += deltaTime * deltaTime * 9.81 * 0.85;
        }else {
            setY(upperDest);
        }
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public static float getyDestination() {
        return yDestination;
    }

    public static float getUpperDest() {
        return upperDest;
    }
}
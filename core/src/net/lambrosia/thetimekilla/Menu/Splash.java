package net.lambrosia.thetimekilla.Menu;

import net.lambrosia.thetimekilla.screens.MenuScreen;

public class Splash {

    private int width, height;
    private float x, y, deltaTime, deltaY, yDestination;

    public Splash() {
        width = 285;
        height = 180;
        x = MenuScreen.VIRTUAL_WIDTH / 2 - width / 2;
        y = MenuScreen.VIRTUAL_HEIGHT+500;
        deltaTime = 2.7f;

        deltaY = 0;
        yDestination = 370;
    }

    public void update(float delta) {
        if (y > yDestination) {
            deltaY += .5 * deltaTime;
            y -= deltaTime * deltaTime * 9.81 * 0.7;

        }
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }
}

package net.lambrosia.thetimekilla.scroll;

import com.badlogic.gdx.Gdx;

public class Scroll {

    private float x, y, yEnd;
    private int width, height;
    private boolean reset;
    public static float speed;

    public Scroll(float x, float y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        speed = 400;
        yEnd = y + height;
    }

    public void update(float delta) {
        y -= speed * Gdx.graphics.getDeltaTime();
        if (y + height < 0) {
            reset = true;
        }
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public void reset(float resetY) {
        y = resetY;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public boolean isReset() {
        return reset;
    }

    public float getYEnd() {
        return y + height;
    }
    public float getSpeed() {
        return speed;
    }
}


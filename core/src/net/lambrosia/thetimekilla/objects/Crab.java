package net.lambrosia.thetimekilla.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import net.lambrosia.thetimekilla.Menu.Menu;
import net.lambrosia.thetimekilla.screens.BeachScreen;
import net.lambrosia.thetimekilla.screens.MenuScreen;
import net.lambrosia.thetimekilla.world.Renderer;

/**
 * Created by dsz on 17/04/16.
 */
public class Crab {
    private Vector2 position;
    private int width, height;
    private float speed = 800;

    private Circle crab1, crab2;
    private Rectangle crab3, crab4;

    public Crab(float x, float y, int widht, int height) {
        position = new Vector2(x, y);
        crab1 = new Circle();
        crab2 = new Circle();
        crab3 = new Rectangle();
        crab4 = new Rectangle();
    }

    public void update(float delta) {
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            setX(getX() - speed * delta);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            setX(getX() + speed * delta);
        }

        if(Gdx.input.isPeripheralAvailable(Input.Peripheral.Accelerometer)){
            position.x-=Gdx.input.getAccelerometerX()*speed*0.7*delta*0.72;
        }

        if (getX() < BeachScreen.crabNullX) {
            setX(BeachScreen.crabNullX);
        }
        if (getX() > MenuScreen.VIRTUAL_WIDTH-BeachScreen.crabX-1) {
            setX(MenuScreen.VIRTUAL_WIDTH-BeachScreen.crabX-1);
        }

        crab1.set(getX() + 7, getY() + 54, 9);
        crab2.set(getX() + 55, getY() + 54, 8);
        crab3.set(getX() + 5, getY() + 12, 54, 43);
        crab4.set(getX() + 5, getY() + 24, 52, 16);
    }

    public void left(float delta) {
        setX(getX() - speed * delta);
    }

    public void right(float delta) {
        setX(getX() + speed * delta);
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public float getX() {
        return position.x;
    }

    public float getY() {
        return position.y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void setX(float x) {
        this.position.x = x;
    }

    public void setY(float y) {
        this.position.y = y;
    }

    public Circle getCrab1() {
        return crab1;
    }

    public Circle getCrab2() {
        return crab2;
    }

    public Rectangle getCrab3() {
        return crab3;
    }

    public Rectangle getCrab4() {
        return crab4;
    }

    public float getSpeed() {
        return speed;
    }
}

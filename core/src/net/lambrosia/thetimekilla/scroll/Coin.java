package net.lambrosia.thetimekilla.scroll;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

import net.lambrosia.thetimekilla.screens.MenuScreen;
import net.lambrosia.thetimekilla.world.Renderer;

public class Coin {
    private float x, y, speed;
    private int width, height;
    private boolean visible = true;
    private Circle coin1, coin2, coin3;
    private ShapeRenderer s;

    private float xWidth;

    private Vector2 cameraUnpr, cameraUnpr2;

    public Coin() {
        width = 43;
        height = 28;
        speed = 400;
        xWidth = Gdx.graphics.getWidth()-(43/MenuScreen.VIRTUAL_WIDTH*Gdx.graphics.getWidth());
        Gdx.app.log(""+xWidth, "");
        cameraUnpr=new Vector2(Gdx.graphics.getWidth()-((43/(MenuScreen.VIRTUAL_WIDTH-2))*Gdx.graphics.getWidth()), 0);
        cameraUnpr2 = new Vector2(Gdx.graphics.getWidth()-(MenuScreen.VIRTUAL_WIDTH/MenuScreen.VIRTUAL_WIDTH*Gdx.graphics.getWidth()), 0);
        Renderer.viewport.unproject(cameraUnpr);
        Renderer.viewport.unproject(cameraUnpr2);
        x = MathUtils.random(cameraUnpr.x, cameraUnpr2.x);
        y = MenuScreen.VIRTUAL_HEIGHT;
        coin1 = new Circle();
        coin2 = new Circle();
        coin3 = new Circle();
        s = new ShapeRenderer();
    }

    public void update(float delta) {
        y -= speed * Gdx.graphics.getDeltaTime();
        if (y < 0 - height) {
            visible = false;
        }
        coin1.set(x + 12, y + 14, 13);
        coin2.set(x + 31, y + 14, 13);
        coin3.set(x + 22, y + 14, 15);
    }
    public void slowDown(){
            setSpeed(400/3);
    }

    public Circle getCoin1() {
        return coin1;
    }
    public Circle getCoin2() {
        return coin2;
    }public Circle getCoin3() {
        return coin3;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public float getX() {
        return x;
    }
    public float getY() {
        return y;
    }
    public float getYEnd(){
        return y+height;
    }
    public float getSpeed() {
        return speed;
    }

    public int getWidth() {
        return width;
    }
    public int getHeight() {
        return height;
    }

    public boolean isVisible() {
        return visible;
    }
    public void setVisible(boolean visible) {
        this.visible = visible;
    }
}
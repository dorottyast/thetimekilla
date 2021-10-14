package net.lambrosia.thetimekilla.scroll;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;

import net.lambrosia.thetimekilla.TheTimeKilla;
import net.lambrosia.thetimekilla.screens.BeachScreen;
import net.lambrosia.thetimekilla.screens.MenuScreen;

/**
 * Created by dsz on 17/04/16.
 */
public class GameOverTab {

    private int width, height;
    public static int got;
    private float x, deltaTime, deltaY, yDestination, upperDest;
    private Rectangle  sound;
    public static float y;
    public GameOverTab() {
        width = 390;
        height = 430;
        x = MenuScreen.VIRTUAL_WIDTH / 2 - width / 2;
        upperDest = MenuScreen.VIRTUAL_HEIGHT + 350 ;
        y = upperDest;
        deltaTime = 2.7f;

        deltaY = 0;
        if(TheTimeKilla.adBinary==1) {
            if((float) Gdx.graphics.getWidth()/Gdx.graphics.getHeight()>(float)0.72) {
                sound = new Rectangle(138, 0, 78, 134);
            }else{
                sound = new Rectangle(138, 0, 78, 82);
            }
        }else if(TheTimeKilla.adBinary==0 || TheTimeKilla.adBinary==2){
            if((float) Gdx.graphics.getWidth()/Gdx.graphics.getHeight()>(float)0.72) {
                sound = new Rectangle(138, 800-134, 78, 134);
            }else {
                sound = new Rectangle(138, 800 - 92, 78, 92);
            }
        }
        if((float) Gdx.graphics.getWidth()/Gdx.graphics.getHeight()>(float)0.72) {
            yDestination = MenuScreen.VIRTUAL_HEIGHT / 2 - height / 2+46;
        }else{
            yDestination =MenuScreen.VIRTUAL_HEIGHT / 2 - height / 2 +90;
        }
    }

    public void update(float delta) {
        if (y > yDestination) {
            deltaY += .5 * deltaTime;
            y -= deltaTime * deltaTime * 9.81 * 0.5 + deltaTime * 0;

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

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public Rectangle getSound() {
        return sound;
    }

    public float getyDestination() {
        return yDestination;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getDeltaTime() {
        return deltaTime;
    }

    public float getDeltaY() {
        return deltaY;
    }
}

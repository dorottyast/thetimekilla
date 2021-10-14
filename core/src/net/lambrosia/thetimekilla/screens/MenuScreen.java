package net.lambrosia.thetimekilla.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Scaling;

import net.lambrosia.thetimekilla.Menu.Menu;
import net.lambrosia.thetimekilla.TheTimeKilla;
import net.lambrosia.thetimekilla.scroll.HighScore;
import net.lambrosia.thetimekilla.scroll.How;

/**
 * Created by dsz on 17/04/16.
 */
public class MenuScreen implements Screen {

    private float dt;
    public static TheTimeKilla game;
    Menu menu;

    public static float VIRTUAL_WIDTH = 480;
    public static float VIRTUAL_HEIGHT = 800;

    private InputMultiplexer processor;

    public static How how;
    public static HighScore highScore;

    public MenuScreen(TheTimeKilla game) {
        dt = 0;
        this.game = game;
        menu = new Menu();
    }

    @Override
    public void show() {
        // TODO Auto-generated method stub

    }

    @Override
    public void render(float delta) {
        dt += delta;
        menu.update(dt);
        menu.render(dt);

    }

    @Override
    public void resize(int width, int height) {
        menu.getViewport().update(width, height);
        menu.getCamera().position.set(menu.getCamera().viewportWidth / 2,
                menu.getCamera().viewportHeight / 2, 0);
        menu.getB().setProjectionMatrix(menu.getCamera().combined);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}



package net.lambrosia.thetimekilla.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Vector3;

import net.lambrosia.thetimekilla.TheTimeKilla;
import net.lambrosia.thetimekilla.scroll.GameOverTab;
import net.lambrosia.thetimekilla.scroll.ObstacleScroller;
import net.lambrosia.thetimekilla.scroll.Scroll;
import net.lambrosia.thetimekilla.textures.Textures;
import net.lambrosia.thetimekilla.world.Renderer;
import net.lambrosia.thetimekilla.world.World;

import javafx.collections.ObservableArray;

/**
 * Created by dsz on 17/04/16.
 */
public class BeachScreen implements Screen {

    private World world;
    private Renderer renderer;
    private float dt;

    private final float VIRTUAL_HEIGHT = 800;
    private final float VIRTUAL_WIDTH = 480;

    public static boolean biggerScreen, ipad;
    public static int soundY, crabX, crabNullX, got;

    private TheTimeKilla game;
    public BeachScreen(TheTimeKilla game) {
        world = new World();
        renderer = new Renderer(world);
        dt = 0;
        this.game = game;
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        world.update(delta);
        dt += delta;
        renderer.render(dt);

        if (ObstacleScroller.setScreen) {
            game.setScreen(TheTimeKilla.menuScreen);
            ObstacleScroller.setScreen = false;
        }

    }

    @Override
    public void resize(int width, int height) {
        renderer.getViewport().update(width, height);
        renderer.camera.position.set(renderer.camera.viewportWidth / 2,
                renderer.camera.viewportHeight / 2, 0);
        renderer.getB().setProjectionMatrix(renderer.getCamera().combined);
        if((float) Gdx.graphics.getWidth()/Gdx.graphics.getHeight()<(float)0.62){
            if(TheTimeKilla.adBinary==0 || TheTimeKilla.adBinary==2){
                soundY = 800-16-30;
            }else {
                soundY = 16;
            }
            crabX = 65+4;
            crabNullX = 7;
            GameOverTab.got = 90;
        }else if((float) Gdx.graphics.getWidth()/Gdx.graphics.getHeight()>(float)0.62 &&
                (float) Gdx.graphics.getWidth()/Gdx.graphics.getHeight()<(float)0.72){
            if(TheTimeKilla.adBinary==0 || TheTimeKilla.adBinary==2){
                soundY = 800-50-30;
            }else {
                soundY = 50;
            }
            crabX = 65;
            crabNullX = 0;
            GameOverTab.got= 90;
        }else if((float) Gdx.graphics.getWidth()/Gdx.graphics.getHeight()>(float)0.72){
            if(TheTimeKilla.adBinary==0 || TheTimeKilla.adBinary==2){
                soundY = 800-90-30;
            }else {
                soundY = 90;
            }
            crabX = 63;
            crabNullX = 0;
            ipad = true;
            GameOverTab.got = 60;
        }
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

    public Renderer getRenderer() {
        return renderer;
    }
}

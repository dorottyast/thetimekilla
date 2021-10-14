package net.lambrosia.thetimekilla.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import net.lambrosia.thetimekilla.Menu.Splash;
import net.lambrosia.thetimekilla.textures.MenuTextures;

/**
 * Created by dsz on 17/04/16.
 */
public class SplashScreen implements Screen {

    private OrthographicCamera camera;
    private Viewport viewport;

    private ShapeRenderer s;
    private SpriteBatch b;
    private Splash splash;

    public SplashScreen(){
        camera = new OrthographicCamera(MenuScreen.VIRTUAL_WIDTH, MenuScreen.VIRTUAL_HEIGHT);
        viewport = new FitViewport(MenuScreen.VIRTUAL_WIDTH, MenuScreen.VIRTUAL_HEIGHT, camera);
        viewport.apply();
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
        b = new SpriteBatch();
        b.setProjectionMatrix(camera.combined);
        splash = new Splash();
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        splash.update(delta);
        Gdx.gl.glClearColor(255 / 255.0f, 255 / 255.0f, 255 / 255.0f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        b.begin();
        b.draw(MenuTextures.libGDX, splash.getX(), splash.getY());
        b.end();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
        camera.position.set(camera.viewportWidth / 2,
                camera.viewportHeight / 2, 0);
        b.setProjectionMatrix(camera.combined);
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
        b.dispose();
    }
}

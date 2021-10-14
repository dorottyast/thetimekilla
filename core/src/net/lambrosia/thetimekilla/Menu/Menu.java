package net.lambrosia.thetimekilla.Menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import net.lambrosia.thetimekilla.screens.MenuScreen;
import net.lambrosia.thetimekilla.scroll.HighScore;
import net.lambrosia.thetimekilla.scroll.How;
import net.lambrosia.thetimekilla.scroll.ObstacleScroller;
import net.lambrosia.thetimekilla.textures.MenuTextures;
import net.lambrosia.thetimekilla.textures.SoundEffects;
import net.lambrosia.thetimekilla.textures.Textures;

/**
 * Created by dsz on 17/04/16.
 */
public class Menu {

    private OrthographicCamera camera;
    private Viewport viewport;

    private ShapeRenderer s;
    private SpriteBatch b;

    private MenuTextures mt;

    private How how;
    private HighScore highScore;
    private Vector3 cameraUnpr;
    private InputProcessor processor;
    private boolean updateHow, resetHow;

    public static boolean setScreen;

    public Menu() {
        camera = new OrthographicCamera(MenuScreen.VIRTUAL_WIDTH, MenuScreen.VIRTUAL_HEIGHT);
        viewport = new FitViewport(MenuScreen.VIRTUAL_WIDTH, MenuScreen.VIRTUAL_HEIGHT, camera);
        viewport.apply();
        camera.position.set(camera.viewportWidth/2, camera.viewportHeight/2, 0);
        b = new SpriteBatch();
        b.setProjectionMatrix(camera.combined);
        mt = new MenuTextures();
        how = new How();
        highScore = new HighScore();
        cameraUnpr = new Vector3();
    }

    public void update(float delta) {
        if(updateHow && how.getY()> how.getyDestination()) {
            how.update(Gdx.graphics.getDeltaTime());
        }else if(resetHow && how.getY()< how.getUpperDest()){
            how.resetGame(Gdx.graphics.getDeltaTime());
        }
        Gdx.input.setInputProcessor(processor);

        if (Gdx.input.justTouched()) {
            cameraUnpr.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            viewport.unproject(cameraUnpr);
            if (cameraUnpr.x > 149 && cameraUnpr.x < 230 && cameraUnpr.y > 391
                    && cameraUnpr.y < 391 + 82
                    && highScore.getY() != highScore.getyDest()
                    && how.y > MenuScreen.VIRTUAL_HEIGHT
                    ) {
                updateHow = true;
                resetHow = false;
                if (ObstacleScroller.soundOn) {
                    SoundEffects.click.play(0.7f);
                }
            } else if (how.getY() < MenuScreen.VIRTUAL_HEIGHT &&
                    cameraUnpr.x > 0 && cameraUnpr.y > 0
                    ) {
                resetHow = true;
                updateHow = false;
                if (ObstacleScroller.soundOn) {
                    SoundEffects.click.play(0.7f);
                }
            } else if (cameraUnpr.x > 253 && cameraUnpr.x < 253 + 110
                    && cameraUnpr.y > 391
                    && cameraUnpr.y < 391 + 82
                    && how.getY() > MenuScreen.VIRTUAL_HEIGHT) {
                highScore.update(delta);
                if (ObstacleScroller.soundOn) {
                    SoundEffects.click.play(0.7f);
                }
            } else if (cameraUnpr.x > highScore.getX() + highScore.getWidth()
                    - 65
                    && cameraUnpr.x < highScore.getX() + highScore.getWidth()
                    + 20 && cameraUnpr.y > 440 && cameraUnpr.y < 520) {
                highScore.resetGame(delta);
                if (ObstacleScroller.soundOn) {
                    SoundEffects.click.play(0.7f);
                }
            } else if (cameraUnpr.y < 375 && how.getY()>MenuScreen.VIRTUAL_HEIGHT) {
                MenuScreen.game.setScreen(MenuScreen.game.beachScreen);
                setScreen = true;
                if (highScore.getY() != MenuScreen.VIRTUAL_HEIGHT + 350) {
                    highScore.setY(MenuScreen.VIRTUAL_HEIGHT + 350);
                }
                if (how.getY() != MenuScreen.VIRTUAL_HEIGHT + 350) {
                    how.setY(MenuScreen.VIRTUAL_HEIGHT + 350);
                }
                if (ObstacleScroller.soundOn) {
                    SoundEffects.click.play(0.7f);
                }
                if(ObstacleScroller.ch){
                    MenuScreen.game.beachScreen.resume();
                }
            }

        }

    }

    public void render(float delta) {
        Gdx.gl.glClearColor(23/255.0f, 28/255.0f, 52/255.0f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        b.begin();
        b.draw(mt.menu, 0, 0);
        b.draw(mt.howTab, how.getX(), how.getY());
        b.draw(MenuTextures.highScoreTab, highScore.getX(),
                highScore.getY());
        Textures.font.setColor(227/255.0f, 105/255.0f, 100/255.0f, 1f);
        Textures.font.getData().setScale(1.2f);
        if(Textures.getHighScore()<10 && Textures.getHighScore() > 0){
            Textures.font.draw(b, "" + Textures.getHighScore(),
                    highScore.getX() + 168, highScore.getY() + 72);
        }
        if(Textures.getHighScore()<100 &&Textures.getHighScore() > 9){
            Textures.font.draw(b, "" + Textures.getHighScore(),
                    highScore.getX() + 148, highScore.getY() + 72);
        }
        if(Textures.getHighScore()<1000 && Textures.getHighScore() > 99){
            Textures.font.draw(b, "" + Textures.getHighScore(),
                    highScore.getX() + 134, highScore.getY() + 72);
        }if(Textures.getHighScore()<10000 && Textures.getHighScore() > 999){
            Textures.font.draw(b, "" + Textures.getHighScore(),
                    highScore.getX() + 120, highScore.getY() + 72);
        }if(Textures.getHighScore() == 0){
            Textures.font.draw(b, "-" ,
                    highScore.getX() + 168, highScore.getY() + 72);
        }if(Textures.getHighScore()>10000){
            Textures.font.draw(b, "" + Textures.getHighScore(),
                    highScore.getX() + 106, highScore.getY() + 72);
        }
        b.end();
    }
    public OrthographicCamera getCamera() {
        return camera;
    }

    public SpriteBatch getB() {
        return b;
    }
    public Viewport getViewport() {
        return viewport;
    }
}


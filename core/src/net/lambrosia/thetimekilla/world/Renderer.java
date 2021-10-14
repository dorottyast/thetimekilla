package net.lambrosia.thetimekilla.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import net.lambrosia.thetimekilla.objects.Crab;
import net.lambrosia.thetimekilla.screens.BeachScreen;
import net.lambrosia.thetimekilla.screens.MenuScreen;
import net.lambrosia.thetimekilla.scroll.Background;
import net.lambrosia.thetimekilla.scroll.Coin;
import net.lambrosia.thetimekilla.scroll.GameOverTab;
import net.lambrosia.thetimekilla.scroll.ObstacleScroller;
import net.lambrosia.thetimekilla.scroll.Overlap;
import net.lambrosia.thetimekilla.scroll.Scroll;
import net.lambrosia.thetimekilla.textures.MenuTextures;
import net.lambrosia.thetimekilla.textures.Textures;

import java.util.ArrayList;

/**
 * Created by dsz on 17/04/16.
 */
public class Renderer {

    private Crab crab;
    private Textures textures;

    private ObstacleScroller obstacleScroller;
    private Scroll coconut, stone, star, ball;
    private Background bg1, bg2;
    private Overlap overlap;
    private ArrayList<Coin> coins;

    public World world;
    public static OrthographicCamera camera;
    private SpriteBatch b;

    private GameOverTab gameOver;
    public static Viewport viewport;

    public static boolean facebookReady;

    public Renderer(World world) {
        this.world = world;
        camera = new OrthographicCamera(MenuScreen.VIRTUAL_WIDTH,
                MenuScreen.VIRTUAL_HEIGHT);
        viewport = new FillViewport(MenuScreen.VIRTUAL_WIDTH,MenuScreen.VIRTUAL_HEIGHT, camera);
        viewport.apply();
        camera.position.set(camera.viewportWidth/2 ,
                camera.viewportHeight/2, 0);
        b = new SpriteBatch();
        b.setProjectionMatrix(camera.combined);
        textures = new Textures();
        loadObjects();
    }

    private void loadObjects() {
        crab = world.getCrab();
        obstacleScroller = world.getObstacleScroller();
        overlap = obstacleScroller.getOverlap();
        bg1 = overlap.getBg1();
        bg2 = overlap.getBg2();
        coconut = overlap.getCoconut();
        stone = overlap.getStone();
        star = overlap.getStar();
        ball = overlap.getBall();
        coins = obstacleScroller.getCoins();
    }

    public void render(float dt) {
        Gdx.gl.glClearColor(228 / 255.0f, 213 / 255.0f, 170 / 255.0f, 1f);
        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);
        Textures.font.setColor(10 / 255.0f, 178 / 255.0f, 178 / 255.0f, 1f);
        Textures.font.getData().setScale(0.7f);
        b.begin();
        //b.draw(Textures.bg, 0, world.getY());
        //b.draw(Textures.bg, 0, world.getY2());
        b.draw(Textures.bg, bg1.getX(), bg1.getY());
        b.draw(Textures.bg, bg2.getX(), bg2.getY());
        if (obstacleScroller.overlaps) {

            for (Coin coin : coins) {
                b.draw(MenuTextures.coin, coin.getX(), coin.getY());
            }
            b.draw(MenuTextures.coconut, coconut.getX(), coconut.getY() + 3);
            b.draw(MenuTextures.stone, stone.getX(), stone.getY() + 11);
            b.draw(MenuTextures.star, star.getX(), star.getY() + 6);
            b.draw(MenuTextures.ball, ball.getX(), ball.getY() + 9);
            b.draw(MenuTextures.menuCrabAnimation.getKeyFrame(dt), crab.getX(),
                    crab.getY() + 7);
        } else {
            for (Coin coin : coins) {
                b.draw(Textures.coin, coin.getX(), coin.getY());
            }
            b.draw(Textures.coconut, coconut.getX(), coconut.getY());
            b.draw(Textures.stone, stone.getX(), stone.getY());
            b.draw(Textures.star, star.getX(), star.getY());
            b.draw(Textures.ball, ball.getX(), ball.getY());
            b.draw(Textures.crabAnimation.getKeyFrame(dt), crab.getX(),
                    crab.getY());
        }
        b.draw(MenuTextures.gameOverTab, obstacleScroller.getGameOver().getX(),
                obstacleScroller.getGameOver().getY());
        if (obstacleScroller.overlaps) {

            Textures.font.draw(b, "" + Textures.getHighScore(),
                    obstacleScroller.getGameOver().getX() + 204,
                    obstacleScroller.getGameOver().getY() + 208);
            Textures.font.draw(b, "" + obstacleScroller.getScore(),
                    obstacleScroller.getGameOver().getX() + 206,
                    obstacleScroller.getGameOver().getY() + 258);
        }
        if (obstacleScroller.soundOn) {
                b.draw(MenuTextures.sound, 161, BeachScreen.soundY);
        } else {
                b.draw(MenuTextures.soundOff, 161, BeachScreen.soundY);
        }
        b.draw(MenuTextures.score, 271, BeachScreen.soundY);
        Textures.font.draw(b, "" + obstacleScroller.getScore(), 321, BeachScreen.soundY+19);
        b.draw(MenuTextures.menuDir, obstacleScroller.getGameOver().getX()
                        + obstacleScroller.getGameOver().getWidth() - 50,
                obstacleScroller.getGameOver().getY()
                        + obstacleScroller.getGameOver().getHeight() - 46);
        if (obstacleScroller.overlaps) {
            if (obstacleScroller.isNewHighScore()) {
                b.draw(MenuTextures.newHS, obstacleScroller.getGameOver().getX() + 70, obstacleScroller.getGameOver().getY()
                        + 87*2 +110);
            } else {
                b.draw(MenuTextures.whoops, obstacleScroller.getGameOver().getX() + 131,
                        obstacleScroller.getGameOver().getY() + 87*2 +110);
            }
            if (!ObstacleScroller.facebookClicked) {
                b.draw(MenuTextures.tellYourFriends, obstacleScroller.getGameOver().getX() + 44, obstacleScroller.getGameOver().getY() + 25);
                facebookReady = false;
            }
        }
        if(ObstacleScroller.facebookClicked){
            b.draw(MenuTextures.invite, 115, obstacleScroller.getGameOver().getY() + 25);
            b.draw(MenuTextures.share, 273, obstacleScroller.getGameOver().getY() + 25);
            facebookReady = true;
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

    public ObstacleScroller getObstacleScroller() {
        return obstacleScroller;
    }
}


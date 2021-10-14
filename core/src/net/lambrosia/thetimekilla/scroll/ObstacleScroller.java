package net.lambrosia.thetimekilla.scroll;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

import net.lambrosia.thetimekilla.objects.Crab;
import net.lambrosia.thetimekilla.screens.MenuScreen;
import net.lambrosia.thetimekilla.textures.SoundEffects;
import net.lambrosia.thetimekilla.textures.Textures;
import net.lambrosia.thetimekilla.world.Renderer;
import net.lambrosia.thetimekilla.world.World;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by dsz on 17/04/16.
 */
public class ObstacleScroller {

    private Overlap overlap;
    private Scroll coconut, stone, star, ball;
    private final float D = 208;
    public Intersector i;
    public static boolean overlaps = false;
    private Crab crab;
    public static Coin coin;
    private ArrayList<Coin> coins;
    private Iterator<Coin> it;
    private boolean readyToSpawnStone = false, readyToSpawnStar = false,
            readyToSpawnBall = false, readyToSpawnCoconut;

    private Circle ball1, coconut1, stone1, stone2, stone3, stone4, star1,
            star2, star3, star4, star5, star6, crab1, crab2;
    private Rectangle stone5, crab3, crab4, coconut2;

    public static int score;
    private SoundEffects se;

    public static boolean soundOn;
    public Vector2 touchPoint, sound;
    private Vector3 cameraUnpr;

    private GameOverTab gameOver;

    private InputMultiplexer processor;

    public static boolean setScreen = false, ch = false;

    private boolean newHighScore = false;
    public static boolean facebookClicked, invite, share;
    public static int ad = 0, ads = 0;

    public static boolean show, resetGO;

    public ObstacleScroller() {

        overlap = new Overlap();
        coconut = overlap.getCoconut();
        stone = overlap.getStone();
        star = overlap.getStar();
        ball = overlap.getBall();
        crab = World.getCrab();

        coconut1 = overlap.getCoconut1();
        coconut2 = overlap.getCoconut2();

        stone1 = overlap.getStone1();
        stone2 = overlap.getStone2();
        stone3 = overlap.getStone3();
        stone4 = overlap.getStone4();
        stone5 = overlap.getStone5();

        star1 = overlap.getStar1();
        star2 = overlap.getStar2();
        star3 = overlap.getStar3();
        star4 = overlap.getStar4();
        star5 = overlap.getStar5();
        star6 = overlap.getStar6();

        ball1 = overlap.getBall1();

        crab1 = crab.getCrab1();
        crab2 = crab.getCrab2();
        crab3 = crab.getCrab3();
        crab4 = crab.getCrab4();

        coins = new ArrayList<Coin>();

        score = 0;
        se = new SoundEffects();

        gameOver = new GameOverTab();
        touchPoint = new Vector2();
        cameraUnpr = new Vector3();
        soundOn = true;
        sound = new Vector2(0, 30);

    }

    public void update(float delta) {
        overlap.update(delta);

        if(resetGO){
            gameOver.resetGame(Gdx.graphics.getDeltaTime());
        }

        if (i.overlaps(crab1, coconut1) || i.overlaps(crab2, coconut1)
                || i.overlaps(crab1, coconut2) || i.overlaps(crab2, coconut2)
                || i.overlaps(crab3, coconut2) || i.overlaps(crab4, coconut2)
                || i.overlaps(crab1, stone1) || i.overlaps(crab1, stone2)
                || i.overlaps(crab1, stone3) || i.overlaps(crab1, stone4)
                || i.overlaps(crab1, stone5) || i.overlaps(crab2, stone1)
                || i.overlaps(crab2, stone2) || i.overlaps(crab2, stone3)
                || i.overlaps(crab2, stone4) || i.overlaps(crab2, stone5)
                || i.overlaps(crab1, star1) || i.overlaps(crab1, star2)
                || i.overlaps(crab1, star3) || i.overlaps(crab1, star4)
                || i.overlaps(crab1, star5) || i.overlaps(crab1, star6)
                || i.overlaps(crab2, star1) || i.overlaps(crab2, star2)
                || i.overlaps(crab2, star3) || i.overlaps(crab2, star4)
                || i.overlaps(crab2, star5) || i.overlaps(crab2, star6)
                || i.overlaps(ball1, crab1) || i.overlaps(ball1, crab2)
                || i.overlaps(coconut1, crab3) || i.overlaps(stone1, crab3)
                || i.overlaps(stone2, crab3) || i.overlaps(stone3, crab3)
                || i.overlaps(stone4, crab3) || i.overlaps(stone5, crab3)
                || i.overlaps(star1, crab3) || i.overlaps(star2, crab3)
                || i.overlaps(star3, crab3) || i.overlaps(star4, crab3)
                || i.overlaps(star5, crab3) || i.overlaps(star6, crab3)
                || i.overlaps(ball1, crab3) || i.overlaps(coconut1, crab3)
                || i.overlaps(stone1, crab4) || i.overlaps(stone2, crab4)
                || i.overlaps(stone3, crab4) || i.overlaps(stone4, crab4)
                || i.overlaps(stone5, crab4) || i.overlaps(star1, crab4)
                || i.overlaps(star2, crab4) || i.overlaps(star3, crab4)
                || i.overlaps(star4, crab4) || i.overlaps(star5, crab4)
                || i.overlaps(star6, crab4) || i.overlaps(ball1, crab4)) {
            if (!overlaps && soundOn) {
                Gdx.input.vibrate(400);
            }
            overlaps = true;
            se.crab.stop();

        }

        if (overlaps) {
            overlap.slowDown();
            for (Coin coin : coins) {
                coin.slowDown();
            }
            resetGO = false;
            gameOver.update(delta);
            crab.setSpeed(400);

        }
        if (((coconut.getYEnd() < MenuScreen.VIRTUAL_HEIGHT - D / 2 + 14) && !readyToSpawnCoconut)) {
            readyToSpawnCoconut = true;
            coin = new Coin();
            coins.add(coin);
        }

        if (coconut.getY() > MenuScreen.VIRTUAL_HEIGHT) {
            readyToSpawnCoconut = false;
        }
        if (((stone.getYEnd() < MenuScreen.VIRTUAL_HEIGHT - D / 2 + 14) && !readyToSpawnStone)) {
            readyToSpawnStone = true;
            coin = new Coin();
            coins.add(coin);
        }

        if (stone.getY() > MenuScreen.VIRTUAL_HEIGHT) {
            readyToSpawnStone = false;
        }

        if (((star.getYEnd() < MenuScreen.VIRTUAL_HEIGHT - D / 2 + 14) && !readyToSpawnStar)) {
            readyToSpawnStar = true;
            coin = new Coin();
            coins.add(coin);
        }

        if (star.getY() > MenuScreen.VIRTUAL_HEIGHT) {
            readyToSpawnStar = false;
        }

        if (((ball.getYEnd() < MenuScreen.VIRTUAL_HEIGHT - D / 2 + 14) && !readyToSpawnBall)) {
            readyToSpawnBall = true;
            coin = new Coin();
            coins.add(coin);
        }

        if (ball.getY() > MenuScreen.VIRTUAL_HEIGHT) {
            readyToSpawnBall = false;
        }

        it = coins.iterator();

        while (it.hasNext()) {
            coin = it.next();
            coin.update(delta);

            if (i.overlaps(crab1, coin.getCoin1())
                    || i.overlaps(crab1, coin.getCoin2())
                    || i.overlaps(crab1, coin.getCoin3())
                    || i.overlaps(crab2, coin.getCoin1())
                    || i.overlaps(crab2, coin.getCoin2())
                    || i.overlaps(crab2, coin.getCoin3())
                    || i.overlaps(coin.getCoin1(), crab3)
                    || i.overlaps(coin.getCoin2(), crab3)
                    || i.overlaps(coin.getCoin3(), crab3)
                    || i.overlaps(coin.getCoin3(), crab4)) {
                if (!overlaps) {
                    it.remove();
                    score++;
                }
                if (soundOn && !overlaps) {
                    se.coinSound.play(1f);
                }
            }
            if (!coin.isVisible()) {
                it.remove();
                overlap.slowDown();
                if (!overlaps && soundOn) {
                    Gdx.input.vibrate(400);
                }
                overlaps = true;
                se.crab.stop();

            }

        }

        Gdx.input.setInputProcessor(processor);

        Renderer.viewport.project(sound);
        if (Gdx.input.justTouched()) {
            cameraUnpr.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            Renderer.viewport.unproject(cameraUnpr);
            if (cameraUnpr.x > gameOver.getSound().x
                    && cameraUnpr.x < gameOver.getSound().x
                    + gameOver.getSound().width
                    && cameraUnpr.y > gameOver.getSound().y
                    && cameraUnpr.y < gameOver.getSound().y
                    + gameOver.getSound().height

                    ) {
                if (soundOn) {
                    soundOn = false;
                    se.crab.stop();
                } else {
                    soundOn = true;
                }
            }
            if (cameraUnpr.x > gameOver.getX()
                    && cameraUnpr.x < gameOver.getX()
                    + gameOver.getWidth()
                    && cameraUnpr.y > gameOver.getY()+87
                    && cameraUnpr.y < gameOver.getY() + 87*2) {
                newGame();
                facebookClicked = false;

                if (soundOn) {
                    se.click.play(1f);
                }if(ad<4){
                    ad++;}
                if(ad == 3){
                    show = true;
                }

            }if(cameraUnpr.x > gameOver.getX()
                    && cameraUnpr.x < gameOver.getX()
                    + gameOver.getWidth()
                    && cameraUnpr.y > gameOver.getY()
                    && cameraUnpr.y < gameOver.getY() + 87){
                facebookClicked = true;
                if(soundOn) {
                    se.click.play(1f);
                }
            }
            if (cameraUnpr.x > gameOver.getX() + gameOver.getWidth() - 80
                    && cameraUnpr.x < gameOver.getX() + gameOver.getWidth()
                    && cameraUnpr.y > gameOver.getY() + gameOver.getHeight()
                    - 80
                    && cameraUnpr.y < gameOver.getY() + gameOver.getHeight()) {
                setScreen = true;
                ch = true;
                if (soundOn) {
                    se.click.play(1f);
                }

            }
            if(Renderer.facebookReady && cameraUnpr.x > gameOver.getX()
                    && cameraUnpr.y > gameOver.getY()
                    && cameraUnpr.y < gameOver.getY() + 87){
                if(cameraUnpr.x < gameOver.getX()
                        + gameOver.getWidth()/2){
                    //setScreen = true;
                    //ch = true;
                    invite = true;
                }else{
                    //setScreen = true;
                    //ch = true;
                    share = true;
                }
            }

        }
        if (!Gdx.input.isPeripheralAvailable(Input.Peripheral.Accelerometer)
                && Gdx.input.isTouched()) {
            if (cameraUnpr.x < MenuScreen.VIRTUAL_WIDTH / 2) {
                crab.left(delta);
            } else {
                crab.right(delta);
            }
        }
        if (!overlaps) {
            crab.setSpeed(800);
            overlap.resetSpeed();

        }
        if (soundOn && !overlaps){

            se.crab.setLooping(true);
            se.crab.setVolume(0.1f);
            se.crab.play();
        }
        if(score > Textures.getHighScore()){
            newHighScore = true;
            Textures.setHighScore(score);
        }
        if(score < Textures.getHighScore()){
            newHighScore = false;
        }


    }

    public void newGame() {
        overlaps = false;
        ads++;
        score = 0;
        overlap.resetCoconut();
        overlap.resetBall();
        overlap.resetStone();
        overlap.resetStar();
        overlap.resetGame();
        resetGO = true;
        coins.clear();
        crab.setX(MenuScreen.VIRTUAL_WIDTH / 2 - 61 / 2);
        ch = false;
    }

    public static Coin getCoin() {
        return coin;
    }

    public ArrayList<Coin> getCoins() {
        return coins;
    }

    public Overlap getOverlap() {
        return overlap;
    }

    public int getScore() {
        return score;
    }

    public GameOverTab getGameOver() {
        return gameOver;
    }
    public boolean isNewHighScore() {
        return newHighScore;
    }

    public Vector2 getSound() {
        return sound;
    }

    public static boolean isOverlaps() {
        return overlaps;
    }

    public static boolean isSoundOn() {
        return soundOn;
    }
}
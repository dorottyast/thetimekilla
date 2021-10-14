package net.lambrosia.thetimekilla.scroll;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

import net.lambrosia.thetimekilla.Menu.Menu;
import net.lambrosia.thetimekilla.screens.MenuScreen;
import net.lambrosia.thetimekilla.world.Renderer;
import net.lambrosia.thetimekilla.world.World;

public class Overlap {

    private Scroll coconut, stone, star, ball;
    private final float D = 208;

    private Background bg1, bg2;

    private Circle ball1, coconut1, stone1, stone2, stone3, stone4, star1,
            star2, star3, star4, star5, star6;
    private Rectangle stone5, coconut2;

    private float nullPoint;

    public Overlap() {
        loadObstacles();
        loadShapes();
        nullPoint = 15;
        bg1 = new Background(0, 0, 480, 1600);
        bg2 = new Background(0, bg1.getYEnd(), 480, 1600);
        coconut = new Scroll(MathUtils.random(nullPoint, MenuScreen.VIRTUAL_WIDTH-53-15), MenuScreen.VIRTUAL_HEIGHT+50, 53, 84);
        stone = new Scroll(
                MathUtils.random(nullPoint, MenuScreen.VIRTUAL_WIDTH-103-15),
                coconut.getYEnd() + D, 103, 76);
        star = new Scroll(MathUtils.random(nullPoint, MenuScreen.VIRTUAL_WIDTH - 65-15),
                stone.getYEnd() + D, 65, 69);
        ball = new Scroll(MathUtils.random(nullPoint, MenuScreen.VIRTUAL_WIDTH - 69-15),
                star.getYEnd() + D, 69, 78);
    }
    public void resetBackground(){
        bg1.setY(0);
    }

    public void update(float delta) {
        bg1.update(delta);
        bg2.update(delta);
        coconut.update(delta);
        stone.update(delta);
        star.update(delta);
        ball.update(delta);

        if(bg1.getY()<-1650){
            resetBackground();
            Gdx.app.log("RESETBACKGROUND", "");
        }
        if(ObstacleScroller.overlaps) {
            if (coconut.getYEnd() < -20) {
                resetGame();
            }
        }

        if (bg1.getYEnd() < 0) {
            bg1.reset(bg2.getYEnd());
        } else if (bg2.getYEnd() < 0) {
            bg2.reset(bg1.getYEnd());
        }
        if (coconut.getYEnd() < 0) {
            coconut.reset(ball.getYEnd() + D);
            resetCoconut();
        } else if (stone.getYEnd() < 0) {
            stone.reset(coconut.getYEnd() + D);
            resetStone();
        } else if (star.getYEnd() < 0) {
            star.reset(stone.getYEnd() + D);
            resetStar();
        } else if (ball.getYEnd() < 0) {
            ball.reset(star.getYEnd() + D);
            resetBall();
        }

        coconut1.set(coconut.getX() + 26, coconut.getY() + 28, 26);
        coconut2.set(coconut.getX()+7, coconut.getY()+63, 30, 12);

        stone1.set(stone.getX() + 17, stone.getY() + 45, 17);
        stone2.set(stone.getX() + 48, stone.getY() + 42, 30);
        stone3.set(stone.getX() + 79, stone.getY() + 51, 17);
        stone4.set(stone.getX() + 79, stone.getY() + 40, 20);
        stone5 = new Rectangle(stone.getX() + 19, stone.getY() + 12, 47, 25);

        star1.set(star.getX() + 41, star.getY() + 3, 6);
        star2.set(star.getX() + 7, star.getY() + 17, 6);
        star3.set(star.getX() + 4, star.getY() + 49, 6);
        star4.set(star.getX() + 36, star.getY() + 62, 6);
        star5.set(star.getX() + 57, star.getY() + 36, 6);
        star6.set(star.getX() + 28, star.getY() + 33, 22);

        ball1.set(ball.getX() + 34, ball.getY() + 43, 36);

    }


    public void loadObstacles() {

    }

    public void loadShapes() {
        ball1 = new Circle();

        coconut1 = new Circle();
        coconut2 = new Rectangle();

        stone1 = new Circle();
        stone2 = new Circle();
        stone3 = new Circle();
        stone4 = new Circle();
        stone5 = new Rectangle();

        star1 = new Circle();
        star2 = new Circle();
        star3 = new Circle();
        star4 = new Circle();
        star5 = new Circle();
        star6 = new Circle();
    }
    public void slowDown(){
        bg1.setSpeed(400/3);
        bg2.setSpeed(400/3);
        coconut.setSpeed(400 / 3);
        stone.setSpeed(400 / 3);
        star.setSpeed(400 / 3);
        ball.setSpeed(400 / 3);
    }
    public void resetSpeed(){
        coconut.setSpeed(400);
        stone.setSpeed(400);
        star.setSpeed(400);
        ball.setSpeed(400);
    }
    public void resetCoconut(){
        coconut.setX(MathUtils.random(nullPoint, MenuScreen.VIRTUAL_WIDTH-53-15));
    }
    public void resetStone(){
        stone.setX(MathUtils.random(nullPoint, MenuScreen.VIRTUAL_WIDTH-103-15));
    }
    public void resetStar(){
        star.setX(MathUtils.random(nullPoint, MenuScreen.VIRTUAL_WIDTH-65-15));
    }
    public void resetBall(){
        ball.setX(MathUtils.random(nullPoint, MenuScreen.VIRTUAL_WIDTH-69-15));
    }
    public void resetGame(){


        coconut.setY(MenuScreen.VIRTUAL_HEIGHT+75);

        stone.setY(coconut.getYEnd() + D);

        star.setY(stone.getYEnd() + D);

        ball.setY(star.getYEnd() + D);

    }

    public Scroll getBall() {
        return ball;
    }

    public Scroll getCoconut() {
        return coconut;
    }

    public Scroll getStar() {
        return star;
    }

    public Scroll getStone() {
        return stone;
    }

    public Circle getStar1() {
        return star1;
    }

    public Circle getStar2() {
        return star2;
    }

    public Circle getStar3() {
        return star3;
    }

    public Circle getStar4() {
        return star4;
    }

    public Circle getStar5() {
        return star5;
    }

    public Circle getStar6() {
        return star6;
    }

    public Circle getStone1() {
        return stone1;
    }

    public Circle getStone2() {
        return stone2;
    }

    public Circle getStone3() {
        return stone3;
    }

    public Circle getStone4() {
        return stone4;
    }

    public Rectangle getStone5() {
        return stone5;
    }

    public Circle getCoconut1() {
        return coconut1;
    }

    public Circle getBall1() {
        return ball1;
    }
    public Rectangle getCoconut2() {
        return coconut2;
    }

    public Background getBg1() {
        return bg1;
    }

    public Background getBg2() {
        return bg2;
    }
}
package net.lambrosia.thetimekilla.textures;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by dsz on 17/04/16.
 */
public class Textures {
    public static Texture texture;
    public static TextureRegion bg, bg2;
    public static Animation crabAnimation;

    public static TextureRegion crabOne, crabTwo, crabThree;
    public static TextureRegion coconut, ball, star, stone;
    public static TextureRegion coin;

    public static BitmapFont font;

    public static Preferences pref;

    public static void load() {
        texture = new Texture(Gdx.files.internal("thetexture.png"));
        texture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        bg = new TextureRegion(texture, 598, 0, 480, 1600);
        bg2 = new TextureRegion(texture, 1500, 0, 480, 1600);

        crabOne = new TextureRegion(texture, 484, 4, 63, 62);
        crabTwo = new TextureRegion(texture, 484, 74, 63, 62);
        crabThree = new TextureRegion(texture, 484, 145, 63, 62);

        coconut = new TextureRegion(texture, 484, 481, 54, 88);
        ball = new TextureRegion(texture, 484, 399, 69, 80);
        star = new TextureRegion(texture, 484, 220, 65, 70);
        stone = new TextureRegion(texture, 484, 310, 103, 77);
        coin = new TextureRegion(texture, 484, 588, 44, 31);

        TextureRegion[] crabs = { crabOne, crabTwo, crabThree };
        crabAnimation = new Animation(0.07f, crabs);
        crabAnimation.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);

        font = new BitmapFont(Gdx.files.internal("sifonn.fnt"));
        font.getData().setScale(0.7f);
        font.setColor(10/255.0f, 178/255.0f, 178/255.0f, 1f);

        pref = Gdx.app.getPreferences("BG");
        if(!pref.contains("scores")){
            pref.putInteger("scores", 0);
        }

    }

    public static void dispose() {
        texture.dispose();
        font.dispose();
    }
    public static Texture getTexture() {
        return texture;
    }

    public static void setHighScore(int val){
        pref.putInteger("scores", val);
        pref.flush();
    }
    public static int getHighScore(){
        return pref.getInteger("scores");
    }
}


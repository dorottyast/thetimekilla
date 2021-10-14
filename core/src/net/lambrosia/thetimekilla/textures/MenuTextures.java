package net.lambrosia.thetimekilla.textures;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class MenuTextures {

    public static Texture texture;

    public static TextureRegion coin, menu, menuCrabOne, menuCrabTwo,
            menuCrabThree, play, how, highScore, coconut, stone, star, ball,
            gameOverTab, sound, pause, score, soundOff, pauseOn, smallMenu,
            howTab, menuDir, highScoreTab, whoops, newHS, libGDX, share, invite, tellYourFriends;

    public static Animation menuCrabAnimation;

    public static void load() {

        texture = Textures.texture;
        texture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        menuCrabOne = new TextureRegion(texture, 485, 661, 56, 61);
        menuCrabTwo = new TextureRegion(texture, 485, 726, 56, 61);
        menuCrabThree = new TextureRegion(texture, 485, 798, 56, 61);

        TextureRegion[] menuCrabs = { menuCrabOne, menuCrabTwo, menuCrabThree };
        menuCrabAnimation = new Animation(0.15f, menuCrabs);
        menuCrabAnimation.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);

        menu = new TextureRegion(texture, 0, 0, 480, 800);

        coin = new TextureRegion(texture, 483, 883, 49, 33);
        how = new TextureRegion(texture, 195, 805, 80, 80);
        highScore = new TextureRegion(texture, 334, 806, 80, 80);

        coconut = new TextureRegion(texture, 483, 1154, 55, 82);
        stone = new TextureRegion(texture, 483, 1078, 104, 68);
        star = new TextureRegion(texture, 483, 1004, 64, 66);
        ball = new TextureRegion(texture, 483, 926, 71, 71);

        sound = new TextureRegion(texture, 484, 1273, 36, 30);
        soundOff = new TextureRegion(texture, 484, 1238, 36, 30);
        score = new TextureRegion(texture, 484, 1312, 38, 28);

        gameOverTab = new TextureRegion(texture, 0, 846, 391, 430);
        smallMenu = new TextureRegion(texture, 1073, 0, 240, 400);
        howTab = new TextureRegion(texture, 1088, 2, 401, 712);
        menuDir = new TextureRegion(texture, 484, 1344, 31, 26);
        highScoreTab = new TextureRegion(texture, 0, 1305, 279, 150);

        whoops = new TextureRegion(texture, 0, 1457, 128, 30);
        newHS = new TextureRegion(texture, 0, 1488, 254, 30);
        newHS = new TextureRegion(texture, 0, 1488, 254, 30);
        libGDX = new TextureRegion(texture, 1095, 833, 285, 182);

        share= new TextureRegion(texture, 2, 1514, 84, 71);
        invite = new TextureRegion(texture, 103, 1514, 92, 71);
        tellYourFriends = new TextureRegion(texture, 221, 1520, 303, 67);
    }

    public static void dispose() {
        texture.dispose();
    }

}

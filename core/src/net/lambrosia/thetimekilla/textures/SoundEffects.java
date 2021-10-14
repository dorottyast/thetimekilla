package net.lambrosia.thetimekilla.textures;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

/**
 * Created by dsz on 17/04/16.
 */
public class SoundEffects {
    public static Sound coinSound, click;
    public static Music crab;

    public static void load(){
        coinSound = Gdx.audio.newSound(Gdx.files.internal("coin2.mp3"));
        crab = Gdx.audio.newMusic(Gdx.files.internal("crab.wav"));
        click = Gdx.audio.newSound(Gdx.files.internal("click.mp3"));
    }

    public static void dispose(){
        coinSound.dispose();
        crab.dispose();
        click.dispose();
    }
}
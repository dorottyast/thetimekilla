package net.lambrosia.thetimekilla;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Timer;

import net.lambrosia.thetimekilla.screens.BeachScreen;
import net.lambrosia.thetimekilla.screens.MenuScreen;
import net.lambrosia.thetimekilla.screens.SplashScreen;
import net.lambrosia.thetimekilla.scroll.ObstacleScroller;
import net.lambrosia.thetimekilla.scroll.Overlap;
import net.lambrosia.thetimekilla.textures.MenuTextures;
import net.lambrosia.thetimekilla.textures.SoundEffects;
import net.lambrosia.thetimekilla.textures.Textures;

public class TheTimeKilla extends Game {


	public static MenuScreen menuScreen;
	public BeachScreen beachScreen;

	private static long splash = 2600L;

	private AdHandler adHandler;
	private boolean show;
	private boolean play;
	public static boolean playSound;

	public TheTimeKilla(AdHandler adHandler) {
		this.adHandler = adHandler;
	}
	private boolean toggle;
	private ObstacleScroller os;
	public static int adBinary;

	@Override
	public void create() {
		adBinary = MathUtils.random(0, 2);
		menuScreen = new MenuScreen(this);
		beachScreen = new BeachScreen(this);
		setScreen(new SplashScreen());
		Textures.load();
		SoundEffects.load();
		MenuTextures.load();

		os = new ObstacleScroller();

		final long splashStart = System.currentTimeMillis();

		new Thread(new Runnable() {

			@Override
			public void run() {
				Gdx.app.postRunnable((new Runnable() {
					@Override
					public void run() {
						long elapsedTime = System.currentTimeMillis() - splashStart;
						if (elapsedTime < splash) {
							Timer.schedule(
									new Timer.Task() {
										@Override
										public void run() {
											setScreen(menuScreen);
											playSound = true;
										}
									}, (float) (splash - elapsedTime) / 1000f);
						} else {
							setScreen(menuScreen);
						}
					}
				}));
			}
		}).start();
	}

	@Override
	public void render() {
		super.render();
		if(ObstacleScroller.share){
			adHandler.shareOnFacebook();
			ObstacleScroller.share = false;
		}
		if(ObstacleScroller.invite){
			adHandler.inviteFriends();
			ObstacleScroller.invite = false;
		}
		if(  getScreen() == menuScreen ){
			if(!ObstacleScroller.show) {
				adHandler.showAds(false);
			}
		}
		if((ObstacleScroller.show && getScreen() == beachScreen && ObstacleScroller.overlaps) ||(ObstacleScroller.show && getScreen() == beachScreen && ((ObstacleScroller.ads%5 == 0) ||(ObstacleScroller.ads%5 == 1)||(ObstacleScroller.ads%5 == 2)||(ObstacleScroller.ads%5 == 3)))){
			adHandler.showAds(true);
		}else if(!ObstacleScroller.setScreen){
			adHandler.showAds(false);
		}
		if(getScreen() == menuScreen){
			beachScreen.pause();
		}
	}

	@Override
	public void pause() {
		super.pause();
	}

	@Override
	public void resume() {

		super.resume();
	}

	@Override
	public void dispose() {
		super.dispose();
		Textures.dispose();
		SoundEffects.dispose();
		MenuTextures.dispose();
	}


}


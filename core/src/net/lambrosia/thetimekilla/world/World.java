package net.lambrosia.thetimekilla.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.viewport.Viewport;

import net.lambrosia.thetimekilla.objects.Crab;
import net.lambrosia.thetimekilla.screens.BeachScreen;
import net.lambrosia.thetimekilla.screens.MenuScreen;
import net.lambrosia.thetimekilla.scroll.Coin;
import net.lambrosia.thetimekilla.scroll.ObstacleScroller;
import net.lambrosia.thetimekilla.scroll.Scroll;

/**
 * Created by dsz on 17/04/16.
 */
public class World {

    public static Crab crab;
    public static ObstacleScroller obstacleScroller;

    public World() {
        crab = new Crab(MenuScreen.VIRTUAL_WIDTH / 2 - 65 / 2, 160, 65, 64);
        obstacleScroller = new ObstacleScroller();
    }

    public void update(float delta) {
        crab.update(delta);
        obstacleScroller.update(delta);
    }

    public static Crab getCrab() {
        return crab;
    }

    public static ObstacleScroller getObstacleScroller() {
        return obstacleScroller;
    }

}

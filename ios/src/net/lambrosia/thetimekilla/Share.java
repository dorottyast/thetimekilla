package net.lambrosia.thetimekilla;

import net.lambrosia.thetimekilla.scroll.ObstacleScroller;

import org.robovm.apple.foundation.NSURL;
import org.robovm.apple.uikit.UIApplication;
import org.robovm.apple.uikit.UIViewController;
import org.robovm.pods.facebook.share.FBSDKShareDialog;
import org.robovm.pods.facebook.share.FBSDKShareLinkContent;

/**
 * Created by dsz on 25/04/16.
 */
public class Share {

    FBSDKShareLinkContent content;
    String coin;

    public Share(){
        if (ObstacleScroller.score == 1 || ObstacleScroller.score == 0) {
            coin = "coin";
        } else {
            coin = "coins";
        }

        content = new FBSDKShareLinkContent();
    }
    public void share(){
        content.setContentURL(new NSURL("http://lambrosia.net/"));
        content.setContentTitle("I have just gained " + ObstacleScroller.score
                + " " + coin + " in the game The Time Killa'. Step it up!");
        content.setContentDescription("Collect coins with this little greedy crab.");
        content.setImageURL(new NSURL("https://2.bp.blogspot.com/-kpc67xZEUgo/VyuYZtpT7XI/AAAAAAAABvQ/eSH6d5MW9skVpkvZpTcSdNP9pL0aXpl5ACLcB/s1600/fbicon.png"));
        UIViewController viewController = UIApplication.getSharedApplication().getKeyWindow().getRootViewController();
        FBSDKShareDialog.show(viewController, content, null);
    }

}

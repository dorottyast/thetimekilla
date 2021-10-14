package net.lambrosia.thetimekilla;

import com.badlogic.gdx.Gdx;

import net.lambrosia.thetimekilla.textures.MenuTextures;
import net.lambrosia.thetimekilla.textures.SoundEffects;
import net.lambrosia.thetimekilla.textures.Textures;

import org.robovm.apple.foundation.NSDictionary;
import org.robovm.apple.foundation.NSError;
import org.robovm.apple.foundation.NSObject;
import org.robovm.apple.foundation.NSURL;
import org.robovm.apple.mobilecoreservices.UTType;
import org.robovm.apple.uikit.UIApplication;
import org.robovm.apple.uikit.UIViewController;
import org.robovm.pods.facebook.share.FBSDKAppInviteContent;
import org.robovm.pods.facebook.share.FBSDKAppInviteDialog;
import org.robovm.pods.facebook.share.FBSDKAppInviteDialogDelegate;
import org.robovm.pods.facebook.share.FBSDKGameRequestDialog;

/**
 * Created by dsz on 21/04/16.
 */
public class InviteFriends extends NSObject{

    FBSDKAppInviteContent inviteContent;
    UIViewController viewController;

    public InviteFriends(){
        inviteContent = new FBSDKAppInviteContent();
        inviteContent.setAppLinkURL(new NSURL("https://fb.me/879525462193647"));
        inviteContent.setAppInvitePreviewImageURL(new NSURL("https://3.bp.blogspot.com/-Q1mik9NL2Yw/Vvl_7rT720I/AAAAAAAABug/qUB4MUQo9KMRGlHHdaUU58tjMZHnmfuTg/s1600/appinvite.png"));
        viewController = UIApplication.getSharedApplication().getKeyWindow().getRootViewController();
    }

    public void inviteFriends(){
        FBSDKAppInviteDialog.show(viewController, inviteContent, null);
    }

    public UIViewController getViewController() {
        return viewController;
    }
}

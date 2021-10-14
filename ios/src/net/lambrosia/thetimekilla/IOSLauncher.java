package net.lambrosia.thetimekilla;

import org.robovm.apple.coregraphics.CGRect;
import org.robovm.apple.coregraphics.CGSize;
import org.robovm.apple.foundation.NSAutoreleasePool;
import org.robovm.apple.foundation.NSPropertyList;
import org.robovm.apple.foundation.NSURL;
import org.robovm.apple.uikit.UIApplication;
import org.robovm.apple.uikit.UIApplicationDelegate;
import org.robovm.apple.uikit.UIApplicationLaunchOptions;
import org.robovm.apple.uikit.UIScreen;
import org.robovm.apple.uikit.UIViewController;
import org.robovm.pods.facebook.core.FBSDKAppEvents;
import org.robovm.pods.facebook.core.FBSDKApplicationDelegate;
import org.robovm.pods.facebook.core.FBSDKTestUsersManager;
import org.robovm.pods.facebook.share.FBSDKAppInviteDialog;
import org.robovm.pods.facebook.share.FBSDKAppInviteDialogDelegate;
import org.robovm.pods.google.mobileads.GADAdSize;
import org.robovm.pods.google.mobileads.GADBannerView;
import org.robovm.pods.google.mobileads.GADBannerViewDelegateAdapter;
import org.robovm.pods.google.mobileads.GADRequest;
import org.robovm.pods.google.mobileads.GADRequestError;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.LifecycleListener;
import com.badlogic.gdx.backends.iosrobovm.IOSApplication;
import com.badlogic.gdx.backends.iosrobovm.IOSApplicationConfiguration;
import com.badlogic.gdx.backends.iosrobovm.IOSGraphics;
import com.badlogic.gdx.backends.iosrobovm.IOSInput;
import com.badlogic.gdx.utils.Array;

import net.lambrosia.thetimekilla.screens.BeachScreen;

public class IOSLauncher extends IOSApplication.Delegate implements AdHandler {

    private String coin;

    private GADBannerView adview;
    float w, h;
    private boolean Banner_AcceptShow = true, adsShow;
    UIViewController controller;

    GADRequest request;
    InviteFriends inviteFriends;
    Share share;


    @Override
    protected IOSApplication createApplication() {

        IOSApplicationConfiguration config = new IOSApplicationConfiguration();
        config.useAccelerometer = true;
        config.useCompass = true;
        config.orientationPortrait = true;
        config.orientationLandscape = false;
        return new IOSApplication(new TheTimeKilla(this), config);
    }

    @Override
    public boolean didFinishLaunching(UIApplication application,
                                      UIApplicationLaunchOptions launchOptions) {
        FBSDKApplicationDelegate.getSharedInstance().didFinishLaunching(application, launchOptions);
        boolean b = super.didFinishLaunching(application, launchOptions);
        initADSBanner();
        return true;
    }


    public void initADSBanner() {
        if (adview != null) {
            return;
        }

        controller = ((IOSApplication) Gdx.app).getUIViewController();
        final CGSize screenSize = UIScreen.getMainScreen().getBounds().getSize();
        w = (float) screenSize.getWidth();
        h = (float) screenSize.getHeight();
        if (w > h) {
            double temp = w;
            w = h;
            h = (float) temp;
        }
        adview = new GADBannerView(GADAdSize.SmartBannerPortrait());
        adview.setRootViewController(controller);
        adview.setAdUnitID("ca-app-pub-2694448054600231/7925451561");
        adview.setDelegate(new GADBannerViewDelegateAdapter() {
            @Override
            public void didReceiveAd(GADBannerView view) {
                super.didReceiveAd(view);
                if (Banner_AcceptShow) {
                    showAds(true);
                } else
                    showAds(false);
            }

            @Override
            public void didFailToReceiveAd(GADBannerView view, GADRequestError error) {
                super.didFailToReceiveAd(view, error);

            }
        });
        if(TheTimeKilla.adBinary==1) {
            adview.setFrame(new CGRect(w / 2f - adview.getFrame().getSize().getWidth() / 2f,
                    0, adview.getFrame().getSize().getWidth(),
                    adview.getFrame().getSize().getHeight()));
        }else{
            adview.setFrame(new CGRect(w / 2f - adview.getFrame().getSize().getWidth() / 2f,
                    h-adview.getFrame().getSize().getHeight(), adview.getFrame().getSize().getWidth(),
                    adview.getFrame().getSize().getHeight()));
        }
        request = new GADRequest();
        adview.loadRequest(request);

        controller.getView().addSubview(adview);
        adview.setHidden(true);

    }


    public static void main(String[] argv) {
        NSAutoreleasePool pool = new NSAutoreleasePool();
        UIApplication.main(argv, null, IOSLauncher.class);
        pool.close();
    }

    @Override
    public void showAds(boolean show) {
        adsShow = show;
        if (adview != null) {
            if (show) {
                adview.setHidden(false);
                if(TheTimeKilla.adBinary==1) {
                    adview.setFrame(
                            new CGRect(w / 2f - adview.getFrame().getSize().getWidth() / 2f, 0,
                                    adview.getFrame().getSize().getWidth(), adview.getFrame().getSize().getHeight()));
                }else if(TheTimeKilla.adBinary==0 || TheTimeKilla.adBinary==2){
                    adview.setFrame(
                            new CGRect(w / 2f - adview.getFrame().getSize().getWidth() / 2f, h-adview.getFrame().getSize().getHeight(),
                                    adview.getFrame().getSize().getWidth(), adview.getFrame().getSize().getHeight()));
                }
            } else {
                adview.setHidden(true);
            }
        }
    }

    @Override
    public void shareOnFacebook() {
        share = new Share();
        share.share();
    }

    @Override
    public void inviteFriends() {
        inviteFriends = new InviteFriends();
        inviteFriends.inviteFriends();
    }

    @Override
    public boolean openURL(UIApplication application, NSURL url, String sourceApplication, NSPropertyList annotation) {
        return FBSDKApplicationDelegate.getSharedInstance().openURL(application, url, sourceApplication, annotation);
    }


}
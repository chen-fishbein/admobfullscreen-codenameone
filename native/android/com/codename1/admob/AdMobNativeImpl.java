package com.codename1.admob;

import com.google.android.gms.ads.*;
import com.codename1.impl.android.*;


import com.codename1.impl.android.*;
import java.lang.Thread;

// see https://developers.google.com/mobile-ads-sdk/docs/admob/android/interstitial

public class AdMobNativeImpl {
    private InterstitialAd interstitial;
    final long timeout = 2000;
    long startNativeRun = 0L;
    boolean duringNativeRun = false;
    boolean isLoaded = false;
    
    private String adID;
    
    public void init(String adID) {
        this.adID = adID;
    }


    /**
     * Makes sure the call is from the native thread, timeout = 2000ms
     * @return
     */
    public boolean isLoaded() {
        final CodenameOneActivity activity = (CodenameOneActivity)AndroidNativeUtil.getActivity();

        System.err.println("inside AdMobFullPageImpl.isLoaded - activity "+(activity == null ? "is null" : "is not null"));

        duringNativeRun = true;
        startNativeRun = System.currentTimeMillis();
        activity.runOnUiThread(new Runnable() {

            public void run() {
                isLoaded = interstitial.isLoaded();
                System.err.println("loaded = "+isLoaded);
                duringNativeRun = false;
            }
        });

        while (System.currentTimeMillis()-startNativeRun < timeout) {
            if (!duringNativeRun) {
                break;
            }

            try {
                Thread.sleep(50);
            }
            catch (Exception ex) {
                System.err.println("Thread interrupted!");
                ex.printStackTrace();
                return isLoaded;
            }
        }

        return isLoaded;
    }

    public boolean loadAd() {
        final CodenameOneActivity activity = (CodenameOneActivity)AndroidNativeUtil.getActivity();
        interstitial = new InterstitialAd(activity);
        interstitial.setAdUnitId(adID);
        interstitial.setAdListener(new AdListener() {

            @Override
            public void onAdClosed() {
                super.onAdClosed();
                Callback.onAdClosed();
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                super.onAdFailedToLoad(errorCode);
                Callback.onAdFailedToLoad(errorCode);
            }

            @Override
            public void onAdLeftApplication() {
                super.onAdLeftApplication();
                Callback.onAdLeftApplication();                
            }

            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
                Callback.onAdLoaded();                
            }

            @Override
            public void onAdOpened() {
                super.onAdOpened();
                Callback.onAdOpened();                
            }
            
            
        
        });
        
        try {
            // Create ad request.
            final AdRequest adRequest = new AdRequest.Builder().build();

            activity.runOnUiThread(new Runnable() {

                public void run() {
                    interstitial.loadAd(adRequest);
                }
            });

            return true;
        }
        catch (Exception ex) {
            System.err.println("startLoadingAd - exception thrown");
            ex.printStackTrace();

            return false;
        }
    }

    public void showAd() {
        System.err.println("inside AdMobFullPageImpl.showAd");
        
        final CodenameOneActivity activity = (CodenameOneActivity)AndroidNativeUtil.getActivity();

        System.err.println("inside showAd - activity "+(activity == null ? "is null" : "is not null"));

        activity.runOnUiThread(new Runnable() {

            public void run() {
            	interstitial.show();
            }
        });
    }

    public boolean isSupported() {
        return true;
    }
}

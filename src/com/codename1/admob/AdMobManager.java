/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.admob;

import com.codename1.system.NativeLookup;
import com.codename1.ui.Display;

/**
 * Utility class to show full screen admob Ads.
 *
 */
public class AdMobManager {

    private AdMobNative admob = null;

    /**
     * Creates an AdMobManager instance
     * @param adID admob AD ID
     */
     public AdMobManager(String adID){
        admob = (AdMobNative) NativeLookup.create(AdMobNative.class);
        if(admob != null){
            admob.init(adID);
        }
    }
    
    /**
     * Show the Ad if loaded
     * @return true if the ad was shown false if the Ad wasn't shown, could happen
     * if the loadAd didn't ended
     */ 
     public boolean showAdIfLoaded(){
        if (admob.isLoaded()) {
            admob.showAd();      
            return true;
        }
        return false;
    }
    
    /**
     * This method loads the Ads, this could take some time and should be called 
     * each time before showAdIfLoaded().
     * @return true if Ad was loaded successfully false otherwise
     */ 
     public boolean loadAd() {
        if (Display.getInstance().isEdt()) {
            return admob.loadAd();
        } else {
            Display.getInstance().callSeriallyAndWait(new Runnable() {

                public void run() {
                    admob.loadAd();
                }
            });

            return true;
        }
    }
    
    /**
     * This is a utility method that will do the load and show.
     */ 
     public void loadAndShow(){
        admob.loadAd();
        Display.getInstance().invokeAndBlock(new Runnable() {

            public void run() {
                while(!admob.isLoaded()){
                    try {
                        Thread.sleep(200);                        
                    } catch (Exception e) {
                    }
                    
                }
            }
        });
        admob.showAd();
    }
     
    /**
     * Sets an AdsListener
     */ 
     public void setAdsListener(AdsListener l) {
        Callback.setListener(l);
    }
     
}

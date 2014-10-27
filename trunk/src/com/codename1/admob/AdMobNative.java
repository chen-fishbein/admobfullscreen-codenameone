/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.admob;

import com.codename1.system.NativeInterface;

/**
 *
 * @author RAM
 */
public interface AdMobNative extends NativeInterface {
    /**
     * Method javadoc information
     */
    public void init(String adID);
    /**
     * Method javadoc information
     */
    public boolean loadAd();
    /**
     * Method javadoc information
     */
    public boolean isLoaded();
    /**
     * Method javadoc information
     */
    public void showAd();
}

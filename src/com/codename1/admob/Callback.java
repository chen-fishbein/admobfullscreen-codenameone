/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.admob;

import com.codename1.ui.Display;

/**
 *
 * @author Chen
 */
public class Callback {
    
    private static AdsListener l;
    
    public static void onAdClosed(){
        if(l != null){
            Display.getInstance().callSerially(new Runnable(){

                public void run() {
                    l.onAdClosed();
                }
            });
        }
    }

    public static void onAdLeftApplication(){
        if(l != null){
            Display.getInstance().callSerially(new Runnable(){

                public void run() {
                    l.onAdLeftApplication();
                }
            });
        }    
    }

    public static void onAdOpened(){
        if(l != null){
            Display.getInstance().callSerially(new Runnable(){

                public void run() {
                    l.onAdOpened();
                }
            });
        }        
    }

    public static void onAdFailedToLoad(final int err){
        if(l != null){
            Display.getInstance().callSerially(new Runnable(){

                public void run() {
                    l.onAdFailedToLoad(err);
                }
            });
        }                     
    }

    
    public static void onAdLoaded(){
        if(l != null){
            Display.getInstance().callSerially(new Runnable(){

                public void run() {
                    l.onAdLoaded();
                }
            });
        }                     
    }
    
    static void setListener(AdsListener listener){
        l = listener;
    }
    
}

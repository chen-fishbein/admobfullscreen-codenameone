# admobfullscreen-codenameone

A Codename One library project that uses Admob Interstitial Ads 
to display full screen ads - https://developers.google.com/mobile-ads-sdk/docs/admob/android/interstitial

The library is implemented for Android, iOS and the Simulator

Thanks for Ram for contributing.(the creator of yhomework)

#Sample Code

public class MyApplication {

    private Form current;

    private AdMobManager admob;
    
    public void init(Object context) {
        try {
            Resources theme = Resources.openLayered("/theme");
            UIManager.getInstance().setThemeProps(theme.getTheme(theme.getThemeResourceNames()[0]));
        } catch(IOException e){
            e.printStackTrace();
        }
        
        String admobId = "<your android adID>";
        if(Display.getInstance().getPlatformName().equals("ios")){
            admobId = "<your iphone adID>";
        }
        admob = new AdMobManager(admobId);
    }
    
    public void start() {
        if(current != null){
            current.show();
            return;
        }
        
        Form f = new Form("FullScreen Ads");
        f.addComponent(new Button(new Command("start"){

            public void actionPerformed(ActionEvent evt) {
                admob.loadAd();
            }
        
        }));
        
        f.addComponent(new Button(new Command("show"){

            public void actionPerformed(ActionEvent evt) {
                admob.showAdIfLoaded();
            }
        
        }));

        f.addComponent(new Button(new Command("load & show"){

            public void actionPerformed(ActionEvent evt) {
                admob.loadAndShow();
            }
        
        }));

        f.show();
        
    }

    public void stop() {
        current = Display.getInstance().getCurrent();
    }
    
    public void destroy() {
    }

}


#Details

1)Build the project <br/>
2)Place the AdmobFullScreen.cn1lib file in your CN1 project lib. <br/>
3)Right click on your CN1 project and select "Refresh Libs" then clean build your project.
<br/>

build for Android - <br/>
1)Add the following build hints: <br/>
android.xapplication=\<meta-data android:name="com.google.android.gms.version" android:value="@integer/google_play_services_version" />\<activity android:name="com.google.android.gms.ads.AdActivity" android:configChanges="keyboard|keyboardHidden|orientation|screenLayout|uiMode|screenSize|smallestScreenSize" /> <br/>
android.includeGPlayServices=true <br/>
<br/>
build for iphone - <br/>
1)Add the following build hints: <br/>
ios.add_libs=AdSupport.framework;SystemConfiguration.framework;CoreTelephony.framework <br/>
ios.objC=true


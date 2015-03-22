# Usage #

Sample code:
```
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
```

# Details #

<br>1)Build the project<br>
<br>2)Place the AdmobFullScreen.cn1lib file in your CN1 project lib.<br>
<br>3)Right click on your CN1 project and select "Refresh Libs" then clean build your project.<br>
<br>
<b>build for Android</b> -<br>
<br>1)Add the following build hints:<br>
<br><b>android.xapplication</b>=<meta-data android:name="com.google.android.gms.version" android:value="@integer/google_play_services_version" /><activity android:name="com.google.android.gms.ads.AdActivity" android:configChanges="keyboard|keyboardHidden|orientation|screenLayout|uiMode|screenSize|smallestScreenSize" /><br>
<br><b>android.includeGPlayServices</b>=true<br>
<br>
<b>build for iphone</b> -<br>
<br>1)Add the following build hints:<br>
<br><b>ios.add_libs</b>=AdSupport.framework;SystemConfiguration.framework;CoreTelephony.framework<br>
<br><b>ios.objC</b>=true
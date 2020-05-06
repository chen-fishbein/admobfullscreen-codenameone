# admobfullscreen-codenameone

A Codename One library project that uses Admob Interstitial Ads 
to display full screen ads - https://developers.google.com/mobile-ads-sdk/docs/admob/android/interstitial

The library is implemented for Android, iOS and the Simulator

Thanks for Ram for contributing.(the creator of yhomework)

## Sample Code

~~~~~
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
        
        // Note:  admobId is the ID of the target ads you want to display
        // not your admob App ID.
        // See instructions for Android and iOS below for specifying admob app ID
        // via build hints.
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
~~~~~

## Installation

The preferred installation method is to use the Extensions manager in Codename One settings. 

**Manual installation:**

* Build the project
* Place the AdmobFullScreen.cn1lib file in your CN1 project lib. 
* Right click on your CN1 project and select "Codename One" > "Refresh CN1Libs" then clean build your project.

### Android Instructions

Android now requires you to add the following snippet to your project's `android.xapplication` build hint

~~~~
<meta-data
android:name="com.google.android.gms.ads.APPLICATION_ID"
android:value="YOUR_ADMOB_APP_ID"/>
~~~~

Where `YOUR_ADMOB_APP_ID` is replaced with your Admob App ID.  Sample Admob App ID: `ca-app-pub-3940256099942544~3347511713`.

### iOS Instructions

iOS now requires you to specify your admob App ID in the Info.plist file.  You can do this with by adding the following build hint to your project:

~~~~
ios.plistInject=<key>GADApplicationIdentifier</key><string>YOUR_ADMOB_APP_ID_</string>
~~~~

Where `YOUR_ADMOB_APP_ID` is replaced with your Admob App ID.  ca-app-pub-XXXXXXXX~XXXXXXXXX


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




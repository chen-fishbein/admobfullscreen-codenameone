#import <Foundation/Foundation.h>

#import "GADInterstitial.h"
#import "GADInterstitialDelegate.h"

@interface com_codename1_admob_AdMobNativeImpl : NSObject<GADInterstitialDelegate> {
    // Declare one as an instance variable
    GADInterstitial *interstitial_;
    BOOL loaded;
    NSString *adID;
}

-(void)init:(NSString*)param;
-(BOOL)isLoaded;
-(BOOL)loadAd;
-(void)showAd;
-(BOOL)isSupported;

-(void)getIsLoadedMainThread;
@end

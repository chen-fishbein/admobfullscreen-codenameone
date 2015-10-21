#import "com_codename1_admob_AdMobNativeImpl.h"
#import "CodenameOne_GLViewController.h"
#import "com_codename1_admob_Callback.h"

@implementation com_codename1_admob_AdMobNativeImpl

-(void)init:(NSString*)param{
    NSLog(@"inside native init");

    adID = param;
    id del = self; 

    [[NSOperationQueue mainQueue] addOperationWithBlock:^ {
        if (interstitial_ != nil) {
#ifndef CN1_USE_ARC            
            [interstitial_ release];
#endif
        }
        interstitial_ = [[GADInterstitial alloc] init];
        interstitial_.adUnitID = adID;
        interstitial_.delegate = del;
    }];
}

-(void)getIsLoadedMainThread{
    NSLog(@"inside native getIsLoadedMainThread");
    loaded = ([interstitial_ isReady]);

    if (loaded) {
        NSLog(@"ad loaded!");
    }
    else {
        NSLog(@"ad not loaded yet");
    }
}

-(BOOL)isLoaded{
    loaded = NO;

    NSLog(@"inside native isLoaded - run on main thread");
    [self performSelectorOnMainThread:@selector(getIsLoadedMainThread) withObject:nil waitUntilDone:YES];

    return loaded;
}

-(BOOL)loadAd{
    NSLog(@"inside native startLoadingAd");
    id del = self; 
    [[NSOperationQueue mainQueue] addOperationWithBlock:^ {
        if (interstitial_ != nil) {
#ifndef CN1_USE_ARC            
            [interstitial_ release];
#endif
        }
        interstitial_ = [[GADInterstitial alloc] init];
        interstitial_.adUnitID = adID;
        interstitial_.delegate = del;
        
        // Your code goes in here
        NSLog(@"start loading Ad");
        [interstitial_ loadRequest:[GADRequest request]];
    }];

    return YES;
}

-(void)showAd{
    NSLog(@"inside native showAd");

    [[NSOperationQueue mainQueue] addOperationWithBlock:^ {
        UIViewController *viewController = [CodenameOne_GLViewController instance]; // big question - what to put here?
        [interstitial_ presentFromRootViewController:viewController];
    }];
}

-(BOOL)isSupported{
    NSLog(@"inside native isSupported");

    return YES;
}

/// Called when an interstitial ad request succeeded.
- (void)interstitialDidReceiveAd:(GADInterstitial *)ad {
  NSLog(@"interstitialDidReceiveAd");
  com_codename1_admob_Callback_onAdLoaded__(CN1_THREAD_GET_STATE_PASS_SINGLE_ARG);
}

/// Called when an interstitial ad request failed.
- (void)interstitial:(GADInterstitial *)ad didFailToReceiveAdWithError:(GADRequestError *)error {
  NSLog(@"interstitialDidFailToReceiveAdWithError: %@", [error localizedDescription]);
  com_codename1_admob_Callback_onAdFailedToLoad___int(CN1_THREAD_GET_STATE_PASS_ARG -1);
}

/// Called just before presenting an interstitial.
- (void)interstitialWillPresentScreen:(GADInterstitial *)ad {
  NSLog(@"interstitialWillPresentScreen");
  com_codename1_admob_Callback_onAdOpened__(CN1_THREAD_GET_STATE_PASS_SINGLE_ARG);
}

/// Called before the interstitial is to be animated off the screen.
- (void)interstitialWillDismissScreen:(GADInterstitial *)ad {
  NSLog(@"interstitialWillDismissScreen");
}

/// Called just after dismissing an interstitial and it has animated off the screen.
- (void)interstitialDidDismissScreen:(GADInterstitial *)ad {
  NSLog(@"interstitialDidDismissScreen");
  com_codename1_admob_Callback_onAdClosed__(CN1_THREAD_GET_STATE_PASS_SINGLE_ARG);  
}

/// Called just before the application will background or terminate because the user clicked on an
/// ad that will launch another application (such as the App Store).
- (void)interstitialWillLeaveApplication:(GADInterstitial *)ad {
  NSLog(@"interstitialWillLeaveApplication");
  com_codename1_admob_Callback_onAdLeftApplication__(CN1_THREAD_GET_STATE_PASS_SINGLE_ARG);  
}

-(void)dealloc {
#ifndef CN1_USE_ARC
    [interstitial_ release];
    [super dealloc];
#endif
}

@end

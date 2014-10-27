#import "com_codename1_admob_AdMobNativeImpl.h"
#import "CodenameOne_GLViewController.h"

@implementation com_codename1_admob_AdMobNativeImpl

-(void)init:(NSString*)param{
    NSLog(@"inside native init");

    adID = param;

    [[NSOperationQueue mainQueue] addOperationWithBlock:^ {
        interstitial_ = [[GADInterstitial alloc] init];
        interstitial_.adUnitID = adID;
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

    [[NSOperationQueue mainQueue] addOperationWithBlock:^ {
        interstitial_ = [[GADInterstitial alloc] init];
        interstitial_.adUnitID = adID;

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

@end

#import <Foundation/Foundation.h>
#import <Capacitor/Capacitor.h>

// Define the plugin using the CAP_PLUGIN Macro, and
// each method the plugin supports using the CAP_PLUGIN_METHOD macro.
CAP_PLUGIN(GeanAppodealPlugin, "GeanAppodeal",
    CAP_PLUGIN_METHOD(initializeAppodeal, CAPPluginReturnPromise);
    CAP_PLUGIN_METHOD(showInterstitial, CAPPluginReturnPromise);
    CAP_PLUGIN_METHOD(showBannerTop, CAPPluginReturnPromise);
    CAP_PLUGIN_METHOD(showBannerBottom, CAPPluginReturnPromise);
    CAP_PLUGIN_METHOD(interstitialIsLoaded, CAPPluginReturnPromise);
)

package app.gean.geanappodeal;

import com.getcapacitor.JSObject;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;

import com.appodeal.ads.Appodeal;
import com.appodeal.ads.InterstitialCallbacks;
import com.appodeal.ads.BannerCallbacks;
import android.content.Intent;
import android.net.Uri;
import android.content.Context;
import android.app.Activity;
import android.widget.Toast;

@CapacitorPlugin(name = "GeanAppodeal")





public class GeanAppodealPlugin extends Plugin {
    /* GeanAppodealPlugin does not have an activity to show ads.
    App's MainActivity.java, at onCreate, should save it's activity here.
    Sth like:

    public class MainActivity extends BridgeActivity {
      @Override
      protected void onCreate(Bundle savedInstanceState) {
          GeanAppodealPlugin.activity = this;
          super.onCreate(savedInstanceState);
      }
    }
     */
    private static Activity activity = null;
    private static String appodealKey = "";
    private static boolean useTestAds = true;
    private static boolean isDev = false;
    private static boolean mustShowBanner = false;
    // private static boolean consent = false;
    private GeanAppodeal implementation = new GeanAppodeal();

    @PluginMethod
    public void initializeAppodeal(PluginCall call) {
        GeanAppodealPlugin.debugMessage("initializeAppodeal");
        boolean result =  false;

        boolean useTestAds = call.getBoolean("useTestAds");
        GeanAppodealPlugin.useTestAds = useTestAds;

        boolean isDev = call.getBoolean("isDev");
        GeanAppodealPlugin.isDev = isDev;

        boolean consent = call.getBoolean("consent");
        // GeanAppodealPlugin.consent = consent;
        
        String key = call.getString("key");
        GeanAppodealPlugin.appodealKey = key;

        String msg = "initializeAppodeal, isDev = " + String.valueOf(isDev) + " useTestAds = " + String.valueOf(useTestAds) + " key = " + key + " consent = " + String.valueOf(consent);
        GeanAppodealPlugin.debugMessage(msg);

        if(GeanAppodealPlugin.activity != null){
          result = true;
          Appodeal.setTesting(useTestAds);
          Appodeal.disableNetwork(GeanAppodealPlugin.activity, "adcolony");
          Appodeal.disableNetwork(GeanAppodealPlugin.activity, "mobvista");
          Appodeal.disableNetwork(GeanAppodealPlugin.activity, "admob");
          Appodeal.initialize(GeanAppodealPlugin.activity, GeanAppodealPlugin.appodealKey, Appodeal.INTERSTITIAL, consent);
          Appodeal.initialize(GeanAppodealPlugin.activity, GeanAppodealPlugin.appodealKey, Appodeal.BANNER, consent);
        }else{
          GeanAppodealPlugin.debugMessage("activity is null");
        }

        Appodeal.setInterstitialCallbacks(new InterstitialCallbacks() {
          @Override
          public void onInterstitialLoaded(boolean isPrecache) {
            GeanAppodealPlugin.debugMessage("Interstitial loaded");
          }

          @Override
          public void onInterstitialFailedToLoad() {
            GeanAppodealPlugin.debugMessage("InterstitialFailedToLoad");
          }

          @Override
          public void onInterstitialShown() {
            GeanAppodealPlugin.debugMessage("onInterstitialShown");
          }
          @Override
          public void onInterstitialShowFailed() {
            GeanAppodealPlugin.debugMessage("onInterstitialShowFailed");
          }
          @Override
          public void onInterstitialClicked() {
            GeanAppodealPlugin.debugMessage("onInterstitialClicked");
          }
          @Override
          public void onInterstitialClosed() {
            GeanAppodealPlugin.debugMessage("onInterstitialClosed");
          }
          @Override
          public void onInterstitialExpired()  {
            GeanAppodealPlugin.debugMessage("onInterstitialExpired");
          }     
        });

        

        JSObject ret = new JSObject();
        ret.put("result", result);
        call.resolve(ret);
    }

    // private void setBannerCallbacks(){
    //   Appodeal.setBannerCallbacks(new BannerCallbacks() {
    //     @Override
    //     public void onBannerLoaded(int height, boolean isPrecache) {
    //       GeanAppodealPlugin.debugMessage("onBannerLoaded");
          
    //       if(GeanAppodealPlugin.mustShowBanner){
    //         Appodeal.show(GeanAppodealPlugin.activity, Appodeal.BANNER_BOTTOM);
    //       }
    //     }

    //     @Override
    //     public void onBannerFailedToLoad() {
    //       // Called when banner failed to load
    //     }
    //     @Override
    //     public void onBannerShown() {
    //       // Called when banner is shown
    //     }
    //     @Override
    //     public void onBannerShowFailed() {
    //       // Called when banner show failed
    //     }
    //     @Override
    //     public void onBannerClicked() {
    //       // Called when banner is clicked
    //     }
    //     @Override
    //     public void onBannerExpired() {
    //       // Called when banner is expired
    //     }
    //   });
    // }

    @PluginMethod
    public void showInterstitial(PluginCall call) {
      boolean result = false;
      GeanAppodealPlugin.debugMessage("showInterstitial");

      String value = call.getString("value");
      JSObject ret = new JSObject();

      if(GeanAppodealPlugin.activity != null){
        if(Appodeal.isLoaded(Appodeal.INTERSTITIAL)){
          Appodeal.show(GeanAppodealPlugin.activity, Appodeal.INTERSTITIAL);
          result = true;  
        }else{
          String msg = "interstitial is not loaded";
          GeanAppodealPlugin.debugMessage(msg);
        }
      }

      ret.put("result", result);
      call.resolve(ret);
    }

    @PluginMethod
    public void showBannerBottom(PluginCall call) {
      boolean result = false;
      GeanAppodealPlugin.debugMessage("showBannerBottom"); 

      String value = call.getString("value");
      JSObject ret = new JSObject();

      if(GeanAppodealPlugin.activity != null){
        if(Appodeal.isLoaded(Appodeal.BANNER)){
          // GeanAppodealPlugin.mustShowBanner = false;
          Appodeal.show(GeanAppodealPlugin.activity, Appodeal.BANNER_BOTTOM);
          call.resolve(ret);
        }else{
          String msg = "banner is not loaded";
          GeanAppodealPlugin.debugMessage(msg);
          /* Set flg so that when banner loads, it is shown
            Check method onBannerLoaded in this file
          */
          // GeanAppodealPlugin.mustShowBanner = true;
        }
      }

      ret.put("result", result);
      call.reject("banner not loaded");
    }

    @PluginMethod
    public void showBannerTop(PluginCall call) {
      boolean result = false;
      GeanAppodealPlugin.debugMessage("showBanner on top"); 

      String value = call.getString("value");
      JSObject ret = new JSObject();

      if(GeanAppodealPlugin.activity != null){
        if(Appodeal.isLoaded(Appodeal.BANNER)){
          // GeanAppodealPlugin.mustShowBanner = false;
          Appodeal.show(GeanAppodealPlugin.activity, Appodeal.BANNER_TOP);
          call.resolve(ret);
        }else{
          String msg = "banner is not loaded";
          GeanAppodealPlugin.debugMessage(msg);
          /* Set flg so that when banner loads, it is shown
            Check method onBannerLoaded in this file
          */
          // GeanAppodealPlugin.mustShowBanner = true;
        }
      }

      ret.put("result", result);
      call.reject("banner not loaded");
    }

    @PluginMethod
    public void interstitialIsLoaded(PluginCall call) {
        boolean isLoaded = Appodeal.isLoaded(Appodeal.INTERSTITIAL);
        GeanAppodealPlugin.debugMessage("interstitialIsLoaded = " + isLoaded);

        if(isLoaded){
            JSObject ret = new JSObject();
            ret.put("value", isLoaded);
            call.resolve(ret);
        }else{
            call.reject("Interstitial is not loaded");
        }
    }

    @PluginMethod
    public void bannerIsLoaded(PluginCall call) {
        boolean isLoaded = Appodeal.isLoaded(Appodeal.BANNER);
        GeanAppodealPlugin.debugMessage("bannerIsLoaded = " + isLoaded);

        if(isLoaded){
            JSObject ret = new JSObject();
            ret.put("value", isLoaded);
            call.resolve(ret);
        }else{
            call.reject("Banner is not loaded");
        }
    }

    private static void debugMessage(String message){
      // Toast messages are only in dev mode (for debugging)
      if(!GeanAppodealPlugin.isDev){
        return;
      }

      // If activity is null, there's no where to show toast
      if(GeanAppodealPlugin.activity == null){
        return;
      }

      Toast toast = Toast.makeText(GeanAppodealPlugin.activity, message, Toast.LENGTH_LONG);
      toast.show();
    }

    public static void setActivity(Activity activity){
      GeanAppodealPlugin.activity = activity;
    }
}

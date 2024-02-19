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
    public static Activity activity = null;
    private static GeanProtocol mainactivity;
    private static String appodealKey = "";
    private static boolean useTestAds = true;
    private static boolean isDev = false;
    private static final boolean mustShowBanner = false;
    // private static boolean consent = false;
    private final GeanAppodeal implementation = new GeanAppodeal();

    @PluginMethod
    public void initializeAppodeal(PluginCall call) {
        // this.debugMessage("initializeAppodeal");
        boolean result =  false;

        boolean useTestAds = call.getBoolean("useTestAds");
        GeanAppodealPlugin.useTestAds = useTestAds;

        boolean isDev = call.getBoolean("isDev");
        GeanAppodealPlugin.isDev = isDev;

        boolean consent = call.getBoolean("consent");
        // GeanAppodealPlugin.consent = consent;

        String key = call.getString("key");
        GeanAppodealPlugin.appodealKey = key;

        String msg = "initializeAppodeal, isDev = " + isDev + " useTestAds = " + useTestAds + " key = " + key + " consent = " + consent;
        // this.debugMessage(msg);

        if(this.activity != null){
          result = true;
          
          // Appodeal.setTesting(useTestAds);


          // this.mainactivity.disableNetwork(this.activity, "adcolony");
          // GeanAppodealPlugin.mainactivity.disableNetwork();
          GeanAppodealPlugin.mainactivity.initialize(key, consent, useTestAds);

          // Appodeal.disableNetwork(GeanAppodealPlugin.activity, "mobvista");
          // Appodeal.disableNetwork(GeanAppodealPlugin.activity, "admob");
          // Appodeal.initialize(GeanAppodealPlugin.activity, GeanAppodealPlugin.appodealKey, Appodeal.INTERSTITIAL, consent);
          // Appodeal.initialize(GeanAppodealPlugin.activity, GeanAppodealPlugin.appodealKey, Appodeal.BANNER, consent);
        }else{
          // this.debugMessage("activity is null");
        }

        Appodeal.setInterstitialCallbacks(new InterstitialCallbacks() {
          @Override
          public void onInterstitialLoaded(boolean isPrecache) {
            // this.debugMessage("Interstitial loaded");
          }

          @Override
          public void onInterstitialFailedToLoad() {
            // this.debugMessage("InterstitialFailedToLoad");
          }

          @Override
          public void onInterstitialShown() {
            // this.debugMessage("onInterstitialShown");
          }
          @Override
          public void onInterstitialShowFailed() {
            // this.debugMessage("onInterstitialShowFailed");
          }
          @Override
          public void onInterstitialClicked() {
            // this.debugMessage("onInterstitialClicked");
          }
          @Override
          public void onInterstitialClosed() {
            // this.debugMessage("onInterstitialClosed");
          }
          @Override
          public void onInterstitialExpired()  {
            // this.debugMessage("onInterstitialExpired");
          }
        });



        JSObject ret = new JSObject();
        ret.put("result", result);
        call.resolve(ret);
    }

    @PluginMethod
    public void showInterstitial(PluginCall call) {
      String value = call.getString("value");
      JSObject ret = new JSObject();
      boolean didShow = GeanAppodealPlugin.mainactivity.showInterstitial(GeanAppodealPlugin.isDev);

      if(didShow){
        call.resolve(ret);
        return;
      }

      ret.put("result", ret);
      call.resolve(ret);
    }

    @PluginMethod
    public void showBannerBottom(PluginCall call) {

      String value = call.getString("value");
      JSObject ret = new JSObject();

      boolean didShow = GeanAppodealPlugin.mainactivity.showBannerBottom(GeanAppodealPlugin.isDev);

      if(didShow){
        call.resolve(ret);
        return;
      }

      ret.put("result", ret);
      call.reject("banner not loaded");
    }

    @PluginMethod
    public void showBannerTop(PluginCall call) {
      String value = call.getString("value");
      JSObject ret = new JSObject();

      boolean didShow = GeanAppodealPlugin.mainactivity.showBannerTop(GeanAppodealPlugin.isDev);

      if(didShow){
        call.resolve(ret);
        return;
      }
   
      ret.put("result", false);
      call.reject("banner not loaded");
    }

    @PluginMethod
    public void interstitialIsLoaded(PluginCall call) {
        boolean isLoaded = GeanAppodealPlugin.mainactivity.interstitialIsLoaded();

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
        boolean isLoaded = GeanAppodealPlugin.mainactivity.bannerIsLoaded();;

        if(isLoaded){
          JSObject ret = new JSObject();
          ret.put("value", isLoaded);
          call.resolve(ret);
        }else{
          call.reject("Banner is not loaded");
        }
    }

    private void debugMessage(String message){
      // Toast messages are only in dev mode (for debugging)
      if(!this.isDev){
        return;
      }

      // If activity is null, there's no where to show toast
      if(this.activity == null){
        return;
      }

      Toast toast = Toast.makeText(this.activity, message, Toast.LENGTH_LONG);
      toast.show();
    }

    public static void setActivity(Activity activity){
      GeanAppodealPlugin.activity = activity;
      GeanAppodealPlugin.mainactivity = (GeanProtocol)activity;
    }
}

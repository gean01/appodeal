package app.gean.geanappodeal;

import com.getcapacitor.JSObject;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;

import com.appodeal.ads.Appodeal;
import com.appodeal.ads.InterstitialCallbacks;
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

    @PluginMethod
    public void showInterstitial(PluginCall call) {
      boolean result = false;
      int duration = Toast.LENGTH_LONG;

      GeanAppodealPlugin.debugMessage("showInterstitial");

      // String x = "";
      // if(Appodeal.isLoaded(Appodeal.INTERSTITIAL)){
      //   x = "Appodeal.isLoaded";
      // }else{
      //   x = "!Appodeal.isLoaded";
      // }
      // GeanAppodealPlugin.debugMessage(x);

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

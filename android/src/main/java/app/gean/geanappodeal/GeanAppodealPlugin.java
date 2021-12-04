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
    // private static boolean consent = false;
    private GeanAppodeal implementation = new GeanAppodeal();

    @PluginMethod
    public void initializeAppodeal(PluginCall call) {
        GeanAppodealPlugin.toast("initializeAppodeal");
        boolean result =  false;

        boolean useTestAds = call.getBoolean("useTestAds");
        GeanAppodealPlugin.useTestAds = useTestAds;

        boolean consent = call.getBoolean("consent");
        // GeanAppodealPlugin.consent = consent;
        
        String key = call.getString("key");
        GeanAppodealPlugin.appodealKey = key;

        String msg = "initializeAppodeal, useTestAds = " + String.valueOf(useTestAds) + " key = " + key + " consent = " + String.valueOf(consent);
        GeanAppodealPlugin.toast(msg);

        if(GeanAppodealPlugin.activity != null){
          result = true;
          Appodeal.setTesting(useTestAds);
          Appodeal.disableNetwork(GeanAppodealPlugin.activity, "adcolony");
          Appodeal.disableNetwork(GeanAppodealPlugin.activity, "mobvista");
          Appodeal.disableNetwork(GeanAppodealPlugin.activity, "admob");
          Appodeal.initialize(GeanAppodealPlugin.activity, GeanAppodealPlugin.appodealKey, Appodeal.INTERSTITIAL, consent);
        }else{
          GeanAppodealPlugin.toast("activity is null");
        }

        // Appodeal.setInterstitialCallbacks(new InterstitialCallbacks() {
        //   @Override
        //   public void onInterstitialLoaded(boolean isPrecache) {
        //     Toast toast = Toast.makeText(GeanAppodealPlugin.activity, "loaded", Toast.LENGTH_LONG);
        //     toast.show();
        //   }

        //   @Override
        //   public void onInterstitialFailedToLoad() {
        //     GeanAppodealPlugin.toast("deu ruim ");
        //   }

        //   @Override
        //   public void onInterstitialShown() {
        //     GeanAppodealPlugin.toast("onInterstitialShown");
        //   }
        //   @Override
        //   public void onInterstitialShowFailed() {
        //     GeanAppodealPlugin.toast("onInterstitialShowFailed");
        //   }
        //   @Override
        //   public void onInterstitialClicked() {
        //     GeanAppodealPlugin.toast("onInterstitialClicked");
        //   }
        //   @Override
        //   public void onInterstitialClosed() {
        //     GeanAppodealPlugin.toast("onInterstitialClosed");
        //   }
        //   @Override
        //   public void onInterstitialExpired()  {
        //     GeanAppodealPlugin.toast("onInterstitialExpired");
        //   }     
        // });

        JSObject ret = new JSObject();
        ret.put("result", result);
        call.resolve(ret);
    }

    @PluginMethod
    public void showInterstitial(PluginCall call) {
      boolean result = false;
      int duration = Toast.LENGTH_LONG;

      GeanAppodealPlugin.toast("showInterstitial");

      // String x = "";
      // if(Appodeal.isLoaded(Appodeal.INTERSTITIAL)){
      //   x = "Appodeal.isLoaded";
      // }else{
      //   x = "!Appodeal.isLoaded";
      // }
      // GeanAppodealPlugin.toast(x);

      String value = call.getString("value");
      JSObject ret = new JSObject();

      if(GeanAppodealPlugin.activity != null){
        if(Appodeal.isLoaded(Appodeal.INTERSTITIAL)){
          Appodeal.show(GeanAppodealPlugin.activity, Appodeal.INTERSTITIAL);
          result = true;  
        }else{
          String msg = "Appodeal is not loaded";
          GeanAppodealPlugin.toast(msg);
        }
      }

      ret.put("result", result);
      call.resolve(ret);
    }

    // @PluginMethod
    // public void setTesting(PluginCall call) {
    //     boolean value = call.getBoolean("value");
    //     GeanAppodealPlugin.useTestAds = value;

    //     JSObject ret = new JSObject();
    //     ret.put("value", value);
    //     call.resolve(ret);
    // }

    // @PluginMethod
    // public void setKey(PluginCall call) {
    //     String key = call.getString("key");
    //     GeanAppodealPlugin.appodealKey = key;

    //     JSObject ret = new JSObject();
    //     ret.put("key", key);
    //     call.resolve(ret);
    // }

    public static void toast(String message){
      if(GeanAppodealPlugin.activity == null){
        return;
      }

      Toast toast = Toast.makeText(GeanAppodealPlugin.activity, message, Toast.LENGTH_LONG);
      toast.show();
    }

    public static void setActivity(Activity activity){
      GeanAppodealPlugin.activity = activity;
      // GeanAppodealPlugin.toast("setActivity");
    }


    // public static void initializeAppodeal() {
    //   Appodeal.setTesting(false);

    //   if(GeanAppodealPlugin.activity != null){
    //     Appodeal.disableNetwork(GeanAppodealPlugin.activity, "adcolony");
    //     Appodeal.disableNetwork(GeanAppodealPlugin.activity, "mobvista");

    //     Appodeal.initialize(GeanAppodealPlugin.activity, GeanAppodealPlugin.appodealKey, Appodeal.INTERSTITIAL);
    //   }
    // }
}

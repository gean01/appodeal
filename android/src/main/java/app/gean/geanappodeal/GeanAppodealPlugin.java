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
    public static Activity activity = null;

    private static String appodealKey = "";
    private static boolean useTestAds = true;
    private GeanAppodeal implementation = new GeanAppodeal();

    @PluginMethod
    public void initializeAppodeal(PluginCall call) {
        boolean result =  false;

        boolean useTestAds = call.getBoolean("useTestAds");
        GeanAppodealPlugin.useTestAds = useTestAds;
        
        String key = call.getString("key");
        GeanAppodealPlugin.appodealKey = key;


        if(GeanAppodealPlugin.activity != null){
          result = true;
          Appodeal.setTesting(useTestAds);
          GeanAppodealPlugin.toast("initializeAppodeal, useTestAds = " + String.valueOf(useTestAds));
          Appodeal.disableNetwork(GeanAppodealPlugin.activity, "adcolony");
          Appodeal.disableNetwork(GeanAppodealPlugin.activity, "mobvista");
          Appodeal.initialize(GeanAppodealPlugin.activity, GeanAppodealPlugin.appodealKey, Appodeal.INTERSTITIAL);
        }

        // Appodeal.setInterstitialCallbacks(new InterstitialCallbacks() {
        //   @Override
        //   public void onInterstitialLoaded(boolean isPrecache) {
        //     // Toast toast = Toast.makeText(GeanAppodealPlugin.activity, GeanAppodealPlugin.appodealKey, duration);
        //     Toast toast = Toast.makeText(GeanAppodealPlugin.activity, "loaded", Toast.LENGTH_LONG);
        //     toast.show();
        //   }


        //   @Override
        //   public void onInterstitialFailedToLoad() {
        //     // Toast toast = Toast.makeText(GeanAppodealPlugin.activity, GeanAppodealPlugin.appodealKey, duration);
        //     Toast toast = Toast.makeText(GeanAppodealPlugin.activity, "deu ruim", Toast.LENGTH_LONG);
        //     toast.show();
        //   }
        //   @Override
        //   public void onInterstitialShown() {
        //     Toast toast = Toast.makeText(GeanAppodealPlugin.activity, "onInterstitialShown", Toast.LENGTH_LONG);
        //     toast.show();
        //   }
        //   @Override
        //   public void onInterstitialShowFailed() {
        //     Toast toast = Toast.makeText(GeanAppodealPlugin.activity, "onInterstitialShowFailed", Toast.LENGTH_LONG);
        //     toast.show();
        //   }
        //   @Override
        //   public void onInterstitialClicked() {
        //     Toast toast = Toast.makeText(GeanAppodealPlugin.activity, "onInterstitialClicked", Toast.LENGTH_LONG);
        //     toast.show();
        //   }
        //   @Override
        //   public void onInterstitialClosed() {
        //     Toast toast = Toast.makeText(GeanAppodealPlugin.activity, "onInterstitialClosed", Toast.LENGTH_LONG);
        //     toast.show();
        //   }
        //   @Override
        //   public void onInterstitialExpired()  {
        //     Toast toast = Toast.makeText(GeanAppodealPlugin.activity, "onInterstitialExpired", Toast.LENGTH_LONG);
        //     toast.show();
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
      String x = "";

      if(Appodeal.isLoaded(Appodeal.INTERSTITIAL)){
        // if(GeanAppodealPlugin.useTestAds){
        
        x = "Appodeal.isLoaded";
      }else{
        x = "!Appodeal.isLoaded";
      }

      // Toast toast = Toast.makeText(GeanAppodealPlugin.activity, GeanAppodealPlugin.appodealKey, duration);
      GeanAppodealPlugin.toast(x);

      String value = call.getString("value");
      JSObject ret = new JSObject();

      if(GeanAppodealPlugin.activity != null){
        // if(Appodeal.isLoaded(Appodeal.INTERSTITIAL)){
          Appodeal.show(GeanAppodealPlugin.activity, Appodeal.INTERSTITIAL);
          result = true;  
        // }
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
      Toast toast = Toast.makeText(GeanAppodealPlugin.activity, message, Toast.LENGTH_LONG);
      toast.show();
    }

    public static void setActivity(Activity activity){
      GeanAppodealPlugin.activity = activity;
      GeanAppodealPlugin.toast("setActivity");
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

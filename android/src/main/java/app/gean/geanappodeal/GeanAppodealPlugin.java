package app.gean.geanappodeal;

import com.getcapacitor.JSObject;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;

import com.appodeal.ads.Appodeal;
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

        if(GeanAppodealPlugin.activity != null){
          result = true;
          Appodeal.setTesting(useTestAds);
          Appodeal.disableNetwork(GeanAppodealPlugin.activity, "adcolony");
          Appodeal.disableNetwork(GeanAppodealPlugin.activity, "mobvista");
          Appodeal.initialize(GeanAppodealPlugin.activity, GeanAppodealPlugin.appodealKey, Appodeal.INTERSTITIAL);
        }

        JSObject ret = new JSObject();
        ret.put("result", result);
        call.resolve(ret);
    }

    @PluginMethod
    public void showInterstitial(PluginCall call) {
      boolean result = false;
      int duration = Toast.LENGTH_LONG;
      String x = "";

      if(GeanAppodealPlugin.useTestAds){
        x = "true";
      }else{
        x = "false";
      }

      // Toast toast = Toast.makeText(GeanAppodealPlugin.activity, GeanAppodealPlugin.appodealKey, duration);
      Toast toast = Toast.makeText(GeanAppodealPlugin.activity, x, duration);
      toast.show();

      String value = call.getString("value");
      JSObject ret = new JSObject();

      if(GeanAppodealPlugin.activity != null){
        if(Appodeal.isLoaded(Appodeal.INTERSTITIAL)){
          Appodeal.show(GeanAppodealPlugin.activity, Appodeal.INTERSTITIAL);
          result = true;  
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

    @PluginMethod
    public void setKey(PluginCall call) {
        String key = call.getString("key");
        GeanAppodealPlugin.appodealKey = key;

        JSObject ret = new JSObject();
        ret.put("key", key);
        call.resolve(ret);
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

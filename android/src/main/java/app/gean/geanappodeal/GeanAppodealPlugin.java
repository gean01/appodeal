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

    private static String appodealKey = "26f05ce04b690dfecefd0a25935445ecbfa675e44163a056";
    private static boolean useTestAds = true;
    private GeanAppodeal implementation = new GeanAppodeal();


    @PluginMethod
    public void showInterstitial(PluginCall call) {
        String value = call.getString("value");

        JSObject ret = new JSObject();
        ret.put("value", implementation.showInterstitial(value));
        call.resolve(ret);

        if(GeanAppodealPlugin.activity != null){
          Appodeal.show(GeanAppodealPlugin.activity, Appodeal.INTERSTITIAL);
        }
    }

    @PluginMethod
    public void setTesting(PluginCall call) {
        boolean value = call.getBoolean("value");
        this.useTestAds = value;

        JSObject ret = new JSObject();
        ret.put("value", value);
        call.resolve(ret);
    }

    public static void initializeAppodeal() {
      Appodeal.setTesting(GeanAppodealPlugin.useTestAds);

      if(GeanAppodealPlugin.activity != null){
        Appodeal.disableNetwork(GeanAppodealPlugin.activity, "adcolony");
        Appodeal.disableNetwork(GeanAppodealPlugin.activity, "mobvista");

        Appodeal.initialize(GeanAppodealPlugin.activity, GeanAppodealPlugin.appodealKey, Appodeal.INTERSTITIAL);
      }
    }
}

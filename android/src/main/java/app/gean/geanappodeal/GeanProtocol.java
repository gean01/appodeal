package app.gean.geanappodeal;
import android.app.Activity;


public interface GeanProtocol {
    // public void disableNetwork(Activity activity, String Network);
    public void disableNetwork(String network);
    public void initialize(String key, boolean consent, boolean useTestAds);
    public boolean showBannerTop(boolean isDev);
    public boolean showBannerBottom(boolean isDev);
    public boolean showInterstitial(boolean isDev);
    public boolean interstitialIsLoaded();
    public boolean bannerIsLoaded();
}

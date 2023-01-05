import Foundation
import Capacitor

/**
 * Please read the Capacitor iOS Plugin Development Guide
 * here: https://capacitorjs.com/docs/plugins/ios
 */
@objc(GeanAppodealPlugin)
public class GeanAppodealPlugin: CAPPlugin {
    private let implementation = GeanAppodeal()
    public static var showInterstitial = false
    
    public func shouldShowInterstitial() -> Bool{
        //print("self.showInterstitial = ", self.showInterstitial)
        return GeanAppodealPlugin.showInterstitial
    }

    public static func resetShouldShowInterstitial(){
        print("Definindo self.showInterstitial como false")
        GeanAppodealPlugin.showInterstitial = false
    }

    @objc func initializeAppodeal(_ call: CAPPluginCall) {
        let key = call.getString("key") ?? ""
//        let isdDev = call.getString("isDev") ?? false
        let useTestAds = call.getBool("useTestAds") ?? true

        call.resolve([
            "value": implementation.initializeAppodeal(key: key, useTestAds: useTestAds)
        ])
    }

    @objc func showInterstitial(_ call: CAPPluginCall) {
        print("setando self.showInterstitial para TRUE")
        GeanAppodealPlugin.showInterstitial = true
        // let value = call.getString("value") ?? ""
        call.resolve([
            "value": implementation.showInterstitial()
        ])
    }
    
}

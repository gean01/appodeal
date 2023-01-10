import Foundation
import Capacitor

public protocol CapazDeMostrarAds{
    func mostrarInterstitial() -> Bool
    func interstitialIsLoaded() -> Bool
}

/**
 * Please read the Capacitor iOS Plugin Development Guide
 * here: https://capacitorjs.com/docs/plugins/ios
 */
@objc(GeanAppodealPlugin)
public class GeanAppodealPlugin: CAPPlugin {
    public static var mostradorDeAds: CapazDeMostrarAds? = nil
    private let implementation = GeanAppodeal()
    
    @objc func initializeAppodeal(_ call: CAPPluginCall) {
        let key = call.getString("key") ?? ""
        let useTestAds = call.getBool("useTestAds") ?? true

        call.resolve([
            "value": implementation.initializeAppodeal(key: key, useTestAds: useTestAds)
        ])
    }

    @objc func showInterstitial(_ call: CAPPluginCall) {
        
        if(GeanAppodealPlugin.mostradorDeAds != nil){
            print("invocando GeanAppodealPlugin.mostradorDeAds.mostrarInterstitial()")

            DispatchQueue.main.async {
                let mostrou: Bool = GeanAppodealPlugin.mostradorDeAds!.mostrarInterstitial()
                if(mostrou){
                    call.resolve([
                        "value": true
                    ])
                }else{
                    call.reject("Ad não pronto")
                }
            }
        }else{
            print("GeanAppodealPlugin.mostradorDeAds é null")
            call.reject("Ad não pronto")
        }
    }

    @objc func interstitialIsLoaded(_ call: CAPPluginCall) {
        
        if(GeanAppodealPlugin.mostradorDeAds != nil){
            print("invocando GeanAppodealPlugin.mostradorDeAds.interstitialIsLoaded()")

            DispatchQueue.main.async {
                let isLoaded: Bool = GeanAppodealPlugin.mostradorDeAds!.interstitialIsLoaded()
                if(isLoaded){
                    call.resolve([
                        "value": true
                    ])
                }else{
                    call.reject("Ad não pronto")
                }
            }
        }else{
            print("GeanAppodealPlugin.mostradorDeAds é null")
            call.reject("Ad não pronto")
        }
    }
    
}

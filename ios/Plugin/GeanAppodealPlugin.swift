import Foundation
import Capacitor

public protocol CapazDeMostrarAds{
    func initializeAppodeal(key: String, useTestAds: Bool)
    func mostrarInterstitial() -> Bool
    func mostrarBannerTop() -> Bool
    func mostrarBannerBottom() -> Bool
    func interstitialIsLoaded() -> Bool
    func bannerIsLoadedTop() -> Bool
    func bannerIsLoadedBottom() -> Bool
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
        
        GeanAppodealPlugin.mostradorDeAds!.initializeAppodeal(key: key, useTestAds: useTestAds)

        call.resolve([
            "value": true
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

    @objc func showBannerTop(_ call: CAPPluginCall) {
        print("Executando showBannerTop")
        
        if(GeanAppodealPlugin.mostradorDeAds != nil){
            print("invocando GeanAppodealPlugin.mostradorDeAds.showBannerTop()")

            DispatchQueue.main.async {
                let mostrou: Bool = GeanAppodealPlugin.mostradorDeAds!.mostrarBannerTop()
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
    
    @objc func showBannerBottom(_ call: CAPPluginCall) {
        
        if(GeanAppodealPlugin.mostradorDeAds != nil){
            print("invocando GeanAppodealPlugin.mostradorDeAds.mostrarBannerBottom()")

            DispatchQueue.main.async {
                let mostrou: Bool = GeanAppodealPlugin.mostradorDeAds!.mostrarBannerBottom()
                if(mostrou){
                    call.resolve([
                        "value": true
                    ])
                }else{
                    call.reject("banner bottom não pronto")
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

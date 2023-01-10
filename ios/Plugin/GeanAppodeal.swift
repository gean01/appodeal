import Foundation





@objc public class GeanAppodeal: NSObject {
   
    
    
   
    @objc public func initializeAppodeal(key: String, useTestAds: Bool) -> String {
//        if let delegate = UIApplication.shared.delegate as? ProtocoloAppodeal {
//            delegate.appodealInit(key: key, useTestAds: useTestAds)
//        }
        print("invocado: GeanAppodeal initializeAppodeal")
        return "dummy response"
    }

    @objc public func showInterstitial() -> String {
        return "dummy response"
    }
    

}

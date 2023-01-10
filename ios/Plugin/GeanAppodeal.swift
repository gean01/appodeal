import Foundation

@objc public protocol ProtocoloAppodeal{
    func appodealShowInterstitial()
}




@objc public class GeanAppodeal: NSObject {
   
    
    
   
    @objc public func initializeAppodeal(key: String, useTestAds: Bool) -> String {
//        if let delegate = UIApplication.shared.delegate as? ProtocoloAppodeal {
//            delegate.appodealInit(key: key, useTestAds: useTestAds)
//        }
        print("invocado: GeanAppodeal initializeAppodeal")
        return "dummy response"
    }

    @objc public func showInterstitial() -> String {
        DispatchQueue.main.async {
            if let delegate = UIApplication.shared.delegate as? ProtocoloAppodeal {
                delegate.appodealShowInterstitial()
            }
        }
        

        print("invocado: GeanAppodeal showInterstitial")
        return "dummy response"
    }
    

}

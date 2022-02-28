import Foundation

@objc public protocol ProtocoloAppodeal{
//    func appodealInit(key: String, useTestAds: Bool)
    func appodealShowInterstitial()
}


@objc public class GeanAppodeal: NSObject {
   private static var activity: UIViewController = UIViewController()
   
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

    @objc public static func setActivity(activity: UIViewController) {
        print("setting activity to \(activity)")
        GeanAppodeal.activity = activity
    }
    
//    @objc public static func setNativeAppodealInstance(nativeAppodeal: NativeAppodeal) {
//        print("setting activity to \(activity)")
//        GeanAppodeal.activity = activity
//    }
}

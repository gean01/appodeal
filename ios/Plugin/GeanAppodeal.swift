import Foundation
import Appodeal

@objc public class GeanAppodeal: NSObject {
   private static var activity: UIViewController = UIViewController()
   private let appodelAppKey = "7518ca51e2d82027e00a3b5ec23cd1c3dd4644767a3454f7"

    @objc public func initializeAppodeal() -> String {
        Appodeal.initialize(withApiKey: self.appodelAppKey, types: [.interstitial], hasConsent: true)
        print("invocado: GeanAppodeal initializeAppodeal")
        return "dummy response"
    }

    @objc public func showInterstitial() -> String {
        print("invocado: GeanAppodeal showInterstitial")
//        if(Appodeal.isReadyForShow(with: .interstitial)){
//            print(" Appodealready")
//            Appodeal.showAd(AppodealShowStyle.interstitial, rootViewController: self)
//        }else{
//            print("Appodeal NOT ready")
//        }
        return "dummy response"
    }

    @objc public static func setActivity(activity: UIViewController) {
        print("setting activity to \(activity)")
        GeanAppodeal.activity = activity
    }
}

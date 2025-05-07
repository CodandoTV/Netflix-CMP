import SwiftUI
import streamplayerapp
import FirebaseCore
import FirebaseAnalytics

@main
struct iOSApp: App {
    
    init() {
        KoinIosHelper().doInitKoin(lottieViewProvider: LottieViewProviderImpl())
        FirebaseApp.configure()
        Analytics.logEvent("ios_app_opened", parameters: nil)
    }
    
    var body: some Scene {
        WindowGroup {
            ContentView()
        }
    }
}


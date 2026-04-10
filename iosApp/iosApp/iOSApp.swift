import SwiftUI
import streamplayerapp
import FirebaseCore

@main
struct iOSApp: App {
    @UIApplicationDelegateAdaptor(AppDelegate.self) var appDelegate

    init() {
        KoinIosHelper().doInitKoin(lottieViewProvider: LottieViewProviderImpl())
        KoinIosHelper().doInitKoin()
        FirebaseApp.configure()
    }

    var body: some Scene {
        WindowGroup {
            ContentView()
        }
    }
}

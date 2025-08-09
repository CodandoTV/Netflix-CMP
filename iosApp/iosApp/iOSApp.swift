import SwiftUI
import streamplayerapp

@main
struct iOSApp: App {
    
    init() {
        KoinIosHelper().doInitKoin(lottieViewProvider: LottieViewProviderImpl())
    }
    
    var body: some Scene {
        WindowGroup {
            ContentView()
        }
    }
}

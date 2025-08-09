import Lottie
import UIKit
import streamplayerapp

class LottieViewProviderImpl : LottieViewProvider {
    func provideLottieView(lottieAnimationJson: String,onAnimationFinish: @escaping () -> Void) -> UIView {
        let lottieView = LottieView()
        lottieView.setupLottieAnimationView(
            animationContentJson: lottieAnimationJson,
            onAnimationFinished: onAnimationFinish
        )
        return lottieView
    }
}
class LottieView : UIView {
    
    private let animationView: LottieAnimationView
    var onAnimationFinished: (() -> Void)?
    
    override init(frame: CGRect) {
        animationView = LottieAnimationView()
        super.init(frame: frame)
    }
    
    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented.")
    }
    
    func setupLottieAnimationView(animationContentJson: String, onAnimationFinished: @escaping () -> Void) {
        self.onAnimationFinished = onAnimationFinished

        animationView.contentMode = .scaleAspectFit
        addSubview(animationView)

        animationView.translatesAutoresizingMaskIntoConstraints = false
        NSLayoutConstraint.activate([
            animationView.leftAnchor.constraint(equalTo: leftAnchor),
            animationView.rightAnchor.constraint(equalTo: rightAnchor),
            animationView.topAnchor.constraint(equalTo: topAnchor),
            animationView.bottomAnchor.constraint(equalTo: bottomAnchor)
        ])

        animationView.loopMode = .playOnce // Toca apenas uma vez
        animationView.frame = bounds
        
        if let animation = loadLottieAnimation(from: animationContentJson) {
            animationView.animation = animation
            animationView.play { finished in
                if finished {
                    self.onAnimationFinished?() // Chama o callback quando terminar
                }
            }
        }
    }
    
    func loadLottieAnimation(from jsonString: String) -> LottieAnimation? {
        guard let data = jsonString.data(using: .utf8) else { return nil }
        return try? JSONDecoder().decode(LottieAnimation.self, from: data)
    }
}

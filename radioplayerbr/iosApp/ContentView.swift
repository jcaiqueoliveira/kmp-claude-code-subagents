import SwiftUI
import shared

struct ContentView: View {
    @StateObject private var viewModel: RadioPlayerViewModelWrapper

    init() {
        let settings = UserDefaultsSettings()
        let repository = RadioRepository(settings: settings)
        let audioPlayer = AudioPlayer()
        _viewModel = StateObject(wrappedValue: RadioPlayerViewModelWrapper(
            repository: repository,
            audioPlayer: audioPlayer
        ))
    }

    var body: some View {
        ComposeView(viewModel: viewModel.viewModel)
            .ignoresSafeArea(.all)
    }
}

class RadioPlayerViewModelWrapper: ObservableObject {
    let viewModel: RadioPlayerViewModel

    init(repository: RadioRepository, audioPlayer: AudioPlayer) {
        self.viewModel = RadioPlayerViewModel(repository: repository, audioPlayer: audioPlayer)
    }
}

struct ComposeView: UIViewControllerRepresentable {
    let viewModel: RadioPlayerViewModel

    func makeUIViewController(context: Context) -> UIViewController {
        MainViewControllerKt.MainViewController(viewModel: viewModel)
    }

    func updateUIViewController(_ uiViewController: UIViewController, context: Context) {
    }
}

// UserDefaults implementation for Settings
class UserDefaultsSettings: Settings {
    private let userDefaults = UserDefaults.standard

    func putString(key: String, value: String) {
        userDefaults.set(value, forKey: key)
    }

    func getStringOrNull(key: String) -> String? {
        return userDefaults.string(forKey: key)
    }

    func remove(key: String) {
        userDefaults.removeObject(forKey: key)
    }

    func clear() {
        let dictionary = userDefaults.dictionaryRepresentation()
        dictionary.keys.forEach { key in
            userDefaults.removeObject(forKey: key)
        }
    }

    func hasKey(key: String) -> Bool {
        return userDefaults.object(forKey: key) != nil
    }
}

package br.com.radioplayerbr

import androidx.compose.ui.window.ComposeUIViewController
import br.com.radioplayerbr.data.repository.RadioRepository
import br.com.radioplayerbr.domain.AudioPlayer
import br.com.radioplayerbr.presentation.RadioPlayerViewModel
import br.com.radioplayerbr.presentation.ui.RadioPlayerApp
import com.russhwolf.settings.NSUserDefaultsSettings
import com.russhwolf.settings.Settings
import platform.Foundation.NSUserDefaults
import platform.UIKit.UIViewController

fun MainViewController(): UIViewController {
    val settings: Settings = NSUserDefaultsSettings(NSUserDefaults.standardUserDefaults)
    val repository = RadioRepository(settings = settings)
    val audioPlayer = AudioPlayer()
    val viewModel = RadioPlayerViewModel(repository, audioPlayer)

    return ComposeUIViewController {
        RadioPlayerApp(viewModel)
    }
}

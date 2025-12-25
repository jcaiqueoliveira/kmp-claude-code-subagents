package br.com.radioplayerbr

import androidx.compose.ui.window.ComposeUIViewController
import br.com.radioplayerbr.presentation.RadioPlayerViewModel
import br.com.radioplayerbr.presentation.ui.RadioPlayerApp
import platform.UIKit.UIViewController

fun MainViewController(viewModel: RadioPlayerViewModel): UIViewController {
    return ComposeUIViewController {
        RadioPlayerApp(viewModel)
    }
}

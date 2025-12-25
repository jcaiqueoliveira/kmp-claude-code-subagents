package br.com.radioplayerbr

import androidx.compose.ui.window.ComposeUIViewController
import br.com.radioplayerbr.di.appModule
import br.com.radioplayerbr.di.platformModule
import br.com.radioplayerbr.presentation.RadioPlayerViewModel
import br.com.radioplayerbr.presentation.ui.RadioPlayerApp
import org.koin.core.context.startKoin
import org.koin.mp.KoinPlatform.getKoin
import platform.UIKit.UIViewController

fun MainViewController(): UIViewController {
    startKoin {
        modules(appModule, platformModule())
    }

    val viewModel = getKoin().get<RadioPlayerViewModel>()

    return ComposeUIViewController {
        RadioPlayerApp(viewModel)
    }
}

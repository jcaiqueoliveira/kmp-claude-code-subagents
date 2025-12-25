package br.com.radioplayerbr.android

import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import br.com.radioplayerbr.di.appModule
import br.com.radioplayerbr.di.platformModule
import br.com.radioplayerbr.presentation.ui.RadioPlayerApp
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.compose.KoinAndroidContext
import org.koin.androidx.compose.koinViewModel
import org.koin.core.context.startKoin

class RadioPlayerApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@RadioPlayerApplication)
            modules(appModule, platformModule())
        }
    }
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            KoinAndroidContext {
                RadioPlayerApp(viewModel = koinViewModel())
            }
        }
    }
}

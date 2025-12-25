package br.com.radioplayerbr.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import br.com.radioplayerbr.data.repository.RadioRepository
import br.com.radioplayerbr.domain.AudioPlayer
import br.com.radioplayerbr.presentation.RadioPlayerViewModel
import br.com.radioplayerbr.presentation.ui.RadioPlayerApp
import com.russhwolf.settings.Settings
import com.russhwolf.settings.SharedPreferencesSettings

class MainActivity : ComponentActivity() {
    private lateinit var viewModel: RadioPlayerViewModel
    private lateinit var audioPlayer: AudioPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize dependencies
        val settings: Settings = SharedPreferencesSettings(
            getSharedPreferences("radio_player_prefs", MODE_PRIVATE)
        )
        val repository = RadioRepository(settings = settings)
        audioPlayer = AudioPlayer(this)

        // Initialize ViewModel
        viewModel = RadioPlayerViewModel(repository, audioPlayer)

        setContent {
            RadioPlayerApp(viewModel)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        audioPlayer.release()
    }
}

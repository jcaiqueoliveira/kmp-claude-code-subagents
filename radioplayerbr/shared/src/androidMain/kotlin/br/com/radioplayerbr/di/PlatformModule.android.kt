package br.com.radioplayerbr.di

import android.content.Context
import br.com.radioplayerbr.domain.AudioPlayer
import com.russhwolf.settings.Settings
import com.russhwolf.settings.SharedPreferencesSettings
import org.koin.dsl.module

actual fun platformModule() = module {
    single<Settings> {
        val context = get<Context>()
        SharedPreferencesSettings(
            context.getSharedPreferences("radio_player_prefs", Context.MODE_PRIVATE)
        )
    }

    single {
        val context = get<Context>()
        AudioPlayer(context)
    }
}

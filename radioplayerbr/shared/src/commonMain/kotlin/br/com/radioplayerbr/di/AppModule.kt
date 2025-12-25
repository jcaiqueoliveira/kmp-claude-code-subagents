package br.com.radioplayerbr.di

import br.com.radioplayerbr.data.remote.RadioApiService
import br.com.radioplayerbr.data.repository.RadioRepository
import br.com.radioplayerbr.domain.AudioPlayer
import br.com.radioplayerbr.presentation.RadioPlayerViewModel
import com.russhwolf.settings.Settings
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single { RadioApiService() }
    single { RadioRepository(apiService = get(), settings = get()) }
    single { AudioPlayer() }
    viewModel { RadioPlayerViewModel(repository = get(), audioPlayer = get()) }
}

expect fun platformModule(): org.koin.core.module.Module

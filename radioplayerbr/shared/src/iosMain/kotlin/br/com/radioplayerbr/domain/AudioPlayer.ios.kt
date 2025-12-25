package br.com.radioplayerbr.domain

import br.com.radioplayerbr.data.model.PlayerState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import platform.AVFoundation.*
import platform.Foundation.NSError
import platform.Foundation.NSURL

actual class AudioPlayer {
    private var avPlayer: AVPlayer? = null
    private val _playerState = MutableStateFlow<PlayerState>(PlayerState.Idle)
    actual val playerState: StateFlow<PlayerState> = _playerState.asStateFlow()

    actual fun play(streamUrl: String) {
        try {
            _playerState.value = PlayerState.Loading

            val url = NSURL.URLWithString(streamUrl)
            if (url != null) {
                val playerItem = AVPlayerItem(uRL = url)

                if (avPlayer == null) {
                    avPlayer = AVPlayer(playerItem = playerItem)
                } else {
                    avPlayer?.replaceCurrentItemWithPlayerItem(playerItem)
                }

                avPlayer?.play()
                _playerState.value = PlayerState.Playing
            } else {
                _playerState.value = PlayerState.Error("URL inválida")
            }
        } catch (e: Exception) {
            _playerState.value = PlayerState.Error(e.message ?: "Erro ao reproduzir")
        }
    }

    actual fun pause() {
        avPlayer?.pause()
        _playerState.value = PlayerState.Paused
    }

    actual fun stop() {
        avPlayer?.pause()
        avPlayer = null
        _playerState.value = PlayerState.Idle
    }

    actual fun release() {
        avPlayer?.pause()
        avPlayer = null
        _playerState.value = PlayerState.Idle
    }

    actual fun isPlaying(): Boolean {
        return avPlayer?.rate != 0.0
    }
}

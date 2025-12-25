package br.com.radioplayerbr.domain

import br.com.radioplayerbr.data.model.PlayerState
import kotlinx.coroutines.flow.StateFlow

expect class AudioPlayer() {
    val playerState: StateFlow<PlayerState>

    fun play(streamUrl: String)
    fun pause()
    fun stop()
    fun release()
    fun isPlaying(): Boolean
}
